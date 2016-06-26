package classes;

public class Achievement {
	private String name;
	private String pictureURL;
	private String description; 
	private String unlockedDescription;
	
	private Achievement(String name, String pictureURL, String description, String unlockedDescription)
	{
		setName(name);
		setPictureURL(pictureURL);
		setDescription(description);
		setUnlockedDescription(unlockedDescription);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureURL() {
		return pictureURL;
	}

	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnlockedDescription() {
		return unlockedDescription;
	}

	public void setUnlockedDescription(String unlockedDescription) {
		this.unlockedDescription = unlockedDescription;
	}
}
