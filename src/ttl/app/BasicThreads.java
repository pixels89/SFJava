package ttl.app;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class BasicThreads {

	public static void main(String[] args) {
		BasicThreads td = new BasicThreads();
		// td.goOld();
		td.go();
	}

	public void go() {

		int numcpus = Runtime.getRuntime().availableProcessors();

		ExecutorService service = Executors.newFixedThreadPool(numcpus + 2);

		Worker w1 = new Worker("Worker 1");
		Worker w2 = new Worker("Worker 2");

		Future<?> f1 = service.submit(w1);
		Future<?> f2 = service.submit(w2);

		try {
			f1.get();
			f2.get();
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}

		int finalSum = w1.getSum() + w2.getSum();

		System.out.println("Final sum = " + finalSum);

		service.shutdown();

		try {
			service.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void goOld() {

		Worker w1 = new Worker("Worker 1");
		Worker w2 = new Worker("Worker 2");

		Thread th1 = new Thread(w1);
		Thread th2 = new Thread(w2);

		th1.start();
		th2.start();

		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int finalSum = w1.getSum() + w2.getSum();

		System.out.println("Final sum = " + finalSum);
	}

	public class Worker implements Runnable {
		private String name;
		private int sum;

		public Worker(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				sum += i;
			}
		}

		public int getSum() {
			return sum;
		}

	}
}
