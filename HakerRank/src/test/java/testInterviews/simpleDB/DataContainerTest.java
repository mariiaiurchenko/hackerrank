package testInterviews.simpleDB;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataContainerTest {

	private static final String KEY1 = "key1";
	private static final String KEY2 = "key2";
	private static final String KEY3 = "key3";
	private static final String KEY4 = "key4";
	private static final String VAL1 = "val1";
	private static final String VAL2 = "val2";
	private static final String VAL3 = "val3";

	@Test
	public void setTest() {
		DataContainer data = new DataContainer();
		data.set("key", "val");
		assertEquals("val", data.get("key"));
		assertEquals(1, data.numequalto("val"));
	}

	@Test
	public void getTest() {
		DataContainer data = setInitData();
		assertEquals(VAL1, data.get(KEY1));
		assertEquals(VAL2, data.get(KEY2));
		assertEquals(null, data.get("fakeKey"));
	}

	@Test
	public void unsetTest() {
		DataContainer data = setInitData();
		assertEquals(VAL1, data.get(KEY1));
		data.unset(KEY1);
		assertEquals(null, data.get(KEY1));
		data.unset(KEY1);
		assertEquals(null, data.get(KEY1));
	}

	@Test
	public void numequaltoTest() {
		DataContainer data = setInitData();
		assertEquals(2, data.numequalto(VAL1));
		assertEquals(1, data.numequalto(VAL2));
		assertEquals(1, data.numequalto(VAL3));
		data.set(KEY3, VAL3);
		assertEquals(1, data.numequalto(VAL3));
		data.unset(KEY3);
		assertEquals(0, data.numequalto(VAL3));
		data.set("kkk", VAL3);
		assertEquals(1, data.numequalto(VAL3));
		data.unset(KEY1);
		assertEquals(1, data.numequalto(VAL1));
		assertEquals(0, data.numequalto("fakeVal"));
	}

	@Test
	public void rollbackTest() {
		// current database
		// KEY1, VAL1
		// KEY2, VAL2
		// KEY3, VAL3
		// KEY4, VAL1

		// prev database
		// KEY1, VAL1
		// KEY2, VAL2
		// KEY3, VAL2
		// KEY4, null
		// "key5", VAL1
		// "key6", VAL2
		DataContainer data = setInitData();
		Transaction transaction = new Transaction(data);
		transaction.prevKeyVal.put(KEY3, VAL2);
		transaction.prevKeyVal.put("key5", VAL1);
		transaction.prevKeyVal.put("key6", VAL2);
		transaction.added.add(KEY4);
		transaction.countVal.put(VAL3, -1);
		transaction.countVal.put(VAL2, 2);
		transaction.countVal.put(VAL1, 0);
		data.rollback(transaction);

		assertEquals(2, data.numequalto(VAL1));
		assertEquals(3, data.numequalto(VAL2));
		assertEquals(0, data.numequalto(VAL3));

		assertEquals(VAL2, data.get("key6"));
		assertEquals(VAL2, data.get(KEY3));
		assertEquals(VAL1, data.get("key5"));
		assertEquals(null, data.get(KEY4));
	}

	private static DataContainer setInitData() {
		DataContainer data = new DataContainer();
		data.set(KEY1, VAL1);
		data.set(KEY2, VAL2);
		data.set(KEY3, VAL3);
		data.set(KEY4, VAL1);
		return data;
	}
}
