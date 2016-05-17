package com.kightley.rbc;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FruitBasket {
	
	private Map<Fruit,Integer> priceMap = new HashMap<>();

	
	public FruitBasket() {
		try {
			init();
		} catch (Exception e) {
			throw new RuntimeException("Unable to parse configuration file", e);
		}
	}
	
	private void init() throws Exception {
		// Load price config
		File file = new File("config/configuration.xml");
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = docBuilder.parse(file);
		NodeList nodes = document.getElementsByTagName("price");
		for (int i = 0; i < nodes.getLength(); ++i) {
			Node n = nodes.item(i);
			String key = n.getAttributes().item(0).getNodeValue().toUpperCase();
			String value = n.getTextContent();
			priceMap.put(Fruit.valueOf(key), Integer.parseInt(value));
		}
	}
	
	public int calculatePrice(Collection<Fruit> basket) {
		int price = 0;
		
		for(Fruit f : basket) {
			if (priceMap.containsKey(f)) {
				price += priceMap.get(f);
			} else {
				System.out.println("Unknown fruit: " + f.name());
			}
		}
		return price;
	}
	
	public int getPrice(Fruit f) {
		return priceMap.get(f);
	}
	
	
	
	public static void main(String[] args) {
		
		FruitBasket fb = new FruitBasket();
		Fruit[] allFruit = Fruit.values();
		List<Fruit> fruitList = Arrays.asList(allFruit);
		
		int priceInPence = fb.calculatePrice(fruitList);
		System.out.println("Price of one each of the fruit = " + priceInPence);
	}

}
