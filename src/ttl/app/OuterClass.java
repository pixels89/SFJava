package ttl.app;

public class OuterClass {
	
	private int value;
	
	public static void main(String[] args) {
		OuterClass oc = new OuterClass();
		oc.value++;
	}

	public void foo() {
		InnerClass ic = new InnerClass();
	}
	
	public static class InnerClass {
		
	}
}
