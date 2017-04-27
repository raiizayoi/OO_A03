
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

	}

	// *** getters & setters ***

	public void setRentBehavior(RentBehavior rb) {
		rentbehavior = rb;
	}

	public int getRentDays() {
		return rentbehavior.getRentDays();
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
