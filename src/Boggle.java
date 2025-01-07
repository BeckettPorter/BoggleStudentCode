import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<String>();

        // TODO: Complete the function findWords(). Add all words that are found both on the board
        //  and in the dictionary.

        BoardPoint[][] newBoard = new BoardPoint[board.length][board[0].length];
        Stack<BoardPoint> stack = new Stack<>();

        // Set up the 2d Array of BoardPoints.
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                newBoard[i][j] = new BoardPoint(i, j, board[i][j]);
            }
        }



        // For each BoardPoint on the board.
        for (int i = 0; i < newBoard.length; i++)
        {
            for (int j = 0; j < newBoard[0].length; j++)
            {
                stack.add(newBoard[i][j]);

                while(!stack.isEmpty())
                {
                    for (BoardPoint point : getSurroundingPoints(stack.peek(), newBoard))
                    {
                        stack.pop().setVisited(true);
                        if (!point.isVisited())
                        {
                            stack.add(point);
                        }
                    }
                }
            }
        }

        // for each traversal, go as far as you can, setting visited to true as u go, if already visited can't go there. Need to check if when at a given word,
        // can this word possibly be longer? If not , go back, otherwise, keep searching. When going back, set the point we are leaving back to false for visited.


        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    private static ArrayList<BoardPoint> getSurroundingPoints(BoardPoint startingPoint, BoardPoint[][] board)
    {
        int xCord = startingPoint.getxPos();
        int yCord = startingPoint.getyPos();

        ArrayList<BoardPoint> surrounding = new ArrayList<>();

        if (xCord - 1 > 0)
        {
            surrounding.add(board[xCord - 1][yCord]);
        }
        if (xCord + 1 < board.length)
        {
            surrounding.add(board[xCord + 1][yCord]);
        }
        if (yCord - 1 > 0)
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
