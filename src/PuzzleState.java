import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.abs;

/**
 * Created by A. Goldani on 2019-01-18.
 * Student ID: 9512762107
 * BlindSearch
 */

public class PuzzleState
{

    private final int PUZZLE_SIZE = 9;
    private final int[] GOAL_STATE = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 0};
    private int costToThis;

    private int[] currentBoard;
    private int outOfPlace = 0;

    public int[] getCurrentBoard() {
        return currentBoard;
    }

    /**
     *
     * @param currentBoard the order of the eight numbers in the beginning, read from input.
     */
    public PuzzleState(int[] currentBoard)
    {
        this.currentBoard = currentBoard;

    }


    public PuzzleState(int[] currentBoard, int costToThis, int outOfPlace)
    {
        this.currentBoard = currentBoard;
        this.costToThis = costToThis;
        this.outOfPlace = outOfPlace;
    }



    /**
     * f(n), is the cost for going to the new state (1); is added to heuristic cost
     *
     * @return 1
     */
    public int getCostToThis()
    {
        return this.costToThis;
    }

    public void setCostToThis(int costToThis) {
        this.costToThis = costToThis;
    }

    private int calcOutOfPlace(int[] board)
    {
        int outOfPlace = 0;
        for (int i = 0; i < board.length; i++)
        {
            if (board[i] != GOAL_STATE[i])
            {
                outOfPlace++;
            }
        }
        return outOfPlace;
    }

    public int getOutOfPlace() {
        return outOfPlace;
    }

    /**
     *
     * @return the place of 0 (Hole) in the board, -1 in case of error.
     */
    private int findHole()
    {
        for (int i = 0; i < PUZZLE_SIZE; i++)
            if (currentBoard[i] == 0)
                return i;

        return -1;
    }

    /**
     *
     * @param state
     * @return a copy of the state given to the function
     */
    private int[] copyBoard(int[] state)
    {
        int[] result = new int[PUZZLE_SIZE];
        System.arraycopy(state, 0, result, 0, PUZZLE_SIZE);
        return result;
    }


    public ArrayList<PuzzleState> genSuccessorList()
    {
        ArrayList<PuzzleState> successorNodes = new ArrayList<PuzzleState>();
        int holePlace = findHole();

        if (holePlace / 3 < 2)  // hole is in the top two rows, we can shift it one row down
            genSuccessorNode(holePlace, holePlace + 3, successorNodes);

        if (holePlace / 3 > 0)  // hole is in the bottom two rows, we can shift it one row up
            genSuccessorNode(holePlace, holePlace - 3, successorNodes);

        if (holePlace % 3 < 2)  // hole is in the left two rows, we can shift it one column right
            genSuccessorNode(holePlace, holePlace + 1, successorNodes);

        if (holePlace % 3 > 0) // hole is in the right two rows, we can shift it one column left
            genSuccessorNode(holePlace, holePlace - 1, successorNodes);

        return successorNodes;
    }

    private void genSuccessorNode(int hole, int replacedBlock, ArrayList<PuzzleState> nodes)
    {
        int cost = abs(hole - currentBoard[replacedBlock]);
        int[] boardCopy = copyBoard(currentBoard);
        boardCopy[hole] = currentBoard[replacedBlock];
        boardCopy[replacedBlock] = 0;
        int newOutOfPlace = calcOutOfPlace(boardCopy);
        nodes.add(new PuzzleState(boardCopy, cost, newOutOfPlace));
    }

    public boolean isGoal()
    {
        for (int i = 0; i < PUZZLE_SIZE; i++)
            if (currentBoard[i] != GOAL_STATE[i])
                return false;

        return true;
    }

    /**
     *
     * @param state the state we want to compare with currBoard
     * @return true if two states have the same board
     */
    public boolean equals(PuzzleState state)
    {
        return Arrays.equals(currentBoard, state.getCurrentBoard());
    }

    public void printCurrState()
    {
        System.out.println("+---+---+---+");
        for (int i = 0; i < 3; i++)
        {
            System.out.print("|");
            for (int j = 0; j < 3; j++)
                System.out.print(" " + currentBoard[3 * i + j] + " |");
            System.out.println("\n+---+---+---+");
        }
    }
}
