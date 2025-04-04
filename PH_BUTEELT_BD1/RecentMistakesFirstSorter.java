import java.util.*;

public class RecentMistakesFirstSorter implements CardOrganizer {
    List<Card> cards;

    public RecentMistakesFirstSorter(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> organize() {
        
        cards.sort((c1, c2) -> {
            if (c1.getLastMistakeTime() == 0 && c2.getLastMistakeTime() == 0) return 0;
            if (c1.getLastMistakeTime() == 0) return 1;
            if (c2.getLastMistakeTime() == 0) return -1;
            return Long.compare(c2.getLastMistakeTime(), c1.getLastMistakeTime());
        });
        return cards;
    }
}