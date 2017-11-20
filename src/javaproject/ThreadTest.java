package javaproject;

import java.util.Random;

public class ThreadTest extends Thread {
	// index 변수를 추가해서 스레드가 동작시에 해당 변수를 증가시키도록 할겁니다.
	private static int index = 0;
	
	private int id = -1;
	public ThreadTest(int id){
		this.id = id;
	}
	public void run(){
		System.out.println( id + "번 쓰레드 동작 중..." );
		Random r = new Random(System.currentTimeMillis());
		int temp = 0;
		try {
			long s = r.nextInt(3000); // 3초내로 끝내자.
			Thread.sleep(s); // 쓰레드를 잠시 멈춤
			temp = index++; // index 변수를 증가시킵니다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println( id + "번 쓰레드 동작 종료... : "+temp);
	}

	public static void main(String[] args) {

		System.out.println("Start main method.");

		for(int i = 0 ; i < 10 ; i++ ){
			ThreadTest test = new ThreadTest(i);
			test.start(); // 이 메소드를 실행하면 Thread 내의 run()을 수행한다.
		}

		try {
			Thread.sleep(5000); // 쓰레드가 종료할 때까지의 충분한 시간을 기다립니다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("current Index: "+ index); // index의 값을 반환합니다.
		System.out.println("End main method.");
	}
}