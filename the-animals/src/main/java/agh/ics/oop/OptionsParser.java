package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        List<MoveDirection> directionList = new ArrayList<>();
        for(int i=0; i<args.length; i++) {
            MoveDirection direction = switch(args[i]) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };
            if(direction != null) {
                directionList.add(direction);
            }
        }
        return directionList.toArray(new MoveDirection[0]);
    }
}
