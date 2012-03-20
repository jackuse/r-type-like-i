package game;

public class TimedEvent {
	private int time;
	private int delay;
	private int quantity;
	private int behavior;
	private int spawnX;
	private int spawnY;
	private int id;
	private int nextSpawnTime;
	private boolean enabled;
	
	public TimedEvent(int t,  int q, int d,int b, int x, int y,int i)
	{
		setTime(t);
		setQuantity(q);
		setDelay(d);
		setBehavior(b);
		setSpawnX(x);
		setSpawnY(y);
		setId(i);
		setEnabled(true);
		
		nextSpawnTime=t;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBehavior() {
		return behavior;
	}

	public void setBehavior(int behavior) {
		this.behavior = behavior;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getNextSpawnTime() {
		return nextSpawnTime;
	}

	public void setNextSpawnTime(int nextSpawnTime) {
		this.nextSpawnTime = nextSpawnTime;
	}
}
