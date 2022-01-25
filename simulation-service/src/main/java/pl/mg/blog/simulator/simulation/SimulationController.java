package pl.mg.blog.simulator.simulation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/util/simulation")
public class SimulationController {

    private final SimulationService simulationService;


    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Boolean> stopSimulation(@RequestBody StartSimulationCommand command) throws SimulationAlreadyStartedException {
        simulationService.startSimulation(command);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Boolean> stopSimulation() throws SimulationAlreadyStoppedException {
        simulationService.stopSimulation();
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
