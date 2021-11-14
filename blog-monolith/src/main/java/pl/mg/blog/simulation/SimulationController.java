package pl.mg.blog.simulation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/util/simulation")
public class SimulationController {

    private final SimulationService simulationService;


    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Boolean> startSimulation(@RequestBody StartSimulationCommand command) throws SimulationAlreadyStartedException {
        simulationService.startSimulation(command);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Boolean> startSimulation() throws SimulationAlreadyStoppedException {
        simulationService.stopSimulation();
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
