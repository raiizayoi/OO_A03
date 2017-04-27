import java.util.List;
import java.util.Random;

public abstract class RentBehavior {
	Video pickVideo(List<Video> vds)
	{
		//pick a video randomly
		Random ran = new Random();
		int index = ran.nextInt(vds.size()) + 1;
		return vds.get(index);
	}
	abstract int getRentDays();
	abstract int getRentVideoCount();
	abstract int getMinRentVideoCount();
}
