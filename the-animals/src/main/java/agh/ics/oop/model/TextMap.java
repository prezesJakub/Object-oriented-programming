package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class TextMap implements WorldNumberPositionMap<String>{
    private final List<String> textList = new ArrayList<>();

    @Override
    public boolean place(String object) {
        textList.add(object);
        return true;
    }

    @Override
    public void move(String object, MoveDirection direction) {
        int oldIndex = textList.indexOf(object);
        switch (direction) {
            case FORWARD, RIGHT -> {
                if (oldIndex < textList.size() - 1) {
                    swap(oldIndex, oldIndex+1);
                }
            }
            case BACKWARD, LEFT -> {
                if (oldIndex > 0) {
                    swap(oldIndex, oldIndex-1);
                }
            }
        }
    }

    public void swap(int index1, int index2) {
        String temp = textList.get(index1);
        textList.set(index1, textList.get(index2));
        textList.set(index2, temp);
    }

    @Override
    public boolean isOccupied(Number position) {
        int positionValue = position.intValue();
        return positionValue >= 0 && positionValue < textList.size();
    }

    @Override
    public String objectAt(Number position) {
        int positionValue = position.intValue();
        if (isOccupied(position)) {
            return textList.get(positionValue);
        }
        return null;
    }

    @Override
    public boolean canMoveTo(Number position) {
        int positionValue = position.intValue();
        return positionValue >= 0 && positionValue < textList.size();
    }

    public String toString() {
        return textList.toString();
    }
}
