package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {

    public static void main(String[] args) {
        System.out.println("System wystartował");

        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
            /*
            AbstractWorldMap map = new GrassField(10);
            AbstractWorldMap map2 = new RectangularMap(7, 9);
*/
            ConsoleMapDisplay display = new ConsoleMapDisplay();
           /*
            map.addObserver(display);
            map2.addObserver(display);

            Simulation simulation = new Simulation(positions, directions, map);
            Simulation simulation2 = new Simulation(positions, directions, map2);

            SimulationEngine engine = new SimulationEngine(List.of(simulation, simulation2));
           // engine.runSync();
            engine.runAsync();
            engine.awaitSimulationEnds();
*/
            List<Simulation> simulations = new ArrayList<>();
            for (int i=0; i<1000; i++) {
                AbstractWorldMap map = (i%2 == 0)
                        ? new GrassField(10)
                        : new RectangularMap(10, 10);
                map.addObserver(display);
                simulations.add(new Simulation(positions, directions, map));
            }
            SimulationEngine engine2 = new SimulationEngine(simulations);
            engine2.runAsyncInThreadPool();
            engine2.awaitSimulationEnds();

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println("System zakończył działanie");
    }
    public static void run(List<MoveDirection> arguments)
    {
        for(int i=0;i<arguments.size();i++)
        {
            switch (arguments.get(i)) {
                case FORWARD ->
                {
                    System.out.println("Zwierzak idzie do przodu");
                    for(int j=0; j<arguments.size(); j++)
                    {
                        System.out.print(arguments.get(j));
                        if(j<arguments.size() -1)
                            System.out.print(", ");
                        else
                            System.out.println();
                    }
                }
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
        }

        }

    }
}
