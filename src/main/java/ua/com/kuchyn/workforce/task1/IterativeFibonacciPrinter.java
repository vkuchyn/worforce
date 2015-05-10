package ua.com.kuchyn.workforce.task1;

import java.io.PrintStream;

/**
 * User: viktor
 * Date: 5/10/15
 */
public class IterativeFibonacciPrinter implements FibonacciPrinter {

	private final PrintStream printStream;

	public IterativeFibonacciPrinter() {
		printStream = System.out;
	}

	public IterativeFibonacciPrinter(PrintStream printStream) {
		this.printStream = printStream;
	}

	@Override
	public void printFibonacciSequence(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Length must be greater than zero");
		}

		long fib1 = 1;
		long fib2 = 1;
		printStream.print("0");
		for (int i = 1; i < length; i++) {
			printStream.print(", " + fib1);
			long buf = fib2;
			fib2 += fib1;
			fib1 = buf;
		}
	}
}
