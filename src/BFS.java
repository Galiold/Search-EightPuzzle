import java.util.*;

/**
 * Created by A. Goldani on 2019-01-19.
 * Student ID: 9512762107
 * BlindSearch
 */

public class BFS
{
    public static void search(int[] board)
    {
        PuzzleNode root = new PuzzleNode(new PuzzleState(board));
        Queue<PuzzleNode> queue = new LinkedList<PuzzleNode>();

//        ((LinkedList<PuzzleNode>) queue).add(root);
        queue.add(root);
        performSearch(queue);
    }

//    private static boolean isVisited(PuzzleNode node)
//    {
//        boolean result = false;
//        PuzzleNode checkNode = node;
//
//        while (node.getParentNode() != null && !result)
//        {
//            if (node.getParentNode().getSelfState().equals(checkNode.getSelfState()))
//                result = true;
//            node = node.getParentNode();
//        }
//
//        return result;
//    }

    public static void performSearch(Queue<PuzzleNode> queue)
    {
        int searchCount = 1;    // number of iterations

        while (!queue.isEmpty())
        {
            PuzzleNode temp = (PuzzleNode) queue.poll();

            Set<PuzzleNode> visited = new HashSet<PuzzleNode>();
            visited.add(temp);

            if (!temp.getSelfState().isGoal())  // Goal not met
            {
                ArrayList<PuzzleState> tempSuccessors = temp.getSelfState().genSuccessorList();

                for (int i = 0; i < tempSuccessors.size(); i++)
                {
                    PuzzleNode newTempNode = new PuzzleNode(temp, tempSuccessors.get(i));

//                    if (!isVisited(newTempNode))
//                        queue.add(newTempNode);
                    if (!visited.contains(newTempNode))
                    {
                        queue.add(newTempNode);
                        visited.add(newTempNode);
                    }


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
                System.out.println("BFS took "+ searchCount+ " iterations.");
                System.exit(0);
            }
        }

    }
}
