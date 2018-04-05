package ttl.def;

public class MyServiceImpl2 implements MyService, OtherService{

	@Override
	public void doStuff(String s) {
		System.out.println("doing stuff with " + s);
	}
	
	@Override
	public void doOtherStuff() {
		MyService.super.doOtherStuff();
	}
}
