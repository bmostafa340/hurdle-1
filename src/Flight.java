
public class Flight {
	
	private int arrivalOrDeparture;
	private int runWayType;
	private int runWayTime;
	private int effectiveEarliestTime;
	private int scheduledTime;
	
	public Flight(int arrivalOrDeparture, int runWayType, int runWayTime, int effectiveEarliestTime, int scheduledTime) {
		this.arrivalOrDeparture = arrivalOrDeparture;
		this.runWayType = runWayType;
		this.runWayTime = runWayTime;
		this.effectiveEarliestTime = effectiveEarliestTime;
		this.scheduledTime = scheduledTime;
	}
	
	public int getArrivalOrDeparture() {
		return arrivalOrDeparture;
	}

	public void setArrivalOrDeparture(int arrivalOrDeparture) {
		this.arrivalOrDeparture = arrivalOrDeparture;
	}

	public int getRunWayType() {
		return runWayType;
	}

	public void setRunWayType(int runWayType) {
		this.runWayType = runWayType;
	}

	public int getRunWayTime() {
		return runWayTime;
	}

	public void setRunWayTime(int runWayTime) {
		this.runWayTime = runWayTime;
	}

	public int getEffectiveEarliestTime() {
		return effectiveEarliestTime;
	}

	public void setEffectiveEarliestTime(int effectiveEarliestTime) {
		this.effectiveEarliestTime = effectiveEarliestTime;
	}

	public int getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(int scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	
}
