package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parametersAreValid() {
        //given
        String[] args = {"b", "f", "r", "b", "l"};

        //then
        List<MoveDirection> expectedResult = List.of(MoveDirection.BACKWARD,
                MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT);
        assertEquals(expectedResult, OptionsParser.parse(args));
    }

    @Test
    void someInvalidParameters() {
        //given
        String[] args = {"b", "f", "x", "h", "l", "r"};

        //then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args));
        assertEquals("x is not legal move specification", exception.getMessage());

    }

    @Test
    void allInvalidParameters() {
        //given
        String[] args = {"siusiak", "pupa", "lololo"};

        //then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args));
        assertEquals("siusiak is not legal move specification", exception.getMessage());
    }

}