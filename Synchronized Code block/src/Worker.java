import java.util.ArrayList;
import java.util.Random;


public class Worker {
	
	private ArrayList<Integer> list1 = new ArrayList<Integer>();
	private ArrayList<Integer> list2 = new ArrayList<Integer>();
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private Random random = new Random();

	private void partOne(){
		synchronized (lock1) {
			
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list1.add(random.nextInt(100));
		}
	}
	
	private void partTwo(){
		synchronized (lock2) {
			
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list2.add(random.nextInt(100));
		}
	}
	
	private void proceed(){
		for(int i = 0; i < 1000; i++){
			partOne();
			partTwo();
		}
	}
	
	public void start(){
		System.out.println("Begin...");
		long startTime = System.currentTimeMillis();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				proceed();				
			}
		});
		
	Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				proceed();				
			}
		});
	
	t1.start();
	t2.start();
	
	try {
		t1.join();
		t2.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
		long endTime = System.currentTimeMillis();
		System.out.println("Time " + (endTime - startTime) + "\n"
				+ "List 1: " + list1.size() + "\n"
				+ "List 2: " + list2.size());
	}
}
