package dictionary; 

/**
 * This is a BST which represents Dictionary, containing WordNodes with a word 
 * and a defintion
 * 
 * You will implement the recursive helper method for removeWord(), to complete 
 * the removal functionality of the dictionary.
 */
public class BSTDictionary {

    // The root node of this BST
    private WordNode root;

    /**
     * This method is provided to you. 
     * 
     * This calls your removeWordHelper() method to remove the given Word from the dictionary, using Hibbard deletion for BSTs
     * 
     * @param word
     * @return
     */
    public void removeWord(String word) {
        // DO NOT EDIT
        root = removeWordHelper(root, word);
    }

    /**
     * This is a recursive helper method to delete a node from a BST.
     *  
     * There are 4 main cases to handle:
     *      1) curr is null
     *      2) word is to the left of curr.getWord() 
     *      3) word is to the right of curr.getWord() 
     *      4) word is equal to curr.getWord() 
     * 
     * The fourth case above has 4 cases of its own:
     *      1) The target has no children (return null)
     *      2) The target only has a left child (return left)
     *      3) The target only has a right child (return right)
     *      4) The target has both children (replace with inorder successor)
     * 
     * View the assignment description for more information on filling out the above cases.
     * 
     * @param word the word to delete
     * @return Recursively return WordNodes, to modify the structure of the tree as you traverse
     */
    private WordNode removeWordHelper(WordNode curr, String word) {
//If a deleted node has no children, simply unhook it from its parent. 
//If a deleted node has one child, replace it with that child.
//If a deleted node has two children, replace it with its inorder successor
//An inorder successor is the next node in sequence. i.e. the smallest node greater than the current node (aka the smallest node in the target’s right subtree)
//To find the inorder successor of a node: move once to the right, then as far left as possible. 
    //base case
    if (curr == null) {
        return null;
    }

    //deleted word less than curr- left subtree
    if (word.compareTo(curr.getWord()) < 0) {
    curr.setLeft(removeWordHelper(curr.getLeft(), word));
    }
    //deleted word greater than curr- right subtree

    else if (word.compareTo(curr.getWord()) > 0) {
        curr.setRight(removeWordHelper(curr.getRight(), word));
    }
    //deleted word same as curr- delete 

    else {
        //one or no children
        if (curr.getLeft() == null) {
            return curr.getRight();
        } else if (curr.getRight() == null) {
            return curr.getLeft();
        } else {
            //2 children
            WordNode TEMPP = findMin(curr.getRight());

            WordNode NewNODE = new WordNode(TEMPP.getWord(), TEMPP.getDefinition());
            NewNODE.setLeft(curr.getLeft());
            NewNODE.setRight(curr.getRight());

            NewNODE.setRight(removeWordHelper(curr.getRight(), TEMPP.getWord()));

            return NewNODE;
        }
        
    }
    return curr; 
}


    /**
     * This method is provided for you.
     * Helper method to find the minimum node of a tree
     * 
     * While curr's left child is not null, move the curr pointer left.
     * After, return curr.
     * 
     * @param node
     * @return
     */
    private WordNode findMin(WordNode curr) {
        // DO NOT EDIT
        while (curr != null && curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        return curr;
    }
    

    /**
     * This method is provided for you, do not edit it.
     * 
     * This inserts a new WordNode in this BST, containing the given
     * word and the given definition
     * 
     * @param word       The word to add
     * @param definition The definition of the word
     */
    public void addWord(String word, String definition) {
        // DO NOT EDIT
        if (root == null) {
            root = new WordNode(word, definition);
            return;
        }
        WordNode ptr = root;
        while (ptr != null) {
            if (word.compareTo(ptr.getWord()) < 0) {
                if (ptr.getLeft() == null) {
                    ptr.setLeft(new WordNode(word, definition));
                    return;
                }
                ptr = ptr.getLeft();
            } else if (word.compareTo(ptr.getWord()) > 0) {
                if (ptr.getRight() == null) {
                    ptr.setRight(new WordNode(word, definition));
                    return;
                }
                ptr = ptr.getRight();
            } else {
                return;
            }
        }
    }

    /**
     * This method is provided for you, do not edit it.
     * 
     * @return the root node of this BST
     */
    public WordNode getRoot() {
        // DO NOT EDIT
        return this.root;
    }
}



