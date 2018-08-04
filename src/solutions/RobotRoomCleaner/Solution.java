package solutions.RobotRoomCleaner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dyj on 8/4/18.
 *
 Given a robot cleaner in a room modeled as a grid.

 Each cell in the grid can be empty or blocked.

 The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

 When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

 Design an algorithm to clean the entire room using only the 4 given APIs shown below.

 interface Robot {
 // returns true if next cell is open and robot moves into the cell.
 // returns false if next cell is obstacle and robot stays on the current cell.
 boolean move();

 // Robot will stay on the same cell after calling turnLeft/turnRight.
 // Each turn will be 90 degrees.
 void turnLeft();
 void turnRight();

 // Clean the current cell.
 void clean();
 }
 Example:

 Input:
 room = [
 [1,1,1,1,1,0,1,1],
 [1,1,1,1,1,0,1,1],
 [1,0,1,1,1,1,1,1],
 [0,0,0,1,0,0,0,0],
 [1,1,1,1,1,1,1,1]
 ],
 row = 1,
 col = 3

 Explanation:
 All grids in the room are marked by either 0 or 1.
 0 means the cell is blocked, while 1 means the cell is accessible.
 The robot initially starts at the position of row=1, col=3.
 From the top left corner, its position is one row below and three columns right.
 Notes:

 The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
 The robot's initial position will always be in an accessible cell.
 The initial direction of the robot will be facing up.
 All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
 Assume all four edges of the grid are all surrounded by wall.

 */

public class Solution {
    public void cleanRoom(Robot robot) {
        Set<String> set  = new HashSet<>();
        int cur_dir = 0; // 0: up, 90: right, 180: down, 270: left
        dfs(robot, set, 0, 0, 0);
    }

    private void dfs(Robot robot, Set<String> set, int i, int j, int cur_dir) {
        String pos = i + "->" + j; // serialize position
        if (set.contains(pos)) return;
        robot.clean(); // clean the pos
        set.add(pos);

        for (int k = 0; k < 4; k++) {
            // try move forward, if not, right ture
            if (robot.move()) {
                // move forward
                int x = i, y = j;
                switch (cur_dir) {
                    case 0:
                        // go up
                        x--;
                        break;
                    case 90:
                        // go right
                        y++;
                        break;
                    case 180:
                        // go down
                        x++;
                        break;
                    case 270:
                        // go left
                        y--;
                        break;
                    default:
                        break;
                }
                dfs(robot, set, x, y, cur_dir);
                // back tracking, go back to the original position
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }
            // turn right and go to next position
            robot.turnRight();
            cur_dir = (cur_dir + 90) % 360;
        }
    }
}

interface Robot {
    // returns true if next cell is open and robot moves into the cell.
    // returns false if next cell is obstacle and robot stays on the current cell.
    boolean move();

    // Robot will stay on the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();
    void turnRight();

    // Clean the current cell.
    void clean();
}
