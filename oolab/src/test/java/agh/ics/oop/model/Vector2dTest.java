package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void equals() {
        // when
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3 = new Vector2d(1, -2);
        Vector2d v4 = null;

        // then
        assertEquals(v1, v2);
        assertNotEquals(v1, v3);
        assertNotEquals(v1, v4);
    }

    @Test
    void toStringTest() {
        // given
        Vector2d v1 = new Vector2d(1, 2);

        // when
        String v2 = v1.toString();

        // then
        assertEquals("(1, 2)", v2);
    }

    @Test
    void precedes() {
        // given
        Vector2d v1_1 = new Vector2d(2, 5);
        Vector2d v2_1 = new Vector2d(1, 3);
        Vector2d v4_1 = new Vector2d(2, 5);
        Vector2d v4_2 = new Vector2d(1, 3);

        // when
        Vector2d v1_2 = new Vector2d(2, 5);
        Vector2d v2_2 = new Vector2d(2, 5);
        Vector2d v3_2 = new Vector2d(1, 3);
        Vector2d v3_1 = new Vector2d(0, 5);

        // then
        assertTrue(v1_1.precedes(v1_2));
        assertTrue(v2_1.precedes(v2_2));
        assertFalse(v3_1.precedes(v3_2));
        assertFalse(v4_1.precedes(v4_2));
    }

    @Test
    void follows() {
        // given
        Vector2d v1_1 = new Vector2d(2, 5);
        Vector2d v2_1 = new Vector2d(1, 3);
        Vector2d v3_1 = new Vector2d(0, 5);
        Vector2d v4_1 = new Vector2d(2, 5);

        // when
        Vector2d v1_2 = new Vector2d(2, 5);
        Vector2d v2_2 = new Vector2d(2, 5);
        Vector2d v3_2 = new Vector2d(1, 3);
        Vector2d v4_2 = new Vector2d(1, 3);

        // then
        assertTrue(v1_1.follows(v1_2));
        assertTrue(v2_2.follows(v2_1));
        assertFalse(v3_2.follows(v3_1));
        assertFalse(v4_2.follows(v4_1));
    }

    @Test
    void upperRight() {
        // given
        Vector2d v1_1 = new Vector2d(2, 5);
        Vector2d v1_2 = new Vector2d(1, -3);
        Vector2d v2_1 = new Vector2d(2, 5);
        Vector2d v2_2 = new Vector2d(1, 10);
        Vector2d expectedResult1 = new Vector2d(2, 5);
        Vector2d expectedResult2 = new Vector2d(2, 10);

        // when
        Vector2d result1 = v1_1.upperRight(v1_2);
        Vector2d result2 = v2_1.upperRight(v2_2);

        // then
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
    }

    @Test
    void lowerLeft() {
        // given
        Vector2d v1_1 = new Vector2d(2, 5);
        Vector2d v1_2 = new Vector2d(1, -3);
        Vector2d v2_1 = new Vector2d(2, 5);
        Vector2d v2_2 = new Vector2d(1, 10);
        Vector2d expectedResult1 = new Vector2d(1, -3);
        Vector2d expectedResult2 = new Vector2d(1, 5);

        // when
        Vector2d result1 = v1_1.lowerLeft(v1_2);
        Vector2d result2 = v2_1.lowerLeft(v2_2);

        // then
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
    }


    @Test
    void add() {
        // given
        Vector2d v1 = new Vector2d(2, 5);
        Vector2d v2 = new Vector2d(1, -3);
        Vector2d expectedResult = new Vector2d(3, 2);

        // when
        Vector2d result = v1.add(v2);

        // then
        assertEquals(expectedResult, result);
    }

    @Test
    void subtract() {
        // given
        Vector2d v1 = new Vector2d(2, 5);
        Vector2d v2 = new Vector2d(1, -3);
        Vector2d expectedResult = new Vector2d(1, 8);

        // when
        Vector2d result = v1.subtract(v2);

        // then
        assertEquals(expectedResult, result);
    }
    
    @Test
    void opposite() {
        // given
        Vector2d v1 = new Vector2d(2, 5);
        Vector2d v2 = new Vector2d(0, 0);
        Vector2d expectedResult1 = new Vector2d(-2, -5);
        Vector2d expectedResult2 = new Vector2d(0, 0);

        // when
        Vector2d result1 = v1.opposite();
        Vector2d result2 = v2.opposite();

        // then
        assertEquals(expectedResult1, result1);
        assertEquals(expectedResult2, result2);
    }
}
