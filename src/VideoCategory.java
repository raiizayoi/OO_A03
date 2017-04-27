
public class VideoCategory {
	private float rentPrice;
	private String categoryName;

	public VideoCategory(String categoryName, float rentPrice) {
		this.rentPrice = rentPrice;
		this.categoryName = categoryName;
	}

	public float getRentPrice() {
		return rentPrice;
	}

	public String getCategoryName() {
		return categoryName;
	}
}
