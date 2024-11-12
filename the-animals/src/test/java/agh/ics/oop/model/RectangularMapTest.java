package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    @Test
    void placeTest() {
        //given
        RectangularMap map = new RectangularMap(8, 8);
        Animal animal1 = new Animal(new Vector2d(6,6));
        Animal animal2 = new Animal(new Vector2d(6,6));
        Animal animal3 = new Animal(new Vector2d(2,1));
        Animal animal4 = new Animal(new Vector2d(9,12));

        //then
        assertTrue(map.place(animal1));
        assertFalse(map.place(animal2));
        assertTrue(map.place(animal3));
        assertFalse(map.place(animal4));
    }
    @Test
    void moveTest() {
        //given
        RectangularMap map = new RectangularMap(8, 8);
        Animal animal1 = new Animal(new Vector2d(6,7));
        Animal animal2 = new Animal(new Vector2d(3,3));
        Animal animal3 = new Animal(new Vector2d(2,3));
        Animal animal4 = new Animal(new Vector2d(3,0));
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        map.place(animal4);

        //when
        map.move(animal3, MoveDirection.RIGHT);
        map.move(animal3, MoveDirection.FORWARD);
        map.move(animal1, MoveDirection.BACKWARD);
        map.move(animal2, MoveDirection.FORWARD);
        map.move(animal4, MoveDirection.BACKWARD);

        //then
        assertEquals(new Vector2d(6,6), animal1.getPosition());
        assertEquals(new Vector2d(3,4), animal2.getPosition());
        assertEquals(new Vector2d(2,3), animal3.getPosition());
        assertEquals(new Vector2d(3,0), animal4.getPosition());
    }

    @Test
    void isOccupiedTest() {
        //given
        RectangularMap map = new RectangularMap(5, 6);
        Animal animal1 = new Animal(new Vector2d(3,4));
        Animal animal2 = new Animal(new Vector2d(5,3));
        map.place(animal1);
        map.place(animal2);

        //then
        assertTrue(map.isOccupied(new Vector2d(3,4)));
        assertFalse(map.isOccupied(new Vector2d(5,3)));
        assertFalse(map.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void objectAtTest() {
        RectangularMap map = new RectangularMap(7, 10);
        Animal animal1 = new Animal(new Vector2d(3,4));
        Animal animal2 = new Animal(new Vector2d(5,3));
        map.place(animal1);
        map.place(animal2);

        //then
        assertEquals(animal1, map.objectAt(new Vector2d(3,4)));
        assertEquals(animal2, map.objectAt(new Vector2d(5,3)));
        assertEquals(null, map.objectAt(new Vector2d(1,1)));
    }
    @Test
    void canMoveToTestWithOneAnimal() {
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal1 = new Animal(new Vector2d(1,3));
        map.place(animal1);

        //then
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
        assertFalse(map.canMoveTo(new Vector2d(1,4)));
        assertFalse(map.canMoveTo(new Vector2d(1,3)));
        assertTrue(map.canMoveTo(new Vector2d(0,1)));
    }
    @Test
    void canMoveToTestWithMoreAnimals() {
        RectangularMap map = new RectangularMap(7, 7);
        Animal animal1 = new Animal(new Vector2d(3,4));
        Animal animal2 = new Animal(new Vector2d(5,3));
        map.place(animal1);
        map.place(animal2);

        //then
        assertFalse(map.canMoveTo(new Vector2d(3,4)));
        assertFalse(map.canMoveTo(new Vector2d(5,3)));
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
        assertFalse(map.canMoveTo(new Vector2d(3,9)));
    }
    @Test
    void rotateOnlyTest() {
        //given
        RectangularMap map = new RectangularMap(4, 4);
        Animal animal1 = new Animal(new Vector2d(1,2));
        map.place(animal1);

        //when
        map.move(animal1, MoveDirection.RIGHT);

        //then
        assertEquals(new Vector2d(1,2), animal1.getPosition());
        assertEquals(MapDirection.EAST, animal1.getOrientation());
    }
}