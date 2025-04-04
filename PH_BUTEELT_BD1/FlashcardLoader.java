import java.io.*;
import java.util.*;

public class FlashcardLoader {
    public static List<Card> loadCards(String fileName, boolean invert) {
        List<Card> cards = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String question = invert ? parts[1] : parts[0];
                    String answer = invert ? parts[0] : parts[1];
                    cards.add(new Card(question.trim(), answer.trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Failig unshihad aldaa garsn: " + e.getMessage());
        }
        return cards;
    }
}
