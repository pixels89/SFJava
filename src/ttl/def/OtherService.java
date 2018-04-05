package ttl.def;

public interface OtherService {

	default void doOtherStuff() {
		System.out.println("OtherService");
	}
}
