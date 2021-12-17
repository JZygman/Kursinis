import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        String answer;
        boolean loopFlag = false;
        int xCoordinateSize = 0, yCoordinateSize = 0, mineAmount = 0, minesNearby = 0, randInt1, randInt2;
        Random rand = new Random();
        Scanner scn = new Scanner(System.in);
        while(!loopFlag)
        {
            System.out.println("Small Grid - (8x8) - Write S");
            System.out.println("Medium Grid - (16x16) - Write M");
            System.out.println("Big Grid - (16x30) - Write B");
            System.out.println("Quit - Write q");
            System.out.println("Write your answer:");
            answer = scn.nextLine();
            switch (answer)
            {
                case "q" -> System.exit(0);
                case "S" -> {
                    xCoordinateSize = 8;
                    yCoordinateSize = 8;
                    mineAmount = 10;
                    loopFlag = true;
                }
                case "M" -> {
                    xCoordinateSize = 16;
                    yCoordinateSize = 16;
                    mineAmount = 40;
                    loopFlag = true;
                }
                case "B" -> {
                    xCoordinateSize = 16;
                    yCoordinateSize = 30;
                    mineAmount = 99;
                    loopFlag = true;
                }
                default -> System.out.println("Wrong Answer");
            }
        }
        int MineGrid[][] = new int[yCoordinateSize][xCoordinateSize];
        int FlagGrid[][] = new int[yCoordinateSize][xCoordinateSize];
        for(int i = 0; i<mineAmount; i++)
        {
            randInt1 = rand.nextInt(yCoordinateSize);
            randInt2 = rand.nextInt(xCoordinateSize);
            if(MineGrid[randInt1][randInt2] == 1) i--;
            else MineGrid[randInt1][randInt2] = 1;
        }
        for(int i = 0; i<yCoordinateSize; i++)
        {
            for (int j = 0; j < xCoordinateSize; j++)
            {
                FlagGrid[i][j] = 1;
            }
        }
        loopFlag = false;
        while(!loopFlag)
        {
            for(int i = 0; i<yCoordinateSize; i++)
            {
                for (int j = 0; j < xCoordinateSize; j++)
                {
                    if (FlagGrid[i][j] == 1)
                    {
                        if(j!=xCoordinateSize-1)
                        {
                            System.out.print("x");
                        } else System.out.print("x\n");
                    }
                    if (FlagGrid[i][j] == 0)
                    {
                        for(int k = i-1; k<=i+1; k++)
                        {
                            for(int z = j-1; z<=j+1; z++)
                            {
                                if(k < 0 || k>=yCoordinateSize || z < 0 || z>=xCoordinateSize)
                                {

                                } else
                                {
                                    if(MineGrid[k][z]==1)
                                        minesNearby++;
                                }
                            }
                        }
                        if(j!=xCoordinateSize-1)
                        {
                            System.out.print(minesNearby);
                        } else System.out.print(minesNearby+"\n");
                        minesNearby = 0;
                    }
                    if (FlagGrid[i][j] == 2)
                    {
                        if(j!=xCoordinateSize-1)
                        {
                            System.out.print("y");
                        } else System.out.print("y\n");
                    }
                }
            }
            Scanner scn2 = new Scanner(System.in);
            System.out.println("What is your next move?");
            System.out.println("Quit - Write q");
            System.out.println("Open mine - Write O");
            System.out.println("Flag mine - Write F");
            answer = scn2.nextLine();
            switch (answer)
            {
                case "q" -> System.exit(0);
                case "O" -> {
                    System.out.println("Choose y coordinate from 1 to "+yCoordinateSize);
                    randInt1 = scn.nextInt();
                    System.out.println("Choose x coordinate from 1 to "+xCoordinateSize);
                    randInt2 = scn.nextInt();
                    if(randInt1<1 || randInt1>yCoordinateSize || randInt2<1 || randInt2>xCoordinateSize)
                    {
                        System.out.println("Wrong coordinates");
                    } else
                    {
                        if(FlagGrid[randInt1-1][randInt2-1]==2)
                        {
                            System.out.println("Can't clear a flagged spot.");
                        }
                        else if(MineGrid[randInt1-1][randInt2-1]==1)
                        {
                            System.out.println("You hit a mine. Game over.");
                            System.exit(0);
                        } else if(FlagGrid[randInt1-1][randInt2-1]==1)
                        {
                            FlagGrid[randInt1-1][randInt2-1] = 0;
                            System.out.println("You have cleared a hidden spot.");
                        } else System.out.println("You hit an already cleared spot.");
                    }
                }
                case "F" -> {
                    System.out.println("Choose y coordinate from 1 to "+yCoordinateSize);
                    randInt1 = scn.nextInt();
                    System.out.println("Choose x coordinate from 1 to "+xCoordinateSize);
                    randInt2 = scn.nextInt();
                    if(randInt1<1 || randInt1>yCoordinateSize || randInt2<1 || randInt2>xCoordinateSize)
                    {
                        System.out.println("Wrong coordinates");
                    } else
                    {
                        if(MineGrid[randInt1-1][randInt2-1]==1 && FlagGrid[randInt1-1][randInt2-1]==1)
                        {
                            mineAmount--;
                            FlagGrid[randInt1-1][randInt2-1]=2;
                            if(mineAmount == 0)
                            {
                                System.out.println("You have flagged all the mines! You won!");
                                System.exit(0);
                            }
                        } else if(MineGrid[randInt1-1][randInt2-1]==1 && FlagGrid[randInt1-1][randInt2-1]==2)
                        {
                            mineAmount++;
                            FlagGrid[randInt1-1][randInt2-1]=1;
                        }
                        if(MineGrid[randInt1-1][randInt2-1]!=1 && FlagGrid[randInt1-1][randInt2-1]==1)
                        {
                            FlagGrid[randInt1-1][randInt2-1]=2;
                        } else if(MineGrid[randInt1-1][randInt2-1]!=1 && FlagGrid[randInt1-1][randInt2-1]==2)
                        {
                            FlagGrid[randInt1-1][randInt2-1]=1;
                        }
                        if(FlagGrid[randInt1-1][randInt2-1]==0)
                        {
                            System.out.println("Can't flag a cleared space.");
                        }
                    }
                }
                default -> System.out.println("Wrong Answer");
            }
        }
    }
}



