package game;

import java.util.ArrayList;

public class EventList {
	private static EventList _instance = new EventList();
	
	private ArrayList<TimedEvent> event;
	
	public final static EventList getInstance(){
		return _instance;
	}
	
	public EventList() {
		event = new ArrayList<TimedEvent>();
	}
	
	public void add(TimedEvent te){
		event.add(te);
	}
			
	public int size(){
		return event.size();
		
	}
	
	public TimedEvent get(int i){
		return event.get(i);
	}
	
	public void reset(){
		event.clear();
	}
	
}
