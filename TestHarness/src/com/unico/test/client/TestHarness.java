package com.unico.test.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestHarness {
	public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 15; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
          }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
 
}

