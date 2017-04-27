import java.util.Random;

public class BreezyRent extends RentBehavior {

	@Override
	public int getRentDays() {
		Random ran = new Random();
		// random rent for 1 or 2 nights
		return ran.nextInt(2) + 1;
	}

	@Override
	public int getRentVideoCount() {
		Random ran = new Random();
		// random rent 1 or 2 videos
		return ran.nextInt(2) + 1;
	}

	@Override
	int getMinRentVideoCount() {
		//
		return 1;
	}

}
