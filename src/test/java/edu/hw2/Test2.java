package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test2 {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Sample test")
    void rectangleArea(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);

        assertEquals(rect.area(), 200.0);
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Sample test (просто  поменял местами команды)")
    void rectangleArea2(Rectangle rect) {
        rect = rect.setHeight(10);
        rect = rect.setWidth(20);

        assertEquals(rect.area(), 200.0);
    }

    @Test
    @DisplayName("Sample test (отрицательные значения rectangle)")
    void rectangleAreaWithNegativeRect() {
        Rectangle rect = new Rectangle(-1, -1);

        assertEquals(rect.area(), 0.0);
    }

    @Test
    @DisplayName("Sample test (отрицательные значения square)")
    void rectangleAreaWithNegativeSquare() {
        Rectangle rect = new Square();
        rect = rect.setHeight(-1);
        rect = rect.setWidth(-32);
        assertEquals(rect.area(), 0.0);
    }
}

