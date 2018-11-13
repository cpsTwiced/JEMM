//importing libraries needed
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//returns wavelength values as an array of size 8
public class wavelength
{
    public static void main(String[] args) throws FileNotFoundException
    {
        //making scanner that can work with the csv files
        Scanner sc = new Scanner(new File("C:/Users/edwar/Desktop/UNB/CS2043/Data/EMGDATA/wrist_ext_original.csv"));
        sc.useDelimiter(",|\\s");

        //making arrays for storing and returning output
        double[][] array = new double[6000][8];
        double[] finalArray = new double[array[0].length];

        //reading in data from the csv file
        for(int i = 0; i < array.length; i++)
        {
          for(int j = 0; j < array[0].length; j++)
          {
            array[i][j] = Double.parseDouble(sc.next());
          }
        }

        //calculation of wavelength, where i is column representing
        //8 channels and and j is row representing the actual data in each
        //channel
        for(int i = 0; i < array[0].length; i++)
        {
          double temp = 0;
          for(int j = 1; j < array.length; j++)
          {
            temp += Math.abs(array[j][i] - array[j-1][i]);
          }
          finalArray[i] = temp;
        }

        //close input
        sc.close();

        //display for testing purposes
        for(int i = 0; i < finalArray.length; i++)
        {
          System.out.println(finalArray[i]);
        }
    }
}
