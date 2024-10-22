package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void startDirectionIsSouth() {
        //given
        MapDirection direction = MapDirection.SOUTH;

        //then
        assertEquals(MapDirection.WEST, direction.next());
        assertEquals(MapDirection.EAST, direction.previous());
        assertEquals("Południe", direction.toString());
        assertEquals(new Vector2d(0, -1), direction.toUnitVector());
    }

    @Test
    void startDirectionIsWest() {
        //given
        MapDirection direction = MapDirection.WEST;

        //then
        assertEquals(MapDirection.NORTH, direction.next());
        assertEquals(MapDirection.SOUTH, direction.previous());
        assertEquals("Zachód", direction.toString());
        assertEquals(new Vector2d(-1, 0), direction.toUnitVector());
    }

    @Test
    void startDirectionIsNorth() {
        //given
        MapDirection direction = MapDirection.NORTH;

        //then
        assertEquals(MapDirection.EAST, direction.next());
        assertEquals(MapDirection.WEST, direction.previous());
        assertEquals("Północ", direction.toString());
        assertEquals(new Vector2d(0, 1), direction.toUnitVector());
    }

    @Test
    void startDirectionIsEast() {
        //given
        MapDirection direction = MapDirection.EAST;

        //then
        assertEquals(MapDirection.SOUTH, direction.next());
        assertEquals(MapDirection.NORTH, direction.previous());
        assertEquals("Wschód", direction.toString());
        assertEquals(new Vector2d(1, 0), direction.toUnitVector());
    }
}