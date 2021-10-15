package pl.mg.blog.simulator.simulation;

import lombok.Value;

@Value
public class StartSimulationCommand {

    private int numberOfUsers;

    private int numberOfThreads;

}
