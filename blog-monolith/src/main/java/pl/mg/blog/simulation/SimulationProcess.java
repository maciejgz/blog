package pl.mg.blog.simulation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.mg.blog.simulation.events.*;

import java.util.Random;

@Component
@Scope(value = "prototype")
@Slf4j
public class SimulationProcess implements Runnable {

    @Override
    public void run() {
        Random rand = new Random();
        int numberOfEvents = SimulationEvent.values().length;

        while (true) {
            int selectedEvent = rand.nextInt(numberOfEvents) + 1;
            log.debug("SELECTED EVENT: " + selectedEvent);
            SimulationEventExecutor strategy = getSimulationEventExecutor(selectedEvent);

            if (strategy != null) {
                strategy.execute();
            } else {
                log.debug("empty strategy...");
            }

            if (Thread.interrupted()) {
                log.debug("thread interrupted...");
                return;
            }
        }

    }

    private SimulationEventExecutor getSimulationEventExecutor(int selectedEvent) {
        SimulationEventExecutor strategy = null;
        if (selectedEvent == SimulationEvent.ADD_POST.getId()) {
            strategy = new AddPostEventExecutor();
        } else if (selectedEvent == SimulationEvent.ADD_COMMENT.getId()) {
            strategy = new AddCommentEventExecutor();
        } else if (selectedEvent == SimulationEvent.LIKE_COMMENT.getId()) {
            strategy = new LikeCommentEventExecutor();
        } else if (selectedEvent == SimulationEvent.DISLIKE_COMMENT.getId()) {
            strategy = new DislikeCommentEventExecutor();
        }
        return strategy;
    }
}
