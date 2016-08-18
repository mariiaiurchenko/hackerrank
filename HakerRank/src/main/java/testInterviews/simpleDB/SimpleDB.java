package testInterviews.simpleDB;

import java.util.LinkedList;
import java.util.Scanner;

public class SimpleDB {
	private static final String NEW_STRING = "> %s";
	private static final String NO_TRANSACTION = String.format(NEW_STRING,
			"NO TRANSACTION");
	private static final String NULL = "NULL";

	DataContainer data;
	LinkedList<Transaction> transactions;

	public static void main(String args[]) throws Exception {
		DataContainer data = new DataContainer();
		LinkedList<Transaction> transactions = new LinkedList<Transaction>();

		SimpleDB dataBase = new SimpleDB(data, transactions);
		Scanner in = new Scanner(System.in);
		String key;
		String val;
		boolean waitForNextCommand = true;
		while (waitForNextCommand) {
			String[] input = in.nextLine().split("\\s+");
			Command command = Command.valueOf(input[0]);
			switch (command) {
			case GET:
				key = input[1];
				System.out.println(dataBase.get(key));
				break;
			case SET:
				key = input[1];
				val = input[2];
				dataBase.set(key, val);
				break;
			case UNSET:
				key = input[1];
				dataBase.unset(key);
				break;
			case NUMEQUALTO:
				val = input[1];
				System.out.println(dataBase.numequalto(val));
				break;
			case BEGIN:
				transactions.add(new Transaction(data));
				break;
			case ROLLBACK:
				String resRolleback = dataBase.rollback();
				if (!resRolleback.isEmpty()) {
					System.out.println(resRolleback);
				}
				break;
			case COMMIT:
				String resCommit = dataBase.commit();
				if (!resCommit.isEmpty()) {
					System.out.println(resCommit);
				}
				break;
			case END:
				waitForNextCommand = dataBase.end();
			default:
				waitForNextCommand = false;
			}
		}
		in.close();
	}

	SimpleDB(DataContainer data, LinkedList<Transaction> transactions) {
		this.data = data;
		this.transactions = transactions;
	}

	public String get(String key) {
		String res = data.get(key);
		return String.format(NEW_STRING, res == null ? NULL : res);
	}

	public void set(String key, String val) {
		if (transactions.size() > 0) {
			transactions.peekLast().set(key, val);
		}
		data.set(key, val);
	}

	public void unset(String key) {
		if (transactions.size() > 0) {
			transactions.peekLast().unset(key);
		}
		data.unset(key);
	}

	public String numequalto(String key) {
		return String.format(NEW_STRING, String.valueOf(data.numequalto(key)));
	}

	public void begin() {
		transactions.add(new Transaction(data));
	}

	public String commit() {
		String res = "";
		if (transactions.size() > 0) {
			transactions.clear();
		} else {
			res = NO_TRANSACTION;
		}
		return res;
	}

	public String rollback() {
		String res = "";
		if (transactions.size() > 0) {
			data.rollback(transactions.removeLast());
		} else {
			res = NO_TRANSACTION;
		}
		return res;
	}

	public boolean end() {
		if (transactions.size() > 0) {
			transactions.removeLast();
			return true;
		}
		return false;
	}
}
