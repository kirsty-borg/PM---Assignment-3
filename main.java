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

        // TODO: 1. Declare solution data structure : an array to store remaining space in the trucks
        int [] size_lorries = new int[num_trucks];
        int [] assign_truck = new int[weights.length];
        int j = 0; // Counter for the lorries
        for (int i = 0; i < size_lorries.length; i++){
            size_lorries[i]= limit;
        }
        // TODO: 2. Exhaustivity: make sure you try every possible option
        for (int i = 0; i < weights.length; i++) {

            int j ; //Counter of the best bin that can accomodate weight[i]
            int min = limit + 1, bi = 0;

            for( j = 0; j < num_trucks; j++) {

                if (size_lorries[j] >= weights[i] &&
                        size_lorries - weights[i] < min) { /* TODO: 3. Dead node condition */

                    bi = j;
                    min = size_lorries[j] - weights[i];
                }
            }
            if (min == limit + 1 ) { /* TODO: 5. Solution node condition */
                System.out.println("not enough space for carry this weight");
            }else{
                assign_truck[i] = bi;
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
                ((ArrayList<Integer>) selectedWeights[assign_truck[i]).add(weights[i]);
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