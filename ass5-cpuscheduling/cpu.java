import java.util.Scanner;

public class cpu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose Scheduling Algorithm:");
        System.out.println("1. First-Come, First-Served (FCFS)");
        System.out.println("2. Round Robin");
        int choice = sc.nextInt();

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] completionTime = new int[n];
        int[] turnAroundTime = new int[n];
        int[] waitingTime = new int[n];

        System.out.println("Enter arrival times and burst times for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + " Arrival Time: ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Process " + (i + 1) + " Burst Time: ");
            burstTime[i] = sc.nextInt();
        }

        if (choice == 1) {
            fcfsScheduling(arrivalTime, burstTime, completionTime, turnAroundTime, waitingTime, n);
        } else if (choice == 2) {
            System.out.print("Enter the time quantum: ");
            int quantum = sc.nextInt();
            int[] remainingTime = burstTime.clone(); // Track remaining burst time
            roundRobinScheduling(arrivalTime, burstTime, remainingTime, completionTime, turnAroundTime, waitingTime, quantum, n);
        } else {
            System.out.println("Invalid choice.");
        }
        sc.close();
    }

    // FCFS Scheduling Algorithm
    public static void fcfsScheduling(int[] arrivalTime, int[] burstTime, int[] completionTime, int[] turnAroundTime, int[] waitingTime, int n) {
        int currentTime = 0;

        for (int i = 0; i < n; i++) {
            // If the current time is less than the arrival time of the process, we wait until the process arrives.
            if (currentTime < arrivalTime[i]) {
                currentTime = arrivalTime[i];
            }

            // Calculate completion time of the current process
            completionTime[i] = currentTime + burstTime[i];
            currentTime = completionTime[i];  // Update current time to completion time of the current process

            // Calculate Turnaround Time and Waiting Time
            turnAroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnAroundTime[i] - burstTime[i];
        }

        displayResults("FCFS", arrivalTime, burstTime, completionTime, waitingTime, turnAroundTime, n);
    }

    // Round Robin Scheduling Algorithm
    public static void roundRobinScheduling(int[] arrivalTime, int[] burstTime, int[] remainingTime, int[] completionTime, int[] turnAroundTime, int[] waitingTime, int quantum, int n) {
        int currentTime = 0;
        boolean done;

        do {
            done = true;
            for (int i = 0; i < n; i++) {
                // Process is considered only if it has arrived and has remaining time
                if (remainingTime[i] > 0 && arrivalTime[i] <= currentTime) {
                    done = false; // Process remains to be executed
                    if (remainingTime[i] > quantum) {
                        // Execute for a time quantum
                        currentTime += quantum;
                        remainingTime[i] -= quantum;
                    } else {
                        // Complete the process
                        currentTime += remainingTime[i];
                        completionTime[i] = currentTime;
                        remainingTime[i] = 0;
                    }
                }
            }
        } while (!done); // Loop until all processes are done

        // Calculate Turnaround Time and Waiting Time
        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnAroundTime[i] - burstTime[i];
        }

        displayResults("Round Robin", arrivalTime, burstTime, completionTime, waitingTime, turnAroundTime, n);
    }

    // Display Results
    public static void displayResults(String algorithm, int[] arrivalTime, int[] burstTime, int[] completionTime, int[] waitingTime, int[] turnAroundTime, int n) {
        System.out.println("\n" + algorithm + " Scheduling Results:");
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time");

        int totalWaitingTime = 0, totalTurnAroundTime = 0;
        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnAroundTime += turnAroundTime[i];
            System.out.println((i + 1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + completionTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnAroundTime[i]);
        }

        System.out.println("\nAverage Waiting Time: " + (float) totalWaitingTime / n);
        System.out.println("Average Turnaround Time: " + (float) totalTurnAroundTime / n);
    }
}