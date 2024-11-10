package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.WorldMap;

import java.util.List;

public class Simulation<T, P> {

    private final List<T> objects;
    private final List<MoveDirection> moves;
    private final WorldMap<T, P> map;

    public Simulation(List<T> objects, List<P> initialPositions, List<MoveDirection> moves, WorldMap<T, P> map) {

        this.map = map;
        this.moves = moves;
        this.objects = objects;

        for (int i = 0; i < objects.size(); i++) {
            map.place(objects.get(i), initialPositions.get(i));
        }
    }

    public void run() {

        int objectIndex = 0;
        int objectCount = objects.size();

        for (MoveDirection move : moves) {
            if (objectIndex > objectCount - 1) {
                break;
            }
            map.move(objects.get(objectIndex), move);
            System.out.println(map);
            objectIndex = (objectIndex + 1) % objectCount;
        }
    }

    @Override
    public String toString() {
        return map.toString();
    }

    public T getObject(int index) {
        return objects.get(index);
    }

    public List<T> getObjects() {
        return objects;
    }
}
