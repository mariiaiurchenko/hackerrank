package testInterviews.simpleDBSolution;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * SimpleDB is a simple key-value pair in-memory database with a predefined set
 * of commands. The usage messages and help tips are provided as commands are
 * used.
 * 
 * Time complexity for all commands except 'ROLLBACK' is O(1). Map is used to
 * achieve constant time read/write. Also value/count map is used to achieve
 * constant time NUMEQUALTO.
 * 
 * ROLLBACK time complexity is O(m) where 'm' is the number of keys modified in
 * current transaction.
 * 
 * @author Mariia Iurchenko
 * 
 * https://github.com/mariiaiurchenko/hackerrank/tree/master/HakerRank/src/main/java/testInterviews/simpleDB
 * https://github.com/mariiaiurchenko/hackerrank/tree/master/HakerRank/src/test/java/testInterviews/simpleDB
 */
public class Solution {

	public static void main(String args[]) {

		DataContainer dataContainer = new DataContainer();
		Deque<Transaction> transactions = new LinkedList<>();
		SimpleDB dataBase = new SimpleDB(dataContainer, transactions);

		try (Scanner in = new Scanner(System.in)) {
			boolean waitForNextCommand = true;
			while (waitForNextCommand) {
				String[] input = in.nextLine().split("\\s+");
				Command command = null;
				try {
					command = Command.valueOf(input[0].toUpperCase());
				} catch (IllegalArgumentException ex) {
					if (!input[0].isEmpty()) {
						System.out.println(Command.help());
					}
					continue;
				}
				if (!command.isValid(input)) {
					System.out.println(command.paramDescription());
					continue;
				}

				switch (command) {
				case GET:
					System.out.println(dataBase.get(input[1]));
					break;
				case SET:
					dataBase.set(input[1], input[2]);
					break;
				case UNSET:
					dataBase.unset(input[1]);
					break;
				case NUMEQUALTO:
					System.out.println(dataBase.numequalto(input[1]));
					break;
				case BEGIN:
					dataBase.begin();
					break;
				case ROLLBACK:
					String rollbackResult = dataBase.rollback();
					if (!rollbackResult.isEmpty()) {
						System.out.println(rollbackResult);
					}
					break;
				case COMMIT:
					String commitResult = dataBase.commit();
					if (!commitResult.isEmpty()) {
						System.out.println(commitResult);
					}
					break;
				case END:
					waitForNextCommand = false;
				}
			}
		}
	}

	private static class SimpleDB {

		private static final String NEW_STRING = "> %s";
		private static final String NO_TRANSACTION = String.format(NEW_STRING,
				"NO TRANSACTION");
		private static final String NULL = "NULL";

		private DataContainer dataContainer;
		private Deque<Transaction> transactions;

		public SimpleDB(DataContainer dataContainer,
				Deque<Transaction> transactions) {
			this.dataContainer = dataContainer;
			this.transactions = transactions;
		}

		public String get(String key) {
			String res = dataContainer.get(key);
			return String.format(NEW_STRING, res == null ? NULL : res);
		}

		public void set(String key, String val) {
			if (transactions.size() > 0) {
				transactions.peek().set(key, val);
			}
			dataContainer.set(key, val);
		}

		public void unset(String key) {
			if (transactions.size() > 0) {
				transactions.peek().unset(key);
			}
			dataContainer.unset(key);
		}

		public String numequalto(String key) {
			return String.format(NEW_STRING,
					String.valueOf(dataContainer.numequalto(key)));
		}

		public void begin() {
			transactions.push(new Transaction(dataContainer));
		}

		public String commit() {
			String res = "";
			if (!transactions.isEmpty()) {
				transactions.clear();
			} else {
				res = NO_TRANSACTION;
			}
			return res;
		}

		public String rollback() {
			String res = "";
			if (!transactions.isEmpty()) {
				transactions.pop().rollback();
			} else {
				res = NO_TRANSACTION;
			}
			return res;
		}
	}

	private static class DataContainer {

		Map<String, String> keyVal;
		Map<String, Integer> countVal;

		public DataContainer() {
			keyVal = new HashMap<>();
			countVal = new HashMap<>();
		}

		public String get(String key) {
			return keyVal.get(key);
		}

		public void set(String key, String val) {
			if (keyVal.containsKey(key)) {
				countVal.put(keyVal.get(key), countVal.get(keyVal.get(key)) - 1);
			}
			keyVal.put(key, val);
			if (countVal.containsKey(val)) {
				countVal.put(val, countVal.get(val) + 1);
			} else {
				countVal.put(val, 1);
			}
		}

		public void unset(String key) {
			if (keyVal.containsKey(key)) {
				String val = keyVal.get(key);
				countVal.put(val, countVal.get(val) - 1);
				keyVal.remove(key);
			}
		}

		public int numequalto(String val) {
			return countVal.get(val) != null ? countVal.get(val) : 0;
		}

		public void adjustCountVal(String key, int val) {
			int prev = countVal.containsKey(key) ? countVal.get(key) : 0;
			countVal.put(key, prev + val);
		}
	}

	/*
	 * contains information to roll back the changes of current transaction
	 */
	private static class Transaction {

		DataContainer dataContainer;

		Map<String, String> prevKeyVal;
		Map<String, Integer> countVal;
		Set<String> added;

		public Transaction(DataContainer data) {
			this.dataContainer = data;
			prevKeyVal = new HashMap<>();
			countVal = new HashMap<>();
			added = new HashSet<>();
		}

		public void set(String key, String val) {
			savePrevData(key);
			decreaseCountVal(val);
			dataContainer.set(key, val);
		}

		public void unset(String key) {
			savePrevData(key);
			if (added.contains(key)) {
				added.remove(key);
			}
			dataContainer.unset(key);
		}

		public void rollback() {
			for (String key : prevKeyVal.keySet()) {
				dataContainer.keyVal.put(key, prevKeyVal.get(key));
			}
			for (String key : added) {
				dataContainer.keyVal.remove(key);
			}
			for (String key : countVal.keySet()) {
				dataContainer.adjustCountVal(key, countVal.get(key));
			}
		}

		private void adjustCounterVal(String val, int delta) {
			int prev = 0;
			if (countVal.containsKey(val)) {
				prev = countVal.get(val);
			}
			countVal.put(val, prev + delta);
		}

		private void increaseCountVal(String val) {
			adjustCounterVal(val, 1);
		}

		private void decreaseCountVal(String val) {
			adjustCounterVal(val, -1);
		}

		private void savePrevData(String key) {
			if (!prevKeyVal.containsKey(key) && !added.contains(key)) {
				if (dataContainer.get(key) == null) {
					added.add(key);
				} else {
					prevKeyVal.put(key, dataContainer.get(key));
				}
			}
			if (dataContainer.get(key) != null) {
				increaseCountVal(dataContainer.get(key));
			}
		}
	}

	private static enum Command {
		GET(1), SET(2), UNSET(1), NUMEQUALTO(1), BEGIN(0), ROLLBACK(0), COMMIT(
				0), END(0);

		private final String paramDescriptionPattern = "> Invalid command format. For command \"%s\" number of expected parameter(s) is %d";
		private static final String HELP = "> Invalid command. Please use one from the commands: GET, SET, UNSET, NUMEQUALTO, BEGIN, ROLLBACK, COMMIT, END;";
		private final int paramCount;

		private Command(int paramCount) {
			this.paramCount = paramCount;
		}

		public boolean isValid(String[] input) {
			return input.length > paramCount;
		}

		public String paramDescription() {
			return String.format(paramDescriptionPattern, this.toString(),
					this.paramCount);
		}

		public static String help() {
			return HELP;
		}
	}

}
