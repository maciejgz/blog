package pl.mg.blog.simulation;

import lombok.Value;

@Value
public class StartSimulationCommand {

    private int numberOfUsers;

    private int numberOfThreads;

}
