import java.util.*;

public class RandomSorter implements CardOrganizer {
    List<Card> cards;

    public RandomSorter(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public List<Card> organize() {
        Collections.shuffle(cards);
        return cards;
    }
}

