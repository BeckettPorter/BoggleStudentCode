public class BoardPoint
{
    private boolean visited = false;
    private int xPos;
    private int yPos;
    private char character;

    public BoardPoint(int xPos, int yPos, char character)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.character = character;
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public int getxPos()
    {
        return xPos;
    }

    public int getyPos()
    {
        return yPos;
    }

    public char getCharacter()
    {
        return character;
    }
}
