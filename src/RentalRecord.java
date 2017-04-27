import java.util.List;

public class RentalRecord {
	private List<Video> rentedVideos;
	private int rentDays;
	private Customer rentCustomer;
	private float rental;
	private int remainingRentDays;
	public boolean isActive;

	public void decreaseRemaingRentDays() {
		if (isActive) {
			remainingRentDays--;
			if (remainingRentDays < 0)
				isActive = false;
		}

	}

	public String toString()
	{
		String custString = "Customer : " + rentCustomer.getName();
		String videosString = "Videos : ";
		for (int i = 0 ;i < rentedVideos.size() ; i++)
			videosString += rentedVideos.get(i).getVideoName() + " ";
		String rentDayString = "Rent For " + rentDays +"days";
		String rentalString = "Cost " + rental + " dollars\n";
		return custString + " " + videosString + rentDayString + " " + rentalString;
	}

	// *** getters & setters ***

	public List<Video> getRentedVideos() {
		return rentedVideos;
	}
}
