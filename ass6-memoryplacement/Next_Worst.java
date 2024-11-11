import java.util.Arrays;
import java.util.Scanner;

public class Next_Worst {

    // Next Fit Algorithm
    public static void nextFit(int[] memoryBlocks, int[] processRequirements) {
        int[] memoryStatus = new int[memoryBlocks.length];
        Arrays.fill(memoryStatus, -1);
        int lastAllocatedIndex = -1;

        for (int i = 0; i < processRequirements.length; i++) {
            boolean allocated = false;

            int startIndex = lastAllocatedIndex + 1;
            for (int j = startIndex; j < memoryBlocks.length; j++) {
                if (memoryBlocks[j] >= processRequirements[i] && memoryStatus[j] == -1) {
                    memoryStatus[j] = i;
                    System.out.println("Process " + i + " (size " + processRequirements[i] + ") allocated to block " + j + " (size " + memoryBlocks[j] + ")");
                    lastAllocatedIndex = j;
                    allocated = true;
                    break;
                }
            }

            if (!allocated) {
                for (int j = 0; j < startIndex; j++) {
                    if (memoryBlocks[j] >= processRequirements[i] && memoryStatus[j] == -1) {
                        memoryStatus[j] = i;
                        System.out.println("Process " + i + " (size " + processRequirements[i] + ") allocated to block " + j + " (size " + memoryBlocks[j] + ")");
                        lastAllocatedIndex = j;
                        allocated = true;
                        break;
                    }
                }
            }

            if (!allocated) {
                System.out.println("Process " + i + " could not be allocated.");
            }
        }
    }

    // Worst Fit Algorithm
    public static void worstFit(int[] memoryBlocks, int[] processRequirements) {
        int[] memoryStatus = new int[memoryBlocks.length];
        Arrays.fill(memoryStatus, -1);

        for (int i = 0; i < processRequirements.length; i++) {
            int worstIndex = -1;
            int worstFitSize = -1;

            for (int j = 0; j < memoryBlocks.length; j++) {
                if (memoryBlocks[j] >= processRequirements[i] && memoryStatus[j] == -1) {
                    int leftoverSpace = memoryBlocks[j] - processRequirements[i];
                    if (leftoverSpace > worstFitSize) {
                        worstFitSize = leftoverSpace;
                        worstIndex = j;
                    }
                }
            }

            if (worstIndex != -1) {
                memoryStatus[worstIndex] = i;
                System.out.println("Process " + i + " (size " + processRequirements[i] + ") allocated to block " + worstIndex + " (size " + memoryBlocks[worstIndex] + ")");
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

        System.out.println("\nNext Fit Allocation:");
        nextFit(memoryBlocks, processRequirements);

        System.out.println("\n----------------------------\n");

        System.out.println("Worst Fit Allocation:");
        worstFit(memoryBlocks, processRequirements);

        sc.close();
    }
}
