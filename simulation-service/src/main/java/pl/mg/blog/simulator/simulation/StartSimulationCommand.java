package pl.mg.blog.simulator.simulation;

import lombok.Value;

@Value
public class StartSimulationCommand {

    int numberOfUsers;

    int numberOfThreads;

}
