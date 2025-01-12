package agh.ics.oop.model;

import agh.ics.oop.model.util.Boundary;
import agh.ics.oop.model.util.RandomPositionGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap {

    private final Map<Vector2d, Grass> grass;

    public GrassField(int grassCount) {
        super();
        this.grass = new HashMap<>();

        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator((int) sqrt(grassCount * 10), (int) sqrt(grassCount * 10), grassCount);
        for (Vector2d grassPosition : randomPositionGenerator) {
            grass.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public Optional<WorldElement> objectAt(Vector2d position) {
        WorldElement animal = animals.get(position);
        if (animal != null)
            return Optional.of(animal);
        WorldElement grassEl = grass.get(position);
        return Optional.ofNullable(grassEl);
    }

    @Override
    public List<WorldElement> getElements() {
        return Stream
                .concat(super.getElements().stream(), grass.values().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Boundary getCurrentBounds() {
        Vector2d ur = new Vector2d(lowerLeft.getX(), lowerLeft.getY());
        Vector2d ll = new Vector2d(upperRight.getX(), upperRight.getY());

        for (WorldElement we : getElements()) {
            ur = ur.upperRight(we.getPos());
            ll = ll.lowerLeft(we.getPos());
        }
        return new Boundary(ll, ur);
    }
}
