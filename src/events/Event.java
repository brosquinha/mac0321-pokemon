package events;

abstract public class Event {
    private int prioridade;

    public Event(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getPrioridade() {
        return this.prioridade;
    }

    abstract public void action();

    abstract public String description();
}
