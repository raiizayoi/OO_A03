
public class RentalRecord {
	Video rentedVideo;
	Customer rentCustomer;
	int keepNights;
	int durataion;

	public void passOneDay() {
		if (durataion > 0)
			durataion--;
	}

	public Boolean isActived() {
		return (durataion > 0);
	}
}
