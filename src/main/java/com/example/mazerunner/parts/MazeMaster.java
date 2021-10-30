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

    private final List<Maze> mazes;
    private int goldPieces;
    private int silverPieces;
    private int copperPieces;

    public MazeMaster(final String... commandFileNames) throws IOException {

        mazes = new ArrayList<>();

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
                mazes.add(maze);
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

            for (int columnIndex = 0; columnIndex < cells.length; columnIndex++) {
                if (cells[columnIndex] == 'U') {
                    maze.setStairsUp(new Coordinates(x, columnIndex));
                } else if (cells[columnIndex] == 'D') {
                    maze.setStairsDown(new Coordinates(x, columnIndex));
                } else if (cells[columnIndex] == 'G') {
                    this.goldPieces++;
                } else if (cells[columnIndex] == 'S') {
                    this.silverPieces++;
                } else if (cells[columnIndex] == 'C') {
                    this.copperPieces++;
                }
            }

            maxColumnIndex = Math.max(maxColumnIndex, cells.length - 1);
        }

        maze.setMazeMap(levelMap);
        maze.setMaxRowIndex(rows.size() - 1);
        maze.setMaxColumnIndex(maxColumnIndex);
    }

    public List<Maze> getMazes() {
        return mazes;
    }

    public int getGoldPieces() {
        return goldPieces;
    }

    public int getSilverPieces() {
        return silverPieces;
    }

    public int getCopperPieces() {
        return copperPieces;
    }

}
