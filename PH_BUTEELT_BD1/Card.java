public class Card {
    String question, answer;
    int correctCount = 0, totalAttempts = 0;
    long lastMistakeTime = 0;

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public boolean checkAnswer(String input) {
        totalAttempts++;
        if (input.trim().equalsIgnoreCase(answer.trim())) {
            correctCount++;
            return true;
        } else {
            lastMistakeTime = System.currentTimeMillis();
            return false;
        }
    }
}
