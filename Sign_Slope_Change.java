import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sign_Slope_Change {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("C:/Users/jtkau/OneDrive/Desktop/UNB_FALL_2018/CS2043/Project/EMG_Graphics/DATA/wrist_ext_original.csv"));
        sc.useDelimiter(",|\\s");

        double[][] inArray = new double[6000][8];

        for (int i = 0; i < inArray.length; i++) {
            for (int j = 0; j < inArray[i].length; j++) {
                inArray[i][j] = Double.parseDouble(sc.next());
            }
        }

        double[] outArray = new double[8];
        boolean calc = false;
        double sum = 0;
        double threshold = 0.0;
        for (int i = 0; i < inArray[0].length; i++) {

            sum = 0;
            for (int j = 1; j < inArray.length - 1; j++) {
                calc = ((inArray[j][i] - inArray[j-1][i]) * (inArray[j][i] - inArray[j+1][i])) >= threshold;
                if (calc) {
                    sum += 1;
                }
            }

            outArray[i] = sum;
        }

        for (int i = 0; i < outArray.length; i++) {
            System.out.println(outArray[i]);
        }
    }
}
