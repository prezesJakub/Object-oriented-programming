package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        List<MoveDirection> directionList = new ArrayList<>();
        for(int i=0; i<args.length; i++) {
            switch(args[i]) {
                case "f" -> directionList.add(MoveDirection.FORWARD);
                case "b" -> directionList.add(MoveDirection.BACKWARD);
                case "r" -> directionList.add(MoveDirection.RIGHT);
                case "l" -> directionList.add(MoveDirection.LEFT);
            }
        }
        return directionList.toArray(new MoveDirection[0]);
    }
}
