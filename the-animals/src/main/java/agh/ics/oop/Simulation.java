package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation<T, P> {
    private final List<T> animals;
    private final List<MoveDirection> moves;
    private final WorldMap<T, P> map;

    public Simulation(List<T> animals, List<MoveDirection> moves, WorldMap<T, P> map) {
        this.animals = new ArrayList<>();
        this.moves = moves;
        this.map = map;

        for (T object : animals) {
            if (map.place(object)) {
                this.animals.add(object);
            }
        }
    }
    public List<T> getAnimals() {
        return this.animals;
    }
    public void run() {
        int animalCount = animals.size();
        for (int i = 0; i < moves.size(); i++) {
            int whichAnimal = i%animalCount;
            T currentAnimal = animals.get(whichAnimal);
            MoveDirection currentMove = moves.get(i);
            map.move(currentAnimal, currentMove);
            System.out.println(map.toString());
        }
    }
}
