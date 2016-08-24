package testInterviews.simpleDB;

public enum Command {
	GET(1), SET(2), UNSET(1), NUMEQUALTO(1), BEGIN(0), ROLLBACK(0), COMMIT(0), END(
			0);

	private final String paramDescriptionPattern = "> Invalid command format. For command \"%s\" number of expected parameter(s) is %d";
	private static final String HELP = "> Invalid command. Please use one from the commands: GET, SET, UNSET, NUMEQUALTO, BEGIN, ROLLBACK, COMMIT, END;";
	private final int paramCount;

	private Command(int paramCount) {
		this.paramCount = paramCount;
	}

	public boolean isValid(String[] input) {
		return input.length > paramCount;
	}

	public String paramDescription() {
		return String.format(paramDescriptionPattern, this.toString(),
				this.paramCount);
	}

	public static String help() {
		return HELP;
	}
}
