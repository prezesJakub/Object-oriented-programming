package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;
    private final WorldMap map;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection> moves, WorldMap map) {
        this.animals = new ArrayList<>();
        this.moves = moves;
        this.map = map;
        for (Vector2d position : startingPositions) {
            try {
                Animal animal = new Animal(position);
                map.place(animal);
                animals.add(animal);
            } catch (IncorrectPositionException e) {
                System.out.println("Error: " + e.getMessage());
            }


        }
    }
    public List<Animal> getAnimals() {
        return this.animals;
    }

    public void run() {
        int animalCount = animals.size();
        for (int i = 0; i < moves.size(); i++) {
            int whichAnimal = i%animalCount;
            Animal currentAnimal = animals.get(whichAnimal);
            MoveDirection currentMove = moves.get(i);
            map.move(currentAnimal, currentMove);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
