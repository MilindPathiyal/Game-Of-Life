//Milind Pathiyal

//Game of Life
import java.io.*;
import java.util.*;
public class Life
{
    boolean[][] table = new boolean[20][20];
    char[][] grid = new char[20][20];
    
    private final int gridSize = 20;
    private final int generations = 5;
    
    //Pre:None
    //Post:None
    public Life()
    {
        File text = new File("life100.txt");
        table = evaluate(text);
        for (int i = 0; i < generations; i++) {
            updateGrid();
        }
        display();
    }
    //Pre:file must exist
    //Post:determines whether a bacteria lives in the designated coordinates
    public boolean[][] evaluate(File file)
    {
        try {
            Scanner input = new Scanner(file);
            input.nextInt();
            while(input.hasNext()) 
            {
                int row = input.nextInt();
                int col = input.nextInt();
                table[row-1][col-1] = true;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return table;
    }
    // pre: none
    // post:determines whether bacteria [x][y] is alive 
    public boolean updateBacteria(int x, int y) {
        int numNeighbor = 0;
        for (int row = -1; row <= 1; row++) {
            int xCheck = x + row;
            for (int col = -1; col <= 1; col++) {
                int yCheck = y + col;
                if ((0 <= xCheck && xCheck < gridSize) && (0 <= yCheck && yCheck < gridSize)) {
                    if (table[xCheck][yCheck]) {
                        numNeighbor++;
                    }
                }
            }
        }
        boolean isAlive = table[x][y];
        if (isAlive)
            numNeighbor--;
        if (!isAlive && numNeighbor == 3)
            return true;
        else if (isAlive && (numNeighbor == 2 || numNeighbor == 3))
            return true;
        else
            return false;
    }
    // pre: none
    // post: updates grid
    public void updateGrid() 
    {
        for (int row = 0; row < gridSize; row++) 
        {
            for (int col = 0; col < gridSize; col++) 
            {
                if (updateBacteria(row,col))
                    grid[row][col] = '*';
                else
                    grid[row][col] = '-';
            }
        }
        
        for(int rows = 0; rows < gridSize; rows++) 
        {
            for(int cols = 0; cols < gridSize; cols++) 
            {
                if (grid[rows][cols] == '*')
                    table[rows][cols] = true;
                else
                    table[rows][cols] = false;
            }
        }
    }
    //Pre:variables must exist
    //Post:prints out # of bacteria in row & col 10 and total # of bacteria living in grid
    public void countLiving()
    {
        int rowCount = 0;
        for (int col = 0; col < gridSize; col++){
            if (table[9][col])
                rowCount++;
        }
        System.out.println("Number in Row 10 ---> " + rowCount);
        int colCount = 0;
        for (int row = 0; row < gridSize; row++) {
            if (table[row][9])
                colCount++;
        }
        System.out.println("Number in Column 10 ---> " + colCount);
        int totalCount = 0;
        for(int x = 0; x < gridSize; x++) {
            for(int y = 0; y < gridSize; y++) {
                if(table[x][y])
                    totalCount++;
            }
        }
        System.out.println("Number of living organisms ---> " + totalCount);
    }
    // pre: none
    // post: displays grid + x & y table
    public void display() {
        for (int row = -1; row < gridSize; row++) {
            if (row == -1) {
                System.out.print("  ");
                for (int i = 0; i < 20; i++)
                    System.out.print(i%10);
                System.out.println();
            }
            else{
                System.out.print("" + row%10+ " ");
                for (int col = 0; col < gridSize; col++)
                    System.out.print(grid[row][col]);
                System.out.println();
            }
        }
        countLiving();
    }
}

/*
01234567890123456789
0 ------**------------
1 -----*-*------------
2 ------*--------**---
3 -------------**--*--
4 -------------**-**--
5 ------------*-*-**--
6 ----------*-**------
7 ----------*---------
8 -------***--**------
9 -----**-**-**-**----
0 -----******---------
1 ------**-****-**----
2 -------*--**--***---
3 ------*--*-***-**---
4 -------**-----**-*--
5 ------**------**--*-
6 -----*-*----**----*-
7 -----*-*---***----*-
8 -----*----*--*-**---
9 --------------------

Number in Row 10 ---> 8

Number in Column 10 ---> 5

Number of living organisms ---> 88
 */