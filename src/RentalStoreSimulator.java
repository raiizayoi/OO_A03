import java.util.ArrayList;
import java.util.List;

public class RentalStoreSimulator {
	private int simulateDays = 0;
	private VideoRentalStore rentStore;
	private List<Customer> customers;
	private RentBehavior[] rentBehaviors;

	public RentalStoreSimulator(VideoRentalStore vrs, int simDays, String custXMLPath) {
		rentStore = vrs;
		this.setSimulateDays(simDays);
		this.loadCustomersXml(custXMLPath);
		this.initializeRentBehaviors();
		this.setCustomerBehavior();
	}

	// Load Customer Data
	public void loadCustomersXml(String filePath) {
		customers = XMLReader.loadCustomersXml(filePath);
		System.out.println(customers.size() + " Customers were loaded!");

	}

	private void initializeRentBehaviors() {
		rentBehaviors = new RentBehavior[3];
		rentBehaviors[0] = new BreezyRent();
		rentBehaviors[1] = new HoarderRent();
		rentBehaviors[2] = new RegularRent();
	}

	private void setCustomerBehavior() {
		for (int i = 0; i < customers.size(); i++) {
			Customer c = customers.get(i);
			// set default to regular
			int behIndex = 2;
			switch (c.getTypeName()) {
			case "breezy": {
				behIndex = 0;
				break;
			}
			case "hoarder": {
				behIndex = 1;
				break;
			}
			case "regular":
			default: {
				behIndex = 2;
				break;
			}
			}
			c.setRentBehavior(rentBehaviors[behIndex]);
		}
	}

	public void performSimulation() {
		for (int d = 0; d <  10/*simulateDays*/; d++) {
			/*if (rentStore.getVideosInStore().size() <= 0)
				continue;*/
			System.out.print(rentStore.getVideosInStoreCount() + " ");
			List<Customer> legalCustomers = getLegalCustomers();
			int i ;
			for (i = 0 ; i < legalCustomers.size() ; i++) {
				Customer c = legalCustomers.get(i);
				if (rentStore.getVideosInStore().size() <= 0)
					break;
				c.rentVideosFromStore(rentStore);
			}
			System.out.println(i);
			legalCustomers = null;
			// customers.get(3).rentVideosFromStore(rentStore);
			rentStore.passOneDay();
		}
	}

	private List<Customer> getLegalCustomers() {
		List<Customer> legalCustomers = new ArrayList<Customer>();
		for (Customer c : customers) {
			if (c.getRentedVideoCount() < rentStore.getCustMaxRentCount()
					&& c.getMinRentVideoCount() <= rentStore.getVideosInStore().size()) {
				legalCustomers.add(c);
			}
		}
		return legalCustomers;
	}

	public void printVideosInStore() {
		List<Video> vList = rentStore.getVideosInStore();
		System.out.println("Videos in the store (" + vList.size() + ") : ");
		for (int i = 0; i < vList.size(); i++) {
			System.out.println("\t - " + vList.get(i).getVideoName());
		}
	}

	public void printTurnover() {
		System.out.println("Turnover : " + rentStore.getTurnover());
	}

	public void printCompletedRentalRecords() {
		List<RentalRecord> completeRRList = rentStore.getCompletedRentalRecords();
		System.out.println("Completed Rentals (" + completeRRList.size() + ") : ");
		for (RentalRecord rr : completeRRList)
			System.out.println("\t - " + rr.toString());
	}

	public void printActiveRentalRecords() {
		List<RentalRecord> activeRRList = rentStore.getActiveRentalRecords();
		System.out.println("Active Rentals (" + activeRRList.size() + ") : ");
		for (RentalRecord rr : activeRRList)
			System.out.println("\t - " + rr.toString());
	}

	// *** getters & setters ***

	public int getSimulateDays() {
		return simulateDays;
	}

	public void setSimulateDays(int simulateDays) {
		this.simulateDays = simulateDays;
	}

}
