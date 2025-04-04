import java.util.*;

public class WorstFirstSorter implements CardOrganizer {
    List<Card> cards;

    public WorstFirstSorter(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> organize() {
        cards.sort(Comparator.comparingDouble(c -> (double) c.correctCount / Math.max(1, c.totalAttempts)));
        return cards;
    }
}

