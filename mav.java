import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class mav {

	public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/Users/MS/Desktop/4/CS 2043/EMGDATA/wrist_ext_original.csv"));
        sc.useDelimiter(",|\\s");
		double[][] array = new double[6000][8];
		for(int i = 0; i < array.length; i++)
        {
          for(int j = 0; j < array[0].length; j++)
          {
            array[i][j] = Double.parseDouble(sc.next());
          }
        }

		double [] mavArray = new double [array[0].length];
		double [] arrayCol = new double [array.length];
		//get each column
		for (int b = 0; b < array[0].length; b++) {
			for (int a = 0; a < array.length; a++) {
				arrayCol [a] = array[a][b];
			}
			mavArray[b] = getMAV(arrayCol);
		}

		System.out.println(Arrays.toString(mavArray));
    System.out.println();
    System.out.println(array[0].length);
    System.out.println();
    System.out.println(array.length);
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
