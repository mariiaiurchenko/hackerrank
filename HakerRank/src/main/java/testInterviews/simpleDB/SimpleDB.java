package testInterviews.simpleDB;

import java.util.Deque;

public class SimpleDB {
	
	private static final String NEW_STRING = "> %s";
	private static final String NO_TRANSACTION = String.format(NEW_STRING, "NO TRANSACTION");
	private static final String NULL = "NULL";

	private DataContainer dataContainer;
	private Deque<Transaction> transactions;

	public SimpleDB(DataContainer dataContainer, Deque<Transaction> transactions) {
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
		transactions.add(new Transaction(dataContainer));
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
