package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void placeTest() {
        //given
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(6,6));
        Animal animal2 = new Animal(new Vector2d(6,6));
        Animal animal3 = new Animal(new Vector2d(2,1));
        Animal animal4 = new Animal(new Vector2d(9,12));

        //then
        assertDoesNotThrow(() -> map.place(animal1));
        assertThrows(IncorrectPositionException.class, () -> map.place(animal2));
        assertDoesNotThrow(() -> map.place(animal3));
        assertDoesNotThrow(() -> map.place(animal4));
    }

    @Test
    void moveTest() throws IncorrectPositionException {
        //given
        WorldMap map = new GrassField(10);
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
        assertEquals(new Vector2d(3,-1), animal4.getPosition());
    }

    @Test
    void isOccupiedTest() throws IncorrectPositionException{
        //given
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(3,4));
        Animal animal2 = new Animal(new Vector2d(5,3));
        map.place(animal1);
        map.place(animal2);

        //then
        assertTrue(map.isOccupied(new Vector2d(3,4)));
        assertTrue(map.isOccupied(new Vector2d(5,3)));
        assertFalse(map.isOccupied(new Vector2d(2,2)));
    }

    @Test
    void objectAtTest() throws IncorrectPositionException{
        //given
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(3,4));
        Animal animal2 = new Animal(new Vector2d(5,3));
        map.place(animal1);
        map.place(animal2);

        //then
        assertTrue(map.objectAt(new Vector2d(3, 4)).isPresent());
        assertEquals(animal1, map.objectAt(new Vector2d(3,4)).get());
        assertTrue(map.objectAt(new Vector2d(5, 3)).isPresent());
        assertEquals(animal2, map.objectAt(new Vector2d(5,3)).get());
        assertFalse(map.objectAt(new Vector2d(1, 1)).isPresent());
    }

    @Test
    void canMoveTest() throws IncorrectPositionException{
        //given
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(3,4));
        Animal animal2 = new Animal(new Vector2d(5,3));
        map.place(animal1);
        map.place(animal2);

        //then
        assertFalse(map.canMoveTo(new Vector2d(3,4)));
        assertFalse(map.canMoveTo(new Vector2d(5,3)));
        assertTrue(map.canMoveTo(new Vector2d(2,3)));
        assertTrue(map.canMoveTo(new Vector2d(3,9)));
    }

    @Test
    void rotateOnlyTest() throws IncorrectPositionException{
        //given
        WorldMap map = new GrassField(8);
        Animal animal1 = new Animal(new Vector2d(1,2));
        map.place(animal1);

        //when
        map.move(animal1, MoveDirection.RIGHT);

        //then
        assertEquals(new Vector2d(1,2), animal1.getPosition());
        assertEquals(MapDirection.EAST, animal1.getOrientation());
    }
    @Test
    void getElementsTest() throws IncorrectPositionException{
        //given
        WorldMap map = new GrassField(14);
        Animal animal1 = new Animal(new Vector2d(1,2));
        Animal animal2 = new Animal(new Vector2d(3,1));
        map.place(animal1);
        map.place(animal2);

        //then
        assertEquals(16, map.getElements().size());
        assertEquals(animal2, map.getElements().get(1));
    }
}