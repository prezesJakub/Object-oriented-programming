package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @Test
    void animalOrientationTest() {
        //given
        List<Vector2d> startingPositions = List.of(new Vector2d(2,2), new Vector2d(1,1));
        List<MoveDirection> moves = List.of(MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD);
        RectangularMap map = new RectangularMap(5, 5);

        //when
        Simulation simulation = new Simulation(startingPositions, moves, map);
        simulation.run();

        //then
        Animal animal1 = simulation.getAnimals().get(0);
        Animal animal2 = simulation.getAnimals().get(1);
        assertEquals(MapDirection.SOUTH, animal1.getOrientation());
        assertEquals(MapDirection.WEST, animal2.getOrientation());
    }
    @Test
    void animalPositionTest() {
        //given
        List<Vector2d> startingPositions = List.of(new Vector2d(1,3), new Vector2d(3,2));
        List<MoveDirection> moves = List.of(MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD);
        RectangularMap map = new RectangularMap(5, 5);

        //when
        Simulation simulation = new Simulation(startingPositions, moves, map);
        simulation.run();

        //then
        Animal animal1 = simulation.getAnimals().get(0);
        Animal animal2 = simulation.getAnimals().get(1);
        assertEquals(new Vector2d(1,1), animal1.getPosition());
        assertEquals(new Vector2d(4,3), animal2.getPosition());
    }
    @Test
    void isNotExitingMap() {
        //given
        List<Vector2d> startingPositions = List.of(new Vector2d(2,3), new Vector2d(2,2));
        List<MoveDirection> moves = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.FORWARD, MoveDirection.BACKWARD);
        RectangularMap map = new RectangularMap(5, 5);

        //when
        Simulation simulation = new Simulation(startingPositions, moves, map);
        simulation.run();

        //then
        Animal animal1 = simulation.getAnimals().get(0);
        Animal animal2 = simulation.getAnimals().get(1);
        assertEquals(new Vector2d(2,4), animal1.getPosition());
        assertEquals(new Vector2d(0,2), animal2.getPosition());
        assertTrue(animal1.getPosition().follows(new Vector2d(0, 0)));
        assertTrue(animal1.getPosition().precedes(new Vector2d(4, 4)));
        assertTrue(animal2.getPosition().follows(new Vector2d(0, 0)));
        assertTrue(animal2.getPosition().precedes(new Vector2d(4, 4)));
    }
    @Test
    void isNotExitingMapByCorners() {
        //given
        List<Vector2d> startingPositions = List.of(new Vector2d(4,4), new Vector2d(0,0));
        List<MoveDirection> moves = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD);
        RectangularMap map = new RectangularMap(5, 5);

        //when
        Simulation simulation = new Simulation(startingPositions, moves, map);
        simulation.run();

        //then
        Animal animal1 = simulation.getAnimals().get(0);
        Animal animal2 = simulation.getAnimals().get(1);
        assertEquals(new Vector2d(4,4), animal1.getPosition());
        assertEquals(new Vector2d(0,0), animal2.getPosition());
    }
    @Test
    void listOfCharactersInterpretationTest() {
        //given
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        List<Vector2d> startingPositions = List.of(new Vector2d(2,3), new Vector2d(2,2));
        List<MoveDirection> moves = OptionsParser.parse(args);
        RectangularMap map = new RectangularMap(5, 5);

        //when
        Simulation simulation = new Simulation(startingPositions, moves, map);
        simulation.run();

        //then
        Animal animal1 = simulation.getAnimals().get(0);
        Animal animal2 = simulation.getAnimals().get(1);
        assertEquals(new Vector2d(3,0), animal1.getPosition());
        assertEquals(new Vector2d(1,4), animal2.getPosition());
        assertEquals(MapDirection.SOUTH, animal1.getOrientation());
        assertEquals(MapDirection.NORTH, animal2.getOrientation());
    }
    @Test
    void listOfInvalidCharacters() {
        //given
        String[] args = {"trololo","f", "x", "f", "r", "f", "y", "b", "xD"};
        List<Vector2d> startingPositions = List.of(new Vector2d(2,1), new Vector2d(1,2));
        List<MoveDirection> moves = OptionsParser.parse(args);
        RectangularMap map = new RectangularMap(5, 5);

        //when
        Simulation simulation = new Simulation(startingPositions, moves, map);
        simulation.run();

        //then
        Animal animal1 = simulation.getAnimals().get(0);
        Animal animal2 = simulation.getAnimals().get(1);
        assertEquals(new Vector2d(1,2), animal1.getPosition());
        assertEquals(new Vector2d(1,4), animal2.getPosition());
        assertEquals(MapDirection.EAST, animal1.getOrientation());
        assertEquals(MapDirection.NORTH, animal2.getOrientation());
    }
}