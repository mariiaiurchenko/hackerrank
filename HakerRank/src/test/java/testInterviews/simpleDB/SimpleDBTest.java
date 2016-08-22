package testInterviews.simpleDB;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Test;
import org.mockito.Mockito;

public class SimpleDBTest {

	private static final String NEW_STRING = "> %s";
	private static final String NO_TRANSACTION = String.format(NEW_STRING,
			"NO TRANSACTION");
	private static final String NULL = "NULL";

	private static final String KEY1 = "key1";
	private static final String KEY2 = "key2";
	private static final String KEY3 = "key3";
	private static final String KEY4 = "key4";
	private static final String VAL1 = "val1";
	private static final String VAL2 = "val2";
	private static final String VAL3 = "val3";

	@Test
	public void getTest() {
		SimpleDB simplDB = initSimpleDB();
		assertEquals(String.format(NEW_STRING, VAL1), simplDB.get(KEY1));
		assertEquals(String.format(NEW_STRING, VAL2), simplDB.get(KEY2));
		assertEquals(String.format(NEW_STRING, VAL1), simplDB.get(KEY4));
		assertEquals(String.format(NEW_STRING, NULL), simplDB.get("notExist"));
	}

	@Test
	public void setTest() {
		SimpleDB simplDB = initSimpleDB();
		simplDB.set(KEY1, VAL2);
		simplDB.set("NewKey", "NewVal");
		assertEquals(String.format(NEW_STRING, VAL2), simplDB.get(KEY1));
		assertEquals(String.format(NEW_STRING, "NewVal"), simplDB.get("NewKey"));
		simplDB.set("NewKey", "NewVal2");
		assertEquals(String.format(NEW_STRING, "NewVal2"),
				simplDB.get("NewKey"));
	}

	@Test
	public void unsetTest() {
		SimpleDB simplDB = initSimpleDB();
		simplDB.unset(KEY1);
		assertEquals(String.format(NEW_STRING, NULL), simplDB.get(KEY1));
		simplDB.set(KEY1, VAL2);
		assertEquals(String.format(NEW_STRING, VAL2), simplDB.get(KEY1));
		assertEquals(String.format(NEW_STRING, NULL), simplDB.get("fakeKey"));
		simplDB.unset("fakeKey");
		assertEquals(String.format(NEW_STRING, NULL), simplDB.get("fakeKey"));
	}

	@Test
	public void numequaltoTest() {
		SimpleDB simplDB = initSimpleDB();
		assertEquals(String.format(NEW_STRING, "0"),
				simplDB.numequalto("fakeVal"));
		assertEquals(String.format(NEW_STRING, "2"), simplDB.numequalto(VAL1));
		assertEquals(String.format(NEW_STRING, "1"), simplDB.numequalto(VAL2));
	}

	@Test
	public void beginTest() {
		Deque<Transaction> transactions = mock(Deque.class);
		SimpleDB simplDB = new SimpleDB(mock(DataContainer.class), transactions);
		simplDB.begin();
		verify(transactions).add(Mockito.any(Transaction.class));
	}

	@Test
	public void commitTest() {
		Deque<Transaction> transactions = mock(Deque.class);
		SimpleDB simplDB = new SimpleDB(mock(DataContainer.class), transactions);
		when(transactions.isEmpty()).thenReturn(false).thenReturn(true);
		assertEquals("", simplDB.commit());
		assertEquals(NO_TRANSACTION, simplDB.commit());
	}

	@Test
	public void rollbackTest() {
		Deque<Transaction> transactions = mock(Deque.class);
		SimpleDB simplDB = new SimpleDB(mock(DataContainer.class), transactions);
		when(transactions.isEmpty()).thenReturn(false).thenReturn(true);
		Transaction transaction = mock(Transaction.class);
		when(transactions.pop()).thenReturn(transaction);
		assertEquals("", simplDB.rollback());
		assertEquals(NO_TRANSACTION, simplDB.rollback());
		verify(transactions, times(1)).pop();
	}

	private SimpleDB initSimpleDB() {
		DataContainer dataContainer = new DataContainer();
		dataContainer.set(KEY1, VAL1);
		dataContainer.set(KEY2, VAL2);
		dataContainer.set(KEY3, VAL3);
		dataContainer.set(KEY4, VAL1);

		Deque<Transaction> transactions = new LinkedList<>();
		SimpleDB simpleDB = new SimpleDB(dataContainer, transactions);
		return simpleDB;
	}
}
