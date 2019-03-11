/**
 * Created by A. Goldani on 2019-01-18.
 * Student ID: 9512762107
 * BlindSearch
 */

public class PuzzleNode implements Comparable<PuzzleNode>
{

    private PuzzleState selfState;
    private PuzzleNode parentNode;

    private int cost;

    public int getCost() {
        return cost;
    }

    /**
     * Constructor of the first search node, no parent
     *
     * @param selfState
     */
    public PuzzleNode(PuzzleState selfState)
    {
        this.parentNode = null;
        this.selfState = selfState;
        this.cost = 0;
    }

    /**
     * Constructor for the other nodes of the search tree
     *
     * @param parentNode
     * @param selfState
     */
    public PuzzleNode(PuzzleNode parentNode, PuzzleState selfState) {
        this.parentNode = parentNode;
        this.selfState = selfState;
        this.cost = this.getSelfState().getCostToThis() + parentNode.getCost();
    }

    /**
     * Constructor
     *
     * @param parentNode
     * @param selfState
     * @param heuristicCost
     */
    public PuzzleNode(PuzzleNode parentNode, PuzzleState selfState, int heuristicCost){
        this.parentNode = parentNode;
        this.selfState = selfState;
        this.cost = this.getSelfState().getCostToThis() + parentNode.getCost() + heuristicCost;
    }

    public PuzzleState getSelfState() {
        return selfState;
    }

    public PuzzleNode getParentNode() {
        return parentNode;
    }


    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < selfState.getCurrentBoard().length; i++) {
            result = result * 10 + selfState.getCurrentBoard()[i];
        }
        return result;
    }


    @Override
    public int compareTo(PuzzleNode o) {
        if (this.getCost() < o.getCost())
            return -1;
        else if (this.getCost() > o.getCost())
            return 1;
        return 0;
    }
}
