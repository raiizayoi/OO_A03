public class HoarderRent extends RentBehavior {

	@Override
	public int getRentDays() {
		return 7;
	}
	@Override
	public int getRentVideoCount() {
		return 3;
	}
	@Override
	int getMinRentVideoCount() {
		return 3;
	}

}
