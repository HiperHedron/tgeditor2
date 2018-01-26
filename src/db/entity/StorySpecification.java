package db.entity;

public class StorySpecification {
	
	private String storyName;
	private String storyDescription;
	private String startingPageNo;
	private int startingEmpathy;
	private int startingSanity;
	
	public StorySpecification(String storyName, String storyDescription, String startingPageNo, int startingEmmapthy,
			int startingSanity) {
		super();
		this.storyName = storyName;
		this.storyDescription = storyDescription;
		this.startingPageNo = startingPageNo;
		this.startingEmpathy = startingEmmapthy;
		this.startingSanity = startingSanity;
	}

	public StorySpecification() {
	
	}

	public String getStoryName() {
		return storyName;
	}

	public void setStoryName(String storyName) {
		this.storyName = storyName;
	}

	public String getStoryDescription() {
		return storyDescription;
	}

	public void setStoryDescription(String storyDescription) {
		this.storyDescription = storyDescription;
	}

	public String getStartingPageNo() {
		return startingPageNo;
	}

	public void setStartingPageNo(String startingPageNo) {
		this.startingPageNo = startingPageNo;
	}

	public int getStartingSanity() {
		return startingSanity;
	}

	public void setStartingSanity(int startingSanity) {
		this.startingSanity = startingSanity;
	}

	public int getStartingEmpathy() {
		return startingEmpathy;
	}

	public void setStartingEmpathy(int startingEmmapthy) {
		this.startingEmpathy = startingEmmapthy;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StorySpecification [storyName=").append(storyName).append(", storyDescription=")
				.append(storyDescription).append(", startingPageNo=").append(startingPageNo)
				.append(", startingEmmapthy=").append(startingEmpathy).append(", startingSanity=")
				.append(startingSanity).append("]");
		return builder.toString();
	}
	
	
	
	
	
	

}
