package pl.mg.blog.simulation.events;

public enum SimulationEvent {

    ADD_POST(1),
    ADD_COMMENT(2),
    LIKE_COMMENT(3),
    DISLIKE_COMMENT(4);

    private final int id;

    SimulationEvent(int i) {
        this.id = i;
    }

    public int getId() {
        return id;
    }
}
