package example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {


    @Test
    void countAliveNeighborsOnWrappedEdges() {
        char[][] field = {
                {'x', 'x'},
                {'x', 'x'}
        };

        assertEquals(8, GameOfLife.countAliveNeighbors(field, 0, 0));
    }


    @Test
    void stableBlockShouldStaySameInMiddle() {
        char[][] initial = {
                {'.','.','.','.','.'},
                {'.','x','x','.','.'},
                {'.','x','x','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
        };

        char[][] expected = {
                {'.','.','.','.','.'},
                {'.','x','x','.','.'},
                {'.','x','x','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
        };

        assertArrayEquals(expected, GameOfLife.nextGeneration(initial));
    }


    @Test
    void deadCellWithThreeNeighborsBecomesAlive() {
        char[][] initial = {
                {'.','x','.'},
                {'.','x','.'},
                {'.','x','.'}
        };

        assertEquals('x', GameOfLife.nextCellState(initial, 1, 0));
    }


    @Test
    void liveCellWithMoreThanThreeNeighborsDies() {
        char[][] initial = {
                {'x','x','x'},
                {'x','x','x'},
                {'x','x','x'}
        };

        assertEquals('.', GameOfLife.nextCellState(initial, 1, 1));
    }


    @Test
    void evolveTwoGenerationsProducesCorrectResult() {
        char[][] initial = {
                {'.','.','.','.','.'},
                {'.','.','x','.','.'},
                {'.','.','x','.','.'},
                {'.','.','x','.','.'},
                {'.','.','.','.','.'},
        };

        char[][] expectedAfter2 = {
                {'.','.','.','.','.'},
                {'.','.','x','.','.'},
                {'.','.','x','.','.'},
                {'.','.','x','.','.'},
                {'.','.','.','.','.'},
        };

        assertArrayEquals(expectedAfter2, GameOfLife.evolve(initial, 2));
    }
}
