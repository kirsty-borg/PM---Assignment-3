package PM---Assignement-3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    /**
     * Main function
     * @param args Arguments received from command line
     */
    public static void main(String[] args) {
        // Input
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] weights = new int[n];
        for (int i = 0; i < weights.length; i++){
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

        // TODO: 2. Exhaustivity: make sure you try every possible option
        while (/* Condition that guarantees the exhaustivity */) {
            if (/* TODO: 3. Dead node condition */) {

            }
            else if (/* TODO: 4. Live node condition */) {

            }
            else if (/* TODO: 5. Solution node condition */) {

            }
        }

        if (/* TODO: 6. We found a solution */) {
            // If we found a solution
            // selectedWeights[i] will contain the weights of each element in the ith lorry
            final Collection<?>[] selectedWeights = new ArrayList<?>[num_trucks];
            for (int i = 0; i < num_trucks; i++) {
                selectedWeights[i] = new ArrayList<Integer>(n);
            }
            for (int i = 0; i < n; i++) {
                // TODO: 7. Put each item inside the corresponding truck
                ((ArrayList<Integer>) selectedWeights[/* Truck ID */]).add(weights[i]);
            }
            for (int i = 0; i < num_trucks; i++) {
                String line = selectedWeights[i].stream()  // We generate a stream of weights
                        .map(Object::toString)  // We turn each weight to a String
                        .collect(Collectors.joining(" "));  // We collect them joining them with a space
                System.out.println(line);
            }
        }
        else {
            // If we didn't find a solution
            System.out.println("There is no solution for the given problem :'(");
        }
    }
}