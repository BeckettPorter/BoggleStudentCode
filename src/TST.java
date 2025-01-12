public class TST
{
    private TSTNode root;

    // Constructor that sets the root to have the first character of the first word in the dictionary.
    public TST(String firstDictionaryWord)
    {
        root = new TSTNode(false, firstDictionaryWord.charAt(0));
    }

    // Method that allows me to call insert with just a word
    public void insert(String word)
    {
        insert(root, word, 0);
    }

    // Actual insert method that recursively inserts a word into a TST
    public void insert(TSTNode node, String word, int index)
    {
        // Base case, check if index is word length, if it is, set it to be a word and return.
        if (index == word.length())
        {
            node.setWord(true);
            return;
        }

        // Get the "nextIndex" or the desired index we want to go to next
        int nextIndex = getNextIndex(node, word, index);

        // Then get the child node at that desired index
        TSTNode desiredNode = node.getNextNodes()[nextIndex];

        // If we don't have a node yet at the desired next node location, since we are inserting, make a node there
        // and set it as a child of the current node to link it.
        if (desiredNode == null)
        {
            desiredNode = new TSTNode(false, word.charAt(index));
            node.setNextNode(nextIndex, desiredNode);
        }

        // If we are going down the middle path, then increment index by 1, otherwise do not.
        if (nextIndex == 1)
        {
            insert(desiredNode, word, index + 1);
        }
        else
        {
            insert(desiredNode, word, index);
        }
    }



    // Contains method that just calls the actual contains method with properly setup vars.
    public boolean contains(String word)
    {
        return contains(root, word, 0);
    }


    // Actual contains method that recursively finds if the TST contains a word.
    public boolean contains(TSTNode node, String word, int index)
    {
        // Base case to see if we have reached the end of the word, then return if it is a word or not.
        if (index == word.length())
        {
            return node.isWord();
        }

        // Get the "desiredNode" which is the target next node
        TSTNode desiredNode = node.getNextNodes()[getNextIndex(node, word, index)];

        // If this next node doesn't exist, the word isn't in the TST, thus not in the dictionary, and is misspelled.
        if (desiredNode == null)
        {
            return false;
        }

        // If we are going down the middle path, then increment index by 1, otherwise do not.
        if (getNextIndex(node, word, index) == 1)
        {
            return contains(desiredNode, word, index + 1);
        }
        else
        {
            return contains(desiredNode, word, index);
        }
    }

    public TSTNode containsPrefix(String word, TSTNode startingNode)
    {
        return containsPrefix(startingNode, word, 0);
    }

    public TSTNode containsPrefix(TSTNode node, String word, int index)
    {
        // Base case to see if we have reached the end of the word, then return true because the prefix exists.
        if (index == word.length())
        {
            return node;
        }

        // Get the "desiredNode" which is the target next node
        TSTNode desiredNode = node.getNextNodes()[getNextIndex(node, word, index)];

        // If this next node doesn't exist, the word isn't in the TST, thus not in the dictionary, and is misspelled.
        if (desiredNode == null)
        {
            return null;
        }

        // If we are going down the middle path, then increment index by 1, otherwise do not.
        if (getNextIndex(node, word, index) == 1)
        {
            return containsPrefix(desiredNode, word, index + 1);
        }
        else
        {
            return containsPrefix(desiredNode, word, index);
        }
    }

    // Method that tells me what route (or index) to traverse down. If the character is a match, go down the middle,
    // otherwise go either left or right depending on whether it is higher or lower.
    private static int getNextIndex(TSTNode node, String word, int index)
    {
        int nextIndex;
        if (word.charAt(index) < node.getCharacter())
        {
            nextIndex = 0;
        }
        else if (word.charAt(index) > node.getCharacter())
        {
            nextIndex = 2;
        }
        else
        {
            nextIndex = 1;
        }
        return nextIndex;
    }

    public TSTNode getRoot()
    {
        return root;
    }
}