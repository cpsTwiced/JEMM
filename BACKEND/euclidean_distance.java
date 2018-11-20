public class euclidean_distance
{
  //testing the euclidean distance function with the values 1:8 and printing them.
  public static void main(String[] args)
  {
    double[] test = new double[8];
    for(int i = 0 ; i < test.length; i++)
    {
      test[i] = i;
    }
    System.out.println(getED(test));
  }

  //euclidean distance calculation/function
  //input must be array of double, where feature values are placed beside each
  //other in the array.
  //Example: array = [mav1, mav2, ssc1, ssc2, wl1, wl2, zc1, zc2]
  //output is 1 value
  public static double getED(double[] array)
  {
    double value = 0;
    for(int i = 0 ; i < array.length; i+=2)
    {
      value += Math.pow((array[i] - array[i+1]),2);
    }
    return Math.sqrt(value);
  }
}
