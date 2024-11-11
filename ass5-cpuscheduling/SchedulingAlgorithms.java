import java.util.*;

class Process {
    int id, arrivalTime, cpuBurstTime1, cpuBurstTime2, priority;
    int completionTime, waitingTime, turnaroundTime;

    // Constructor for Process
    Process(int id, int arrivalTime, int cpuBurstTime1, int cpuBurstTime2, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.cpuBurstTime1 = cpuBurstTime1;
        this.cpuBurstTime2 = cpuBurstTime2;
        this.priority = priority;
    }
}

public class SchedulingAlgorithms {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask the user which algorithm they want to use
        System.out.println("Which algorithm would you like to use?");
        System.out.println("1. SJF (Shortest Job First)");
        System.out.println("2. Priority Scheduling");
        int choice = sc.nextInt();

        // Ask for the number of processes
        System.out.println("Enter number of processes:");
        int n = sc.nextInt();

        Process[] processes = new Process[n];

        // Input processes with their burst times and priority if necessary
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time, CPU Burst Time 1, CPU Burst Time 2 for Process " + (i + 1));
            int arrival = sc.nextInt();
            int cpuBurst1 = sc.nextInt();
            int cpuBurst2 = sc.nextInt();
            
            int priority = -1; // Initialize priority variable
            sc.nextLine(); // Consume the leftover newline after reading burst times

            if (choice == 2) { // If Priority Scheduling is selected
                System.out.println("Enter Priority for Process " + (i + 1)); // Ask for priority
                priority = sc.nextInt();  // Now it will ask for priority
                sc.nextLine(); // Consume the leftover newline after reading priority
            }

            processes[i] = new Process(i + 1, arrival, cpuBurst1, cpuBurst2, priority);
        }

        // Apply selected scheduling algorithm
        if (choice == 1) {
            sjfScheduling(processes, n);
        } else if (choice == 2) {
            priorityScheduling(processes, n);
        } else {
            System.out.println("Invalid choice");
        }
    }

    // Shortest Job First Scheduling
    public static void sjfScheduling(Process[] processes, int n) {
        Arrays.sort(processes, Comparator.comparingInt(p -> p.cpuBurstTime1)); // Sort by CPU burst time (SJF)

        int currentTime = 0; // To keep track of the current time
        int completed = 0; // To keep track of the number of completed processes
        boolean[] isCompleted = new boolean[n]; // To mark processes as completed

        // Traverse until all processes are completed
        while (completed < n) {
            Process nextProcess = null;
            for (Process p : processes) {
                // Check for processes that are not completed and have arrived
                if (!isCompleted[p.id - 1] && p.arrivalTime <= currentTime) {
                    if (nextProcess == null || p.cpuBurstTime1 < nextProcess.cpuBurstTime1) {
                        nextProcess = p;
                    }
                }
            }

            if (nextProcess != null) {
                // Execute the first CPU burst time
                currentTime += nextProcess.cpuBurstTime1;
                // Now execute the second CPU burst time
                currentTime += nextProcess.cpuBurstTime2;

                nextProcess.completionTime = currentTime;
                nextProcess.turnaroundTime = nextProcess.completionTime - nextProcess.arrivalTime;
                nextProcess.waitingTime = nextProcess.turnaroundTime - (nextProcess.cpuBurstTime1 + nextProcess.cpuBurstTime2);
                isCompleted[nextProcess.id - 1] = true;
                completed++;
            } else {
                currentTime++; // If no process can be executed, increment time
            }
        }

        // Display results for each process
        System.out.println("\nSJF Scheduling Results");
        System.out.println(String.format("%-8s%-15s%-15s%-15s", "Process", "Completion Time", "Waiting Time", "Turnaround Time"));
        for (Process p : processes) {
            System.out.println(String.format("%-8d%-15d%-15d%-15d", p.id, p.completionTime, p.waitingTime, p.turnaroundTime));
        }
    }

    // Priority Scheduling
    public static void priorityScheduling(Process[] processes, int n) {
        // Sort processes by priority first (ascending order of priority)
        Arrays.sort(processes, Comparator.comparingInt(p -> p.priority));

        int currentTime = 0; // To keep track of the current time
        int completed = 0; // To keep track of the number of completed processes
        boolean[] isCompleted = new boolean[n]; // To mark processes as completed

        // Traverse until all processes are completed
        while (completed < n) {
            Process nextProcess = null;
            for (Process p : processes) {
                // Check for processes that are not completed and have arrived
                if (!isCompleted[p.id - 1] && p.arrivalTime <= currentTime) {
                    if (nextProcess == null || p.cpuBurstTime1 < nextProcess.cpuBurstTime1) {
                        nextProcess = p;
                    }
                }
            }

            if (nextProcess != null) {
                // Execute the first CPU burst time
                currentTime += nextProcess.cpuBurstTime1;
                // Now execute the second CPU burst time
                currentTime += nextProcess.cpuBurstTime2;

                nextProcess.completionTime = currentTime;
                nextProcess.turnaroundTime = nextProcess.completionTime - nextProcess.arrivalTime;
                nextProcess.waitingTime = nextProcess.turnaroundTime - (nextProcess.cpuBurstTime1 + nextProcess.cpuBurstTime2);
                isCompleted[nextProcess.id - 1] = true;
                completed++;
            } else {
                currentTime++; // If no process can be executed, increment time
            }
        }

        // Display results for each process
        System.out.println("\nPriority Scheduling Results");
        System.out.println(String.format("%-8s%-15s%-15s%-15s%-15s", "Process", "Priority", "Completion Time", "Waiting Time", "Turnaround Time"));
        for (Process p : processes) {
            System.out.println(String.format("%-8d%-15d%-15d%-15d%-15d", p.id, p.priority, p.completionTime, p.waitingTime, p.turnaroundTime));
        }
    }
}
