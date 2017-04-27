public abstract class RentBehavior {
	/*int pickVideo(int videosCoubt)
	{
		//pick a video randomly
		Random ran = new Random();
		int index = ran.nextInt(videosCoubt);
		return index;
	}*/
	abstract int getRentDays();
	abstract int getRentVideoCount();
	//the minimum number of video will be rented
	abstract int getMinRentVideoCount();
}
