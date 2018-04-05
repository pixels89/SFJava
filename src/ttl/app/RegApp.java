package ttl.app;

import java.util.Arrays;
import java.util.stream.Stream;

public class RegApp {

	public static void main(String[] args) {
		RegApp ra = new RegApp();
		ra.go();
	}

	public void go() {
		String [][] data = { { "one", "two", "three"},
				{ "five", "six", "seven" }
		};
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length;j++) {
				String s = data[i][j];
			}
		}
		Arrays.stream(data)
				.peek(sarr -> {
					System.out.println("Peek 1 " + sarr + ":" + sarr.length);
				})
				.flatMap((sarr) -> Arrays.stream(sarr))
				.peek(sarr -> {
					System.out.println("Peek 2 " + sarr + ":" + sarr.length());
				}).forEach(System.out::println);
	}
	
	
}
