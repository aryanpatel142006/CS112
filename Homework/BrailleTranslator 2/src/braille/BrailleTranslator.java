package braille;

import java.util.ArrayList;

/**
 * Contains methods to translate Braille to English and English to Braille using
 * a BST.
 * Reads encodings, adds characters, and traverses tree to find encodings.
 * 
 * @author Seth Kelley
 * @author Kal Pandit
 */
public class BrailleTranslator {

    private TreeNode treeRoot;

    /**
     * Default constructor, sets symbols to an empty ArrayList
     */
    public BrailleTranslator() {
        treeRoot = null;
    }

    /**
     * Reads encodings from an input file as follows:
     * - One line has the number of characters
     * - n lines with character (as char) and encoding (as string) space-separated
     * USE StdIn.readChar() to read character and StdIn.readLine() after reading
     * encoding
     * 
     * @param inputFile the input file name
     */
    public void createSymbolTree(String inputFile) {

        /* PROVIDED, DO NOT EDIT */

        StdIn.setFile(inputFile);
        int numberOfChars = Integer.parseInt(StdIn.readLine());
        for (int i = 0; i < numberOfChars; i++) {
            Symbol s = readSingleEncoding();
            addCharacter(s);
        }
    }

    /**
     * Reads one line from an input file and returns its corresponding
     * Symbol object
     * 
     * ONE line has a character and its encoding (space separated)
     * 
     * @return the symbol object
     */
    public Symbol readSingleEncoding() {
        // WRITE YOUR CODE HERE
        Character character= StdIn.readChar();
        String enc = StdIn.readString();
        StdIn.readLine();
        Symbol sim = new Symbol() ;
        sim.setCharacter(character);
        sim.setEncoding(enc);

        return sim; // Replace this line, it is provided so your code compiles
    }

    /**
     * Adds a character into the BST rooted at treeRoot.
     * Traces encoding path (0 = left, 1 = right), starting with an empty root.
     * Last digit of encoding indicates position (left or right) of character within
     * parent.
     * 
     * @param newSymbol the new symbol object to add
     */
    public void addCharacter(Symbol newSymbol) {
        // WRITE YOUR CODE HERE
        if (treeRoot == null){ 
            Symbol firstNode = new Symbol("");
            TreeNode TNode = new TreeNode(firstNode,null,null);
            treeRoot = TNode;
        }
        TreeNode selector = treeRoot;
        TreeNode PreSelector;
        String enc = newSymbol.getEncoding();
        String partialEnc = "";

        int i =0;

        while (partialEnc.length()!=(enc.length()-1)){
            partialEnc += enc.charAt(i);
            PreSelector = selector;
            if (enc.charAt(i) == 'L'){
                selector=selector.getLeft();
                if (selector == null){
                    Symbol s = new Symbol(partialEnc);
                    TreeNode tempN = new TreeNode(s, null, null);
                    PreSelector.setLeft(tempN);
                    selector = PreSelector.getLeft();
                }
            } else if (enc.charAt(i) == 'R'){
                selector=selector.getRight();
                if (selector == null){
                    Symbol s = new Symbol(partialEnc);
                    TreeNode tempN = new TreeNode(s, null, null);
                    PreSelector.setRight(tempN);
                    selector = PreSelector.getRight();
                }
            }

            // if (selector == null){
            //     Symbol s = new Symbol(partialEnc);
            //     selector.setSymbol(s);
            // }

            // if (selector.getLeft() == null && selector.getRight() == null){
            //     Symbol s = new Symbol(partialEncoding)
            //     selector.setSymbol(s);
            // }
            
            // while (selector!=null) {
            //     if (selector.getLeft().getSymbol().getEncoding() == partialEnc){
            //         selector = selector.getLeft();
            //     }else{
            //         selector = selector.getRight();
            //     }
            // }
            i+=1;
        }
        PreSelector = selector;
        if (enc.charAt(i) == 'L'){
            selector=selector.getLeft();
            if (selector == null){
                TreeNode tempN = new TreeNode(newSymbol, null, null);
                PreSelector.setLeft(tempN);
                selector = PreSelector.getLeft();
            }
        }else if (enc.charAt(i) == 'R'){
            selector=selector.getRight();
            if (selector == null){
                TreeNode tempN = new TreeNode(newSymbol, null, null);
                PreSelector.setRight(tempN);
                selector = PreSelector.getRight();
            }
        }

        // if (selector == null){
        //     // Symbol s = new Symbol(newSymbol.getCharacter(),partialEncoding)
        //     selector.setSymbol(newSymbol);
        // }
 
 
    }

    /**
     * Given a sequence of characters, traverse the tree based on the characters
     * to find the TreeNode it leads to
     * 
     * @param encoding Sequence of braille (Ls and Rs)
     * @return Returns the TreeNode of where the characters lead to, or null if there is no path
     */
    
    public TreeNode getSymbolNode(String encoding) {
        // WRITE YOUR CODE HERE
        if (treeRoot == null){ 
            return null;
        }
        TreeNode selector = treeRoot;
        TreeNode PreSelector; // preselctor
        int i = 0;
        while (i!= (encoding.length()-1) && selector != null){
            PreSelector = selector;
            if (encoding.charAt(i) == 'L'){
                selector = selector.getLeft();
            }else if (encoding.charAt(i) == 'R'){
                selector = selector.getRight();
            }
            i++;
        }
        
        }else {
            return selector;
        }
         // Replace this line, it is provided so your code compiles
    }

   

    // private class Traverse {
    //     // TreeNode[] NodeL = new TreeNode[];
    //     // public static String verse(TreeNode tNode, char CHAR) {
    //     //     if (tNode.getLeft() == null && (tNode.getRight() == null)) {
    //     //         if (tNode.getSymbol().getCharacter() == CHAR){
    //     //             return tNode.getSymbol().getEncoding();
    //     //         }
    //     //     }
    //     //     if (tNode.getLeft() == null){
    //     //         return null;
    //     //     }
    //     //     if (tNode.getRight() == null){
    //     //         return null;
    //     //     }
            
    //     //     verse(tNode.getLeft(),CHAR);
    //     //     verse(tNode.getRight(),CHAR);
    //     //     return null;
    //     // }
    // }

    
     /**
     * Given a character to look for in the tree will return the encoding of the
     * character
     * 
     * @param character The character that is to be looked for in the tree
     * @return Returns the String encoding of the character
     */
    
    public String findBrailleEncoding(char character) {
        // WRITE YOUR CODE HERE
        class Traverse {
            private static void verse(TreeNode tNode, char CHAR,String[] l) {
                if (tNode.getLeft() == null && (tNode.getRight() == null)) {
                    if (tNode.getSymbol().getCharacter() == CHAR){
                        l[0] = tNode.getSymbol().getEncoding();
                    }
                    return;
                }
                if (tNode.getLeft() == null){
                    Traverse.verse(tNode.getRight(),CHAR,l);
                    return;
                }
                if (tNode.getRight() == null){
                    Traverse.verse(tNode.getLeft(),CHAR,l);
                    return;
                }
                
                // System.out.println(tNode.getSymbol().getEncoding());
                Traverse.verse(tNode.getLeft(),CHAR,l);
                Traverse.verse(tNode.getRight(),CHAR,l);
            }
            private static String traverse(TreeNode root, char CHAR){
                String[] l = new String[1];
                l[0] = null;
                Traverse.verse(root,CHAR,l);
                return l[0];
            }
            
        }
        // while (character != tempChar){
        // }
    
        // String STRING = verse(treeRoot,character);
        // if (STRING == ""){
        //     return null;
        // }else{
        //     return STRING;
        // }
        
        return Traverse.traverse(treeRoot,character);
        
    }

    /**
     * Given a prefix to a Braille encoding, return an ArrayList of all encodings that start with
     * that prefix
     * 
     * @param start the prefix to search for
     * @return all Symbol nodes which have encodings starting with the given prefix
     */
    public ArrayList<Symbol> encodingsStartWith(String start) {
        // WRITE YOUR CODE HERE
        class Traverse {
            private static void verse(TreeNode tNode,ArrayList<Symbol> l) {
                if (tNode.getLeft() == null && (tNode.getRight() == null)) {
                        l.add(tNode.getSymbol());
                        return;
                }
                if (tNode.getLeft() == null){
                    Traverse.verse(tNode.getRight(),l);
                    return;
                }
                if (tNode.getRight() == null){
                    Traverse.verse(tNode.getLeft(),l);
                    return;
                }
                
                // System.out.println(tNode.getSymbol().getEncoding());
                Traverse.verse(tNode.getLeft(),l);
                Traverse.verse(tNode.getRight(),l);
            }
            private static ArrayList<Symbol> traverse(TreeNode root){
                ArrayList<Symbol> l = new ArrayList<Symbol>();
                Traverse.verse(root,l);
                return l;
            }
            
        }
        ArrayList<Symbol> l = new ArrayList<Symbol>();
        TreeNode selector = treeRoot;
        TreeNode PreSelector;
        String enc = start;
        String partialEnc = "";

        int i =0;

        while (partialEnc.length()!=(enc.length()-1)){
            partialEnc += enc.charAt(i);
            PreSelector = selector;
            if (enc.charAt(i) == 'L'){
                selector=selector.getLeft();
                if (selector == null){
                    return null;
                }
            } else if (enc.charAt(i) == 'R'){
                selector=selector.getRight();
                if (selector == null){
                    return null;
                }
            }
            i+=1;
        }
      
        // for (int j = 0; i<start.length(); j++){
        //     if ((start.charAt(j) != 'L') || (start.charAt(j) != 'L')){
        //         Symbol s = new Symbol();
        //         l.add(s);
        //         return l;
        //     } 
        // }

        // if (selector == null){
        //     Symbol s = new Symbol(null);
        //     l.add(s);
        //     return l;
        // }
        return Traverse.traverse(selector); // Replace this line, it is provided so your code compiles
    }

    /**
     * Reads an input file and processes encodings six chars at a time.
     * Then, calls getSymbolNode on each six char chunk to get the
     * character.
     * 
     * Return the result of all translations, as a String.
     * @param input the input file
     * @return the translated output of the Braille input
     */
    public String translateBraille(String input) {
        // WRITE YOUR CODE HERE

        return null; // Replace this line, it is provided so your code compiles
    }


    /**
     * Given a character, delete it from the tree and delete any encodings not
     * attached to a character (ie. no children).
     * 
     * @param symbol the symbol to delete
     */
    public void deleteSymbol(char symbol) {
        // WRITE YOUR CODE HERE
 
    }

    public TreeNode getTreeRoot() {
        return this.treeRoot;
    }

    public void setTreeRoot(TreeNode treeRoot) {
        this.treeRoot = treeRoot;
    }

    public void printTree() {
        printTree(treeRoot, "", false, true);
    }

    private void printTree(TreeNode n, String indent, boolean isRight, boolean isRoot) {
        StdOut.print(indent);

        // Print out either a right connection or a left connection
        if (!isRoot)
            StdOut.print(isRight ? "|+R- " : "--L- ");

        // If we're at the root, we don't want a 1 or 0
        else
            StdOut.print("+--- ");

        if (n == null) {
            StdOut.println("null");
            return;
        }
        // If we have an associated character print it too
        if (n.getSymbol() != null && n.getSymbol().hasCharacter()) {
            StdOut.print(n.getSymbol().getCharacter() + " -> ");
            StdOut.print(n.getSymbol().getEncoding());
        }
        else if (n.getSymbol() != null) {
            StdOut.print(n.getSymbol().getEncoding() + " ");
            if (n.getSymbol().getEncoding().equals("")) {
                StdOut.print("\"\" ");
            }
        }
        StdOut.println();

        // If no more children we're done
        if (n.getSymbol() != null && n.getLeft() == null && n.getRight() == null)
            return;

        // Add to the indent based on whether we're branching left or right
        indent += isRight ? "|    " : "     ";

        printTree(n.getRight(), indent, true, false);
        printTree(n.getLeft(), indent, false, false);
    }

}
