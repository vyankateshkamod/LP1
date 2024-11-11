Theory: 
• There is a data area shared among a number of processor registers. • The data area could be a file, a block of main memory, or even a bank of processor registers. 
• There are a number of processes that only read the data area (readers) and a number that only write to the data area (writers). 
• The conditions that must be satisfied are
➢ Any number of readers may read simultaneously read the file. 
➢ Only one write at a time may write to the file. 
➢ If a writer is writing to the file, no reader may read it. 

Semaphore:
    Definition: Semaphores are system variables used for synchronization of process
    Two types of Semaphore:
    ➢ Counting semaphore – integer value can range over an unrestricted domain
    ➢ Binary semaphore –
    Integer value can range only between 0 and 1; can be simpler to implement Also known as mutex locks

Algorithm for Reader Writer:
1. import java.util.concurrent.Semaphore;
2. Create a class RW
3. Declare semaphores – mutex and wrt
4. Declare integer variable readcount = 0
5. Create a nested class Reader implements Runnable
    a. Override run method (Reader Logic)
        i. wait(mutex);
        ii. readcount := readcount +1;
        iii. if readcount = 1 then
        iv. wait(wrt);
        v. signal(mutex);
        vi. ...
        vii. reading is performed
        viii. ...
        ix. wait(mutex);
        x. readcount := readcount – 1;
        xi. if readcount = 0 then signal(wrt);
        xii. signal(mutex):

6. Create a nested class Writer implements Runnable
    a. Override run method (Writer Logic)
        i. wait(wrt);
        ii. ...
        iii. writing is performed
        iv. ...
        v. signal(wrt);
7. Create a class main
    a. Create Threads for Reader and Writer
    b. Start these thread





### 1. **Synchronization of Threads**:
   - Thread synchronization is the coordination of the execution of threads to ensure that shared resources are accessed safely. Without synchronization, multiple threads might interfere with each other while accessing shared resources, leading to race conditions and inconsistent data. Synchronization ensures that threads execute in a proper sequence to avoid these issues. Techniques like locks, mutexes, semaphores, and monitors are used to achieve synchronization.

### 2. **Reader-Writer Problem**:
   - The reader-writer problem deals with a situation where multiple threads are accessing shared data. Some threads only **read** the data, while others **write** to it. The challenge is to allow multiple threads to read the data simultaneously but to ensure that writing to the data is exclusive (i.e., no other thread can read or write while a thread is writing). The problem has two main types:
     - **First-Come, First-Served (FCFS)**: Writers have exclusive access, and multiple readers can read at once, but no writer can write if any readers are reading.
     - **Priority to Writers**: Writers have priority over readers to avoid indefinite blocking of writers.

### 3. **Wait and Signal (or Sequence) Functions**:
   - **Wait (P) Operation**: Decreases the value of a semaphore. If the value is less than 0, the process executing the operation is blocked until the value becomes greater than or equal to 0.
   - **Signal (V) Operation**: Increases the value of a semaphore. If there are any blocked processes waiting for the semaphore, one of them will be unblocked.

   These functions are fundamental in synchronization mechanisms, where `Wait` is used to acquire resources, and `Signal` is used to release them.

### 4. **Semaphore**:
   - A **semaphore** is a synchronization tool used to control access to a shared resource in a concurrent system such as multi-threaded or multi-process applications. It is a variable or abstract data type that provides a simple but powerful mechanism for managing the synchronization of processes or threads. Semaphores are typically implemented as counters with two primary operations: **Wait** (P) and **Signal** (V). They help prevent race conditions by ensuring that only a limited number of processes can access the resource at the same time.

### 5. **Different Types of Semaphore**:
   - **Binary Semaphore (Mutex)**: A binary semaphore is a semaphore with only two values: 0 and 1. It is used to implement mutual exclusion (mutex), allowing only one process to access the critical section at a time.
   - **Counting Semaphore**: A counting semaphore is used to manage access to a resource pool with multiple instances. The value of the semaphore can range from 0 to an arbitrary positive integer. It tracks the available resources (e.g., the number of printers or buffers). The value is incremented when a resource is released and decremented when a resource is acquired.