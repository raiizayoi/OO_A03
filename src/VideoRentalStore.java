import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VideoRentalStore {

	// map videoCategories by category names
	private Map<String, VideoCategory> videoCategories;
	private List<Video> videoCatalog;
	private List<Video> videosInStore;
	// map rentalRecords by customer names
	private List<RentalRecord> rentalRecords;
	private int custMaxRentDay;
	private int custMaxRentCount;
	private float turnover;

	public VideoRentalStore(int maxRentDay, int maxRentCount, String categoryXMLPath, String videoXMLPath) {
		this.setCustMaxRentDay(maxRentDay);
		this.setCustMaxRentCount(maxRentCount);
		this.loadCategoriesXml(categoryXMLPath);
		this.loadVideosXml(videoXMLPath);
		this.setVideoCategory();
		this.setTurnover(0);
		videosInStore = new ArrayList<Video>(videoCatalog);
		rentalRecords = new ArrayList<RentalRecord>();
	}

	public void loadCategoriesXml(String filePath) {
		videoCategories = XMLReader.loadCategoriesXml(filePath);
		System.out.println(videoCategories.size() + " Video Categories were loaded!");
	}

	public void loadVideosXml(String filePath) {
		videoCatalog = XMLReader.loadVideosXml(filePath);
		System.out.println(videoCatalog.size() + " Videos were loaded!");
	}

	private void setVideoCategory() {
		for (int i = 0; i < videoCatalog.size(); i++) {
			Video v = videoCatalog.get(i);
			VideoCategory vc = videoCategories.get(v.getCategoryName());
			if (vc != null)
				v.setCategory(vc);
		}
	}

	public void rentVideos(List<Video> vList, int days, Customer c) {
		float rental = 0;
		for (Video v : vList) {
			videosInStore.remove(v);
			rental += countRental(v, days);
		}
		recordRental(vList, days, c, rental);
		setTurnover(getTurnover() + rental);
	}

	public void returnVideo(Video v) {
		videosInStore.add(v);
	}

	private void recordRental(List<Video> vList, int days, Customer c, float rental) {
		RentalRecord newRecord = new RentalRecord(vList, days, c, rental);
		rentalRecords.add(newRecord);
	}

	public float countRental(Video v, int days) {
		return v.getRentPrice() * (float) days;
	}

	public void passOneDay() {

		for (RentalRecord rr : rentalRecords) {
			rr.decreaseRemaingRentDays();
			if (rr.returning)
				rr.returnVideos(this);
		}
	}

	// *** getters & setters ***

	public List<Video> getVideosInStore() {
		return videosInStore;
	}

	public int getCustMaxRentCount() {
		return custMaxRentCount;
	}

	public void setCustMaxRentCount(int custMaxRentCount) {
		this.custMaxRentCount = custMaxRentCount;
	}

	public int getCustMaxRentDay() {
		return custMaxRentDay;
	}

	public void setCustMaxRentDay(int custMaxRentDay) {
		this.custMaxRentDay = custMaxRentDay;
	}

	public float getTurnover() {
		return turnover;
	}

	public void setTurnover(float turnover) {
		this.turnover = turnover;
	}

	public List<RentalRecord> getRentalRecords() {
		return rentalRecords;
	}

	public int getVideosInStoreCount() {
		return videosInStore.size();
	}

	public List<RentalRecord> getCompletedRentalRecords() {
		List<RentalRecord> completeRR = new ArrayList<RentalRecord>();
		for (int i = 0; i < rentalRecords.size(); i++) {
			RentalRecord temp = rentalRecords.get(i);
			if (!temp.isActive)
				completeRR.add(temp);
		}
		return completeRR;
	}

	public List<RentalRecord> getActiveRentalRecords() {
		List<RentalRecord> activeRR = new ArrayList<RentalRecord>();
		for (int i = 0; i < rentalRecords.size(); i++) {
			RentalRecord temp = rentalRecords.get(i);
			if (temp.isActive)
				activeRR.add(temp);
		}
		return activeRR;
	}

}
