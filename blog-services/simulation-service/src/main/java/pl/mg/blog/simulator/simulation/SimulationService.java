package pl.mg.blog.simulator.simulation;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mg.blog.simulator.commons.UserDto;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Service generating fake data for the blog app.
 */
@Service
@Slf4j
public class SimulationService {

    public static final String SIMULATION_ALREADY_STARTED_EXCEPTION_MESSAGE = "Simulation is already started. Stop it and run with new criteria.";
    public static final String SIMULATION_ALREADY_STOPPED_MESSAGE = "Simulation is already stopped.";
    private static boolean simulationStarted = false;

    private static ExecutorService executorService;

    public void startSimulation(StartSimulationCommand command) throws SimulationAlreadyStartedException {
        if (simulationStarted) {
            throw new SimulationAlreadyStartedException(SIMULATION_ALREADY_STARTED_EXCEPTION_MESSAGE);
        }
        createSimulationUsers(command.getNumberOfUsers());

        if (executorService == null || executorService.isShutdown()) {
            executorService = Executors.newFixedThreadPool(command.getNumberOfThreads());
        }

        for (int i = 0; i < command.getNumberOfThreads(); i++) {
            executorService.submit(new SimulationProcess());
        }

        log.debug("Simulation started...");
        simulationStarted = Boolean.TRUE;
    }


    public void stopSimulation() throws SimulationAlreadyStoppedException {
        if (!simulationStarted) {
            throw new SimulationAlreadyStoppedException(SIMULATION_ALREADY_STOPPED_MESSAGE);
        }

        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdownNow();
            try {
                if (!executorService.awaitTermination(15, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException ex) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        } else {
            throw new SimulationAlreadyStoppedException(SIMULATION_ALREADY_STOPPED_MESSAGE);
        }

        log.debug("Simulation stopped...");
        simulationStarted = Boolean.FALSE;
    }

    private void createSimulationUsers(int numberOfUsers) {
        Faker faker = new Faker();
        for (int i = 0; i < numberOfUsers; i++) {
            UserDto user = new UserDto(faker.name().username(), faker.funnyName().name());
            //FIXME add test users
           // UserRepository.USERS.putIfAbsent(user.getUsername(), user);
        }
        //log.debug("Users created: {}", UserRepository.USERS.size() - 2);
    }

}
