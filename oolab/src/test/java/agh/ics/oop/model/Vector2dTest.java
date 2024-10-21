package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void equalsTest() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(1, -2);
        Vector2d v4 = null;

        assertTrue(v1.equals(v2));
        assertFalse(v1.equals(v3));
        assertFalse(v1.equals(v4));
        assertFalse(v4.equals(v1));
    }

    @Test
    void toStringTest() {
        Vector2d v1 = new Vector2d(1, 2);
        String v2 = v1.toString();
        assertEquals("(1, 2)", v2);
    }

    @Test
    void precedesTest() {
        Vector2d v1_1 = new Vector2d(2, 5);
        Vector2d v1_2 = new Vector2d(2, 5);
        assertTrue(v1_1.precedes(v1_2));

        Vector2d v2_1 = new Vector2d(1, 3);
        Vector2d v2_2 = new Vector2d(2, 5);
        assertTrue(v2_1.precedes(v2_2));

        Vector2d v3_1 = new Vector2d(0, 5);
        Vector2d v3_2 = new Vector2d(1, 3);
        assertFalse(v3_1.precedes(v3_2));

        Vector2d v4_1 = new Vector2d(2, 5);
        Vector2d v4_2 = new Vector2d(1, 3);
        assertFalse(v4_1.precedes(v4_2));
    }

    @Test
    void followsTest() {
        Vector2d v1_1 = new Vector2d(2, 5);
        Vector2d v1_2 = new Vector2d(2, 5);
        assertTrue(v1_1.precedes(v1_2));

        Vector2d v2_1 = new Vector2d(1, 3);
        Vector2d v2_2 = new Vector2d(2, 5);
        assertTrue(v2_2.follows(v2_1));

        Vector2d v3_1 = new Vector2d(0, 5);
        Vector2d v3_2 = new Vector2d(1, 3);
        assertFalse(v3_2.follows(v3_1));

        Vector2d v4_1 = new Vector2d(2, 5);
        Vector2d v4_2 = new Vector2d(1, 3);
        assertFalse(v4_2.follows(v4_1));
    }

    @Test
    void upperRightTest() {
        Vector2d v1_1 = new Vector2d(2, 5);
        Vector2d v1_2 = new Vector2d(1, -3);
        Vector2d expectedResult1 = new Vector2d(2, 5);

        Vector2d v2_1 = new Vector2d(2, 5);
        Vector2d v2_2 = new Vector2d(1, 10);
        Vector2d expectedResult2 = new Vector2d(2, 10);

        Vector2d result1 = v1_1.upperRight(v1_2);
        Vector2d result2 = v2_1.upperRight(v2_2);

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
    }

    @Test
    void lowerLeftTest() {
        Vector2d v1_1 = new Vector2d(2, 5);
        Vector2d v1_2 = new Vector2d(1, -3);
        Vector2d expectedResult1 = new Vector2d(1, -3);

        Vector2d v2_1 = new Vector2d(2, 5);
        Vector2d v2_2 = new Vector2d(1, 10);
        Vector2d expectedResult2 = new Vector2d(1, 5);

        Vector2d result1 = v1_1.lowerLeft(v1_2);
        Vector2d result2 = v2_1.lowerLeft(v2_2);

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
    }


    @Test
    void addTest() {
        Vector2d v1 = new Vector2d(2, 5);
        Vector2d v2 = new Vector2d(1, -3);
        Vector2d expectedResult = new Vector2d(3, 2);

        Vector2d result = v1.add(v2);

        assertEquals(expectedResult, result);
    }

    @Test
    void subtractTest() {
        Vector2d v1 = new Vector2d(2, 5);
        Vector2d v2 = new Vector2d(1, -3);
        Vector2d expectedResult = new Vector2d(1, 8);

        Vector2d result = v1.subtract(v2);

        assertEquals(expectedResult, result);
    }
    
    @Test
    void oppositeTest() {
        Vector2d v1 = new Vector2d(2, 5);
        Vector2d expectedResult1 = new Vector2d(-2, -5);
        Vector2d v2 = new Vector2d(0, 0);
        Vector2d expectedResult2 = new Vector2d(0, 0);

        Vector2d result1 = v1.opposite();
        Vector2d result2 = v2.opposite();

        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
    }
}
