package com.hepsiburadapages.request;

import lombok.Data;


@Data
public class GroceryRequestPojo {
	private double price;
	private String name;
	private int id;
	private int stock;
}
