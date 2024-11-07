package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {

    public static void main(String[] args) {
        System.out.println("System wystartował");
        List<MoveDirection> directionList = OptionsParser.parse(args);
        /*
        run(directionList);
        Animal animal1 = new Animal();
        System.out.println(animal1);
        Animal animal2 = new Animal(new Vector2d(3, 1));
        System.out.println(animal2);
         */

        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        List<Animal> animals = new ArrayList<>();
        for (Vector2d position : positions) {
            animals.add(new Animal(position));
        }
        WorldMap<Animal, Vector2d> map = new RectangularMap(5, 5);
        Simulation<Animal, Vector2d> simulation = new Simulation<>(animals, directions, map);
        simulation.run();

        //Test TextMap
        List<String> textList = List.of("Ala", "ma", "sowoniedźwiedzia", "a", "Ola", "posiada", "żółwiojaszczurkę");
        WorldNumberPositionMap<String> textMap = new TextMap();
        Simulation<String, Number> textSimulation = new Simulation<>(textList, directions, textMap);
        textSimulation.run();

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
