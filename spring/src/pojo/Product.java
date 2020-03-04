package pojo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("p")
public class Product {
	private int id;
	private String name;
	@Autowired
	private Category gory;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getGory() {
		return gory;
	}
	public void setGory(Category gory) {
		this.gory = gory;
	}
	
	
}
