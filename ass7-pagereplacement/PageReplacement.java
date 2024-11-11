import java.util.*;

public class PageReplacement {
    // Method to simulate FIFO page replacement algorithm
    public static void fifo(int[] pages, int frameSize) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> frames = new HashSet<>();
        int pageFaults = 0;
        int pageHits = 0;

        for (int page : pages) {
            if (!frames.contains(page)) {
                if (frames.size() == frameSize) {
                    int removed = queue.poll();
                    frames.remove(removed);
                }
                frames.add(page);
                queue.add(page);
                pageFaults++;
            } else {
                pageHits++;
            }
        }
        System.out.println("FIFO - Page Faults: " + pageFaults + ", Page Hits: " + pageHits);
    }

    // Method to simulate LRU page replacement algorithm
    public static void lru(int[] pages, int frameSize) {
        Map<Integer, Integer> frames = new LinkedHashMap<>();
        int pageFaults = 0;
        int pageHits = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            if (!frames.containsKey(page)) {
                if (frames.size() == frameSize) {
                    int lruPage = Collections.min(frames.entrySet(), Map.Entry.comparingByValue()).getKey();
                    frames.remove(lruPage);
                }
                pageFaults++;
            } else {
                pageHits++;
            }
            frames.put(page, i); // Update or add page with current timestamp
        }
        System.out.println("LRU - Page Faults: " + pageFaults + ", Page Hits: " + pageHits);
    }

    // Method to simulate Optimal page replacement algorithm
    public static void optimal(int[] pages, int frameSize) {
        Set<Integer> frames = new HashSet<>();
        int pageFaults = 0;
        int pageHits = 0;

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            if (!frames.contains(page)) {
                if (frames.size() == frameSize) {
                    int farthest = i;
                    int pageToReplace = -1;
                    for (int f : frames) {
                        int nextUse = Integer.MAX_VALUE;
                        for (int j = i + 1; j < pages.length; j++) {
                            if (pages[j] == f) {
                                nextUse = j;
                                break;
                            }
                        }
                        if (nextUse > farthest) {
                            farthest = nextUse;
                            pageToReplace = f;
                        }
                    }
                    frames.remove(pageToReplace);
                }
                frames.add(page);
                pageFaults++;
            } else {
                pageHits++;
            }
        }
        System.out.println("Optimal - Page Faults: " + pageFaults + ", Page Hits: " + pageHits);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of pages: ");
        int n = sc.nextInt();
        int[] pages = new int[n];
        System.out.println("Enter the page reference sequence:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.print("Enter the frame size: ");
        int frameSize = sc.nextInt();

        System.out.println("\nPage Replacement Algorithm Results:");
        fifo(pages, frameSize);
        lru(pages, frameSize);
        optimal(pages, frameSize);
    }
}
