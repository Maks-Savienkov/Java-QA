package cdu.edu.ua;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoresStatUtilTest {
    private ScoresStatUtil scoresStatUtil;
    private static List<Double> studentScores;
    private static List<Double> listWithOnlyOneScore;
    private static List<Double> lessThanLowerBoundStudentScores;
    private static List<Double> greaterThanUpperBoundStudentScores;

    @BeforeAll
    static void beforeAll() {
        studentScores = new ArrayList<>();
        studentScores.add(3.0);
        studentScores.add(5.0);
        studentScores.add(5.0);
        studentScores.add(9.0);
        studentScores.add(8.0);

        listWithOnlyOneScore = new ArrayList<>();
        listWithOnlyOneScore.add(5.0);

        lessThanLowerBoundStudentScores = new ArrayList<>();
        lessThanLowerBoundStudentScores.add(0.0);
        lessThanLowerBoundStudentScores.add(-4.0);
        lessThanLowerBoundStudentScores.add(-12.0);
        lessThanLowerBoundStudentScores.add(-100.0);

        greaterThanUpperBoundStudentScores = new ArrayList<>();
        greaterThanUpperBoundStudentScores.add(101.0);
        greaterThanUpperBoundStudentScores.add(200.0);
    }

    @BeforeEach
    void beforeEach() {
        scoresStatUtil = new ScoresStatUtil();
    }

    @Test
    public void can_get_average() {
        assertEquals(6.0, scoresStatUtil.getAverageScore(studentScores));
    }

    @Test
    @DisplayName("getAverage_should_return_0_when_scores_list_is_empty")
    void test_getAverage_if_student_have_not_scores() {
        assertEquals(0, scoresStatUtil.getAverageScore(Collections.emptyList()));
    }

    @Test
    void can_get_average_if_student_have_only_one_mark() {
        assertEquals(5.0, scoresStatUtil.getAverageScore(listWithOnlyOneScore));
    }

    @Test
    void getAverage_throws_exception_if_scores_less_than_lower_bound() {
        String message = assertThrows(
                IllegalArgumentException.class,
                () -> scoresStatUtil.getAverageScore(lessThanLowerBoundStudentScores)
        ).getMessage();
        assertEquals(
                "Mark cannot be less then 1 (one).",
                message
        );
    }

    @Test
    void getAverage_throws_exception_if_scores_greater_than_upper_bound() {
        String message = assertThrows(
                IllegalArgumentException.class,
                () -> scoresStatUtil.getAverageScore(greaterThanUpperBoundStudentScores)
        ).getMessage();
        assertEquals(
                "Mark cannot be greater than 100 (one hundred).",
                message
        );
    }
}
