package Practice3;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
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

        //Additional Variables
        boolean initialCon = false;
        // int weightBound = limit * num_trucks;


        // GENIUS AT WORK...
        // [CODE IN PROGRESS...]
        // RECOMMENDED STRUCTURE OF YOUR ALGORITHM:
        // TODO: 1. Declare solution data structure

        ArrayList<ArrayList<Integer>> trucks = new ArrayList<>();
        trucks.add(new ArrayList<>());

        //Don't know which part it becomes but needed
        //Sort and then traverse for descending order
        int temp = 0;
        Arrays.sort(weights);
        for (int i = 0; i < weights.length / 2; ++i) {
            temp = weights[i];
            weights[i] = weights[weights.length - i - 1];
            weights[weights.length - i - 1] = temp;
        }

        System.out.println(Arrays.toString(weights));

        if (weights[0] > limit) {
            // System.out.println("No solution.");
            initialCon = true;
        }

        for (int i = 0; i < weights.length; i++) {

            if (initialCon) {
                break;
            }

            boolean put = false; //to see whether we have put the item into a truck or not
            int currentTruck = 0;

            // TODO: 2. Exhaustive: make sure you try every possible option
            while (!put/* Condition that guarantees the exhaustive */) {
                if ((trucks.get(currentTruck).stream().mapToInt(Integer::intValue).sum() + weights[i]) > limit && currentTruck==(trucks.size()-1)/* TODO: 3. Dead node condition */) {
                    //Current weight does not fit in current truck -> new truck
                    trucks.add(new ArrayList<Integer>());
                    trucks.get(trucks.size() - 1).add(weights[i]);
                    put = true;
                } else if (trucks.get(currentTruck).stream().mapToInt(Integer::intValue).sum() + weights[i] <= limit/* TODO: 4. Live node condition */) {
                    trucks.get(currentTruck).add(weights[i]);
                    put = true;
                } else /* TODO: 5. Solution node condition )*/ {
                    currentTruck++;
                    if(trucks.size()<currentTruck){
                        trucks.add(new ArrayList<>());
                    }
                }
            }
        }
        if (/* TODO: 6. We found a solution */trucks.size() <= num_trucks && !initialCon) {
            /*// If we found a solution
            // selectedWeights[i] will contain the weights of each element in the ith lorry
            final Collection<?>[] selectedWeights = new ArrayList<?>[num_trucks];
            for (int i = 0; i < num_trucks; i++) {
                selectedWeights[i] = new ArrayList<Integer>(n);
            }
            for (int i = 0; i < n; i++) {
                // TODO: 7. Put each item inside the corresponding truck
                ((ArrayList<Integer>) selectedWeights[/* Truck ID *//*]).add(weights[i]);
            }
            for (int i = 0; i < num_trucks; i++) {
                String line = selectedWeights[i].stream()  // We generate a stream of weights
                        .map(Object::toString)  // We turn each weight to a String
                        .collect(Collectors.joining(" "));  // We collect them joining them with a space
                System.out.println(line);
            }*/
            for (int i = 0; i < trucks.size(); i++) {
                System.out.println("\nTruck " + (i + 1));
                for (int j = 0; j < trucks.get(i).size(); j++) {
                    System.out.print("[" + trucks.get(i).get(j) + "]");
                }
            }
        } else {
            // If we didn't find a solution
            System.out.println("There is no solution for the given problem :'(");
            if (!initialCon) {
                System.out.println("Required number of trucks: " + trucks.size());
                for (int i = 0; i < trucks.size(); i++) {
                    System.out.println("\nTruck " + (i + 1));
                    for (int j = 0; j < trucks.get(i).size(); j++) {
                        System.out.print("[" + trucks.get(i).get(j) + "]");
                    }
                }
            }
        }
    }
}