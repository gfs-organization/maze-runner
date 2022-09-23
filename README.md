# Maze Runner

This is the Maze Runner Code Challenge

You have been dropped in a maze of unknown size and must find the exit. The maze is laid out in a grid pattern. You will
always start in the NorthWest corner of the maze (position 0,0 of the grid). Your goal is to map the shortest path
through the maze by sending directional coordinates for each step. You will take steps by passing in an array of
Strings, where each item in the array is one of 4 cardinal direction points: N, S, E, W.

- N will take 1 step towards the top of the grid.
- S will take 1 step towards the bottom of the grid.
- E will... (If you can't figure out where East is now, don't start this challenge.)
- W  (See the directions for East.)

Each set of directions will return a String indicating you have hit a "Wall" or "Open Space" or "Exit". If you hit an
open space, you have moved to that space. If you hit "Exit" you are done, and you will receive a success message with
your Secret Code.

Become a Runner in the Maze by sending a PUT request to:
https://gfs-maze-runner.herokuapp.com/mazerunner/{level}

with JSON body: ["E", "S", "N", "W"]

There are 7 levels to this maze (1, 2, 3, 4, 5, 6, 7).

You may submit your solution to each as you find them. Please submit your directions through the maze, plus your secret
code for each maze.

Things to consider:

1. The exit can be anywhere in the grid. Even the middle.
2. The grid is not necessarily a square.
3. You can use Postman or SoapUI to test submissions and responses.



