package model;

import java.sql.Date;

public class Item {
	private String name;
	private String type;
	private int price;
	private Date dateAquired;
	private int id;
	
	public Item() {
		
	}
	public Item(String name, String type, int price, Date date) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.dateAquired = date;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getDateAquired() {
		return dateAquired;
	}
	public void setDateAquired(Date dateAquired) {
		this.dateAquired = dateAquired;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.getId() + " | " 
				+ this.getName() + " | " 
				+ this.getType() + " | " 
				+ this.getPrice() + " | " 
				+ this.getDateAquired().toString(); 
	}
	
	
}
