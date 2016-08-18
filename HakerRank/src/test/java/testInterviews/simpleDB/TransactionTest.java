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
	private static final String VAL1 = "val1";
	private static final String VAL2 = "val2";
	private static final String VAL3 = "val3";

	@Test
	public void setTest() {
		DataContainer data = initMock();
		Transaction transaction = new Transaction(data);
		transaction.set(KEY1, VAL3);
		transaction.set(KEY3, VAL2);

		assertEquals(VAL1, transaction.prevKeyVal.get(KEY1));
		assertEquals(new Integer(1), transaction.countVal.get(VAL1));
		assertEquals(new Integer(-1), transaction.countVal.get(VAL3));
		assertFalse(transaction.prevKeyVal.containsKey(KEY3));
		assertTrue(transaction.added.contains(KEY3));
		verify(data).set(KEY1, VAL3);
		verify(data).set(KEY3, VAL2);
	}

	@Test
	public void unsetTest() {
		DataContainer data = initMock();
		Transaction transaction = new Transaction(data);
		transaction.unset(KEY1);
		transaction.set(KEY3, VAL2);
		transaction.unset(KEY3);

		assertEquals(VAL1, transaction.prevKeyVal.get(KEY1));
		assertEquals(new Integer(1), transaction.countVal.get(VAL1));
		verify(data).unset(KEY1);
		assertFalse(transaction.prevKeyVal.containsKey(KEY3));
		assertFalse(transaction.added.contains(KEY3));
		verify(data).set(KEY3, VAL2);
		verify(data).unset(KEY3);
	}

	private DataContainer initMock() {
		DataContainer data = mock(DataContainer.class);
		when(data.get(KEY1)).thenReturn(VAL1);
		when(data.get(KEY2)).thenReturn(VAL2);
		return data;
	}
}
