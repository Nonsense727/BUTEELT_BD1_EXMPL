import java.io.*;
import java.util.*;

public class FlashcardApp {
    public static void main(String[] args) {
        if (args.length == 0 || Arrays.asList(args).contains("--help")) {
            printHelp();
            return;
        }

        String fileName = args[0];
        String order = "random";
        int repetitions = 1;
        boolean invertCards = false;

        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("--order") && i + 1 < args.length) {
                order = args[i + 1];
            } else if (args[i].equals("--repetitions") && i + 1 < args.length) {
                repetitions = Integer.parseInt(args[i + 1]);
            } else if (args[i].equals("--invertCards")) {
                invertCards = true;
            }
        }

        List<Card> cards = FlashcardLoader.loadCards(fileName, invertCards);
        CardOrganizer organizer = getOrganizer(order, cards);

        FlashcardTrainer trainer = new FlashcardTrainer(organizer, repetitions);
        trainer.start();
    }

    private static CardOrganizer getOrganizer(String order, List<Card> cards) {
        return switch (order) {
            case "worst-first" -> new WorstFirstSorter(cards);
            case "recent-mistakes-first" -> new RecentMistakesFirstSorter(cards);
            default -> new RandomSorter(cards);
        };
    }

    private static void printHelp() {
        System.out.println("flashcard <cards-file> [options]");
        System.out.println("--help                      tuslamjiin medeelel haruulah");
        System.out.println("--order <order>             Zohion baiguulalt: random, worst-first, recent-mistakes-first");
        System.out.println("--repetitions <num>         Neg cardiig hdn udaa zuv haruulahiig shaardlaga bolgono");
        System.out.println("--invertCards               asuult hariult solij haruulna");
    }
}
