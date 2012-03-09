package game;

public class Level {
	
	int id;
	int killOjectif;
	private int maxLvl;
	
	public Level(int id){
		set(id);
	}

	private void initLvl() {
		switch (id) {
		case 1:
			killOjectif=10;
			break;
		case 2:
			killOjectif=20;
			break;
		case 3:
			killOjectif=50;
			break;
		case 4:
			killOjectif=70;
			break;
		case 5:
			
			break;

		default:
			break;
		}
		
	}

	public void set(int id) {
		this.id = id;
		initLvl();
		
	}

	public boolean next() {
		if(id<maxLvl){
			id++;
			return false;
		}
		return true;
		
	}

	public int getLvl() {
		return id;
	}

	public int getKill() {
		return killOjectif;
	}
	
	

}
