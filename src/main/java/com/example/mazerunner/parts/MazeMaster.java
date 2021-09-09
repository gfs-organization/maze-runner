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

        for (int x = 0; x < commandFileNames.length; x++) {
            try (final InputStream inputStream = new ClassPathResource(commandFileNames[x]).getInputStream();
                    final Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {

                final Maze maze = new Maze();
                final List<String> rows = lines.collect(Collectors.toList());
                maze.setMazeTitle(rows.get(0));
                maze.setSuccessMessage(rows.get(1));

                rows.remove(0); //remove title
                rows.remove(0); // remove success message

                buildMaze(maze, rows);
                mazes.put(x, maze);
            }
        }
    }

    private void buildMaze(final Maze maze, final List<String> rows) {
        final List<char[]> levelMap = new ArrayList<>(rows.size());
        int maxColumnIndex = 0;

        for (int x = 0; x < rows.size(); x++) {
            final String row = rows.get(x);
            final char[] cells = row.toCharArray();
            levelMap.add(cells);

            int stairs = row.indexOf("U");
            if (stairs > -1) {
                maze.setStairsUp(new Coordinates(x, stairs));
            }
            stairs = row.indexOf("D");
            if (stairs > -1) {
                maze.setStairsDown(new Coordinates(x, stairs));
            }
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
