package ttl.def;

public interface MyService {
	public void doStuff(String s);
	
	default public void doOtherStuff() {
		System.out.println("MyService");
	}
	
	public static String sayHello() {
		return "hello";
	}
}
