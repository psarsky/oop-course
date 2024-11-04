package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class OptionsParserTest {

    @Test
    void parseCorrectInput() {
        // given
        String[] input = {"f", "b", "r", "l"};
        MoveDirection[] expectedResult = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};

        // when
        List<MoveDirection> result = OptionsParser.parse(input);

        // then
        assertArrayEquals(expectedResult, result.toArray());
    }

    @Test
    void parseMixedInput() {
        // given
        String[] input = {"f", "fdhbfv", "b", "eeee", "r", "", "l", "uwuwuewue"};
        MoveDirection[] expectedResult = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};

        // when
        List<MoveDirection> result = OptionsParser.parse(input);

        // then
        assertArrayEquals(expectedResult, result.toArray());
    }

    @Test
    void parseIncorrectInput() {
        // given
        String[] input = {"fdhbfv", "eeee", "", "uwuwuewue"};
        MoveDirection[] expectedResult = {};

        // when
        List<MoveDirection> result = OptionsParser.parse(input);

        // then
        assertArrayEquals(expectedResult, result.toArray());
    }

    @Test
    void parseEmptyInput() {
        // given
        String[] input = {};
        MoveDirection[] expectedResult = {};

        // when
        List<MoveDirection> result = OptionsParser.parse(input);

        // then
        assertArrayEquals(expectedResult, result.toArray());
    }
}
