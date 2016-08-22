package testInterviews.simpleDB;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import testInterviews.simpleDB.DataContainer;
import testInterviews.simpleDB.Transaction;

public class TransactionTest {

	private static final String KEY1 = "key1";
	private static final String KEY2 = "key2";
	private static final String KEY3 = "key3";
	private static final String KEY4 = "key4";
	private static final String VAL1 = "val1";
	private static final String VAL2 = "val2";
	private static final String VAL3 = "val3";

	@Test
	public void setTest() {
		DataContainer dataContainer = initMock();
		Transaction transaction = new Transaction(dataContainer);
		transaction.set(KEY1, VAL3);
		transaction.set(KEY3, VAL2);

		assertEquals(VAL1, transaction.prevKeyVal.get(KEY1));
		assertEquals(new Integer(1), transaction.countVal.get(VAL1));
		assertEquals(new Integer(-1), transaction.countVal.get(VAL3));
		assertFalse(transaction.prevKeyVal.containsKey(KEY3));
		assertTrue(transaction.added.contains(KEY3));
		verify(dataContainer).set(KEY1, VAL3);
		verify(dataContainer).set(KEY3, VAL2);
	}

	@Test
	public void unsetTest() {
		DataContainer dataContainer = initMock();
		Transaction transaction = new Transaction(dataContainer);
		transaction.unset(KEY1);
		transaction.set(KEY3, VAL2);
		transaction.unset(KEY3);

		assertEquals(VAL1, transaction.prevKeyVal.get(KEY1));
		assertEquals(new Integer(1), transaction.countVal.get(VAL1));
		verify(dataContainer).unset(KEY1);
		assertFalse(transaction.prevKeyVal.containsKey(KEY3));
		assertFalse(transaction.added.contains(KEY3));
		verify(dataContainer).set(KEY3, VAL2);
		verify(dataContainer).unset(KEY3);
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
		DataContainer dataContainer = new DataContainer();
		dataContainer.set(KEY1, VAL1);
		dataContainer.set(KEY2, VAL2);
		dataContainer.set(KEY3, VAL3);
		dataContainer.set(KEY4, VAL1);

		Transaction transaction = new Transaction(dataContainer);
		transaction.prevKeyVal.put(KEY3, VAL2);
		transaction.prevKeyVal.put("key5", VAL1);
		transaction.prevKeyVal.put("key6", VAL2);
		transaction.added.add(KEY4);
		transaction.countVal.put(VAL3, -1);
		transaction.countVal.put(VAL2, 2);
		transaction.countVal.put(VAL1, 0);
		transaction.rollback();

		assertEquals(2, dataContainer.numequalto(VAL1));
		assertEquals(3, dataContainer.numequalto(VAL2));
		assertEquals(0, dataContainer.numequalto(VAL3));

		assertEquals(VAL2, dataContainer.get("key6"));
		assertEquals(VAL2, dataContainer.get(KEY3));
		assertEquals(VAL1, dataContainer.get("key5"));
		assertEquals(null, dataContainer.get(KEY4));
	}

	private DataContainer initMock() {
		DataContainer data = mock(DataContainer.class);
		when(data.get(KEY1)).thenReturn(VAL1);
		when(data.get(KEY2)).thenReturn(VAL2);
		return data;
	}
}
