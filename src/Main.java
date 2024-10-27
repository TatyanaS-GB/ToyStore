public class Main {
    public static void main(String[] args) {
        ToyRaffle raffle = new ToyRaffle();
        raffle.put("1", "конструктор", 2);
        raffle.put("2", "робот", 2);
        raffle.put("3", "кукла", 6);

        raffle.saveResultsToFile("results.txt",10);
    }
}