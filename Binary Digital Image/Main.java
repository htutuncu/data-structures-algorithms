import java.io.File;
import java.util.Scanner;

/**
 * CSE 222 HW#03
 * @author Hikmet Tutuncu
 */
public class Main {
    /**
     * Main function to get string from the user and calling solution methods.
     * */
    public static void main(String[] args) throws Exception{
        //String Part1path = args[0];
        /*  Opening the .txt file. It can be opened terminal parameter. */
        File file = new File("C:\\Users\\Hikmet\\Desktop\\hw3\\hw3.txt"); /* Give the txt file path. */

        Scanner scanner = new Scanner(file);

        /*  Result is the final result for part1.   */
        int result = SolutionPart1(scanner,file);
        System.out.println("Result is: "+ result);

    }

    /**
     * This function calculates how many islands in the given .txt file.
     * It uses stack data structure. I implemented it in MyStack.java.
     * */
    private static int SolutionPart1(Scanner scanner,File file) throws Exception {
        /**
         * Program calculates that how many rows and columns at first.
         * */
        int total_row = 0;
        int total_col = 0;
        String myStr;
        myStr = scanner.nextLine();
        Scanner s = new Scanner(myStr);
        String[] arr = myStr.split(" ");
        total_col = arr.length;
        while (scanner.hasNextLine()){
            myStr = scanner.nextLine();
            total_row++;
        }
        total_row++;
        /**
         * And then, initialize the 2D Array of every component.
         * */
        scanner = new Scanner(file);
        String[][] array = new String[total_row][total_col];
        for ( int i=0; scanner.hasNextLine(); i++ )
        {
            myStr = scanner.nextLine();
            array[i] = myStr.split(" ");
        }

        String zero = "0";
        String one = "1";
        /** Letters count. Program initializes every island is unique letter.*/
        String[] letters = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S",
                            "T","U","V","W","Y","X","Y","Z"};
        int k = 0;  /** Count of result. How many islands.  */
        MyStack mystack = new MyStack();
        /**
         * This nested loop wander around 2D array. If there is a "1", push it to the stack, and push its neighbours.
         * Program push every 1 into stack until there is no neighbours. If there is no neighbour, count increases and
         * program understands it is a end of island.
         * */
        for(int i=0; i<total_row; i++){
            for(int j=0; j<total_col; j++){
                if ( array[i][j].equals(one) ){
                    MyStack.Coordinate coo = new MyStack.Coordinate(i,j);
                    mystack.push(coo);  /*  If there is a "1" program push it to the stack. */
                    int a = i; int b = j;
                    while(!mystack.IsEmpty()){
                        if( a+1 < total_row && array[a+1][b].equals(one) ){
                            array[a+1][b] = letters[k];
                            coo = new MyStack.Coordinate(a+1,b);
                            mystack.push(coo);
                        }
                        if( a-1!=-1 && array[a-1][b].equals(one) ){
                            array[a-1][b] = letters[k];
                            coo = new MyStack.Coordinate(a-1,b);
                            mystack.push(coo);
                        }
                        if ( b+1 < total_col && array[a][b+1].equals(one)){
                            array[a][b+1] = letters[k];
                            coo = new MyStack.Coordinate(a,b+1);
                            mystack.push(coo);
                        }
                        if( b-1!=-1 && array[a][b-1].equals(one) ){
                            array[a][b-1] = letters[k];
                            coo = new MyStack.Coordinate(a,b-1);
                            mystack.push(coo);
                        }
                        a = mystack.peek().getX();
                        b = mystack.pop().getY();
                    }
                    k++;


                }
            }
        }
        for (int i=0; i<total_row; i++){
            for (int j=0; j<total_col; j++)
                System.out.print(array[i][j]+" ");
            System.out.println();
        }
        System.out.println("Sonuc:" + k);
        return k;   /*  Count of how many islands in the input and returns it.  */
    }

    private static String SolutionPart2(Scanner scanner,File file2) throws Exception{
        String myStr;
        int j=0;
        while (scanner.hasNextLine()){
            myStr = scanner.nextLine();
            j++;
        }
        String[] array = new String[j];
        scanner = new Scanner(file2);
        j=0;
        while (scanner.hasNextLine()){
            myStr = scanner.nextLine();
            array[j] = myStr;
            j++;
        }
        for (int i=0; i<j; i++)
            System.out.println(array[i]);
        //System.out.println("j="+j);
        //char[] arr = array[0].toCharArray();

        return array[0];
    }

}
