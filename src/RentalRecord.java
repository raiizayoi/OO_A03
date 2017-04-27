import java.util.List;

public class RentalRecord {
	private List<Video> rentedVideos;
	private int rentDays;
	private Customer rentCustomer;
	private float rental;
	private int remainingRentDays;
	public boolean isActive;
	public boolean returning;

	public RentalRecord(List<Video> vList, int days, Customer c, float rental)
	{
		this.isActive = true;
		this.returning = false;
		this.rentedVideos = vList;
		this.rentDays = days;
		this.remainingRentDays = days;
		this.rentCustomer = c;
		this.rental = rental;
	}

	public void decreaseRemaingRentDays() {
		if (isActive) {
			remainingRentDays--;
			if (remainingRentDays <= 0)
				returning = true;

		}
	}

	public void returnVideos(VideoRentalStore rentalStore) {
		this.returning = false;
		this.isActive = false;
		rentCustomer.setRentedCount(0);
		for (Video v : rentedVideos) {
			rentalStore.returnVideo(v);
		}
	}

	public String toString() {
		String custString = "Customer : " + rentCustomer.getName();
		String videosString = "Videos : ";
		for (Video v : rentedVideos)
			videosString += "'" + v.getVideoName() + "' ";
		String rentDayString = "Rent For " + rentDays + " days";
		String rentalString = "Cost " + rental + " dollars\n";
		return custString + ", " + videosString + ", " +rentDayString + ", " + rentalString + ".";
	}

	// *** getters & setters ***

	public List<Video> getRentedVideos() {
		return rentedVideos;
	}
}
