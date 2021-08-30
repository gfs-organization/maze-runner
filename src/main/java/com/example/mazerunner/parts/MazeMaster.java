package com.example.mazerunner.parts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.ClassPathResource;

public class MazeMaster {

    private final Map<Integer, Maze> mazes;

    public MazeMaster(final String... commandFileNames) throws IOException {

        mazes = new HashMap<>();

        for (final String commandFileName : commandFileNames) {

            try (final InputStream inputStream = new ClassPathResource(commandFileName).getInputStream();
                    final Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {

                final Maze maze = new Maze();
                final List<String> rows = lines.collect(Collectors.toList());
                maze.setMazeTitle(rows.get(0));
                maze.setSuccessMessage(rows.get(1));

                rows.remove(0); //remove title
                rows.remove(0); // remove success message

                buildMaze(maze, rows);
                mazes.put(1, maze);
            }
        }
    }

    private void buildMaze(final Maze maze, final List<String> rows) {
        final List<char[]> levelMap = new ArrayList<>(rows.size());
        int maxColumnIndex = 0;

        for (final String row : rows) {
            final char[] cells = row.toCharArray();
            levelMap.add(cells);

            maxColumnIndex = Math.max(maxColumnIndex, cells.length - 1);
        }

        maze.setMazeMap(levelMap);
        maze.setMaxRowIndex(rows.size() - 1);
        maze.setMaxColumnIndex(maxColumnIndex);
    }

    public Map<Integer, Maze> getMazes() {
        return mazes;
    }
}
