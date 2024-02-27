package cdu.edu.ua;

import java.util.List;

public class ScoresStatUtil {
    public double getAverageScore(List<Double> studentScores) {
        if (studentScores.isEmpty()) {
            return 0;
        }
        double sum = 0.0;
        for (Double score : studentScores) {
            if (score < 1) {
                throw new IllegalArgumentException("Mark cannot be less then 1 (one).");
            }
            if (score > 100) {
                throw new IllegalArgumentException("Mark cannot be greater than 100 (one hundred).");
            }
            sum += score;
        }

        return sum / studentScores.size();
    }


}
