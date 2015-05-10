package ua.com.kuchyn.workforce.task1;

import java.io.PrintStream;

/**
 * User: viktor
 * Date: 5/10/15
 */
public class RecursiveFibonacciPrinter implements FibonacciPrinter {

	private final PrintStream printStream;

	public RecursiveFibonacciPrinter() {
		printStream = System.out;
	}

	public RecursiveFibonacciPrinter(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void printFibonacciSequence(long length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Length must be greater than zero");
		}
		if (length == 1) {
			printStream.print("0,");
		} else if (length == 2) {
			printStream.print("0, 1,");
		} else {

		}


	}

}
