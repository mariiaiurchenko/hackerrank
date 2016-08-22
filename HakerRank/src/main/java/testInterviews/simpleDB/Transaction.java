package testInterviews.simpleDB;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * contain the information to roll back the changes from current transaction  
 */
public class Transaction {

	// we can include transactions into dataContainer
	// all operations in dataContainer will manager transaction values
	// previous, count, adding
	// this reference will be reverted
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
