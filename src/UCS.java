import java.util.*;

/**
 * Created by A. Goldani on 2019-01-24.
 * Student ID: 9512762107
 * BlindSearch
 */

public class UCS
{
    public static void search(int[] board)
    {
        PuzzleNode root = new PuzzleNode(new PuzzleState(board));
        PriorityQueue<PuzzleNode> queue = new PriorityQueue<PuzzleNode>();
        queue.add(root);
        performSearch(queue);
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

    public static void performSearch(PriorityQueue<PuzzleNode> queue)
    {
        int searchCount = 1;    // number of iterations

        while (!queue.isEmpty())
        {
            PuzzleNode temp = (PuzzleNode) queue.poll();

            if (!temp.getSelfState().isGoal())  // Goal not met
            {
                ArrayList<PuzzleState> tempSuccessors = temp.getSelfState().genSuccessorList();

                for (int i = 0; i < tempSuccessors.size(); i++)
                {
                    PuzzleNode newTempNode = new PuzzleNode(temp, tempSuccessors.get(i));

                    if (!isVisited(newTempNode))
                        queue.add(newTempNode);
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
                System.out.println("UCS took "+ searchCount+ " iterations.");
                System.exit(0);
            }
        }

    }
}