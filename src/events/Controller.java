package events;

class EventSet {
    private Event[] events;
    private int index = 0;
    private int next = 0;
    public EventSet() {
        events = new Event[100];
    }

    public void add(Event e) {
        if (index >= events.length)
            return;
        events[index++] = e;
    }

    public Event getNext() {
        boolean looped = false;
        int start = next;
        do {
            next = (next + 1) % events.length;
            if (start == next) looped = true;
            if ((next == (start + 1) % events.length)
                    && looped)
                return null;
        } while (events[next] == null);
        return events[next];
    }

    public void removeCurrent() {
        events[next] = null;
    }
}

public class Controller {
    private EventSet es1 = new EventSet(), es2 = new EventSet();

    public void addEvent(int trainerID, Event c) {
        if (trainerID == 0) es1.add(c);
        else if (trainerID == 1) es2.add(c);
        else return;
    }

    public void run() {
        Event e1, e2;
        while (((e1 = es1.getNext()) != null) && ((e2 = es2.getNext()) != null)) {
            if (e1.getPriority() >= e2.getPriority()) {
                //System.out.println("es1: " + es1);
                e1.action();
                es1.removeCurrent();
                if (es1.getNext() == null) break; // a rodada pode terminar no meio do loop
                //System.out.println("es2: " + es2);
                e2.action();
                es2.removeCurrent();
            } else {
                //System.out.println("es2: " + es2);
                e2.action();
                es2.removeCurrent();
                if (es2.getNext() == null) break;
                //System.out.println("es1: " + es1);
                e1.action();
                es1.removeCurrent();
            }
        }
    }
}