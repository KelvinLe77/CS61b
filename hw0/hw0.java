public class hw0 {

  public static int max(int[] a) {
    int max = a[0];
    int pointer = 0;
    for (int x = 0; pointer < a.length; pointer += 1) {
      if (a[pointer] > max) {
        max = a[pointer];
      }
    }
    return max;
  }

  public static boolean threeSum(int[] a) {
    for (int x = 0; x < a.length; x += 1) {
      for (int y = 0; y < a.length; y += 1) {
        for (int z = 0; z < a.length; z += 1) {
          if (a[x] + a[y] + a[z] == 0) {
            return true;
          }
          else {
            return false;
          }
        }
      }
    }
    return false;
  }

  public static boolean threeSumDistinct(int[] a) {
    for (int x = 0; x < a.length; x += 1) {
      for (int y = 0; y < a.length; y += 1) {
        for (int z = 0; z < a.length; z += 1) {
          if (a[x] + a[y] + a[z] == 0 && x != y && y != z && x != z) {
            return true;
          }
          else {
            return false;
          }
        }
      }
    }
    return false;
  }
}
