import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class Boggle
{
    private static String[] dictionary;
    private static BoardPoint[][] board;
    private static final ArrayList<String> goodWords = new ArrayList<String>();

    public static String[] findWords(char[][] board, String[] dictionary)
    {
        Boggle.dictionary = dictionary;

        // New version of the board that replaces the character board with a board made up of BoardPoint objects.
        Boggle.board = new BoardPoint[board.length][board[0].length];


        // Set up the 2d Array of BoardPoints.
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                Boggle.board[i][j] = new BoardPoint(i, j, board[i][j]);
            }
        }

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                dfs(Boggle.board[i][j], "");
            }
        }




        // #TODO: figure this out
        // for each traversal, go as far as you can, setting visited to true as u go, if already visited can't go there. Need to check if when at a given word,
        // can this word possibly be longer? If not , go back, otherwise, keep searching. When going back, set the point we are leaving back to false for visited.


        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    private static void dfs(BoardPoint point, String currentWord)
    {
        if (point.isVisited())
        {
            return;
        }
        if (!checkIsPrefixInDictionary(currentWord))
        {
            return;
        }
        if (checkIsWordInDictionary(currentWord))
        {
            if (!goodWords.contains(currentWord))
            {
                goodWords.add(currentWord);
            }
        }

        point.setVisited(true);
        for (BoardPoint surroundingPoint : getSurroundingPoints(point))
        {
            dfs(surroundingPoint, currentWord + point.getCharacter());
        }
        point.setVisited(false);
    }


    // Checks to see if a given string is a prefix of any word in the dictionary.
    private static boolean checkIsPrefixInDictionary(String potentialPrefix)
    {
        for (String word : dictionary)
        {
            if (word.startsWith(potentialPrefix))
            {
                return true;
            }
        }
        return false;
    }

    // Checks to see if a given string is a word the dictionary.
    private static boolean checkIsWordInDictionary(String potentialWord)
    {
        for (String word : dictionary)
        {
            if (word.equals(potentialWord))
            {
                return true;
            }
        }
        return false;
    }

    // Gets all valid surrounding board points on the board from a given starting point.
    private static ArrayList<BoardPoint> getSurroundingPoints(BoardPoint startingPoint)
    {
        int xCord = startingPoint.getxPos();
        int yCord = startingPoint.getyPos();

        ArrayList<BoardPoint> surrounding = new ArrayList<>();

        // This is really dumb, might try to make it with a loop later but it works for now.
        if (xCord - 1 >= 0)
        {
            surrounding.add(board[xCord - 1][yCord]);
        }
        if (xCord + 1 < board.length)
        {
            surrounding.add(board[xCord + 1][yCord]);
        }
        if (yCord - 1 >= 0)
        {
            surrounding.add(board[xCord][yCord - 1]);
        }
        if (yCord + 1 < board.length)
        {
            surrounding.add(board[xCord][yCord + 1]);
        }
        return surrounding;
    }
}