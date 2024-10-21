package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {

    public static void main(String[] args) {
        System.out.println("System wystartował");
        MoveDirection[] directionList = OptionsParser.parse(args);
        run(directionList);
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
