package db.entity;

public class Storyline {

	


	private String pageNo;
	private String pageText;
	private String milestoneJournalEntry;
	
	public Storyline() {
		super();
		this.pageNo = "A pageNo";
		this.pageText = "A pageText";
		this.milestoneJournalEntry = "A milestoneJournalEntry";
	}
	
	public Storyline(String pageNo, String pageText, String milestoneJournalEntry) {
		super();
		this.pageNo = pageNo;
		this.pageText = pageText;
		this.milestoneJournalEntry = milestoneJournalEntry;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageText() {
		return pageText;
	}

	public void setPageText(String pageText) {
		this.pageText = pageText;
	}

	public String getMilestoneJournalEntry() {
		return milestoneJournalEntry;
	}

	public void setMilestoneJournalEntry(String milestoneJournalEntry) {
		this.milestoneJournalEntry = milestoneJournalEntry;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Storyline [pageNo=");
		builder.append(pageNo);
		builder.append(", pageText=");
		builder.append(pageText);
		builder.append(", milestoneJournalEntry=");
		builder.append(milestoneJournalEntry);
		builder.append("]");
		return builder.toString();
	}
	
	public static String addReference(String source, String target, String reference) {
		String replacement = String.format("[ref=%s][b]%s[/b][/ref]",reference,target);
		return source.replaceAll("\\b"+target+"\\b", replacement); //tylko cale slowa w regexp
	}

	

	

	
	
	
	
	
	

}
