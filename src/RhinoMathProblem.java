import java.util.HashSet;

public class RhinoMathProblem {
    
    public static int countPermutations(int n) {
        HashSet<int[]> solutions = new HashSet<>();

        for (int a = 0; a <= 100; a++) {
            for (int b = a + 1; b <= 100; b++) {
                for (int c = b + 1; c <= 100; c++) {
                    for (int d = c + 1; d <= 100; d++) {
                        for (int e = d + 1; e <= 100; e++) {
                            if (a+b+c+d+e == n) {
                                solutions.add(new int[] {a,b,c,d,e});
                            }
                        }
                    }
                }
            }   
        }

        return solutions.size();
    }

    public static void main(String[] args) {
        System.out.println(countPermutations(71));
    }
}
