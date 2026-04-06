import java.util.Scanner;
public class hw {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows =  scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();

        int[][] grid = new int[rows][cols];

        System.out.println("Enter the elements of the grid:");
        for (int r = 0; r < rows; r++) {
            for (int c  = 0; c <cols; c++) {
                grid[r][c]  = scanner.nextInt();
            }
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Grid Menu ---");
            System.out.println("1. Display Grid");
            System.out.println("2. Row/Column Sums");
            System.out.println("3. Max/Min");
            System.out.println("4. Frequency Check");
            System.out.println("5. Pattern Detection");
            System.out.println("6. Transform Grid (Rotate/Swap/Reverse)");
            System.out.println("7. Subgrid Analysis");
            System.out.println("8. Boundary & Diagonals");
            System.out.println("9. Validation Check");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            if (choice == 1) {
                displayGrid(grid);
            } else if (choice == 2) {
                agg(grid);
            } else if (choice == 3) {
                MaxMin(grid);
            } else if (choice == 4) {
                System.out.print("Enter target number: ");
                int target = scanner.nextInt();
                System.out.print("Enter threshold: ");
                int threshold = scanner.nextInt();
                frequency(grid, target, threshold);
            } else if (choice == 5) {
                int rowIdx = patternDetection(grid);
                if (rowIdx != -1) System.out.println("Increasing pattern found in row: " + rowIdx);
                else System.out.println("No increasing pattern found.");
            } else if (choice == 6) {
                System.out.println("1. Rotate Row Right\n2. Swap Rows\n3. Reverse Column");
                int subChoice = scanner.nextInt();
                if (subChoice == 1) {
                    System.out.print("Which row? ");
                    rotateRowRight(grid, scanner.nextInt());
                } else if (subChoice == 2) {
                    System.out.print("Enter row 1 and row 2: ");
                    swapRows(grid, scanner.nextInt(), scanner.nextInt());
                } else if (subChoice == 3) {
                    System.out.print("Which column? ");
                    reverseColumn(grid, scanner.nextInt());
                }
            } else if (choice == 7) {
                System.out.println("Enter rowStart, rowEnd, colStart, colEnd:");
                processSubgrid(grid, scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            } else if (choice == 8) {
                printBoundaryAndDiagonals(grid);
            } else if (choice == 9) {
                if (hasDuplicateInRow(grid)) System.out.println("Duplicates found in at least one row.");
                else System.out.println("No duplicates found in any row.");
            } else if (choice == 0) {
                System.out.println("Exiting...");
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void agg(int[][] grid){
        int[] sumR = new int[grid.length];
        for(int r = 0; r < grid.length; r++){
            int sum = 0;
            for(int c = 0; c < grid[0].length; c++){
                    sum += grid[r][c];
            }
            sumR[r] = sum;
        }
        int[] sumC = new int[grid[0].length]; 
        for(int c = 0; c < grid[0].length; c++){
            int sum = 0;
            for(int r = 0; r < grid.length; r++){
                sum += grid[r][c];
            }
            sumC[c] = sum;
        }

        System.out.println("row Sum");
        for(int r = 0; r < sumR.length; r++){
            System.out.print(sumR[r] + " ");
        }
        System.out.println("");
        System.out.println("column Sum");
        for(int c = 0; c < sumC.length; c++){
            System.out.print(sumC[c] + " ");
        }
    }

    public static void MaxMin(int[][] grid){
            int maxVal = grid[0][0];
            int maxRow = 0;
            int maxCol = 0;
            int minVal = grid[0][0];
            int minRow = 0;
            int minCol = 0;
            for(int r = 0; r < grid.length; r++){
                for(int c = 0; c < grid[0].length; c++){
                    if(grid[r][c] > maxVal){
                        maxVal = grid[r][c];
                        maxRow = r;
                        maxCol = c;
                    }
                    if(grid[r][c] < minVal){
                        minVal = grid[r][c];
                        minRow = r;
                        minCol = c;
                    }
                }
            }
            System.out.println("Maximum Value : " + maxVal + " and its position is at : " + "(" + maxRow + "," + maxCol + ")");
            System.out.println("Minimum Value : " + minVal + " and its position is at : " + "(" + minRow + "," + minCol + ")");
    }

    public static void frequency(int[][] grid, int target, int threshold){
            int freqCount = 0;
            int greaterValCount = 0;
            for(int r = 0; r < grid.length; r++){
                for(int c = 0; c < grid[0].length; c++){
                    if(grid[r][c] == target){
                        freqCount++;
                    }
                    if(grid[r][c] > threshold){
                        greaterValCount++; 
                    }
            }
        }
            System.out.println("The frequency of the number you chose is : " + freqCount);
            System.out.println("Amount of numbers greater than threshold : " + greaterValCount);
    }

    public static void comp(int[][] grid){
        int maxRowSum = Integer.MIN_VALUE;
        int maxRowIndex = -1;
        for(int r = 0; r < grid.length; r++){
            int currentRowSum = 0;
            for(int c = 0; c < grid[0].length; c++){
                currentRowSum += grid[r][c];
            }
            if(currentRowSum > maxRowSum){
                maxRowSum = currentRowSum;
                maxRowIndex = r;
            }
        }
        int minColSum = Integer.MAX_VALUE;
        int minColIndex = -1;
        for(int c = 0; c < grid[0].length; c++){
            int currentColSum = 0;
            for(int r = 0; r < grid.length; r++){
                currentColSum += grid[r][c];
            }
            if(currentColSum < minColSum){
                minColSum = currentColSum;
                minColIndex = c;
            }
        }
        System.out.println("Row "+ maxRowIndex + "has the highest sum: " + maxRowSum);
        System.out.println("Column " + minColIndex + "has the lowest sum: " +minColSum);
    }

   public static int patternDetection(int[][] grid){
    for(int r =0; r < grid.length; r++){
        boolean isIncreasing =true;

        for(int c = 1; c < grid[0].length; c++){
            if(grid[r][c]< grid[r][c - 1]){
                isIncreasing =false;
            }
        }
        if(isIncreasing){
            return r ;
        }
    }
    return -1;
}

    public static void rotateRowRight(int[][] grid, int row) {
    int last= grid[row][grid[0].length- 1];
    
    for (int c = grid[0].length - 1; c > 0; c--) {
        grid[row][c] = grid[row][c - 1];
    }
    
    grid[row][0] = last;

    System.out.println("After rotating row " + row);
    for (int r =0; r <grid.length; r++){
        for (int c = 0; c< grid[0].length; c++) {
            System.out.print(grid[r][c]+ " ");
        }
        System.out.println();
    }
    System.out.println();
}

    public static void swapRows(int[][] grid, int r1, int r2) {
    int[] temp = grid[r1];
    grid[r1] = grid[r2];
    grid[r2] = temp;

    System.out.println("After swapping row " + r1 + " and row " + r2);
    for (int r = 0; r < grid.length; r++) {
        for (int c = 0; c < grid[0].length; c++) {
            System.out.print(grid[r][c] + " ");
        }
        System.out.println();
    }
    System.out.println();
}

    public static void reverseColumn(int[][] grid, int c) {
    int top = 0;
    int bottom = grid.length-1;
    
    while (top <bottom) {
        int temp =grid[top][c];
        grid[top][c] = grid[bottom][c];
        grid[bottom][c]= temp;
        
        top++;
        bottom--;
    }
    System.out.println("After reversing column " +c);
    for (int r=0; r< grid.length; r++){
        for (int col=0; col< grid[0].length; col++){
            System.out.print(grid[r][col]+ " ");
        }
        System.out.println();
    }
    System.out.println();
}

    public static void processSubgrid(int[][] grid, int rowStart,int rowEnd, int colStart,int colEnd){
    int sum = 0;
    int max =grid[rowStart][colStart];

    System.out.println("Subgrid elements:");

    for (int r= rowStart; r <= rowEnd;  r++)  {
        for (int c = colStart; c <= colEnd; c++){
            System.out.print(grid[r][c] + " ");
            sum += grid[r][c];

            if(grid[r][c] > max){
                max= grid[r][c];
            }
        }
        System.out.println();
    }

    System.out.println("Sum of subgrid ="+ sum);
    System.out.println("Max of subgrid = "+ max);
    System.out.println();
}

public static void printBoundaryAndDiagonals(int[][] grid){
    int row = grid.length;

    System.out.println("Boundary elements:");
    for(int r= 0;r < row;r++){
        for(int c = 0; c < grid[0].length;c++){
            if(r == 0||r == row - 1||c ==0||c==grid[0].length- 1){
                System.out.print(grid[r][c]+" ");
            }
        }
    }
    System.out.println();

    System.out.println("1st diagonal:");
    for(int i = 0; i < row; i++){
        System.out.print(grid[i][i]+" ");
    }
    System.out.println();

    System.out.println("2nd diagonal:");
    for(int i = 0; i < row ;i++){
        System.out.print(grid[i][row-1-i]+" ");
    }
    System.out.println();
}

public static boolean hasDuplicateInRow(int[][] grid) {

    for (int r = 0; r < grid.length; r++) {

        for (int c = 0; c < grid[0].length; c++) {

            for (int next = c + 1; next < grid[0].length; next++) {

                if (grid[r][c] == grid[r][next]) {
                    return true;
                }

            }
        }
    }

    return false;
}

public static void displayGrid(int[][] grid) {
    for (int r = 0; r < grid.length; r++) {
        for (int c = 0; c < grid[0].length; c++) {
            System.out.print(grid[r][c] + " ");
        }
        System.out.println();
    }
}
}