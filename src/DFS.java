import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by A. Goldani on 2019-01-24.
 * Student ID: 9512762107
 * BlindSearch
 */

public class DFS
{
    public static void search(int[]board)
    {
        PuzzleNode root = new PuzzleNode(new PuzzleState(board));
        Stack<PuzzleNode> stack = new Stack<PuzzleNode>();

        stack.add(root);

        performSearch(stack);
    }

    private static boolean isVisited(PuzzleNode node)
    {
        boolean result = false;
        PuzzleNode checkNode = node;

        while (node.getParentNode() != null && !result)
        {
            if (node.getParentNode().getSelfState().equals(checkNode.getSelfState()))
                result = true;

            node = node.getParentNode();
        }

        return result;
    }

    private static void performSearch(Stack<PuzzleNode> stack)
    {
        int searchCount = 1;

        while (!stack.isEmpty())
        {
            PuzzleNode temp = stack.pop();

            if (!temp.getSelfState().isGoal())  // Goal not yet met
            {
                ArrayList<PuzzleState> tempSuccessors = temp.getSelfState().genSuccessorList();

                for (int i = 0; i < tempSuccessors.size(); i++)
                {
                    PuzzleNode newNode = new PuzzleNode(temp, tempSuccessors.get(i));

                    if (!isVisited(newNode))
                        stack.push(newNode);
                }
                searchCount++;
            }
            else    // Goal met
            {
                Stack<PuzzleNode> pathToGoal = new Stack<PuzzleNode>();
                pathToGoal.push(temp);
                temp = temp.getParentNode();

                while (temp.getParentNode() != null)
                {
                    pathToGoal.push(temp);
                    temp = temp.getParentNode();
                }
                pathToGoal.push(temp);

                int pathSize = pathToGoal.size();

                System.out.println("=============");
                for (int i = 0; i < pathSize; i++)
                {
                    temp = pathToGoal.pop();
                    temp.getSelfState().printCurrState();
                    System.out.println("=============");
                }
                System.out.println("DFS took "+ searchCount+ " iterations.");
                System.exit(0);
            }
        }
    }
}
