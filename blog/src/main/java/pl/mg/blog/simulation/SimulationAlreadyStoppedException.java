package pl.mg.blog.simulation;

public class SimulationAlreadyStoppedException extends Exception {
    public SimulationAlreadyStoppedException() {
        super();
    }

    public SimulationAlreadyStoppedException(String message) {
        super(message);
    }

    public SimulationAlreadyStoppedException(String message, Throwable cause) {
        super(message, cause);
    }
}
