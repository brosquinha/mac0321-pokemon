package events;

class EventSet {
	private Event[] events = new Event[100];
	private int index = 0;
	private int next = 0;
	public void add(Event e) {
	if(index >= events.length)
		return; // (In real life, throw exception)
		events[index++] = e;
	}
	public Event getNext() {
		boolean looped = false;
		int start = next;
		do {
			next = (next + 1) % events.length;
			// See if it has looped to the beginning:
			if(start == next) looped = true;
			// If it loops past start, the list
			// is empty:
			if((next == (start + 1) % events.length)
			&& looped)
			return null;
		} while(events[next] == null);
		return events[next];
	}
	public void removeCurrent() {
		events[next] = null;
	}
}

public class Controller {
	private EventSet es1 = new EventSet(), es2 = new EventSet();
	public void addEvent(Event c) { es1.add(c); }
	public void run() {
		Event e1, e2;
		while(((e1 = es1.getNext()) != null) && ((e2 = es2.getNext()) != null) ) {
			if(e1.getPrioridade() >= e2.getPrioridade()) {
				e1.action();
				System.out.println(e1.description());
				es1.removeCurrent();
				if (es1.getNext() == null) break; // a rodada pode terminar no meio do loop
				e2.action();
				System.out.println(e2.description());
				es2.removeCurrent();
			}
		}
	}
}