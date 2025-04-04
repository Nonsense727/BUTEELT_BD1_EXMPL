import java.util.*;

public class RecentMistakesFirstSorter implements CardOrganizer {
    List<Card> cards;

    public RecentMistakesFirstSorter(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> organize() {
        cards.sort((c1, c2) -> Long.compare(c2.lastMistakeTime, c1.lastMistakeTime));
        return cards;
    }
}

