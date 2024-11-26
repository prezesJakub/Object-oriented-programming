package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulationList;
    private final List<Thread> threadList = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public SimulationEngine(List<Simulation> simulationList) {
        this.simulationList = simulationList;
    }

    public List<Simulation> getSimulationList() {
        return simulationList;
    }

    public List<Thread> getThreadList() {
        return threadList;
    }

    public void runSync() {
        for (Simulation simulation : simulationList) {
            simulation.run();
        }
    }
    public void runAsync() {
        for (Simulation simulation : simulationList) {
            Thread thread = new Thread(simulation);
            threadList.add(thread);
            thread.start();
        }
        awaitSimulationEnds();
    }
    public void runAsyncInThreadPool() {
        for (Simulation simulation : simulationList) {
            executorService.submit(simulation);
        }
        awaitSimulationEnds();
    }

    public void awaitSimulationEnds() {
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            executorService.shutdownNow();
        }
    }
}
