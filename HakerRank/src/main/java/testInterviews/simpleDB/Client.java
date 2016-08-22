package testInterviews.simpleDB;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

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
				if (!command.validation(input)) {
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
					waitForNextCommand = false;
				}
			}
		}
	}
}
