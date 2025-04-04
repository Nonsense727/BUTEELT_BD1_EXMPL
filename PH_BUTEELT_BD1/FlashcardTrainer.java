import java.util.*;

public class FlashcardTrainer {
    private final CardOrganizer organizer;
    private final int repetitions;

    public FlashcardTrainer(CardOrganizer organizer, int repetitions) {
        this.organizer = organizer;
        this.repetitions = repetitions;
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Card> organizedCards = organizer.organize();

            long start = System.currentTimeMillis();
            boolean allCorrect = true;

            for (Card card : organizedCards) {
                int correctStreak = 0;
                int totalRepeats = 0;

                while (correctStreak < repetitions) {
                    System.out.println("Асуулт: " + card.question);
                    System.out.print("→ ");
                    String answer = scanner.nextLine();
                    totalRepeats++;

                    if (card.checkAnswer(answer)) {
                        correctStreak++;
                        System.out.println("Зөв!\n");
                    } else {
                        correctStreak = 0;
                        allCorrect = false;
                        System.out.println("Буруу! Хариулт: " + card.answer + "\n");
                    }
                }

                if (totalRepeats > 5) {
                    System.out.println("🏆 Амжилт: REPEAT");
                }
                if (card.correctCount >= 3) {
                    System.out.println("🏆 Амжилт: CONFIDENT");
                }
            }

            long totalTime = (System.currentTimeMillis() - start) / 1000;
            if (allCorrect) {
                System.out.println("🏆 Амжилт: CORRECT");
            }
            if ((double) totalTime / organizedCards.size() < 5) {
                System.out.println("🏆 Амжилт: FAST LEARNER");
            }
        }
    }
}
