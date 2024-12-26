package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        return Arrays.stream(args).map(arg -> switch (arg) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "l", "left" -> MoveDirection.LEFT;
            default -> throw new IllegalArgumentException(arg + " is not legal move specification");
        }).collect(Collectors.toList());
    }
}
