import java.util.Scanner;

/**
 * Created by A. Goldani on 2019-01-06.
 * Student ID: 9512762107
 * BlindSearch
 */

public class Main {
    public static void main(String[] args) {
//        1 2 3 4 5 6 7 0 8

        int[] puzzleArray = new int[9];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Puzzle Array: ");
        for (int i = 0; i < 9; i++) {
            puzzleArray[i] = scanner.nextInt();
        }
        System.out.print("Enter Search Method:\n1. BFS\n2. DFS\n3. A*\n4. BDS\n5. UCS\n");
        int choice = scanner.nextInt();

        switch (choice)
        {
            case 1:
                BFS.search(puzzleArray);
            case 2:
                DFS.search(puzzleArray);
            case 3:
                AStar.search(puzzleArray);
            case 4:
                BDS.search(puzzleArray);
            case 5:
                UCS.search(puzzleArray);
        }

    }

}
