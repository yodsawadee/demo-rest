package com.example.demorest.products;

public class ProductResponse{

	private int id;
	private String name;
	private double price;

	public ProductResponse() {
	}

	public ProductResponse(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}
