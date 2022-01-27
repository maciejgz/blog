package pl.mg.blog.simulator.events;

public enum SimulationEvent {

    ADD_POST(1),
    ADD_COMMENT(2),
    LIKE_COMMENT(3),
    DISLIKE_COMMENT(4),
    LIKE_POST(5),
    BLACKLIST(6),
    REMOVE_FROM_BLACKLIST(7),
    REGISTER_USER(8);

    private final int id;

    SimulationEvent(int i) {
        this.id = i;
    }

    public int getId() {
        return id;
    }
}
