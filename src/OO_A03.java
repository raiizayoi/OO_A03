
public class OO_A03 {

	public static void main(String[] args) {

		// Initialize remtalStore
		VideoRentalStore rentalStore = new VideoRentalStore(
				7, // maxRentDay,
				3, // maxRentCount
				"resources/VideoCategories.xml", // VideoCategoryData
				"resources/Videos.xml" // VideoData
		);

		// Initialize remtalStoreSimulator
		RentalStoreSimulator rsSimulator = new RentalStoreSimulator(
				rentalStore,
				35, //simulate days
				"resources/Customers.xml" // CustomerData
				);
		// *** Start Simulation ***
		rsSimulator.performSimulation();
		rsSimulator.printVideosInStore();
		rsSimulator.printTurnover();
		rsSimulator.printCompletedRentalRecords();
		rsSimulator.printActiveRentalRecords();

		// ***

	}

}
