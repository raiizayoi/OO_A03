import java.beans.Customizer;
import java.util.List;
import java.util.Map;

public class VideoRentalStore {

	// map videoCategories by category names
	Map<String, VideoCategory> videoCategories;
	List<Video> videoCatalog;
	List<Video> videosInStore;
	// map rentalRecords by customer names
	List<RentalRecord> rentalRecords;
	private int custMaxRentDay;
	private int custMaxRentCount;
	private float turnover;

	public VideoRentalStore(int maxRentDay, int maxRentCount, String categoryXMLPath, String videoXMLPath) {
		this.setCustMaxRentDay(maxRentDay);
		this.setCustMaxRentCount(maxRentCount);
		this.loadCategoriesXml(categoryXMLPath);
		this.loadVideosXml(videoXMLPath);
		this.setVideoCategory();

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

	public void rentOutVideos(Video v, int days, Customer c)
	{

	}
	private void recordRental(Video v, int days, Customer c, float rental)
	{
		RentalRecord newRecord = new RentalRecord();
		rentalRecords.add(newRecord);
	}
	public float countRental(Video v, int days)
	{
		return v.getRentPrice() * (float)days;
	}

	// *** getters & setters ***

	public List<Video> getVideosInStore()
	{
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

}
