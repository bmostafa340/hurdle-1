
public class Runway {
	
	private int type;
	private int remTime;
	
	public Runway(int type) {
		this.type = type;
		this.remTime = 0;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRemTime() {
		return remTime;
	}

	public void setRemTime(int remTime) {
		this.remTime = remTime;
	}
	
}
