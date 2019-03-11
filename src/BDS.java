import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by A. Goldani on 2019-01-24.
 * Student ID: 9512762107
 * BlindSearch
 */

@SuppressWarnings("ALL")
public class BDS
{
    private static final int[] GOAL_STATE = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};

    public static void search(int[] board)
    {
        PuzzleNode root = new PuzzleNode(new PuzzleState(board));
        Queue<PuzzleNode> rootQueue = new LinkedList<PuzzleNode>();

        PuzzleNode goal = new PuzzleNode(new PuzzleState(GOAL_STATE));
        Queue<PuzzleNode> goalQueue = new LinkedList<PuzzleNode>();

        rootQueue.add(root);
        goalQueue.add(goal);
        performSearch(rootQueue, goalQueue);
    }

    private static void performSearch(Queue<PuzzleNode> rQueue, Queue<PuzzleNode> gQueue)
    {
        while (!rQueue.isEmpty() && !gQueue.isEmpty())
        {
            PuzzleNode tempRoot = rQueue.poll();
            PuzzleNode tempGoal = gQueue.poll();

            ArrayList<PuzzleNode> rootVisited = new ArrayList<PuzzleNode>();
            ArrayList<PuzzleState> rootSuccessors = tempRoot.getSelfState().genSuccessorList();

            ArrayList<PuzzleNode> goalVisited = new ArrayList<PuzzleNode>();
            ArrayList<PuzzleState> goalSuccessors = tempGoal.getSelfState().genSuccessorList();

            for (int i = 0; i < rootSuccessors.size(); i++) {
                PuzzleNode newRNode = new PuzzleNode(tempRoot, rootSuccessors.get(i));
                if (!rootVisited.contains(newRNode))
                {
                    rQueue.add(newRNode);
                    rootVisited.add(newRNode);
                }
            }

            for (int i = 0; i < goalSuccessors.size(); i++) {
                PuzzleNode newGNode = new PuzzleNode(tempGoal, goalSuccessors.get(i));
                if (!goalVisited.contains(newGNode))
                {
                    gQueue.add(newGNode);
                    goalVisited.add(newGNode);
                }
            }


            for (int i = 0; i < rootVisited.size(); i++) {
                for (int j = 0; j < goalVisited.size(); j++) {
//                    rootVisited.get(i).getSelfState().printCurrState();
//                    goalVisited.get(j).getSelfState().printCurrState();
                    if (goalVisited.get(j).getSelfState().equals(rootVisited.get(i).getSelfState())) {
                        System.out.println("Root stack:");
                        PuzzleNode temp = rootVisited.get(i);

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
                        while (!pathToGoal.isEmpty())
                        {
                            temp = pathToGoal.pop();
                            temp.getSelfState().printCurrState();
                            System.out.println("=============");
                        }

                        System.out.println("Goal Queue:");

                        temp = goalVisited.get(j);
                        Queue<PuzzleNode> goalToRoot = new LinkedList<PuzzleNode>();
                        goalToRoot.add(temp);
                        temp = temp.getParentNode();

                        while (temp.getParentNode() != null)
                        {
                            goalToRoot.add(temp);
                            temp = temp.getParentNode();
                        }
                        goalToRoot.add(temp);

                        pathSize = goalToRoot.size();

//                        temp = goalToRoot.poll();
                        System.out.println("=============");
                        while (!goalToRoot.isEmpty())
                        {
                            temp = goalToRoot.poll();
                            temp.getSelfState().printCurrState();
                            System.out.println("=============");
                        }

                        System.exit(0);
                    }
                }
            }
        }
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
}
