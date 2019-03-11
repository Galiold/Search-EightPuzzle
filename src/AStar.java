import java.util.*;

/**
 * Created by A. Goldani on 2019-01-24.
 * Student ID: 9512762107
 * BlindSearch
 */

public class AStar {
    public static void search(int[] board) {
        PuzzleNode root = new PuzzleNode(new PuzzleState(board));
        PriorityQueue<PuzzleNode> queue = new PriorityQueue<PuzzleNode>();
        queue.add(root);

        int searchCount = 0;

        while (!queue.isEmpty()) {
            PuzzleNode temp = queue.poll();

            if (!temp.getSelfState().isGoal())  // Goal not yet met
            {
                ArrayList<PuzzleState> tempSuccessors = temp.getSelfState().genSuccessorList();

                for (int i = 0; i < tempSuccessors.size(); i++) {
                    PuzzleNode visitedNode;
                    visitedNode = new PuzzleNode(temp, tempSuccessors.get(i), tempSuccessors.get(i).getOutOfPlace());
                    if (!isVisited(visitedNode))
                        queue.add(visitedNode);
                }
                searchCount++;

            } else    // Goal met
            {
                Stack<PuzzleNode> pathToGoal = new Stack<PuzzleNode>();
                pathToGoal.push(temp);
                temp = temp.getParentNode();

                while (temp.getParentNode() != null) {
                    pathToGoal.push(temp);
                    temp = temp.getParentNode();
                }
                pathToGoal.push(temp);

                int pathSize = pathToGoal.size();

                System.out.println("=============");
                for (int i = 0; i < pathSize; i++) {
                    temp = pathToGoal.pop();
                    temp.getSelfState().printCurrState();
                    System.out.println("=============");
                }
                System.out.println("A* took " + searchCount + " iterations.");
                System.exit(0);
            }


        }
    }

    private static boolean isVisited(PuzzleNode node) {
        boolean result = false;
        PuzzleNode checkNode = node;

        while (node.getParentNode() != null && !result) {
            if (node.getParentNode().getSelfState().equals(checkNode.getSelfState()))
                result = true;
            node = node.getParentNode();
        }
        return result;
    }
}
