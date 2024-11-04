package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> startingPositions, List<MoveDirection> moves) {
        this.animals = new ArrayList<>();
        this.moves = moves;
        for (int i = 0; i < startingPositions.size(); i++) {
            animals.add(new Animal(startingPositions.get(i)));
        }
    }
    public List<Animal> getAnimals() {
        return this.animals;
    }
    public void run() {
        int animalCount = animals.size();
        for (int i = 0; i < moves.size(); i++) {
            Animal currentAnimal = animals.get(i%animalCount);
            currentAnimal.move(moves.get(i));
            System.out.printf("Zwierzę %d: %s%n", i%animalCount, currentAnimal);
        }
    }
}
