import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Main
{
    static boolean isSafeToPut(int[][] board,int i,int j,int no){
        
        
        //To check for that column....
        for(int k=0;k<9;k++){
            if(board[k][j]==no)
                return false;
        }
        
        //Check for corresponding Row
        for(int k=0;k<9;k++)
            if(board[i][k]==no)
                return false;
        
        //Check for isPresent in subbox
        
        int subI = i-(i%3);
        int subJ = j-(j%3);
        
        // sub mat check
        for(int k = 0;k<3;k++)
        {
            for(int l = 0;l<3;l++)
            {
                if(board[k+subI][l+subJ] == no) return false;
            }
        }
        return true;
    }
    
    static boolean sudokuSolver(int i,int j,int[][] board){
        
        if(j>=9){
            return sudokuSolver(i+1,0,board);
        }
        //Base case :
        if(i==9){
            
            for(int p=0;p<9;p++){
                for(int q=0;q<9;q++){
                    System.out.print(board[p][q]+" ");
                }
                System.out.println();
            }
            return true;
        }
        
        if(j<=8&&board[i][j]!=0){
           return sudokuSolver(i,j+1,board);
        }
        
        
        if(board[i][j]==0){ // i,j position is empty
            
        for(int no=1;no<=9;no++){
            
            if(isSafeToPut(board,i,j,no)==true){
             
                board[i][j] = no;
                boolean isSafeToFurther = sudokuSolver(i,j+1,board); 
                
                if(isSafeToFurther==true){
                    return true;
                }  
                else{
                    board[i][j] = 0;
                }
            }
        }
        
        return false; //No the sudoku couldnt be solved.....
        }
        
        return false;
        
    }
    public static void main (String[] args)
    {
        //code goes here
        Scanner sc = new Scanner(System.in);
        int input;
        do{
            System.out.println("Press '1' to Enter the Board");
            System.out.println("Press '0' to Exit the Game");
            input = sc.nextInt();
            int[][] board = new int[9][9];
            switch(input){
             
             case 1:
                System.out.println("Enter the Board");
                System.out.println("I'm Ready to Solve :)");
                    for(int i =0;i<9;i++){
                        for(int j =0;j<9;j++){
                            board[i][j] = sc.nextInt();
                        }
                    }
                System.out.println("Your Board is solved :)");
                 
                boolean isValidSudoku = sudokuSolver(0,0,board);
                
                if(isValidSudoku == false){
                    System.out.println("Not a valid sudoku Board");
                }
                break;
            case 0:
            System.out.println("Thank You :(");
            break;
                
            default:
                break;
            }
        }
        while(input != 0);
 
    }
}
