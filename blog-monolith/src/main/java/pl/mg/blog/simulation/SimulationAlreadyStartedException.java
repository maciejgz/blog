package pl.mg.blog.simulation;

public class SimulationAlreadyStartedException extends Exception {
    public SimulationAlreadyStartedException() {
        super();
    }

    public SimulationAlreadyStartedException(String message) {
        super(message);
    }

    public SimulationAlreadyStartedException(String message, Throwable cause) {
        super(message, cause);
    }
}
