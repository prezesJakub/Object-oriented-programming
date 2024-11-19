package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updateCounter = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        updateCounter++;
        System.out.println("Map Update #" + updateCounter);
        System.out.println("Operation: " + message);
        System.out.println(worldMap);
    }
}
