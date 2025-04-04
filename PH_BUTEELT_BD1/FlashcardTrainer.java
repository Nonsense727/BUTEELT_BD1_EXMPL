import java.util.*;

public class FlashcardTrainer {
    private final CardOrganizer organizer;
    private final int repetitions;
    private List<Card> cards;

    public FlashcardTrainer(CardOrganizer organizer, int repetitions) {
        this.organizer = organizer;
        this.repetitions = repetitions;
        this.cards = new ArrayList<>(organizer.organize());
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            long start = System.currentTimeMillis();
            boolean allCorrect = true;

            for (Card card : cards) {
                int correctStreak = 0;
                int totalRepeats = 0;

                while (correctStreak < repetitions) {
                    System.out.println("Asuult: " + card.question);
                    System.out.print("Tanii hariult: ");
                    String answer = scanner.nextLine();
                    totalRepeats++;

                    if (card.checkAnswer(answer)) {
                        correctStreak++;
                        System.out.println("Zuv bn!\n");
                    } else {
                        correctStreak = 0;
                        allCorrect = false;
                        System.out.println("Buruu bn! Zuv hariult: " + card.answer + "\n");
                    }
                }

                if (totalRepeats > 5) {
                    System.out.println(": REPEAT");
                }
                if (card.getCorrectCount() >= 3) {
                    System.out.println(": CONFIDENT");
                }
            }

            long totalTime = (System.currentTimeMillis() - start) / 1000;
            if (allCorrect) {
                System.out.println(": CORRECT");
            }
            if ((double) totalTime / cards.size() < 5) {
                System.out.println(": FAST LEARNER");
            }
        }
    }
}