import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		int sum = 0;
		int d = 0;
		for (d = 0; d < simulateDays; d++) {
			rentStore.passOneDay();
			System.out.print("Day[" + d + "] StoreOpenVideosCount : " + rentStore.getVideosInStoreCount() + " ");
			if (rentStore.getVideosInStore().size() <= 0) {
				System.out.println();
				continue;
			}
			List<Customer> legalCustomers = getLegalCustomers();
			Random ran = new Random();
			int todaysCustomerCount = ran.nextInt(legalCustomers.size()) + 1;
			int i;
			for (i = 0; i < todaysCustomerCount; i++) {
				Customer c = pickRandomCostomer(legalCustomers);
				if (rentStore.getVideosInStore().size() <= 0)
					break;
				c.rentVideosFromStore(rentStore);
				legalCustomers.remove(c);
			}
			System.out.println("CustomerCount : " + i);
			sum += i;
			legalCustomers = null;			
		}
		System.out.println(sum + " RentalRecords in " + d + " days.");
	}

	private Customer pickRandomCostomer(List<Customer> custList) {
		Random ran = new Random();
		int index = ran.nextInt(custList.size());
		Customer pickedCust = custList.get(index);
		return pickedCust;
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
