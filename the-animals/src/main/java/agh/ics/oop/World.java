package agh.ics.oop;

import agh.ics.oop.model.*;

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
        WorldMap map = new RectangularMap(5, 5);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
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
