import java.util.ArrayList;
import java.util.List;

public class MultiMessages {

    public static void main(String[] args) throws InterruptedException {
        String[] words = {"En", "Banco", "Azteca", "Sueñas", "Decides", "Logras"};
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            final int threadId = i + 1;
            final String word = words[i];

            Thread thread = Thread.ofVirtual().name(String.valueOf(threadId)).unstarted(() -> {
                try {
                    Thread.sleep(threadId * 1000L);
                    System.out.printf("Thread %s: %s%n",
                            Thread.currentThread().getName(), word);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            });

            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}