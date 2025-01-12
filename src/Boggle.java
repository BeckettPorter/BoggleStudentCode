import java.util.*;

// Boggle by Beckett Porter, Zachary Blickensderfer
// Completed January 10th, 2025

public class Boggle
{
    private static TST TSTDictionary;
    // mainBoard is a version of the board made up of BoardPoint objects.
    private static BoardPoint[][] mainBoard;
    // Make goodWords a hash set to avoid adding any duplicates in an efficient way.
    private static HashSet<String> goodWords;

    public static String[] findWords(char[][] board, String[] dictionary)
    {
        // Reset after each run and make a new TST.
        TSTDictionary = new TST(dictionary[0]);

        // Add all the dictionary words to the TST.
        for (String word : dictionary)
        {
            TSTDictionary.insert(word);
        }

        // Reset the goodWords array with a blank array.
        goodWords = new HashSet<>();

        // New version of the board that replaces the character board with a board made up of BoardPoint objects.
        Boggle.mainBoard = new BoardPoint[board.length][board[0].length];

        // Set up the 2d Array of BoardPoints.
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                Boggle.mainBoard[i][j] = new BoardPoint(i, j, board[i][j]);
            }
        }

        // Go through the board of BoardPoints and run depth first search on each point.
        for (int i = 0; i < mainBoard.length; i++)
        {
            for (int j = 0; j < mainBoard[0].length; j++)
            {
                dfs(Boggle.mainBoard[i][j], "", TSTDictionary.getRoot());
            }
        }

        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    // Recursive depth first search which goes from a starting point and searches around
    // the rest of the board for words, adding them to goodWords if they are valid.
    private static void dfs(BoardPoint point, String currentWord, TSTNode currentNode)
    {
        // If the point is already visited, return.
        if (point.isVisited())
        {
            return;
        }
        // Check if the word exists as at least a prefix in the dictionary, if not return.
        // #todo: RETURN NEXT NODE IN prefix search, null if doesn't exist (acts as bool)
        TSTNode prefixStartNode = TSTDictionary.containsPrefix(currentWord, currentNode);
        if (prefixStartNode == null)
        {
            return;
        }
        // Check if the word exists as a full word in the dictionary, if so, add it to good words.
        if (TSTDictionary.contains(currentWord))
        {
            goodWords.add(currentWord);
        }


        // Set a point as visited, then send calls to search surrounding points, and once those are complete,
        // set this point as not visited again.
        point.setVisited(true);
        for (BoardPoint surroundingPoint : getSurroundingPoints(point))
        {
            dfs(surroundingPoint, currentWord + point.getCharacter(), prefixStartNode);
        }
        point.setVisited(false);
    }

    // Gets all valid surrounding board points on the board from a given starting point.
    private static ArrayList<BoardPoint> getSurroundingPoints(BoardPoint startingPoint)
    {
        int xCord = startingPoint.getxPos();
        int yCord = startingPoint.getyPos();

        ArrayList<BoardPoint> surrounding = new ArrayList<>();

        // This is really dumb, might try to make it with a loop later, but it works for now.
        if (xCord - 1 >= 0)
        {
            surrounding.add(mainBoard[xCord - 1][yCord]);
        }
        if (xCord + 1 < mainBoard.length)
        {
            surrounding.add(mainBoard[xCord + 1][yCord]);
        }
        if (yCord - 1 >= 0)
        {
            surrounding.add(mainBoard[xCord][yCord - 1]);
        }
        if (yCord + 1 < mainBoard.length)
        {
            surrounding.add(mainBoard[xCord][yCord + 1]);
        }
        return surrounding;
    }
}