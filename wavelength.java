import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class wavelength
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner sc = new Scanner(new File("C:/Users/edwar/Desktop/UNB/CS2043/Data/EMGDATA/wrist_ext_original.csv"));
        sc.useDelimiter(",|\\s");
        double[][] array = new double[6000][8];
        double[] arr = new double[array.length-1];
        double[] finalArray = new double[array[0].length];
        for(int i = 0; i < array.length; i++)
        {
          for(int j = 0; j < array[0].length; j++)
          {
            array[i][j] = Double.parseDouble(sc.next());
          }
        }
        for(int i = 0; i < array[0].length; i++)
        {
          for(int j = 1; j < array.length; j++)
          {
            arr[j-1] = Math.abs(array[j][i] - array[j-1][i]);
          }
          double temp = 0;
          for(int k = 0; k < arr.length; k++)
          {
            temp += arr[k];
          }
          finalArray[i] = temp;
        }
        sc.close();
        //display
        for(int i = 0; i < finalArray.length; i++)
        {
          System.out.println(finalArray[i]);
        }
    }
}
