import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	
	private static final int MAJOR = 1;
	private static final int MINOR = 2;
	private static final int ARRIVAL = 1;
	private static final int DEPARTURE = 2;
	private static final int NUM_FLIGHTS = 892;
	private static final int NUM_INTERNATIONAL_FLIGHTS = 141;
	
	private static int time = 0;
	private static ArrayList<Integer>[] hourlyDelayArrivals;
	private static ArrayList<Integer>[] hourlyDelayDepartures;
	private static ArrayList<Flight> flights;
	private static Runway[] runways;
	
	public static void main(String[] args) {
		initializeLists();
		while (!flights.isEmpty()) {
			for (Runway runway: runways) {
				assignFlight(runway);
			}
			advanceTime();
		}
		display();
	}
	
	private static void initializeLists() {
		initializeFlights();
		initializeRunways();
		initializeRecords();
	}
	
	/*
	 * Fills the flights ArrayList with NUM_FLIGHTS flights randomly selected from the data file.
	 * Randomly assigns NUM_INTERNATIONAL_FLIGHTS as international flights, and sets their runway type
	 * to major, as international flights require a larger runway.
	 */
	private static void initializeFlights() {
		Reader rd = new Reader("C:\\Users\\bmost\\eclipse-workspace\\Hurdle_1\\src\\Simulation_Data.txt");
		flights = rd.getFlights();
		while (flights.size() > NUM_FLIGHTS) {
			flights.remove(ThreadLocalRandom.current().nextInt(0, flights.size()));
		}
		for (int i = 0; i < NUM_INTERNATIONAL_FLIGHTS; i++) {
			int random = ThreadLocalRandom.current().nextInt(0, flights.size());
			flights.get(random).setRunWayTime(4500);
			flights.get(random).setRunWayType(MAJOR);
		}
	}
	
	/*
	 * Initializes the runways array with one major runway and two minor runways,
	 * reflecting the state of affairs of SFO during construction.
	 */
	private static void initializeRunways() {
		runways = new Runway[3];
		for (int i = 0; i < 1; i++) {
			runways[i] = new Runway(MAJOR);
		}
		for (int i = 1; i < runways.length; i++) {
			runways[i] = new Runway(MINOR);
		}
	}
	
	/*
	 * Initializes records of delayed arrivals and delayed departures
	 * by hour.
	 */
	@SuppressWarnings("unchecked")
	private static void initializeRecords() {
		hourlyDelayArrivals = new ArrayList[24];
		hourlyDelayDepartures = new ArrayList[24];
		for (int i = 0; i < 24; i++) {
			hourlyDelayArrivals[i] = new ArrayList<Integer>();
			hourlyDelayDepartures[i] = new ArrayList<Integer>();
		}
	}
	
	/*
	 * If the runway is not occupied, removes the next compatible flight from the flights list, 
	 * updates the appropriate record,cand occupies the runway for an appropriate amount of time.
	 */
	private static void assignFlight(Runway runway) {
		if (runway.getRemTime() <= 0) {
			int i = 0;
			while (flights.size() > i && flights.get(i).getEffectiveEarliestTime() <= time) {
				Flight flight = flights.get(i);
				if (flight.getRunWayType() == runway.getType()) {
					runway.setRemTime(flight.getRunWayTime());
					int index = 0;
					if (flight.getScheduledTime() >= 2350000) {
						index = 0;
					} else {
						index = (flight.getScheduledTime() + 50000)/100000;
					}
					if (flight.getArrivalOrDeparture() == ARRIVAL) {
						hourlyDelayArrivals[index].add(time - flight.getScheduledTime());
					} else {
						hourlyDelayDepartures[index].add(time - flight.getScheduledTime());
					}
					flights.remove(flight);
					break;
				}
				i++;
			}
		}
	}
	
	/*
	 * Increments time by the minimum time required for a runway to become free.
	 * Updates the time left on each runway.
	 */
	private static void advanceTime() {
		int deltaTime = 10000000;
		if (!flights.isEmpty() && flights.get(0).getEffectiveEarliestTime() > time) {
			deltaTime = flights.get(0).getEffectiveEarliestTime() - time;
			for (Runway runway: runways) {
				runway.setRemTime(runway.getRemTime() - deltaTime);
			}
			time = flights.get(0).getEffectiveEarliestTime();
		} else {
			for (Runway runway: runways) {
				if (runway.getRemTime() < deltaTime && runway.getRemTime() > 0) {
					deltaTime = runway.getRemTime();
				}
			}
			for (Runway runway: runways) {
				runway.setRemTime(runway.getRemTime() - deltaTime);
			}
			time += deltaTime;
		}
	}
	
	/*
	 * Prints average predicted arrival and departure delays in hundredths of an hour
	 * on an hour by hour basis.
	 */
	private static void display() {
		System.out.println("Hourly Delay Arrivals");
		for (int i = 0; i < 24; i++) {
			int avgDelay = 0;
			int denom = 0;
			for (Integer delay: hourlyDelayArrivals[i]) {
				avgDelay += delay;
				denom++;
			}
			if (avgDelay != 0) {
				avgDelay /= denom;
				avgDelay /= 1000;
			}
			System.out.println(i + ": " + avgDelay);
		}
		System.out.println();
		System.out.println("Hourly Delay Departures");
		for (int i = 0; i < 24; i++) {
			int avgDelay = 0;
			int denom = 0;
			for (Integer delay: hourlyDelayDepartures[i]) {
				avgDelay += delay;
				denom++;
			}
			if (avgDelay != 0) {
				avgDelay /= denom;
				avgDelay /= 1000;
			} 
			System.out.println(i + ": " + avgDelay);
		}
	}
	
}
