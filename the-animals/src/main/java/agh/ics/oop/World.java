package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {

    public static void main(String[] args) {
        System.out.println("System wystartował");
        MoveDirection[] directionList = OptionsParser.parse(args);
        run(directionList);
        // Testowanie Vector2d
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        // Testowanie MapDirection
        /*
        System.out.println(MapDirection.NORTH.toString());
        System.out.println(MapDirection.WEST.toString());
        System.out.println(MapDirection.EAST.toString());
        System.out.println(MapDirection.SOUTH.toString());
        System.out.println(MapDirection.NORTH.next());
        System.out.println(MapDirection.EAST.previous());
        System.out.println(MapDirection.WEST.next());
        System.out.println(MapDirection.WEST.toUnitVector());
        System.out.println(MapDirection.SOUTH.toUnitVector());
        */
        System.out.println("System zakończył działanie");
    }
    public static void run(MoveDirection[] arguments)
    {
        for(int i=0; i<arguments.length; i++)
        {
            switch (arguments[i]) {
                case FORWARD ->
                {
                    System.out.println("Zwierzak idzie do przodu");
                    for(int j=0; j<arguments.length; j++)
                    {
                        System.out.print(arguments[j]);
                        if(j<arguments.length-1)
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
