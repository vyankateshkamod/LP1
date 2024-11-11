import java.util.Arrays;
import java.util.Scanner;

public class First_Best {
    
    // First Fit Algorithm
    public static void firstFit(int[] memoryBlocks, int[] processRequirements) {
        int[] memoryStatus = new int[memoryBlocks.length];
        Arrays.fill(memoryStatus, -1);

        for (int i = 0; i < processRequirements.length; i++) {
            boolean allocated = false;
            for (int j = 0; j < memoryBlocks.length; j++) {
                if (memoryBlocks[j] >= processRequirements[i] && memoryStatus[j] == -1) {
                    memoryStatus[j] = i;
                    System.out.println("Process " + i + " (size " + processRequirements[i] + ") allocated to block " + j + " (size " + memoryBlocks[j] + ")");
                    allocated = true;
                    break;
                }
            }
            if (!allocated) {
                System.out.println("Process " + i + " could not be allocated.");
            }
        }
    }

    // Best Fit Algorithm
    public static void bestFit(int[] memoryBlocks, int[] processRequirements) {
        int[] memoryStatus = new int[memoryBlocks.length];
        Arrays.fill(memoryStatus, -1);

        for (int i = 0; i < processRequirements.length; i++) {
            int bestIndex = -1;
            int bestFitSize = Integer.MAX_VALUE;
            
            for (int j = 0; j < memoryBlocks.length; j++) {
                if (memoryBlocks[j] >= processRequirements[i] && memoryStatus[j] == -1) {
                    int leftoverSpace = memoryBlocks[j] - processRequirements[i];
                    if (leftoverSpace < bestFitSize) {
                        bestFitSize = leftoverSpace;
                        bestIndex = j;
                    }
                }
            }
            
            if (bestIndex != -1) {
                memoryStatus[bestIndex] = i;
                System.out.println("Process " + i + " (size " + processRequirements[i] + ") allocated to block " + bestIndex + " (size " + memoryBlocks[bestIndex] + ")");
            } else {
                System.out.println("Process " + i + " could not be allocated.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input for memory blocks
        System.out.print("Enter the number of memory blocks: ");
        int m = sc.nextInt();
        int[] memoryBlocks = new int[m];
        System.out.println("Enter the sizes of memory blocks:");
        for (int i = 0; i < m; i++) {
            memoryBlocks[i] = sc.nextInt();
        }

        // Input for process requirements
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        int[] processRequirements = new int[n];
        System.out.println("Enter the sizes of process requirements:");
        for (int i = 0; i < n; i++) {
            processRequirements[i] = sc.nextInt();
        }

        System.out.println("\nFirst Fit Allocation:");
        firstFit(memoryBlocks, processRequirements);

        System.out.println("\nBest Fit Allocation:");
        bestFit(memoryBlocks, processRequirements);

        sc.close();
    }
}
