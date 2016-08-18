package testInterviews.simpleDB;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * contain the information to roll back the changes from current transaction  
 */
public class Transaction {

	DataContainer data;

	Map<String, String> prevKeyVal;
	Map<String, Integer> countVal;
	Set<String> added;

	Transaction(DataContainer data) {
		this.data = data;
		prevKeyVal = new HashMap<String, String>();
		countVal = new HashMap<String, Integer>();
		added = new HashSet<String>();
	}

	public void set(String key, String val) {
		savePrevData(key);
		decreaseCountVal(val);
		data.set(key, val);
	}

	public void unset(String key) {
		savePrevData(key);
		if (added.contains(key)) {
			added.remove(key);
		}
		data.unset(key);
	}

	private void increaseCountVal(String val) {
		int prev = 0;
		if (countVal.containsKey(val)) {
			prev = countVal.get(val);
		}
		countVal.put(val, prev + 1);
	}

	private void decreaseCountVal(String val) {
		int prev = 0;
		if (countVal.containsKey(val)) {
			prev = countVal.get(val);
		}
		countVal.put(val, prev - 1);
	}

	private void savePrevData(String key) {
		if (!prevKeyVal.containsKey(key) && !added.contains(key)) {
			if (data.get(key) == null) {
				added.add(key);
			} else {
				prevKeyVal.put(key, data.get(key));
			}
		}
		if (data.get(key) != null) {
			increaseCountVal(data.get(key));
		}
	}

}
