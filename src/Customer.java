import java.util.ArrayList;
import java.util.List;

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

		int rentableCount = Math.min(rt.getCustMaxRentCount() - rentedVideoCount, rt.getVideosInStoreCount());
		int rentCount = rentbehavior.getRentVideoCount();
		if (rentCount > rentableCount)
			rentCount = rentableCount;
		int days = rentbehavior.getRentDays();
		//System.out.println(rentCount);
		List<Video> videosToBeRented = new ArrayList<Video>();
		for (int i = 0; i < rentCount; i++) {
			Video v = rt.getVideosInStore().get(i);
			videosToBeRented.add(v);
			//System.out.println("***" + v.getVideoName() + "***");
			rentedVideoCount++;
		}
		rt.rentVideos(videosToBeRented, days, this);
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
