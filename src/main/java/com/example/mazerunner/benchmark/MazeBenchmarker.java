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
        final List<String> directions = Arrays.asList("S", "S", "E", "E", "N", "E", "E", "E", "S", "S", "E", "E", "S", "E", "E", "N", "N", "E", "E",
                "E", "E", "S", "S", "S", "E", "E", "E", "E", "N", "N", "N", "E", "E", "S", "S", "S", "S", "S", "S", "S", "W", "W", "W", "W", "W", "W",
                "W", "W", "S", "S", "E", "E", "E", "S", "S", "S", "S", "S", "S", "E", "S", "S", "W", "W", "W", "W", "W", "W", "W", "W", "W", "N", "N",
                "W", "N", "N", "E", "E", "E", "N", "N", "N", "N", "W", "W", "W", "W", "W", "W", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S",
                "S", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "N", "E", "E", "E", "E", "N", "E", "N", "N", "E", "E", "N", "N", "E", "E", "E",
                "E", "S", "S", "S", "S", "S", "S", "E", "E", "E", "E", "E", "N", "N", "W", "N", "N", "E", "E", "E", "E", "E", "E", "E", "E", "E", "N",
                "N", "W", "N", "N", "N", "N", "N", "N", "W", "W", "W", "N", "N", "E", "E", "E", "E", "E", "E", "E", "E", "N", "N", "N", "N", "N", "N",
                "N", "N", "E", "E", "S", "E", "E", "N", "E", "E", "E", "S", "S", "E", "E", "S", "E", "E", "N", "N", "E", "E", "E", "E", "S", "S", "S",
                "E", "E", "E", "E", "N", "N", "N", "E", "E", "S", "S", "S", "S", "S", "W", "W", "W", "W", "W", "W", "W", "W", "S", "S", "S", "S", "E",
                "E", "E", "S", "S", "S", "S", "S", "S", "E", "S", "S", "W", "W", "W", "W", "W", "W", "W", "W", "W", "S", "S", "E", "S", "S", "E", "E",
                "E", "E", "E", "N", "E", "E", "E", "E", "S", "E", "E", "E", "N", "N", "E", "N", "E", "N", "E", "E", "N", "N", "N", "N", "N", "N", "N",
                "N", "E", "E", "E", "E", "E", "E", "N", "N", "E", "N", "E", "N", "N", "N", "N", "N", "W", "N", "N", "E", "E", "E", "E", "E", "E", "E",
                "N");
        for (final String direction : directions) {
            try {
                mazeMaster.step(direction, coordinates);
            } catch (final FoundExitException e) {
                //                System.out.println(e.getMessage());
            }
        }
    }

}
