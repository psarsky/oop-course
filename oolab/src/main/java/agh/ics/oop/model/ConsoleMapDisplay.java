package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int count = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println("Map ID: " + worldMap.getID() + "\n" + "Update " + ++count + ": " + message + "\n" + worldMap);
    }
}
