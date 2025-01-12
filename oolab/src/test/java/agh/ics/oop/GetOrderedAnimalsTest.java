package agh.ics.oop;


import agh.ics.oop.model.*;
import agh.ics.oop.model.util.IncorrectPositionException;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetOrderedAnimalsTest {
    @Test
    void getOrderedAnimals1() {
        AbstractWorldMap map = new GrassField(10);

        Animal animal1 = new Animal(new Vector2d(3, 4));
        Animal animal2 = new Animal(new Vector2d(1, 2));
        Animal animal3 = new Animal(new Vector2d(2, 2));

        try {
            map.place(animal1);
            map.place(animal2);
            map.place(animal3);
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }

        Collection<Animal> orderedAnimals = map.getOrderedAnimals();

        assertEquals(animal2, orderedAnimals.iterator().next());
    }

    @Test
    void getOrderedAnimals2() {
        AbstractWorldMap map = new GrassField(10);

        Animal animal1 = new Animal(new Vector2d(2, 3));
        Animal animal2 = new Animal(new Vector2d(1, 1));
        Animal animal3 = new Animal(new Vector2d(2, 2));

        try {
            map.place(animal1);
            map.place(animal2);
            map.place(animal3);
        } catch (IncorrectPositionException e) {
            e.printStackTrace();
        }

        Collection<Animal> orderedAnimals = map.getOrderedAnimals();

        assertEquals(3, orderedAnimals.size());

        Animal[] animalsArray = orderedAnimals.toArray(new Animal[0]);
        assertEquals(animal2, animalsArray[0]);
        assertEquals(animal3, animalsArray[1]);
        assertEquals(animal1, animalsArray[2]);
    }
}