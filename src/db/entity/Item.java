package db.entity;

public class Item {

	


	private String itemId;
	private String name;
	private String desc;

	
	public Item() {
		super();
		this.itemId = "An ID";
		this.name = "A name";
		this.desc = "A description";
		
	}


	public Item(String itemId, String name, String desc) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.desc = desc;
	}
	
	
	public String getItemId() {
		return itemId;
	}


	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", name=" + name + ", desc=" + desc + "]";
	}
	
	
	
	
	

}
