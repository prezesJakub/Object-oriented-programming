package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected final MapVisualizer visualizer = new MapVisualizer(this);
    protected final List<MapChangeListener> observers = new ArrayList<>();
    private final UUID id = UUID.randomUUID();


    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.put(position, animal);
            mapChanged("Animal placed at " + position);
        } else {
            throw new IncorrectPositionException(position);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();

        objectAt(oldPosition)
                .filter(element -> element == animal)
                .ifPresent(element -> {
                    animal.move(direction, this);
                    animals.remove(oldPosition);
                    Vector2d newPosition = animal.getPosition();
                    animals.put(newPosition, animal);
                    mapChanged("Animal moved from " + oldPosition + " to " + newPosition);
                });
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        return Optional.ofNullable(animals.get(position));
    }

    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    protected void mapChanged(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }

    public abstract Boundary getCurrentBounds();

    @Override
    public String toString() {
        Boundary bounds = getCurrentBounds();
        return visualizer.draw(bounds.lowerLeft(), bounds.upperRight());
    }

    public List<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(animals.values());
        return elements;
    }

    public List<Animal> getOrderedAnimals() {
        return animals.values().stream().sorted(Comparator.comparing((Animal a) -> a.getPosition().getX())
                .thenComparing((Animal a) -> a.getPosition().getY()))
                .toList();
    }

}
