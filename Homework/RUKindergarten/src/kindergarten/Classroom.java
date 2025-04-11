package kindergarten;

/**
 * You're a kindergarten teacher and you have many students to teach.
 * You'll supervise students when they line up, when they're in class,
 * and when they're playing musical chairs!
 * 
 * @author Ethan Chou
 * @author Kal Pandit
 * @author Maksims Kurjanovics Kravcenko
 */
public class Classroom {
    private SNode studentsInLine; // when students are in line - refers to the front of a singly linked list
    private SNode musicalChairs; // when students are in musical chairs: holds a reference to the LAST student in
                                 // the CLL
    private boolean[][] openSeats; // represents seat positions in the classroom
    private Student[][] studentsInSeats; // represents students in their corresponding seats

    /**
     * Constructor for classrooms. Do not edit.
     * 
     * @param inLine          passes in students in line
     * @param mChairs         passes in musical chairs
     * @param seats           passes in availability
     * @param studentsSitting passes in students sitting
     */
    public Classroom(SNode inLine, SNode mChairs, boolean[][] seats, Student[][] studentsSitting) {
        studentsInLine = inLine;
        musicalChairs = mChairs;
        openSeats = seats;
        studentsInSeats = studentsSitting;
    }

    /**
     * Default constructor starts an empty classroom. Do not edit.
     */
    public Classroom() {
        this(null, null, null, null);
    }

    /**
     * This method simulates students filing in line, before they enter the
     * classroom.
     * 
     * It does this by reading students from an input file and adding each to the
     * front
     * of the studentsInLine linked list.
     * 
     * 1. Open the file using StdIn.setFile(filename);
     * 
     * 2. For each line of the input file:
     * 1. read a student from the file
     * 2. create a Student object with the student's info
     * 3. insert the Student to the FRONT of studentsInLine
     * 
     * Students are stored in reverse alphabetical order in the input file.
     * This method will put students in A-Z order.
     * 
     * DO NOT implement a sorting method, PRACTICE add to front.
     * 
     * @param filename the student input file
     */
    public void enterClassroom(String filename) {
        // WRITE YOUR CODE HERE

        // StdIn.setFile(filename);
        // int n = StdIn.readInt();
        // String firstName = StdIn.readString();
        // String lastName = StdIn.readString();
        // int height = StdIn.readInt();

        // Student S = new Student();
        // S.setFirstName(firstName);
        // S.setLastName(lastName);
        // S.setHeight(height);

        // studentsInLine.setStudent(S);



        StdIn.setFile(filename);
        int n = StdIn.readInt();

        for (int i = 0; i<n ; i++){
            
            String firstName = StdIn.readString();
            String lastName = StdIn.readString();
            int height = StdIn.readInt();

            Student S = new Student();
            S.setFirstName(firstName);
            S.setLastName(lastName);
            S.setHeight(height);

            SNode Node = new SNode();
            Node.setStudent(S);
            Node.setNext(studentsInLine);
            studentsInLine = Node;

        }


    }

    /**
     * 
     * This method creates the open seats in the classroom.
     * 
     * 1. Open the file using StdIn.setFile(seatingChart);
     * 
     * 2. You will read the seating input file with the format:
     * An integer representing the number of rows in the classroom
     * An integer representing the number of columns in the classroom
     * Number of r lines, each containing c true or false values (true represents
     * that a
     * seat is present in that column)
     * 
     * 3. Initialize openSeats and studentsInSeats arrays with r rows and c columns
     * 
     * 4. Update studentsInSeats with the booleans read from the input file
     * 
     * This method does not seat students in the seats.
     * 
     * @param openSeatsFile the seating chart input file
     */
    public void createSeats(String openSeatsFile) {
        // WRITE YOUR CODE HERE

        StdIn.setFile(openSeatsFile);
        int r = StdIn.readInt();
        int c = StdIn.readInt();
        // int r = StdIn.readInt();
        // System.out.println(r);
        // System.out.println(c);

        openSeats = new boolean[r][c];
        for (int i = 0; i<r; i++){
            for (int j = 0; j<c; j++){
                openSeats[i][j] = StdIn.readBoolean();
            }
        }
        
        studentsInSeats = new Student[r][c];
        
        
    }

    /**
     * 
     * This method simulates students moving from the line to their respective
     * seats.
     * 
     * Students are removed one by one from the line and inserted into
     * studentsInSeats
     * according to the seating positions in openSeats.
     * 
     * studentsInLine will then be empty at the end of this method.
     * 
     * NOTE: If the students just played musical chairs, the winner of musical
     * chairs is seated separately
     * by seatMusicalChairsWinner().
     */
    public void seatStudents() {
        // WRITE YOUR CODE HERE
        SNode Ptr = studentsInLine;
        while (Ptr != null){
            boolean terminate = false; 
            for (int i = 0; i<studentsInSeats.length; i++){
                for (int j = 0; j<studentsInSeats[0].length; j++){
                    if (openSeats[i][j] == true && studentsInSeats[i][j] == null){
                        studentsInSeats[i][j] = Ptr.getStudent();
                        // openSeats[i][j] = false;
                        terminate = true;
                        break;
                    }
                }
                if (terminate == true){
                    break;
                }
            }
            Ptr = Ptr.getNext();
            studentsInLine = Ptr;
        }
    }

    /**
     * Traverses studentsInSeats row by row, column by column removing a student
     * from their seat and adding them to the END of the musical chairs CLL.
     * 
     * NOTE: musicalChairs refers to the LAST student in the CLL.
     */
    public void insertMusicalChairs() {
        // WRITE YOUR CODE HERE
        for (int i = 0; i<studentsInSeats.length; i++){
            for (int j = 0; j<studentsInSeats[0].length; j++){
                if (studentsInSeats[i][j] != null){
                    if (musicalChairs == null){
                        SNode Node = new SNode();
                        Node.setStudent(studentsInSeats[i][j]);
                        studentsInSeats[i][j] = null;
                        Node.setNext(Node);
                        musicalChairs = Node;
                    }else{
                        SNode Node = new SNode();
                        Node.setStudent(studentsInSeats[i][j]);
                        Node.setNext(musicalChairs.getNext());
                        musicalChairs.setNext(Node);
                        studentsInSeats[i][j] = null;
                        musicalChairs = Node;
                    }
                }
            }
        }



        // System.out.println("THIS IS CRAZY FR");
        // System.out.println("----------------------------------------------------------------------");

        // SNode Ptr = musicalChairs;
        // while (Ptr != null){
        //     System.out.println(Ptr.getStudent().getFirstName());
        //     Ptr = Ptr.getNext();
        // }

        // System.out.println("----------------------------------------------------------------------");
    }
    /**
     * 
     * Removes a random student from the musicalChairs, if they can't find a seat.
     * Once eliminated students go back to the line.
     * 
     * @param size represents the number of students currently sitting in
     *             musicalChairs
     * 
     *             1. Use StdRandom.uniform(size) to pick the nth student (n=0 =
     *             student at front).
     *             2. Search for the selected student, and delete them from
     *             musicalChairs
     *             3. Call insertByName to insert the deleted student back to the
     *             line.
     * 
     *             The random value denotes the refers to the position of the
     *             student to be removed
     *             in the musicalChairs. 0 is the first student
     */
    public void moveStudentFromChairsToLine(int size) {

        int RandNumber = StdRandom.uniform(size); // gen of completely random number
        SNode prev = new SNode();
        SNode ptr = musicalChairs.getNext();
        
        while(RandNumber>0){ 
            prev = ptr;
            ptr = ptr.getNext();
    //     System.out.println("************************************************");
    //     System.out.println("THIS IS FROM FRONT");
    //     System.out.println(Ptr.getStudent().getFullName());
    //     System.out.println("************************************************");
            RandNumber = RandNumber-1;
        }
        Student stdLine = ptr.getStudent(); 

       
        if(ptr == musicalChairs){
            prev.setNext(ptr.getNext());
            musicalChairs = prev;
        } 
        else if(ptr == musicalChairs.getNext()){
            musicalChairs.setNext(ptr.getNext());
        }
        else{
            prev.setNext(ptr.getNext());
        }
        // Just testing
        // System.out.println(stdLine);
        insertByName(stdLine);

        // Finally done lol

        // // Generate a random index between 0 (inclusive) and size (exclusive)
        // int randIndex = StdRandom.uniform(size);
    
        // // Start with the first node in musicalChairs
        // SNode Ptr = musicalChairs;
        // SNode Prev = new SNode();
    
        // // If the index to remove is 0, we handle the special case where we remove the first node
        // if (randIndex == 0) {
        //     // Remove the first node by updating the head of the circular list
        //     musicalChairs = Ptr.getNext();
        //     // Insert the student into the studentsInLine list
        //     insertByName(Ptr.getStudent());
        //     return;  // Exit as we've already removed the first node
        // }
    
        // // Traverse the list to find the node to remove
        // for (int i = 0; i < randIndex; i++) {
        //     Prev = Ptr;
        //     Ptr = Ptr.getNext();
        // }
    
        // // Now Ptr is the node to be removed, and Prev is the node before it
        // // If Ptr is the last node, Prev will be the second-to-last node
        // if (Ptr.getNext() == musicalChairs) {
        //     // Removing the last node, update Prev to point to the first node
        //     Prev.setNext(musicalChairs);
        // } else {
        //     // Remove the node from the middle by updating Prev's next pointer
        //     Prev.setNext(Ptr.getNext());
        // }
    
        // // Insert the student into the studentsInLine list
        // insertByName(Ptr.getStudent());
        

        // WRITE YOUR CODE HERE

        // int randIndex = StdRandom.uniform(size);
        // SNode Ptr = musicalChairs;
        // SNode Prev = null;
        // if (randIndex == 0){
        //     musicalChairs = Ptr.getNext();
        //     System.out.println("************************************************");
        //     System.out.println("THIS IS FROM FRONT");
        //     System.out.println(Ptr.getStudent().getFullName());
        //     System.out.println("************************************************");
        //     insertByName(Ptr.getStudent());
        //     printMusicalChairs();

        // }else{
        //     int r = 0;
        //     while( Ptr != null && r <= randIndex+1){
        //         if (r == randIndex+1){
        //             insertByName(Ptr.getStudent());
        //             System.out.println("************************************************");
        //             System.out.println("THIS IS FROM OTHER PLACES");
        //             System.out.println(Ptr.getStudent().getFullName());
        //             System.out.println("************************************************");
        //             Prev.setNext(Ptr.getNext());
        //             printMusicalChairs();

        //         }
        //         Prev = Ptr;
        //         Ptr = Ptr.getNext();
        //         r++;
        //     }
        // }
        
        
        // boolean terminate = false;
        // for (int i = 0; i<studentsInSeats.length; i++){
        //     for (int j = 0; j<studentsInSeats[0].length; j++){
        //         if (randIndex == 0 && studentsInSeats[i][j] != null){
                    
        //             insertByName(studentsInSeats[i][j]);
        //             studentsInSeats[i][j] = null;
        //             terminate = true;
        //             break;
        //         }
        //         if (studentsInSeats[i][j] != null){
        //             randIndex--;
                    
        //         }
        //     }
        //     if (terminate == true){
        //         break;
        //     }

        // }

    }

    /**
     * Inserts a single student, eliminated from musical chairs, to the line
     * inserted
     * in ascending order by last name and then first name if students have the same
     * last name
     * 
     * USE compareNameTo on a student: < 0 = less than, > 0 greater than, = 0 equal
     * 
     * @param eliminatedStudent the student eliminated from chairs to insert
     */
    public void insertByName(Student eliminatedStudent) {

        // Initialize pointers to traverse the list
        SNode Ptr = studentsInLine;
        SNode Prev = null;
    
        // Traverse the list to find the correct position to insert
        while ((Ptr != null) && (Ptr.getStudent().compareNameTo(eliminatedStudent) < 0)) {
            Prev = Ptr;
            Ptr = Ptr.getNext();
        }
    
        // Create a new node for the eliminated student
        SNode Node = new SNode();
        Node.setStudent(eliminatedStudent);
        Node.setNext(null);
    
        // Case 1: Insert at the front (before the first node)
        if (Prev == null) {
            // New node becomes the first node
            Node.setNext(studentsInLine);
            studentsInLine = Node;
        } 
        // Case 2: Insert at the end (after the last node)
        else if (Ptr == null) {
            // Insert at the end, set the last node's next to the new node
            Prev.setNext(Node);
        } 
        // Case 3: Insert in the middle
        else {
            // Insert the new node between Prev and Ptr
            Prev.setNext(Node);
            Node.setNext(Ptr);
        }
        


        // WRITE YOUR CODE HERE
        // SNode Ptr = studentsInLine;
        // SNode Prev = null;
        // while (Ptr != null && Ptr.getStudent().compareNameTo(eliminatedStudent) < 0){
        //     Prev = Ptr;
        //     Ptr = Ptr.getNext();   
        // }

        // SNode Node = new SNode();
        // Node.setStudent(eliminatedStudent);
        // Node.setNext(null);
        // if (Prev == null){
        //     studentsInLine = Node;
        // } else if(Ptr == null){
        //     studentsInLine.setNext(Node);
        // }else {
        //     Node.setNext(Ptr.getNext());
        //     Ptr.setNext(Node);
        // }
    }

    /**
     * 
     * Removes ALL eliminated students from musical chairs and inserts those
     * students
     * back in studentsInLine in ascending name order (earliest to latest).
     * 
     * At the end of this method, all students will be in studentsInLine besides
     * the winner, who will only be in musicalChairs.
     * 
     * 1. Find the number of students currently in musicalChairs
     * 2. While there's more than 1 student in musicalChairs, call
     * moveRandomStudentFromChairsToLine()
     * --> pass the size from step (1) into the method call.
     */
    public void eliminateLosingStudents() {
        // WRITE YOUR CODE HERE
        SNode Ptr = musicalChairs.getNext();
        int size = 1;

        while (Ptr != null && Ptr != musicalChairs){
            size++;
            Ptr = Ptr.getNext();
        }
        while (size!=1){
            moveStudentFromChairsToLine(size);
            size--;
        }


    }

    /*
     * If musicalChairs (CLL) contains ONLY ONE student (the winner),
     * this method removes the winner from musicalChairs and inserts that student
     * into the first available seat in studentsInSeats. musicalChairs will then be
     * empty.
     * 
     * ASSUME eliminateLosingStudents is called before this method is.
     * 
     * NOTE: This method does nothing if there is more than one student in
     * musicalChairs
     * OR if musicalChairs is empty.
     */
    public void seatMusicalChairsWinner() {
        // WRITE YOUR CODE HERE
        Boolean terminate = false;
        if (musicalChairs == null){
            return;
        }


        for (int i = 0; i< studentsInSeats.length ; i++){
            for (int j = 0; j< studentsInSeats[0].length ; j++){
         
                if (openSeats[i][j] == true){
                    studentsInSeats[i][j] = musicalChairs.getStudent();
                    musicalChairs = null;
                    terminate = true;
                    break;
                }
            }
            if (terminate == true){
                break;
            }
        }
    }

    /**
     * 
     * This method simulates the game of Msical Chairs!
     * 
     * This method calls previously-written methods to remove students from
     * musicalChairs until there is only one student (the winner), then seats the
     * winner
     * and then seat the students from the studentsInline.
     * 
     * DO NOT UPDATE THIS METHOD
     */
    public void playMusicalChairs() {
        eliminateLosingStudents();
        seatMusicalChairsWinner();
        seatStudents();
    }

    /**
     * Used by driver to display students in line
     * DO NOT edit.
     */
    public void printStudentsInLine() {

        // Print studentsInLine
        StdOut.println("Students in Line:");
        if (studentsInLine == null) {
            StdOut.println("EMPTY");
        }

        for (SNode ptr = studentsInLine; ptr != null; ptr = ptr.getNext()) {
            StdOut.print(ptr.getStudent().print());
            if (ptr.getNext() != null) {
                StdOut.print(" -> ");
            }
        }
        StdOut.println();
        StdOut.println();
    }

    /**
     * Prints the seated students; can use this method to debug.
     * DO NOT edit.
     */
    public void printSeatedStudents() {

        StdOut.println("Sitting Students:");

        if (studentsInSeats != null) {

            for (int i = 0; i < studentsInSeats.length; i++) {
                for (int j = 0; j < studentsInSeats[i].length; j++) {

                    String stringToPrint = "";
                    if (studentsInSeats[i][j] == null) {

                        if (openSeats[i][j] == false) {
                            stringToPrint = "X";
                        } else {
                            stringToPrint = "EMPTY";
                        }

                    } else {
                        stringToPrint = studentsInSeats[i][j].print();
                    }

                    StdOut.print(stringToPrint);

                    for (int o = 0; o < (10 - stringToPrint.length()); o++) {
                        StdOut.print(" ");
                    }
                }
                StdOut.println();
            }
        } else {
            StdOut.println("EMPTY");
        }
        StdOut.println();
    }

    /**
     * Prints the musical chairs; can use this method to debug.
     * DO NOT edit.
     */
    public void printMusicalChairs() {
        StdOut.println("Students in Musical Chairs:");

        if (musicalChairs == null) {
            StdOut.println("EMPTY");
            StdOut.println();
            return;
        }
        SNode ptr;
        for (ptr = musicalChairs.getNext(); ptr != musicalChairs; ptr = ptr.getNext()) {
            StdOut.print(ptr.getStudent().print() + " -> ");
        }
        if (ptr == musicalChairs) {
            StdOut.print(musicalChairs.getStudent().print() + " - POINTS TO FRONT");
        }
        StdOut.println();
    }

    /**
     * Prints the state of the classroom; can use this method to debug.
     * DO NOT edit.
     */
    public void printClassroom() {
        printStudentsInLine();
        printSeatedStudents();
        printMusicalChairs();
    }

    /**
     * Used to get and set objects.
     * DO NOT edit.
     */

    public SNode getStudentsInLine() {
        return studentsInLine;
    }

    public void setStudentsInLine(SNode l) {
        studentsInLine = l;
    }

    public SNode getMusicalChairs() {
        return musicalChairs;
    }

    public void setMusicalChairs(SNode m) {
        musicalChairs = m;
    }

    public boolean[][] getOpenSeats() {
        return openSeats;
    }

    public void setOpenSeats(boolean[][] a) {
        openSeats = a;
    }

    public Student[][] getStudentsInSeats() {
        return studentsInSeats;
    }

    public void setStudentsInSeats(Student[][] s) {
        studentsInSeats = s;
    }

}
