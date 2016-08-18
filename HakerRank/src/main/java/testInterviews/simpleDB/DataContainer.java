package testInterviews.simpleDB;

import java.util.HashMap;
import java.util.Map;

public class DataContainer {

	Map<String, String> keyVal;
	Map<String, Integer> countVal;

	DataContainer() {
		keyVal = new HashMap<String, String>();
		countVal = new HashMap<String, Integer>();
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

	public void rollback(Transaction transaction) {
		for (String key : transaction.prevKeyVal.keySet()) {
			keyVal.put(key, transaction.prevKeyVal.get(key));
		}
		for (String key : transaction.added) {
			keyVal.remove(key);
		}
		for (String key : transaction.countVal.keySet()) {
			adjustCountVal(key, transaction.countVal.get(key));
		}
	}

	private void adjustCountVal(String key, int val) {
		int prev = countVal.containsKey(key) ? countVal.get(key) : 0;
		countVal.put(key, prev + val);
	}
}
