package contest.tinkoff;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        var nm = scanner.nextLine().split(" ");
        int n = Integer.parseInt(nm[0]);

        var stringNumbers = scanner.nextLine().split(" ");
        var numbers = new int[stringNumbers.length * 2];

        for (int i = 0; i < stringNumbers.length; ++i) {
            int number = Integer.parseInt(stringNumbers[i]);
            numbers[i * 2] = number;
            numbers[i * 2 + 1] = number;
        }

        var result = sumToTarget(n, numbers);
        if (!result.isEmpty()) {
            System.out.println(result.size());
            System.out.println(transformToString(result));
        }
        else {
            System.out.println("-1");
        }
    }

    public static String transformToString(List<Integer> numbers) {
        var sb = new StringBuilder(numbers.size() * 2);
        for (var i = numbers.size() - 1; i >= 0; --i) sb.append(numbers.get(i)).append(" ");
        return sb.toString().trim();
    }

    public static List<Integer> sumToTarget(int target, final int[] numbers) {
        var result = new ArrayList<Integer>();

        sumToTargetInternal(target, numbers, 0, result);

        return result;
    }

    private static boolean sumToTargetInternal(int target, final int[] numbers, int idx, final List<Integer> result) {
        if (target == 0) return true;
        if (target < 0) return false;

        while (idx < numbers.length) {
            var rem = target - numbers[idx];

            if (sumToTargetInternal(rem, numbers, idx + 1, result)) {
                result.add(numbers[idx]);
                return true;
            }

            ++idx;
        }

        return false;
    }
}
