package com.kightley.rbc;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class FruitBasketTest {

	@Test
	public void testInitialisation() {
		FruitBasket basket = new FruitBasket();
		
		List<Fruit> fruit = new ArrayList<>();
		
		fruit.add(Fruit.APPLE);
		assertEquals(15, basket.calculatePrice(fruit));
		fruit.clear();
		
		fruit.add(Fruit.BANANA);
		assertEquals(20, basket.calculatePrice(fruit));
		fruit.clear();
		
		fruit.add(Fruit.ORANGE);
		assertEquals(30, basket.calculatePrice(fruit));
		fruit.clear();
		
		fruit.add(Fruit.LEMON);
		assertEquals(35, basket.calculatePrice(fruit));
		fruit.clear();
		
		fruit.add(Fruit.PEACH);
		assertEquals(25, basket.calculatePrice(fruit));
	}
	
	
	@Test
	public void testFruitPrice() {
		FruitBasket basket = new FruitBasket();
		
		assertEquals(15, basket.getPrice(Fruit.APPLE));
		assertEquals(20, basket.getPrice(Fruit.BANANA));
		assertEquals(30, basket.getPrice(Fruit.ORANGE));
		assertEquals(35, basket.getPrice(Fruit.LEMON));
		assertEquals(25, basket.getPrice(Fruit.PEACH));
	}
	
	@Test
	public void testBasketCalculation()  {
		FruitBasket basket = new FruitBasket();
		
		List<Fruit> fruit = new ArrayList<>();
		
		for (int i = 0; i < 10; ++i) {
			int expectation = createTestBasket(basket, fruit);
			assertEquals(expectation, basket.calculatePrice(fruit));
		}
	}

	private int createTestBasket(FruitBasket basket, List<Fruit> list) {
		list.clear();
		int total = 0;
		
		for (Fruit f : Fruit.values()) {
			int rnd = (int) (Math.random() * 10);
			int price = basket.getPrice(f);
			for (int i = 0; i < rnd; ++i) {
				list.add(f);
			}
			total += price * rnd;
		}
				
		// Randomize the list so it is not ordered by fruit
		Collections.shuffle(list);

		return total;
	}
}
