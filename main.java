package Practice3;

import java.util.*;
import java.util.stream.Collectors;

public class Solution2 {
    /**
     * Main function
     *
     * @param args Arguments received from command line
     */
    public static void main(String[] args) {
        // Input
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] weights = new int[n];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = scanner.nextInt();
        }
        scanner.close();

        // Problem variables
        final int limit = 20000; // This is the capacity of the truck
        final int num_trucks = 3; // Number of trucks

        // GENIUS AT WORK...
        // [CODE IN PROGRESS...]
        // RECOMMENDED STRUCTURE OF YOUR ALGORITHM:
        // TODO: 1. Declare solution data structure
        int[] weightPlace = new int[weights.length];
        Arrays.fill(weightPlace, 0);

        int[] currWeight = new int[num_trucks];
        Arrays.fill(currWeight, 0);

        boolean unsolved = true;
        boolean exceeds = false;
        int k = 0;

        // TODO: 2. Exhaustivity: make sure you try every possible option
        while (unsolved && !exceeds/* Condition that guarantees the exhaustivity */) {
            weightPlace[k]++;
            if (weightPlace[k] == 4 || (weights[k] + currWeight[weightPlace[k] - 1]) > limit/* TODO: 3. Dead node condition */) {
                if (weightPlace[k] == 4) {
                    weightPlace[k] = 0;
                    k--;
                    if (k < 0) {
                        exceeds = true;
                    } else {
                        currWeight[weightPlace[k] - 1] -= weights[k];
                    }
                }else if (weights[k] > limit) {
                    exceeds = true;
                }

            } else if (k < weights.length - 1/* TODO: 4. Live node condition */) {
                currWeight[weightPlace[k] - 1] += weights[k];
                k++;
            } else if (k == weights.length - 1/* TODO: 5. Solution node condition */) {
                currWeight[weightPlace[k] - 1] += weights[k];
                unsolved = false;
            }
        }

        if (!unsolved/* TODO: 6. We found a solution */) {
            // If we found a solution
            // selectedWeights[i] will contain the weights of each element in the ith lorry
            final Collection<?>[] selectedWeights = new ArrayList<?>[num_trucks];
            for (int i = 0; i < num_trucks; i++) {
                selectedWeights[i] = new ArrayList<Integer>(n);
            }
            for (int i = 0; i < n; i++) {
                // TODO: 7. Put each item inside the corresponding truck
                if (weightPlace[i] == 1) {
                    ((ArrayList<Integer>) selectedWeights[0]).add(weights[i]);
                } else if (weightPlace[i] == 2) {
                    ((ArrayList<Integer>) selectedWeights[1]).add(weights[i]);
                } else if (weightPlace[i] == 3) {
                    ((ArrayList<Integer>) selectedWeights[2]).add(weights[i]);
                }
            }
            for (int i = 0; i < num_trucks; i++) {
                String line = selectedWeights[i].stream()  // We generate a stream of weights
                        .map(Object::toString)  // We turn each weight to a String
                        .collect(Collectors.joining(" "));  // We collect them joining them with a space
                System.out.println(line);
            }
        } else {
            // If we didn't find a solution
            System.out.println("There is no solution for the given problem :'(");
        }
    }
}