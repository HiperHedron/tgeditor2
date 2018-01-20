package db.entity;

import db.DBTools;

public class Interaction extends DBTools {
	
	private String reference;
	private String name;
	private String page;
	private String map;
	private int empathy;
	private int sanity;
	private String desc;
	private String journal;
	private int empatyTreshold;
	private int sanityTreshold;
	private String pagesLocked;
	private String takeItemID;
	
	public Interaction() {
		this.reference = "A reference";
		this.name = "A name";
		this.page = "page999";
		this.map = "map999";
		this.empathy = 100;
		this.sanity = -100;
		this.desc = "A description";
		this.journal = "A journal entry";
	}
	
	

	public Interaction(String reference, String name, String page, String map, int empathy, int sanity, String desc,
			String journal, int empatyTreshold, int sanityTreshold, String pagesLocked, String takeItemID) {
		this.reference = reference;
		this.name = name;
		this.page = page;
		this.map = map;
		this.empathy = empathy;
		this.sanity = sanity;
		this.desc = desc;
		this.journal = journal;
		this.empatyTreshold = empatyTreshold;
		this.sanityTreshold = sanityTreshold;
		this.pagesLocked = pagesLocked;
		this.takeItemID = takeItemID;
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public int getEmpathy() {
		return empathy;
	}

	public void setEmpathy(int empathy) {
		this.empathy = empathy;
	}

	public int getSanity() {
		return sanity;
	}

	public void setSanity(int sanity) {
		this.sanity = sanity;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public int getEmpatyTreshold() {
		return empatyTreshold;
	}

	public void setEmpatyTreshold(int empatyTreshold) {
		this.empatyTreshold = empatyTreshold;
	}

	public int getSanityTreshold() {
		return sanityTreshold;
	}

	public void setSanityTreshold(int sanityTreshold) {
		this.sanityTreshold = sanityTreshold;
	}

	public String getPagesLocked() {
		return pagesLocked;
	}

	public void setPagesLocked(String pagesLocked) {
		this.pagesLocked = pagesLocked;
	}

	public String getTakeItemID() {
		return takeItemID;
	}

	public void setTakeItemID(String takeItemID) {
		this.takeItemID = takeItemID;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Interaction [reference=");
		builder.append(reference);
		builder.append(", name=");
		builder.append(name);
		builder.append(", page=");
		builder.append(page);
		builder.append(", map=");
		builder.append(map);
		builder.append(", empathy=");
		builder.append(empathy);
		builder.append(", sanity=");
		builder.append(sanity);
		builder.append(", desc=");
		builder.append(desc);
		builder.append(", journal=");
		builder.append(journal);
		builder.append(", empatyTreshold=");
		builder.append(empatyTreshold);
		builder.append(", sanityTreshold=");
		builder.append(sanityTreshold);
		builder.append(", pagesLocked=");
		builder.append(pagesLocked);
		builder.append(", takeItemID=");
		builder.append(takeItemID);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	/*public Interaction(String reference, String name, String page, String map, int empathy, int sanity, String desc, String journal) {
		this.reference = reference;
		this.name = name;
		this.page = page;
		this.map = map;
		this.empathy = empathy;
		this.sanity = sanity;
		this.desc = desc;
		this.journal = journal;
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public int getEmpathy() {
		return empathy;
	}

	public void setEmpathy(int empathy) {
		this.empathy = empathy;
	}

	public int getSanity() {
		return sanity;
	}

	public void setSanity(int sanity) {
		this.sanity = sanity;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Interaction [reference=");
		builder.append(reference);
		builder.append(", name=");
		builder.append(name);
		builder.append(", page=");
		builder.append(page);
		builder.append(", map=");
		builder.append(map);
		builder.append(", empathy=");
		builder.append(empathy);
		builder.append(", sanity=");
		builder.append(sanity);
		builder.append(", desc=");
		builder.append(desc);
		builder.append(", journal=");
		builder.append(journal);
		builder.append("]");
		return builder.toString();
	}*/
	
	
	
	
	

}
