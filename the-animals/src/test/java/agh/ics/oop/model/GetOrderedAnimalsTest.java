package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetOrderedAnimalsTest {
    @Test
    void getOrderedAnimalsTest1() throws IncorrectPositionException {
        //given
        AbstractWorldMap map = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(2, 5));
        Animal animal2 = new Animal(new Vector2d(3, 6));
        Animal animal3 = new Animal(new Vector2d(4, 7));

        map.place(animal1);
        map.place(animal2);
        map.place(animal3);

        //when
        List<Animal> orderedAnimals = map.getOrderedAnimals();

        //then
        assertEquals(animal1, orderedAnimals.get(0));
        assertEquals(animal2, orderedAnimals.get(1));
        assertEquals(animal3, orderedAnimals.get(2));
    }
    @Test
    void getOrderedAnimalsTest2() throws IncorrectPositionException {
        //given
        AbstractWorldMap map = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(4, 3));
        Animal animal2 = new Animal(new Vector2d(1, 3));
        Animal animal3 = new Animal(new Vector2d(1, 7));

        map.place(animal1);
        map.place(animal2);
        map.place(animal3);

        //when
        List<Animal> orderedAnimals = map.getOrderedAnimals();

        //then
        assertEquals(animal2, orderedAnimals.get(0));
        assertEquals(animal3, orderedAnimals.get(1));
        assertEquals(animal1, orderedAnimals.get(2));
    }
    @Test
    void getOrderedAnimalsTest3() throws IncorrectPositionException {
        //given
        AbstractWorldMap map = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(2, 3));
        Animal animal2 = new Animal(new Vector2d(2, 2));
        Animal animal3 = new Animal(new Vector2d(2, 7));

        map.place(animal1);
        map.place(animal2);
        map.place(animal3);

        //when
        List<Animal> orderedAnimals = map.getOrderedAnimals();

        //then
        assertEquals(animal2, orderedAnimals.get(0));
        assertEquals(animal1, orderedAnimals.get(1));
        assertEquals(animal3, orderedAnimals.get(2));
    }
}
