package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void vectorsAreEqual() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(1,2);

        //then
        assertTrue(v1.equals(v2));
        assertTrue(v1.precedes(v2));
        assertTrue(v1.follows(v2));
        assertEquals(new Vector2d(2,4),v1.add(v2));
        assertEquals(new Vector2d(0,0),v1.subtract(v2));
        assertEquals(new Vector2d(1, 2), v1.lowerLeft(v2));
        assertEquals(new Vector2d(1, 2), v1.upperRight(v2));
    }

    @Test
    void vectorsAreNotEqual() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(3,4);

        //then
        assertFalse(v1.equals(v2));
        assertTrue(v1.precedes(v2));
        assertFalse(v2.precedes(v1));
        assertFalse(v1.follows(v2));
        assertTrue(v2.follows(v1));
        assertEquals(new Vector2d(4,6),v1.add(v2));
        assertEquals(new Vector2d(-2,-2),v1.subtract(v2));
        assertEquals(new Vector2d(1, 2), v1.lowerLeft(v2));
        assertEquals(new Vector2d(3, 4), v1.upperRight(v2));
    }

    @Test
    void theParameterXIsEqual() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(1,4);

        //then
        assertFalse(v1.equals(v2));
        assertTrue(v1.precedes(v2));
        assertFalse(v2.precedes(v1));
        assertFalse(v1.follows(v2));
        assertTrue(v2.follows(v1));
        assertEquals(new Vector2d(2,6),v1.add(v2));
        assertEquals(new Vector2d(0,-2),v1.subtract(v2));
        assertEquals(new Vector2d(1, 2), v1.lowerLeft(v2));
        assertEquals(new Vector2d(1, 4), v1.upperRight(v2));
    }

    @Test
    void theParameterYIsEqual() {
        //given
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(3,2);

        //then
        assertFalse(v1.equals(v2));
        assertTrue(v1.precedes(v2));
        assertFalse(v2.precedes(v1));
        assertFalse(v1.follows(v2));
        assertTrue(v2.follows(v1));
        assertEquals(new Vector2d(4,4),v1.add(v2));
        assertEquals(new Vector2d(-2,0),v1.subtract(v2));
        assertEquals(new Vector2d(1, 2), v1.lowerLeft(v2));
        assertEquals(new Vector2d(3, 2), v1.upperRight(v2));
    }

    @Test
    void resultOfCornerIsDifferentThanOneOfParameters() {
        //given
        Vector2d v1 = new Vector2d(5,-2);
        Vector2d v2 = new Vector2d(-1,4);

        //then
        assertEquals(new Vector2d(-1, -2), v1.lowerLeft(v2));
        assertEquals(new Vector2d(5, 4), v1.upperRight(v2));
        assertFalse(v1.precedes(v2));
        assertFalse(v1.follows(v2));
        assertFalse(v2.precedes(v1));
        assertFalse(v2.follows(v1));
    }

    @Test
    void vectorHasNaturalParameters() {
        //given
        Vector2d v1 = new Vector2d(4,7);

        //then
        assertEquals("(4, 7)", v1.toString());
        assertEquals(new Vector2d(-4,-7), v1.opposite());
    }

    @Test
    void vectorHasNegativeParameters() {
        //given
        Vector2d v1 = new Vector2d(-6,-15);

        //then
        assertEquals("(-6, -15)", v1.toString());
        assertEquals(new Vector2d(6,15), v1.opposite());
    }

}