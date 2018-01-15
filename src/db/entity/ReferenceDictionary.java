package db.entity;

public class ReferenceDictionary {

	


	private String reference;
	private String name;
	private String desc;

	
	public ReferenceDictionary() {
		super();
		this.reference = "A reference";
		this.name = "A name";
		this.desc = "A description";
	}


	public ReferenceDictionary(String reference, String name, String desc) {
		super();
		this.reference = reference;
		this.name = name;
		this.desc = desc;
	}


	public String getReference() {
		return reference;
	}


	public void setReference(String reference) {
		this.reference = reference;
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
		StringBuilder builder = new StringBuilder();
		builder.append("ReferenceDictionary [reference=");
		builder.append(reference);
		builder.append(", name=");
		builder.append(name);
		builder.append(", desc=");
		builder.append(desc);
		builder.append("]");
		return builder.toString();
	}

	

	
	
	
	
	
	

}
