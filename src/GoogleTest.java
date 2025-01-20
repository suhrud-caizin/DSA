import java.util.Arrays;

public class GoogleTest {


//    public static int solve(int N,int[] A, int[] X, int[] Y, int L) {
//
//
//        while (true) {
//            int minIndex = 0;
//            for (int i = 1; i < N; i++) {
//                if (A[i] < A[minIndex]) {
//                    minIndex = i;
//                }
//            }
//
//
//            if (minIndex < 2) {
//                break;
//            }
//
//            int D = A[minIndex] / 5;
//            if (D == 0) {
//                break;
//            }
//
//
//            A[minIndex] -= 5 * D;
//            A[X[minIndex]] += 3 * D;
//            A[Y[minIndex]] += 2 * D;
//        }
//
//
//        int minValue = Arrays.stream(A).min().orElse(0);
//        return minValue - L;
//    }


    public static void solve(String Q, int[] friends){
        char[] qArray = Q.toCharArray();

        for (int friend:friends){

            for (int i = 1; i <= qArray.length; i++) {
                if(i*friend > qArray.length) break;

                if(qArray[friend*i-1] == '0') qArray[friend*i-1] = '1';
                else{
                    qArray[friend*i-1] = '0';
                }
            }
        }

        System.out.println(qArray);
    }





    public static void main(String[] args) {
//        solve(5,new int[]{7,2,7,5,4},new int[]{6,6,1,2,2},new int[]{6,6,1,2,3},0));
//        System.out.println(Arrays.toString(
                solve("000", new int[]{3, 2, 1});
    }
}
