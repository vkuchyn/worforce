package ua.com.kuchyn.workforce.task1;

import java.io.PrintStream;
import java.util.Arrays;

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
	public void printFibonacciSequence(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Length must be greater than zero");
		}

		long[] fibonacciArray = new long[length];
		fibonacci(length, fibonacciArray);
		printStream.print(Arrays.toString(fibonacciArray));
	}

	private long fibonacci(int length, long[] fibonacciArray) {
		long fib;
		if (length == 1) {
			fib = 0;
		} else if (length == 2) {
			fib = fibonacci(1, fibonacciArray) + 1;
		} else {
			fib = fibonacci(length - 1, fibonacciArray) + fibonacci(length - 2, fibonacciArray);
		}
		fibonacciArray[length - 1] = fib;
		return fib;
	}

}
