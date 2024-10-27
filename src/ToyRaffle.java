import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyRaffle {
    private PriorityQueue<Toy> toyQueue;

    public ToyRaffle() {
        toyQueue = new PriorityQueue<>();
    }

    public void put(String id, String name, int weight) {
        Toy toy = new Toy(id, name, weight);
        toyQueue.add(toy);
    }

    public String get() {
        if (toyQueue.isEmpty()) {
            return "No toys available";
        }

        Random random = new Random();
        int totalWeight = 0;
        for (Toy toy : toyQueue) {
            totalWeight += toy.weight;
        }

        int randomWeight = random.nextInt(totalWeight) + 1;
        for (Toy toy : toyQueue) {
            if (randomWeight <= toy.weight) {
                return toy.name;
            }
            randomWeight -= toy.weight;
        }

        return "Error: No toy found";
    }

    public void saveResultsToFile(String filename, int numResults) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < numResults; i++) {
                String result = get();
                writer.write(result + "\n");
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Toy implements Comparable<Toy> {
        String id;
        String name;
        int weight;

        public Toy(String id, String name, int weight) {
            this.id = id;
            this.name = name;
            this.weight = weight;
        }

        @Override
        public int compareTo(Toy other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
}