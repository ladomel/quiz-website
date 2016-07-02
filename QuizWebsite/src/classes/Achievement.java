package classes;

public class Achievement {
	private int id;
	private String name;
	private String pictureURL;
	private String description; 
	
	private Achievement(int id, String name, String pictureURL, String description)
	{
		setName(name);
		setPictureURL(pictureURL);
		setDescription(description);
		setId(id);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
