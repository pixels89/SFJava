package ttl.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {
	
	public static void main(String[] args) throws IOException {
		WordCounter wc = new WordCounter();
		
		Map<String, Long> words = wc.getWordFreq("PrideAndPrejudice.txt");
		
		//words.forEach((k, v) -> System.out.println(k + ":" + v));
		

		Comparator<String> byFreq = (a1, a2) -> {
			long v1 = words.get(a1);
			long v2 = words.get(a2);
			
			long result = v1 - v2;
			if(result == 0) {
				result = a1.compareTo(a2);
			}
			return (int)result;
		};

		Map<String, Long> m = new TreeMap<>(byFreq);
		m.putAll(words);
		
		m.forEach((k, v) -> System.out.println(k + ":" + v));
	}
	
	public Map<String, Long> getWordFreq(String fileName) throws IOException {
		
		Map<String, Long> m1 = Files.lines(Paths.get(fileName))
				.map(s -> s.split("\\W"))
				.flatMap(s1 -> Arrays.stream(s1))
				.collect(Collectors.groupingBy(s -> s, TreeMap::new, Collectors.counting()));
		
		return m1;
		
		
	}

}
