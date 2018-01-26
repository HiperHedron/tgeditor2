package db.entity;

public class InventoryItemUseRefMatch {
	
	private String refName;
	private String itemID;
	private String effectDescript;
	private int sanity;
	private int empathy;
	private int sanityTreshold;
	private int empathyTreshold;
	private String pageNo;
	private String mapNo;
	private String optionalJOurnalEntry;
	private String pagesLocked;
	private String removeFromInventoryFlag;
	public InventoryItemUseRefMatch(String refName, String itemID, String effectDescript, int sanity, int empathy,
			int sanityTreshold, int empathyTreshold, String pageNo, String mapNo, String optionalJOurnalEntry,
			String pagesLocked, String removeFromInventoryFlag) {
		super();
		this.refName = refName;
		this.itemID = itemID;
		this.effectDescript = effectDescript;
		this.sanity = sanity;
		this.empathy = empathy;
		this.sanityTreshold = sanityTreshold;
		this.empathyTreshold = empathyTreshold;
		this.pageNo = pageNo;
		this.mapNo = mapNo;
		this.optionalJOurnalEntry = optionalJOurnalEntry;
		this.pagesLocked = pagesLocked;
		this.removeFromInventoryFlag = removeFromInventoryFlag;
	}
	
	public InventoryItemUseRefMatch() {
		
	}
	public String getRefName() {
		return refName;
	}
	public void setRefName(String refName) {
		this.refName = refName;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getEffectDescript() {
		return effectDescript;
	}
	public void setEffectDescript(String effectDescript) {
		this.effectDescript = effectDescript;
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
	public String getOptionalJOurnalEntry() {
		return optionalJOurnalEntry;
	}
	public void setOptionalJOurnalEntry(String optionalJOurnalEntry) {
		this.optionalJOurnalEntry = optionalJOurnalEntry;
	}
	public String getPagesLocked() {
		return pagesLocked;
	}
	public void setPagesLocked(String pagesLocked) {
		this.pagesLocked = pagesLocked;
	}
	public String getRemoveFromInventoryFlag() {
		return removeFromInventoryFlag;
	}
	public void setRemoveFromInventoryFlag(String removeFromInventoryFlag) {
		this.removeFromInventoryFlag = removeFromInventoryFlag;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InventoryItemUseRefMatch [refName=").append(refName).append(", itemID=").append(itemID)
				.append(", effectDescript=").append(effectDescript).append(", sanity=").append(sanity)
				.append(", empathy=").append(empathy).append(", sanityTreshold=").append(sanityTreshold)
				.append(", empathyTreshold=").append(empathyTreshold).append(", pageNo=").append(pageNo)
				.append(", mapNo=").append(mapNo).append(", optionalJOurnalEntry=").append(optionalJOurnalEntry)
				.append(", pagesLocked=").append(pagesLocked).append(", removeFromInventoryFlag=")
				.append(removeFromInventoryFlag).append("]");
		return builder.toString();
	}
	
	

}
