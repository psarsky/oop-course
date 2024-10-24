package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class OptionsParserTest {

    @Test
    void parse() {
        // given
        String[] input1 = {"f", "b", "r", "l"};
        String[] input2 = {"f", "fdhbfv", "b", "eeee", "r", "", "l", "uwuwuewue"};
        MoveDirection[] expectedResult = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};

        // when
        MoveDirection[] result1 = OptionsParser.parse(input1);
        MoveDirection[] result2 = OptionsParser.parse(input2);

        // then
        assertArrayEquals(expectedResult, result1);
        assertArrayEquals(expectedResult, result2);
    }
}
