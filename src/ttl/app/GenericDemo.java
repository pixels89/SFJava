package ttl.app;

import java.util.ArrayList;
import java.util.List;

public class GenericDemo {

	public static void main(String[] args) {
		
		GenericDemo gd = new GenericDemo();
		gd.go();
	}
	
	public void go() {
		List<Number> lnumber = new ArrayList<>();
		lnumber.add(22.5);
		lnumber.add(5);
		lnumber.add(22);
		lnumber.add(30.67);
		
		double result = sum(lnumber);
		System.out.println(result);
		
		List<Integer> lint = new ArrayList<>();
		lint.add(10);
		lint.add(20);
		lint.add(35);
		lint.add(101);
		
		result = sum(lint);
		
		Integer [] iarr = { 0, 20, 40, 44 };
		
		addToList(lint, iarr);
		
		System.out.println(lint);
		
		addToList(lnumber, iarr);
		
	}
	
	public <T> void addToList(List<? super T> li, T [] arr) {
		
		for(T it : arr) {
			li.add(it);
		}
	}
	
	
	public double sum(List<? extends Number> list) {
		double sum = 0;
		for(Number n : list) {
			sum += n.doubleValue();
		}
		
		return sum;
	}
}
