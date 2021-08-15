# Maze Runner
This is the Maze Runner Code Challenge

You have been dropped in a maze of unknown size and must find the exit. The maze is laid out in a grid pattern. You 
will always start in the Northeast corner of the maze (position 0,0 of a grid). Your goal is to map the shortest 
path through the maze by collecting directional coordinates at each step. You will take a step by calling one of 
four methods on the MazeMaster object:

- stepNorth()  (North is at the top of the grid)
- stepSouth()  (South is at the bottom of the grid)
- stepEast()   (If you can't figure out where East is now, don't start this challenge.)
- stepWest()   (See the directions for East.)

Each method will return a String indicating "Wall" or "Open Space" or "Exit".  If you have hit a wall, you do not move.
If you hit an open space, you have moved to that space. If you hit "Exit" you are done. Your solution should return a 
comma delimited string of your steps (e.g. N,S,E,W,W,E,N).

Things to consider:
1. The exit can be anywhere in the grid. Even the middle.
2. The grid is not necessarily a square. (Could be a rectangle. Won't be a triangle or a circle.)
3. In your workspace, you can either implement your own functional MazeMaster, or just mock it out. Only submit your 
   implementation for runTheMaze().

Example:
stepNorth() -> "Wall"
stepEast() -> "Open Space"
stepEast() -> "Open Space"
stepEast() -> "Wall" // note: you did not move
stepSouth() -> "Exit"

Your output should be "E,E,S" as the shortest path.

