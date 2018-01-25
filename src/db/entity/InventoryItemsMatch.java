package db.entity;

import db.DBTools;

public class InventoryItemsMatch extends DBTools{
	
	private String itemID_1;
	private String itemID_2;
	private String createdObjectID;
	private String efectDesc;
	private int sanity;
	private int empathy;
	private int sanityTreshold;
	private int empathyTreshold;
	private String pageNo;
	private String mapNo;
	private String optionalJournalEntry;
	private String pagesLocked;
	
	public InventoryItemsMatch(String itemID_1, String itemID_2, String createdObjectID, String efectDesc, int sanity,
			int empathy, int sanityTreshold, int empathyTreshold, String pageNo, String mapNo,
			String optionalJournalEntry, String pagesLocked) {
		super();
		this.itemID_1 = itemID_1;
		this.itemID_2 = itemID_2;
		this.createdObjectID = createdObjectID;
		this.efectDesc = efectDesc;
		this.sanity = sanity;
		this.empathy = empathy;
		this.sanityTreshold = sanityTreshold;
		this.empathyTreshold = empathyTreshold;
		this.pageNo = pageNo;
		this.mapNo = mapNo;
		this.optionalJournalEntry = optionalJournalEntry;
		this.pagesLocked = pagesLocked;
	}
	
	public InventoryItemsMatch() {
		
	}

	public String getItemID_1() {
		return itemID_1;
	}

	public void setItemID_1(String itemID_1) {
		this.itemID_1 = itemID_1;
	}

	public String getItemID_2() {
		return itemID_2;
	}

	public void setItemID_2(String itemID_2) {
		this.itemID_2 = itemID_2;
	}

	public String getCreatedObjectID() {
		return createdObjectID;
	}

	public void setCreatedObjectID(String createdObjectID) {
		this.createdObjectID = createdObjectID;
	}

	public String getEfectDesc() {
		return efectDesc;
	}

	public void setEfectDesc(String efectDesc) {
		this.efectDesc = efectDesc;
	}

	public int getSanity() {
		return sanity;
	}

	public void setSanity(int sanity) {
		this.sanity = sanity;
	}

	public int getEmpathy() {
		return empathy;
	}

	public void setEmpathy(int empathy) {
		this.empathy = empathy;
	}

	public int getSanityTreshold() {
		return sanityTreshold;
	}

	public void setSanityTreshold(int sanityTreshold) {
		this.sanityTreshold = sanityTreshold;
	}

	public int getEmpathyTreshold() {
		return empathyTreshold;
	}

	public void setEmpathyTreshold(int empathyTreshold) {
		this.empathyTreshold = empathyTreshold;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getMapNo() {
		return mapNo;
	}

	public void setMapNo(String mapNo) {
		this.mapNo = mapNo;
	}

	public String getOptionalJournalEntry() {
		return optionalJournalEntry;
	}

	public void setOptionalJournalEntry(String optionalJournalEntry) {
		this.optionalJournalEntry = optionalJournalEntry;
	}

	public String getPagesLocked() {
		return pagesLocked;
	}

	public void setPagesLocked(String pagesLocked) {
		this.pagesLocked = pagesLocked;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InventoryItemsMatch [itemID_1=");
		builder.append(itemID_1);
		builder.append(", itemID_2=");
		builder.append(itemID_2);
		builder.append(", createdObjectID=");
		builder.append(createdObjectID);
		builder.append(", efectDesc=");
		builder.append(efectDesc);
		builder.append(", sanity=");
		builder.append(sanity);
		builder.append(", empathy=");
		builder.append(empathy);
		builder.append(", sanityTreshold=");
		builder.append(sanityTreshold);
		builder.append(", empathyTreshold=");
		builder.append(empathyTreshold);
		builder.append(", pageNo=");
		builder.append(pageNo);
		builder.append(", mapNo=");
		builder.append(mapNo);
		builder.append(", optionalJournalEntry=");
		builder.append(optionalJournalEntry);
		builder.append(", pagesLocked=");
		builder.append(pagesLocked);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	

}
