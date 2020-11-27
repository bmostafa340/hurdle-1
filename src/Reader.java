import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	
	File inputText;
	ArrayList<Flight> flights;
	
	/*
	 * Reads the input file and initializes a list of flights based on the
	 * file contents.
	 */
	public Reader(String fileName) {
		this.inputText = new File(fileName);
		flights = new ArrayList<Flight>();
		try {
			Scanner sc = new Scanner(inputText);
			while (sc.hasNext()) {
				int arrivalOrDeparture = sc.next().equals("Arrival") ? 1 : 2;
				int runWayType = arrivalOrDeparture == 1 ? 1 : 2;
				int effectiveEarliestTime = (int)(sc.nextDouble() * 1000) * 100;
				int scheduledTime = (int)(sc.nextDouble() * 1000) * 100;
				flights.add(new Flight(arrivalOrDeparture, runWayType, 4500, effectiveEarliestTime, scheduledTime));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Flight> getFlights() {
		return flights;
	}
	
}
