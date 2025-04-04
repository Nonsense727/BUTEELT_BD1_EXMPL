import java.util.*;

public class WorstFirstSorter implements CardOrganizer {
    List<Card> cards;

    public WorstFirstSorter(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> organize() {
        cards.sort((c1, c2) -> {
            double failureRate1 = 1 - (double) c1.getCorrectCount() / Math.max(1, c1.getTotalAttempts());
            double failureRate2 = 1 - (double) c2.getCorrectCount() / Math.max(1, c2.getTotalAttempts());
            return Double.compare(failureRate2, failureRate1); 
        });
        return cards;
    }
}