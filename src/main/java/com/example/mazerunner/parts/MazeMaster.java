package com.example.mazerunner.parts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.ClassPathResource;

public class MazeMaster {

    private final Maze maze;

    public MazeMaster(final String commandFileName) throws IOException {

        maze = new Maze();

        try (final InputStream inputStream = new ClassPathResource(commandFileName).getInputStream();
                final Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {

            final List<String> rows = lines.collect(Collectors.toList());

            maze.setMazeTitle(rows.get(0));
            maze.setSuccessMessage(rows.get(1));

            rows.remove(0); //remove title
            rows.remove(0); // remove success message

            buildMaze(rows);
        }
    }

    private void buildMaze(final List<String> rows) {
        final List<char[]> mazeMap = new ArrayList<>(rows.size());
        int maxColumnIndex = 0;

        for (final String row : rows) {
            final char[] cells = row.toCharArray();
            mazeMap.add(cells);

            maxColumnIndex = Math.max(maxColumnIndex, cells.length - 1);
        }

        maze.setMazeMap(mazeMap);
        maze.setMaxRowIndex(rows.size() - 1);
        maze.setMaxColumnIndex(maxColumnIndex);
    }

    public Maze getMaze() {
        return maze;
    }
}
