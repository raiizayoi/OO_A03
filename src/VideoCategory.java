
public class VideoCategory {
	private float rentPrice;
	private String name;

	public VideoCategory(String categoryName, float rentPrice) {
		this.rentPrice = rentPrice;
		this.name = categoryName;
	}

	// *** getters & setters ***

	public float getRentPrice() {
		return rentPrice;
	}

	public String getName() {
		return name;
	}
}
