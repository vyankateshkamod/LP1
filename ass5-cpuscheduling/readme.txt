### FCFS (First-Come, First-Served) Algorithm
1. **Initialize** the current time to 0.
2. **For each process** (in the order of arrival):
   - **Check if the process has arrived** by the current time. If not, update the current time to the process's arrival time.
   - **Calculate the completion time** of the process by adding its burst time to the current time.
   - **Update** the current time to the completion time of the process.
   - **Calculate turnaround time** as `Completion Time - Arrival Time`.
   - **Calculate waiting time** as `Turnaround Time - Burst Time`.
3. **Display results** with process details, and calculate average waiting and turnaround times.

### Round Robin Algorithm
1. **Initialize** the current time to 0.
2. Set the **time quantum** and **clone burst times** into remaining times.
3. **Loop** through processes until all are completed:
   - **For each process**:
     - If it has remaining time and has arrived by the current time:
       - If **remaining time > quantum**, allocate time quantum to the process, reduce its remaining time, and increase the current time by the quantum.
       - If **remaining time ≤ quantum**, complete the process, set its remaining time to 0, and update its completion time to the current time.
4. **Calculate turnaround time** as `Completion Time - Arrival Time` and **waiting time** as `Turnaround Time - Burst Time`.
5. **Display results** with process details, and calculate average waiting and turnaround times.

### 1. **Types of CPU Scheduler**:
   - **Long-Term Scheduler**: Decides which processes are admitted to the ready queue.
   - **Short-Term Scheduler**: Decides which process in the ready queue gets the CPU next.
   - **Medium-Term Scheduler**: Manages processes swapped in and out of memory, focusing on handling processes in the ready queue that might be swapped out to disk.

### 2. **Difference between Long and Short-Term Scheduling**:
   - **Long-Term Scheduling**: Controls the admission of processes into the system. It focuses on ensuring there is a balance between processes in memory and processes in the ready queue.
   - **Short-Term Scheduling**: Decides which process should run next, and is concerned with maximizing CPU utilization and ensuring the process with the highest priority or requirement gets the CPU.

### 3. **Logic of Program**:
   - The logic of a program refers to the sequence of instructions that define how the program operates to solve a particular task. In the context of scheduling, the program's logic would depend on the scheduling algorithm and process management functions.

### 4. **Preemptive vs Non-Preemptive Scheduling**:
   - **Preemptive Scheduling**: The CPU can be taken away from a running process if a higher-priority process becomes ready. The running process is interrupted and placed back in the ready queue.
   - **Non-Preemptive Scheduling**: Once a process starts executing, it runs until it finishes or voluntarily relinquishes control. It is not interrupted by other processes.

### 5. **Types of Scheduling Algorithms**:
   - **First-Come, First-Served (FCFS)**
   - **Shortest Job Next (SJN)**
   - **Priority Scheduling**
   - **Round Robin (RR)**
   - **Multilevel Queue Scheduling**
   - **Multilevel Feedback Queue Scheduling**

### 6. **Priority Scheduling May Cause Low-Priority Processes to Starve**:
   - In priority scheduling, processes with low priority may never get executed if there are always high-priority processes available to run. This leads to starvation of low-priority processes because they are continually preempted by higher-priority ones.

### 7. **Goals of Scheduling**:
   - **Maximize CPU Utilization**: Keep the CPU busy as much as possible.
   - **Maximize Throughput**: Complete as many processes as possible in a given time.
   - **Minimize Turnaround Time**: Minimize the total time from submission to completion of a process.
   - **Minimize Waiting Time**: Reduce the amount of time a process spends in the ready queue.
   - **Maximize Fairness**: Ensure all processes get a fair amount of CPU time.

### 8. **Difference between Preemptive and Non-Preemptive Scheduling**:
   - **Preemptive Scheduling**: The CPU can forcibly take away a process from the CPU if a higher-priority process arrives.
   - **Non-Preemptive Scheduling**: The running process is allowed to finish its execution before any other process is given CPU time, even if a higher-priority process arrives.

### 9. **Which Scheduling Algorithm is Best? Why?**:
   - There is no single "best" scheduling algorithm, as the effectiveness depends on the system and workload:
     - **FCFS** is simple but may lead to poor turnaround times for long processes.
     - **Round Robin** is fair and ideal for time-sharing systems.
     - **Shortest Job Next** minimizes waiting time but requires knowledge of future processes, making it impractical in most systems.
     - **Priority Scheduling** can lead to starvation, but it works well when prioritizing critical tasks.
   - The choice of algorithm should depend on the specific goals such as minimizing response time, maximizing throughput, or ensuring fairness.