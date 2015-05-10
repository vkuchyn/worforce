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
		printStream.print("0,");
	}
}
