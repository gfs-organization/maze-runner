package com.example.mazerunner.benchmark;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import com.example.mazerunner.exceptions.FoundExitException;
import com.example.mazerunner.exceptions.MazeException;
import com.example.mazerunner.exceptions.WallException;
import com.example.mazerunner.navigation.steppers.CardinalStepper;
import com.example.mazerunner.parts.CardinalDirection;
import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.Maze;
import com.example.mazerunner.parts.MazeMaster;

public class MazeBenchmarker {

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void benchmark_test() throws IOException, URISyntaxException {

        final MazeMaster mazeMaster = new MazeMaster("theEndlessLoop.txt");
        final Maze maze = mazeMaster.getMaze();
        final CardinalStepper cardinalStepper = new CardinalStepper();
        final Coordinates coordinates = new Coordinates();
        final List<String> directions = Arrays.asList("E", "E", "E", "E", "E", "S", "S", "S", "S", "W", "W", "W", "W", "W", "N", "N", "N", "N");

        for (int i = 0; i < 100000; i++) {
            try {
                for (final String direction : directions) {
                    final CardinalDirection stepDirection = CardinalDirection.getByName(direction);
                    cardinalStepper.doStep(maze, stepDirection, coordinates);
                }

            } catch (final FoundExitException e) {
                System.out.println("Found the exit after " + i + " steps.");
            } catch (final WallException we) {
                System.out.println("Hit a wall after " + i + " steps.");
            } catch (final MazeException me) {
                throw new IllegalArgumentException(me.getMessage());
            }
        }
    }

}
