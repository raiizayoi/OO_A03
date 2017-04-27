
public class Video {
	private String name;
	private String categoryName;
	private VideoCategory category;

	public Video(String videoName, String categoryName) {
		this.name = videoName;
		this.setCategoryName(categoryName);
	}

	// *** getters & setters ***

	public String getVideoName() {
		return name;
	}

	public void setCategory(VideoCategory vc) {
		this.category = vc;
	}

	public VideoCategory getVideoCategory() {
		return category;
	}

	public float getRentPrice() {
		return category.getRentPrice();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
