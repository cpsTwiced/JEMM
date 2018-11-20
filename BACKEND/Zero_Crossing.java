import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;

public class Zero_Crossing {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = new Scanner(new File("C:/Users/edwar/Desktop/UNB/CS2043/Data/EMGDATA/wrist_ext_original.csv"));
        sc.useDelimiter(",|\\s");

        double[][] inArray = new double[6000][8];

        for (int i = 0; i < inArray.length; i++) {
            for (int j = 0; j < inArray[i].length; j++) {
                inArray[i][j] = Double.parseDouble(sc.next());
            }
        }

        int[] zc = {0,0,0,0,0,0,0,0};

        double threshold = 0.1;

        for (int i = 0; i < inArray[0].length; i++) {

            for (int j = 0; j < inArray.length-1; j++) {

                if (inArray[j][i] * inArray[j+1][i] < threshold) {

                        zc[i] += 1;
                }

                if (Math.abs(inArray[j+1][i] / inArray[j][i]) <= 0) {

                        zc[i] += 1;
                }

            }
        }

        System.out.println();

        for (int i = 0; i < zc.length; i++) {
            System.out.println(zc[i]);
        }
    }
}
