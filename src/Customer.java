
public class Customer {
	private String name;
	private String typeName;
	private int rentedVideoCount;
	private RentBehavior rentbehavior;

	public Customer(String custName, String custType) {
		this.name = custName;
		this.typeName = custType;
	}

	public void rentVideosFromStore(VideoRentalStore rt) {

		int rentCount = rentbehavior.getRentVideoCount();
		int days = rentbehavior.getRentDays();
		for (int i = 0; i < rentCount; i++) {
			Video v = rentbehavior.pickVideo(rt.getVideosInStore());
			rt.rentOutVideos(v, days, this);
		}

	}

	// *** getters & setters ***

	public void setRentBehavior(RentBehavior rb) {
		rentbehavior = rb;
	}

	public int getMinRentVideoCount() {
		return rentbehavior.getMinRentVideoCount();
	}

	public String getTypeName() {
		return typeName;
	}

	public String getName() {
		return name;
	}

	public int getRentedVideoCount() {
		return rentedVideoCount;
	}
}
