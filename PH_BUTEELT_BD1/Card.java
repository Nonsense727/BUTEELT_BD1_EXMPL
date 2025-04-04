import java.io.*;
import java.util.*;

public class Card implements Serializable {
    private static final String STATS_FILE = "card_stats.ser";
    String question, answer;
    private int correctCount = 0;
    private int totalAttempts = 0;
    private long lastMistakeTime = 0;

    public long getLastMistakeTime() {
        return lastMistakeTime;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public void setLastMistakeTime(long lastMistakeTime) {
        this.lastMistakeTime = lastMistakeTime;
    }

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public static void saveStats(List<Card> cards) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(STATS_FILE))) {
            out.writeObject(cards);
        } catch (IOException e) {
            System.out.println("Could not save statistics: " + e.getMessage());
        }
    }

    public static List<Card> loadStats(List<Card> currentCards) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(STATS_FILE))) {
            List<Card> savedCards = (List<Card>) in.readObject();
            // Merge with current cards
            for (Card current : currentCards) {
                for (Card saved : savedCards) {
                    if (current.question.equals(saved.question) && current.answer.equals(saved.answer)) {
                        current.correctCount = saved.correctCount;
                        current.totalAttempts = saved.totalAttempts;
                        current.lastMistakeTime = saved.lastMistakeTime;
                        break;
                    }
                }
            }
            return currentCards;
        } catch (Exception e) {
            // File doesn't exist or other error - return current cards
            return currentCards;
        }
        }
    

    public boolean checkAnswer(String input) {
        totalAttempts++;
        if (input.trim().equalsIgnoreCase(answer.trim())) {
            correctCount++;
            return true;
        } else {
            lastMistakeTime = System.currentTimeMillis(); // Update timestamp on mistake
            return false;
        }
    }
}
