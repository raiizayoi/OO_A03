import java.util.Random;

public class RegularRent extends RentBehavior {

	@Override
	public int getRentDays() {
		Random ran = new Random();
		// random rent for 3 to 5 nights
		return ran.nextInt(3) + 3;
	}

	@Override
	public int getRentVideoCount() {
		Random ran = new Random();
		// random rent 1 to 3 videos
		return ran.nextInt(3) + 1;
	}

	@Override
	int getMinRentVideoCount() {
		return 1;
	}

}
