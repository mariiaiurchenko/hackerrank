package testInterviews.simpleDB;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

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
 * 
 * @author Mariia Iurchenko
 * 
 */
public class Client {

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
					transactions.push(new Transaction(dataContainer));
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
}
