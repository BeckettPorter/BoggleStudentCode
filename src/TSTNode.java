public class TSTNode
{
    // Instance variables
    private boolean isWord;
    private TSTNode[] nextNodes = new TSTNode[3];
    private char character;

    // Constructor that sets if it is a word or not and sets the character.
    public TSTNode(boolean isWord, char c)
    {
        this.isWord = isWord;
        this.character = c;
    }

    // Getters and Setters
    public boolean isWord()
    {
        return isWord;
    }

    public void setWord(boolean word)
    {
        isWord = word;
    }

    public TSTNode[] getNextNodes()
    {
        return nextNodes;
    }

    public void setNextNode(int index, TSTNode nodeToAdd)
    {
        this.nextNodes[index] = nodeToAdd;
    }

    public char getCharacter()
    {
        return character;
    }

    public void setCharacter(char character)
    {
        this.character = character;
    }
}