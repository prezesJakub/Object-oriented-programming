package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parametersAreValid() {
        //given
        String[] args = {"b", "f", "r", "b", "l"};

        //then
        MoveDirection[] expectedResult = {MoveDirection.BACKWARD,
                MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT};
        assertArrayEquals(expectedResult, OptionsParser.parse(args));
    }

    @Test
    void someInvalidParameters() {
        //given
        String[] args = {"b", "f", "x", "h", "l", "r"};

        //then
        MoveDirection[] expectedResult = {MoveDirection.BACKWARD, MoveDirection.FORWARD,
        MoveDirection.LEFT, MoveDirection.RIGHT};
        assertArrayEquals(expectedResult, OptionsParser.parse(args));
    }

}