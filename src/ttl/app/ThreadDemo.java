package ttl.app;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo {

	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		// td.goOld();
		td.go();
	}

	public void go() {

		int numcpus = Runtime.getRuntime().availableProcessors();

		ExecutorService service = Executors.newFixedThreadPool(numcpus + 2);
		
		IdGen idgen = new IdGen();

		Worker w1 = new Worker("Worker 1", idgen);
		Worker w2 = new Worker("Worker 2", idgen);

		Future<?> f1 = service.submit(w1);
		Future<?> f2 = service.submit(w2);

		try {
			f1.get();
			f2.get();
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}

		int finalSum = w1.getSum() + w2.getSum();

		System.out.println("Final idgen is " + idgen.getNextId());

		service.shutdown();

		try {
			service.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public class Worker implements Runnable {
		private String name;
		private int sum;
		private IdGen idgen;

		public Worker(String name, IdGen idgen) {
			this.name = name;
			this.idgen = idgen;
		}

		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				int nextId = idgen.getNextId();
				//Do something with nextId
				sum += nextId;
			}
		}

		public int getSum() {
			return sum;
		}

	}
	
	public class IdGen 
	{
		private Object syncObject = new Object();

		//private int nextId = 0;
		private AtomicInteger nextId = new AtomicInteger(0);
		
		public int getNextId() {
			/*
			synchronized(syncObject) {
				return nextId++;
			}
			*/
			
			return nextId.getAndIncrement();
		}
	}
}

