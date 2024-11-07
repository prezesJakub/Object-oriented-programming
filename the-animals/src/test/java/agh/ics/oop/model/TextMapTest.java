package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextMapTest {
    @Test
    void moveTest() {
        //given
        TextMap map = new TextMap();
        map.place("Ela");
        map.place("ma");
        map.place("krowogeparda");

        //when
        map.move("ma", MoveDirection.BACKWARD);
        map.move("Ela", MoveDirection.FORWARD);

        //then
        assertEquals("[ma, krowogeparda, Ela]", map.toString());
    }

    @Test
    void swapTest() {
        //given
        TextMap map = new TextMap();
        map.place("Krzysiek");
        map.place("ma");
        map.place("koniopsa");
        map.place("a");
        map.place("Wojtek");
        map.place("nie");

        //when
        map.swap(1, 5);
        map.swap(2, 4);

        //then
        assertEquals("[Krzysiek, nie, Wojtek, a, koniopsa, ma]", map.toString());
    }

    @Test
    void isOccupiedTest() {
        //given
        TextMap map = new TextMap();
        map.place("Kotek");
        map.place("Mariusz");

        //then
        assertTrue(map.isOccupied(1));
        assertFalse(map.isOccupied(2));
    }

    @Test
    void objectAtTest() {
        //given
        TextMap map = new TextMap();
        map.place("Pies");
        map.place("Leopold");

        //then
        assertEquals("Pies", map.objectAt(0));
        assertEquals("Leopold", map.objectAt(1));
        assertNull(map.objectAt(2));
    }
    @Test
    void canMoveTest() {
        //given
        TextMap map = new TextMap();
        map.place("Krowa");
        map.place("Leokadia");

        //then
        assertTrue(map.canMoveTo(1));
        assertFalse(map.canMoveTo(2));
    }
}