package com.dianping.cat.demo;

import org.junit.Test;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;

public class TestBusinessMessage {

	@Test
	public void test() throws Exception{
		while(true){
			
			for (int i = 0; i < 1000; i++) {
				Transaction t = Cat.newTransaction("URL", "/index");
				Cat.logMetric("order", "quantity" , i);
				
				t.complete();
			}
			for (int i = 0; i < 1000; i++) {
				Transaction t = Cat.newTransaction("URL", "/detail");
				Cat.logMetric("payment.pending", "amount" , i);
				
				t.complete();
			}
			for (int i = 0; i < 1000; i++) {
				Transaction t = Cat.newTransaction("URL", "/order/submitOrder");
				Cat.logMetric("payment.success", "amount" , i);
				
				t.complete();
			}
			
			Thread.sleep(10000);
		}
	}

}