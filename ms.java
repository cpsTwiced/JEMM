import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class ms {

	public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/Users/edwar/Desktop/EMGDATA/wrist_ext_original.csv"));
        sc.useDelimiter(",|\\s");
		double[][] array = new double[6000][8];
		for(int i = 0; i < array.length; i++)
        {
          for(int j = 0; j < array[0].length; j++)
          {
            array[i][j] = Double.parseDouble(sc.next());
          }
        }
		
		/*for(int z = 0; z < array.length; z++)
		{
			for(int x = 0; x < array[0].length; x++)
			{
				System.out.println(array[z][x]);
			}
		}*/
		
		double [] mavArray = new double [array[0].length];
		//get each column
		for (int b = 0; b < array[0].length; b++) {
			double [] arrayCol = new double [array.length];
			for (int a = 0; a < array.length; a++) {
				arrayCol [a] = array[a][b];
			}
			mavArray[b] = getMAV(arrayCol);
		}
		
		System.out.println(Arrays.toString(mavArray));
	}
		
		
	public static double getMAV(double [] array) {
		//x is the sum 
		double x = 0;
		for (int n = 0; n < array.length; n++) {
			x = x + array [n];
		}
		double y = (Math.abs(x))/array.length;
		return y;
		
	}
}

