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

import com.example.mazerunner.parts.Coordinates;
import com.example.mazerunner.parts.FoundExitException;
import com.example.mazerunner.parts.MazeMaster;

public class MazeBenchmarker {

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void benchmark_test() throws IOException, URISyntaxException {

        final MazeMaster mazeMaster = new MazeMaster("theSnake.txt");
        final Coordinates coordinates = new Coordinates();
        final List<String> directions = Arrays.asList("E", "E", "S", "E", "S", "W", "S", "W", "S", "E", "E", "S");
        for (final String direction : directions) {
            try {
                mazeMaster.step(direction, coordinates);
            } catch (final FoundExitException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
