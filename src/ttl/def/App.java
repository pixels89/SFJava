package ttl.def;

public class App {

	public static void main(String[] args) {
		MyService msi = new MyServiceImpl2();
		msi.doStuff("stuff");
		
		msi.doOtherStuff();
	}
}
