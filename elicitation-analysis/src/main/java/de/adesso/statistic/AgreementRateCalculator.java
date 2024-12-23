package de.adesso.statistic;

import de.adesso.core.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static de.adesso.ElicitationAnalysisApp.SEPARATOR;

public class AgreementRateCalculator {

    private static final List<Integer> allIds = IntStream.rangeClosed(1, 36).boxed().collect(Collectors.toList());
    private static final List<Integer> femaleIds = Arrays.asList(1, 6, 9, 11, 12, 14, 19, 20, 23, 25, 29, 34);
    private static final List<Integer> maleIds = Arrays.asList(21, 22, 24, 26, 27, 28, 30, 31, 32, 33, 35, 36, 2, 3, 4, 5, 7, 8, 10, 13, 15, 16, 17);

    private static final List<Integer> youngAgeIds = Arrays.asList(6, 11, 12, 17, 22, 26, 35);
    private static final List<Integer> oldAgeIds = Arrays.asList(1, 2, 3, 5, 10, 13, 14, 16, 18, 30, 34);
    private static final List<Integer> middleAgeIds = Arrays.asList(4, 7, 8, 9, 15, 19, 20, 21, 23, 24, 25, 27, 28, 29, 31, 32, 33, 36);

    private static final List<Integer> usePencilIds = Arrays.asList(1, 5, 6, 9, 10, 11, 12, 13, 14, 15, 19, 21, 22, 23, 24, 28, 29, 31, 32, 35, 36);
    private static final List<Integer> notUsePencilIds = Arrays.asList(2, 3, 4, 7, 8, 16, 17, 18, 20, 25, 26, 27, 30, 33, 34);

    private static final List<Integer> englishIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
    private static final List<Integer> germanIds = Arrays.asList(19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36);

    private static final List<List<Integer>> RESULTS = new ArrayList<>();

    private static final List<List<Integer>> TASK1 = Arrays.asList(
            Arrays.asList(3, 8, 9, 10, 15, 16, 17, 20, 21, 23, 26, 30, 34),
            Arrays.asList(2, 6, 7, 11, 12, 13, 25, 29, 31, 32, 33, 36),
            Arrays.asList(5, 18, 35),
            Arrays.asList(1, 4),
            Arrays.asList(14),
            Arrays.asList(19, 22, 24, 28),
            Arrays.asList(27)
    );

    private static final List<List<Integer>> TASK2 = Arrays.asList(
            Arrays.asList(2, 3, 6, 7, 8, 11, 12, 13, 14, 16, 17, 19, 20, 21, 22, 23, 24, 25, 27, 28, 29, 30, 31, 32, 33, 34, 36),
            Arrays.asList(9),
            Arrays.asList(18),
            Arrays.asList(1),
            Arrays.asList(4),
            Arrays.asList(15),
            Arrays.asList(5),
            Arrays.asList(26),
            Arrays.asList(35),
            Arrays.asList(10)
    );

    private static final List<List<Integer>> TASK3 = Arrays.asList(
            Arrays.asList(2, 3, 7, 11, 13, 16, 17, 19, 20, 21, 22, 23, 25, 27, 28, 29, 31, 32, 33, 34),
            Arrays.asList(8),
            Arrays.asList(6, 14, 30, 36),
            Arrays.asList(12),
            Arrays.asList(18),
            Arrays.asList(5),
            Arrays.asList(9),
            Arrays.asList(1),
            Arrays.asList(4),
            Arrays.asList(24),
            Arrays.asList(15),
            Arrays.asList(26),
            Arrays.asList(10),
            Arrays.asList(35)
    );

    private static final List<List<Integer>> TASK4 = Arrays.asList(
            Arrays.asList(2, 3, 12, 16, 19, 21, 23, 31, 32, 36),
            Arrays.asList(5),
            Arrays.asList(1),
            Arrays.asList(18),
            Arrays.asList(14),
            Arrays.asList(7, 13, 15, 17, 20, 22, 29, 30),
            Arrays.asList(11),
            Arrays.asList(9, 24, 33),
            Arrays.asList(6, 8, 10, 27, 28),
            Arrays.asList(25),
            Arrays.asList(4),
            Arrays.asList(26),
            Arrays.asList(34),
            Arrays.asList(35)
    );

    private static final List<List<Integer>> TASK5 = Arrays.asList(
            Arrays.asList(1, 5, 8, 10, 11, 15, 16, 17),
            Arrays.asList(6, 12, 13, 36),
            Arrays.asList(2),
            Arrays.asList(7),
            Arrays.asList(4),
            Arrays.asList(14),
            Arrays.asList(22),
            Arrays.asList(3, 26, 32),
            Arrays.asList(18, 19, 20, 21, 27, 28, 29, 31),
            Arrays.asList(9),
            Arrays.asList(23),
            Arrays.asList(24, 30),
            Arrays.asList(25, 33),
            Arrays.asList(34),
            Arrays.asList(35)
    );

    private static final List<List<Integer>> TASK6 = Arrays.asList(
            Arrays.asList(3, 12, 13, 16, 19, 20, 21, 22, 23, 28, 29, 30, 31, 33, 34, 36),
            Arrays.asList(2, 6, 7, 14, 17, 25, 27, 32),
            Arrays.asList(1, 5, 8, 10, 11, 15),
            Arrays.asList(18),
            Arrays.asList(4),
            Arrays.asList(9),
            Arrays.asList(24),
            Arrays.asList(26),
            Arrays.asList(35)
    );

    private static final List<List<Integer>> TASK7 = Arrays.asList(
            Arrays.asList(3, 5, 7, 17, 20, 21, 23, 25, 26, 27, 29, 31, 33),
            Arrays.asList(8, 13, 16, 18, 32),
            Arrays.asList(6, 30),
            Arrays.asList(11),
            Arrays.asList(15),
            Arrays.asList(1),
            Arrays.asList(19, 22, 28),
            Arrays.asList(14),
            Arrays.asList(4),
            Arrays.asList(2),
            Arrays.asList(12),
            Arrays.asList(9),
            Arrays.asList(24),
            Arrays.asList(10, 36),
            Arrays.asList(34),
            Arrays.asList(35)
    );

    private static final List<List<Integer>> TASK8 = Arrays.asList(
            Arrays.asList(8, 10, 20, 21, 22, 23, 28, 33, 36),
            Arrays.asList(6, 9, 13, 15, 17),
            Arrays.asList(3, 16, 19, 31, 32),
            Arrays.asList(5, 7, 18),
            Arrays.asList(1),
            Arrays.asList(34),
            Arrays.asList(24, 27, 29, 30),
            Arrays.asList(14),
            Arrays.asList(4),
            Arrays.asList(2),
            Arrays.asList(12),
            Arrays.asList(11),
            Arrays.asList(25),
            Arrays.asList(26),
            Arrays.asList(35)
    );

    private static final List<List<Integer>> TASK9 = Arrays.asList(
            Arrays.asList(3, 12, 14, 21, 26, 27, 28, 29, 31),
            Arrays.asList(11, 20, 22, 32),
            Arrays.asList(1, 4, 7),
            Arrays.asList(8, 10, 17, 18, 33),
            Arrays.asList(15, 16, 34),
            Arrays.asList(2, 5, 6),
            Arrays.asList(9),
            Arrays.asList(13),
            Arrays.asList(19, 23, 24, 30, 36),
            Arrays.asList(25),
            Arrays.asList(35)
    );

    private static final List<List<Integer>> TASK10 = Arrays.asList(
            Arrays.asList(6, 9, 12, 14, 16, 20, 21, 22, 24, 25, 28, 29),
            Arrays.asList(3, 26, 36),
            Arrays.asList(1, 7, 15),
            Arrays.asList(4, 11, 33, 34),
            Arrays.asList(5),
            Arrays.asList(18),
            Arrays.asList(17),
            Arrays.asList(32),
            Arrays.asList(8, 10),
            Arrays.asList(13),
            Arrays.asList(31),
            Arrays.asList(2),
            Arrays.asList(19),
            Arrays.asList(27),
            Arrays.asList(23, 30),
            Arrays.asList(35)
    );

    private static final List<List<Integer>> TASK11 = Arrays.asList(
            Arrays.asList(1, 16, 18, 32),
            Arrays.asList(7, 8, 10, 17, 34),
            Arrays.asList(2, 11, 15, 33),
            Arrays.asList(3, 12, 21, 31, 36),
            Arrays.asList(4, 5, 6),
            Arrays.asList(14),
            Arrays.asList(9),
            Arrays.asList(19, 23, 24, 27, 28, 29, 30),
            Arrays.asList(20, 26),
            Arrays.asList(22, 25),
            Arrays.asList(13),
            Arrays.asList(35)
    );

    private static final List<List<Integer>> TASK12 = Arrays.asList(
            Arrays.asList(3, 14, 20, 21, 23, 24, 26, 27, 28, 29, 31, 32, 36),
            Arrays.asList(4, 5, 7, 13, 15, 34),
            Arrays.asList(12, 19, 22, 25, 30),
            Arrays.asList(8, 10, 11, 16, 17, 33),
            Arrays.asList(2),
            Arrays.asList(1),
            Arrays.asList(6, 9),
            Arrays.asList(35),
            Arrays.asList(18)
    );

    private static final List<List<Integer>> TASK13 = Arrays.asList(
            Arrays.asList(3, 7, 14, 18, 20, 21, 23, 24, 26, 27, 28, 29, 31, 32, 36),
            Arrays.asList(8, 10, 11, 17, 33),
            Arrays.asList(16, 19, 22, 25, 30),
            Arrays.asList(2),
            Arrays.asList(6, 9),
            Arrays.asList(5),
            Arrays.asList(4, 12, 15, 34),
            Arrays.asList(1),
            Arrays.asList(13),
            Arrays.asList(35)
    );

    private static final List<List<Integer>> TASK14 = Arrays.asList(
            Arrays.asList(3, 7, 15, 17, 19, 21, 23, 26, 28, 29),
            Arrays.asList(8, 10, 13, 32),
            Arrays.asList(4, 11),
            Arrays.asList(12),
            Arrays.asList(6),
            Arrays.asList(34),
            Arrays.asList(1, 14),
            Arrays.asList(2),
            Arrays.asList(33),
            Arrays.asList(5),
            Arrays.asList(22, 27),
            Arrays.asList(18, 31),
            Arrays.asList(9, 16, 20, 25),
            Arrays.asList(24, 30),
            Arrays.asList(35),
            Arrays.asList(36)
    );

    private static final List<List<Integer>> TASK15 = Arrays.asList(
            Arrays.asList(3, 9, 16, 21, 22, 26, 29),
            Arrays.asList(1, 7, 13, 34),
            Arrays.asList(17, 19, 27, 28, 30),
            Arrays.asList(25),
            Arrays.asList(14),
            Arrays.asList(31),
            Arrays.asList(32),
            Arrays.asList(8, 11, 15, 18),
            Arrays.asList(10),
            Arrays.asList(4),
            Arrays.asList(6),
            Arrays.asList(12, 20),
            Arrays.asList(23),
            Arrays.asList(24),
            Arrays.asList(2, 5),
            Arrays.asList(33),
            Arrays.asList(35),
            Arrays.asList(36)
    );

    private static final List<List<Integer>> TASK16 = Arrays.asList(
            Arrays.asList(3, 14, 20, 22, 28),
            Arrays.asList(21, 26, 27, 29),
            Arrays.asList(10, 11, 18, 33),
            Arrays.asList(17),
            Arrays.asList(4, 5, 8, 15, 16, 34),
            Arrays.asList(6, 9),
            Arrays.asList(1, 7),
            Arrays.asList(2),
            Arrays.asList(12),
            Arrays.asList(36),
            Arrays.asList(19, 23, 24, 25, 30, 31),
            Arrays.asList(32),
            Arrays.asList(13),
            Arrays.asList(35)
    );

    private static final List<List<Integer>> TASK17 = Arrays.asList(
            Arrays.asList(8, 21, 28, 29),
            Arrays.asList(27, 13),
            Arrays.asList(7),
            Arrays.asList(2),
            Arrays.asList(34),
            Arrays.asList(6, 15, 25),
            Arrays.asList(10),
            Arrays.asList(1),
            Arrays.asList(5),
            Arrays.asList(33),
            Arrays.asList(12),
            Arrays.asList(17),
            Arrays.asList(18),
            Arrays.asList(4),
            Arrays.asList(14, 20, 22, 23),
            Arrays.asList(3, 11, 19, 24),
            Arrays.asList(16, 26, 32),
            Arrays.asList(9),
            Arrays.asList(30),
            Arrays.asList(31),
            Arrays.asList(35),
            Arrays.asList(36)
    );

    private static final List<List<Integer>> TASK18 = Arrays.asList(
            Arrays.asList(3, 14, 21),
            Arrays.asList(6, 8, 13, 17, 25),
            Arrays.asList(26, 28, 29),
            Arrays.asList(11),
            Arrays.asList(12),
            Arrays.asList(4),
            Arrays.asList(16),
            Arrays.asList(9),
            Arrays.asList(15),
            Arrays.asList(2),
            Arrays.asList(33),
            Arrays.asList(5),
            Arrays.asList(32),
            Arrays.asList(7),
            Arrays.asList(1),
            Arrays.asList(18),
            Arrays.asList(10),
            Arrays.asList(19),
            Arrays.asList(20),
            Arrays.asList(22, 31),
            Arrays.asList(23),
            Arrays.asList(24),
            Arrays.asList(27),
            Arrays.asList(30),
            Arrays.asList(34),
            Arrays.asList(35),
            Arrays.asList(36)
    );

    private static final List<List<Integer>> TASK19 = Arrays.asList(
            Arrays.asList(1),
            Arrays.asList(20, 22, 24, 29, 31),
            Arrays.asList(11, 12, 25, 27),
            Arrays.asList(21, 28),
            Arrays.asList(6),
            Arrays.asList(3, 19, 23, 32, 36),
            Arrays.asList(26),
            Arrays.asList(8, 10, 14, 16, 17, 33),
            Arrays.asList(15, 34),
            Arrays.asList(13),
            Arrays.asList(30),
            Arrays.asList(9),
            Arrays.asList(18),
            Arrays.asList(2),
            Arrays.asList(7, 35),
            Arrays.asList(4),
            Arrays.asList(5)
    );

    private static final List<List<Integer>> TASK20 = Arrays.asList(
            Arrays.asList(6, 10, 15, 25, 33),
            Arrays.asList(12, 16, 31, 32),
            Arrays.asList(8, 17),
            Arrays.asList(14),
            Arrays.asList(11),
            Arrays.asList(13),
            Arrays.asList(5),
            Arrays.asList(18),
            Arrays.asList(9),
            Arrays.asList(29),
            Arrays.asList(2),
            Arrays.asList(34),
            Arrays.asList(1),
            Arrays.asList(7),
            Arrays.asList(4),
            Arrays.asList(19, 24, 27),
            Arrays.asList(23, 26, 36),
            Arrays.asList(21, 22, 28),
            Arrays.asList(35),
            Arrays.asList(3),
            Arrays.asList(20),
            Arrays.asList(30)
    );

    private static final int[][] RESULTS_ALL = {
            {13, 12, 3, 2, 1, 4, 1},
            {27, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {20, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {10, 1, 1, 1, 1, 8, 1, 3, 5, 1, 1, 1, 1, 1},
            {8, 4, 1, 1, 1, 1, 1, 3, 8, 1, 1, 2, 2, 1, 1},
            {16, 8, 6, 1, 1, 1, 1, 1, 1},
            {13, 5, 2, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 2, 1, 1},
            {9, 5, 5, 3, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1},
            {9, 4, 3, 5, 3, 3, 1, 1, 5, 1, 1},
            {12, 3, 3, 4, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1},
            {4, 5, 4, 5, 3, 1, 1, 7, 2, 2, 1, 1},
            {13, 6, 5, 6, 1, 1, 2, 1, 1},
            {15, 5, 5, 1, 2, 1, 4, 1, 1, 1},
            {10, 4, 2, 1, 1, 1, 2, 1, 1, 1, 2, 2, 4, 2, 1, 1},
            {7, 4, 5, 1, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1},
            {5, 4, 4, 1, 6, 2, 2, 1, 1, 1, 6, 1, 1, 1},
            {4, 2, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 4, 4, 3, 1, 1, 1, 1, 1},
            {3, 5, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1},
            {1, 5, 4, 2, 1, 5, 1, 6, 2, 1, 1, 1, 1, 1, 2, 1, 1},
            {5, 4, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 1, 1, 1}
    };

    private static final int[][] RESULTS_FEMALE = {
            {4, 5, 1, 1, 1},
            {10, 1, 1},
            {7, 2, 1, 1, 1},
            {3, 1, 1, 2, 1, 1, 1, 1, 1},
            {2, 2, 1, 3, 1, 1, 1, 1},
            {6, 3, 2, 1},
            {4, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 2, 1, 1, 1, 1, 1, 1, 1, 1},
            {3, 2, 1, 1, 1, 1, 2, 1},
            {7, 1, 2, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 3, 1, 1},
            {4, 1, 3, 1, 1, 2},
            {4, 1, 2, 2, 2, 1},
            {3, 1, 1, 1, 1, 2, 3},
            {2, 2, 1, 1, 1, 1, 1, 2, 1},
            {2, 1, 1, 1, 2, 1, 1, 3},
            {1, 1, 2, 1, 1, 3, 2, 1},
            {1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 3, 1, 2, 1, 1, 1},
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    private static final int[][] RESULTS_MALE = {
            {9, 7, 2, 1, 3, 1},
            {17, 1, 1, 1, 1, 1, 1},
            {13, 1, 2, 1, 1, 1, 1, 1, 1, 1},
            {7, 1, 6, 2, 4, 1, 1, 1},
            {6, 2, 1, 1, 1, 1, 3, 4, 2, 1, 1},
            {10, 5, 4, 1, 1, 1, 1},
            {9, 4, 1, 1, 2, 1, 1, 1, 2, 1},
            {7, 3, 4, 2, 3, 1, 1, 1, 1},
            {6, 2, 2, 4, 2, 2, 1, 3, 1},
            {5, 3, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1},
            {2, 4, 3, 4, 2, 4, 1, 1, 1, 1},
            {9, 5, 2, 5, 1, 1},
            {10, 4, 3, 1, 1, 2, 1, 1},
            {7, 4, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1},
            {5, 2, 4, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1},
            {3, 3, 2, 1, 5, 1, 1, 1, 3, 1, 1, 1},
            {3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 1, 1, 1, 1},
            {2, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1},
            {3, 1, 2, 3, 1, 5, 1, 1, 1, 1, 2, 1, 1},
            {3, 3, 2, 1, 1, 1, 1, 1, 2, 2, 3, 1, 1, 1}
    };

    private static final int[][] RESULTS_YOUNG = {
            {2, 3, 1, 1},
            {5, 1, 1},
            {3, 1, 1, 1, 1},
            {1, 2, 1, 1, 1, 1},
            {2, 2, 1, 1, 1},
            {2, 2, 1, 1, 1},
            {2, 1, 1, 1, 1, 1},
            {1, 2, 1, 1, 1, 1},
            {2, 2, 1, 1, 1},
            {3, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 2, 2, 1, 1},
            {1, 2, 1, 1, 1, 1},
            {2, 1, 1, 1, 1, 1},
            {2, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {2, 1, 1, 1, 1, 1},
            {1, 2, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
    };

    private static final int[][] RESULTS_OLD = {
            {5, 2, 2, 1, 1},
            {7, 1, 1, 1, 1},
            {5, 2, 1, 1, 1, 1},
            {3, 1, 1, 1, 1, 2, 1, 1},
            {4, 1, 1, 1, 1, 1, 1, 1},
            {5, 2, 3, 1},
            {2, 3, 1, 1, 1, 1, 1, 1},
            {1, 1, 2, 2, 1, 1, 1, 1, 1},
            {2, 1, 2, 2, 2, 1, 1},
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {3, 2, 1, 1, 1, 1, 1, 1},
            {2, 3, 1, 2, 1, 1, 1},
            {3, 1, 2, 1, 1, 1, 1, 1},
            {1, 2, 1, 2, 1, 1, 1, 1, 1},
            {2, 3, 1, 1, 1, 1, 2},
            {2, 2, 3, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 3, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},

    };

    private static final int[][] RESULTS_MIDDLE = {
            {6, 7, 1, 3, 1},
            {15, 1, 1, 1},
            {12, 1, 1, 1, 1, 1, 1},
            {6, 4, 3, 3, 1, 1},
            {2, 1, 1, 1, 1, 7, 1, 1, 1, 2},
            {9, 4, 2, 1, 1, 1},
            {9, 2, 1, 2, 1, 1, 1, 1},
            {7, 2, 3, 1, 3, 1, 1},
            {5, 2, 2, 2, 1, 1, 4, 1},
            {7, 1, 2, 2, 1, 1, 1, 1, 1, 1},
            {1, 2, 2, 3, 1, 1, 6, 1, 1},
            {10, 3, 2, 2, 1},
            {11, 2, 2, 1, 2},
            {7, 2, 1, 1, 1, 1, 3, 1, 1},
            {3, 1, 3, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1},
            {2, 3, 1, 3, 1, 1, 1, 5, 1},
            {4, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1},
            {1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {4, 2, 2, 4, 2, 1, 1, 1, 1},
            {3, 2, 1, 1, 1, 1, 1, 3, 2, 2, 1},
    };

    private static final int[][] RESULTS_USE_PENCIL = {
            {5, 8, 2, 1, 1, 4},
            {15, 1, 1, 1, 1, 1, 1},
            {10, 3, 1, 1, 1, 1, 1, 1, 1, 1},
            {7, 1, 1, 1, 4, 1, 2, 3, 1},
            {5, 4, 1, 1, 1, 5, 1, 1, 1, 1},
            {10, 3, 5, 1, 1, 1},
            {5, 2, 1, 1, 1, 1, 3, 1, 1, 1, 1, 2, 1},
            {6, 4, 3, 1, 1, 2, 1, 1, 1, 1},
            {6, 3, 1, 1, 1, 2, 1, 1, 4, 1},
            {9, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 1, 2, 4, 2, 1, 1, 5, 1, 1, 1},
            {9, 3, 3, 2, 1, 2, 1},
            {9, 2, 2, 2, 1, 2, 1, 1, 1},
            {6, 3, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1},
            {4, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1},
            {3, 2, 2, 2, 2, 1, 1, 1, 4, 1, 1, 1},
            {3, 1, 2, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1},
            {2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1},
            {1, 4, 2, 2, 1, 4, 2, 1, 1, 1, 1, 1},
            {3, 3, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 1},
    };

    private static final int[][] RESULTS_NOT_USE_PENCIL = {
            {8, 4, 1, 1, 1},
            {12, 1, 1, 1},
            {10, 1, 1, 1, 1, 1},
            {3, 1, 4, 1, 2, 1, 1, 1, 1},
            {3, 1, 1, 1, 2, 3, 1, 2, 1},
            {6, 5, 1, 1, 1, 1},
            {8, 3, 1, 1, 1, 1},
            {3, 1, 2, 2, 1, 2, 1, 1, 1, 1},
            {3, 1, 2, 4, 2, 1, 1, 1},
            {3, 2, 1, 3, 1, 1, 1, 1, 1, 1},
            {2, 4, 2, 1, 1, 2, 2, 1},
            {4, 3, 2, 4, 1, 1},
            {6, 3, 3, 1, 2},
            {4, 1, 1, 1, 1, 1, 1, 1, 3, 1},
            {3, 2, 3, 1, 2, 1, 1, 1, 1},
            {2, 2, 2, 1, 4, 1, 1, 2},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1},
            {1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 1, 1, 4, 1, 1, 1, 1, 1, 1},
            {2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    private static final int[][] RESULTS_ENGLISH = {
            {7, 6, 2, 2, 1},
            {11, 1, 1, 1, 1, 1, 1, 1},
            {7, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1},
            {4, 1, 1, 1, 1, 4, 1, 1, 3, 1},
            {8, 3, 1, 1, 1, 1, 1, 1, 1},
            {4, 5, 6, 1, 1, 1},
            {4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 5, 2, 3, 1, 1, 1, 1, 1, 1},
            {3, 1, 3, 4, 2, 3, 1, 1},
            {5, 1, 3, 2, 1, 1, 1, 2, 1, 1},
            {3, 4, 3, 2, 3, 1, 1, 1},
            {2, 5, 1, 5, 1, 1, 2, 1},
            {4, 4, 1, 1, 2, 1, 3, 1, 1},
            {4, 3, 2, 1, 1, 2, 1, 1, 1, 2},
            {3, 3, 1, 1, 4, 1, 1, 1, 1, 2},
            {2, 3, 1, 5, 2, 2, 1, 1, 1},
            {1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1},
            {2, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1},
            {3, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private static final int[][] RESULTS_GERMAN = {
            {6, 6, 1, 4, 1},
            {16, 1, 1},
            {13, 2, 1, 1, 1},
            {6, 4, 2, 2, 1, 1, 1, 1},
            {1, 1, 2, 7, 1, 2, 2, 1, 1},
            {12, 3, 1, 1, 1},
            {9, 1, 1, 3, 1, 1, 1, 1},
            {7, 3, 1, 4, 1, 1, 1},
            {6, 3, 1, 1, 5, 1, 1},
            {7, 2, 2, 1, 1, 1, 1, 2, 1},
            {1, 1, 1, 3, 7, 2, 2, 1},
            {11, 1, 4, 1, 1},
            {11, 1, 4, 1, 1},
            {6, 1, 1, 1, 2, 1, 2, 2, 1, 1},
            {4, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {3, 4, 1, 1, 1, 6, 1, 1},
            {3, 1, 1, 1, 1, 3, 2, 2, 1, 1, 1, 1},
            {1, 1, 3, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1},
            {5, 2, 2, 4, 1, 1, 1, 1, 1},
            {2, 2, 1, 1, 3, 3, 3, 1, 1, 1}
    };

    private static final int[][] RESULTS_COMMAND_TYPE = {
            {25, 3, 2, 1, 4, 1}, // Ändern
            {76, 3, 1, 2, 10, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1}, // Löschen
            {25, 1, 1, 2, 1, 8, 4, 1, 25, 1, 1, 1, 3, 1, 2, 1, 1, 1, 2, 3, 1, 2, 20}, // Summe (Zeichen und Summe getrennt)
            {45, 1, 1, 2, 1, 8, 4, 1, 25, 1, 1, 1, 3, 1, 2, 1, 1, 1, 2, 3, 1, 2}, // Summe (Zeichen und Summe zsm)
            {8, 4, 1, 1, 1, 1, 1, 3, 8, 1, 1, 2, 2, 1, 1}, // Spalte einfügen
            {13, 5, 2, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 2, 1, 1}, // Verschieben
            {9, 4, 3, 5, 3, 3, 1, 1, 5, 1, 1}, // Formatierung
            {12, 3, 3, 4, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // Zellen umrahmen
            {9, 4, 3, 1, 1, 9, 5, 2, 1, 1}, // Kopieren
            {29, 10, 10, 11, 2, 1, 4, 1, 1, 1, 1, 1}, // Diagramm erstellen
            {10, 4, 2, 1, 1, 1, 2, 1, 1, 1, 2, 2, 4, 2, 1, 1}, // Serie fortführen
            {7, 4, 5, 1, 1, 1, 1, 4, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1}, // Transponieren
            {5, 4, 4, 1, 6, 2, 2, 1, 1, 1, 6, 1, 1, 1}, // Sortieren
            {3, 5, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1}, // Formel konstruieren
            {1, 5, 4, 2, 1, 5, 1, 6, 2, 1, 1, 1, 1, 1, 2, 1, 1}, // Bedingte Formatierung
            {5, 4, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 1, 1, 1} // Multiplizieren
    };

    public static void main(String[] args) {
        // Initialize RESULTS list with empty lists for each task.
        for (int i = 0; i < 20; i++) {
            RESULTS.add(new ArrayList<>());
        }
        validateResults();
        createResults();
        System.out.println("RESULTS: " + RESULTS);

        final List<List<List<Integer>>> ALL_TASKS = Arrays.asList(
                TASK1, TASK2, TASK3, TASK4, TASK5,
                TASK6, TASK7, TASK8, TASK9, TASK10,
                TASK11, TASK12, TASK13, TASK14, TASK15,
                TASK16, TASK17, TASK18, TASK19, TASK20);

        System.out.println("All Tasks:");
        for (int i = 0; i < ALL_TASKS.size(); i++) {
            printFilteredTaskResults(ALL_TASKS.get(i), allIds);
        }

        System.out.println("Female Tasks:");
        for (int i = 0; i < ALL_TASKS.size(); i++) {
            printFilteredTaskResults(ALL_TASKS.get(i), femaleIds);
        }
        System.out.println("Male Tasks:");
        for (int i = 0; i < ALL_TASKS.size(); i++) {
            printFilteredTaskResults(ALL_TASKS.get(i), maleIds);
        }
        System.out.println("Young Age Tasks:");
        for (int i = 0; i < ALL_TASKS.size(); i++) {
            printFilteredTaskResults(ALL_TASKS.get(i), youngAgeIds);
        }
        System.out.println("Old Age Tasks:");
        for (int i = 0; i < ALL_TASKS.size(); i++) {
            printFilteredTaskResults(ALL_TASKS.get(i), oldAgeIds);
        }
        System.out.println("Middle Age Tasks:");
        for (int i = 0; i < ALL_TASKS.size(); i++) {
            printFilteredTaskResults(ALL_TASKS.get(i), middleAgeIds);
        }
        System.out.println("Use Pencil Tasks:");
        for (int i = 0; i < ALL_TASKS.size(); i++) {
            printFilteredTaskResults(ALL_TASKS.get(i), usePencilIds);
        }
        System.out.println("Not Use Pencil Tasks:");
        for (int i = 0; i < ALL_TASKS.size(); i++) {
            printFilteredTaskResults(ALL_TASKS.get(i), notUsePencilIds);
        }
        System.out.println("English Tasks:");
        for (int i = 0; i < ALL_TASKS.size(); i++) {
            printFilteredTaskResults(ALL_TASKS.get(i), englishIds);
        }
        System.out.println("German Tasks:");
        for (int i = 0; i < ALL_TASKS.size(); i++) {
            printFilteredTaskResults(ALL_TASKS.get(i), germanIds);
        }

        // Calculate the agreement rate
        System.out.println(SEPARATOR);
        System.out.println("Calculating Agreement Rate....");
        System.out.println("All Ids:");
        startAgreementRateCalculation(RESULTS_ALL);
        System.out.println("Female Ids:");
        startAgreementRateCalculation(RESULTS_FEMALE);
        System.out.println("Male Ids:");
        startAgreementRateCalculation(RESULTS_MALE);
        System.out.println("Young Age Ids:");
        startAgreementRateCalculation(RESULTS_YOUNG);
        System.out.println("Old Age Ids:");
        startAgreementRateCalculation(RESULTS_OLD);
        System.out.println("Middle Age Ids:");
        startAgreementRateCalculation(RESULTS_MIDDLE);
        System.out.println("Use Pencil Ids:");
        startAgreementRateCalculation(RESULTS_USE_PENCIL);
        System.out.println("Not Use Pencil Ids:");
        startAgreementRateCalculation(RESULTS_NOT_USE_PENCIL);
        System.out.println("English Ids:");
        startAgreementRateCalculation(RESULTS_ENGLISH);
        System.out.println("German Ids:");
        startAgreementRateCalculation(RESULTS_GERMAN);

        System.out.println("Command Type Tasks:");
        startAgreementRateCalculation(RESULTS_COMMAND_TYPE);

    }

    private static void printFilteredTaskResults(List<List<Integer>> task, List<Integer> ids) {
        List<Integer> sizes = new ArrayList<>();
        for (List<Integer> group : task) {
            List<Integer> filteredGroup = new ArrayList<>(group);
            filteredGroup.retainAll(ids);

            if (!filteredGroup.isEmpty()) {
                sizes.add(filteredGroup.size());
            }
        }

        System.out.println("{" + sizes.stream().map(String::valueOf).collect(Collectors.joining(", ")) + "},");
    }

    private static void createResults() {
        insertResults(TASK1, 0);
        insertResults(TASK2, 1);
        insertResults(TASK3, 2);
        insertResults(TASK4, 3);
        insertResults(TASK5, 4);
        insertResults(TASK6, 5);
        insertResults(TASK7, 6);
        insertResults(TASK8, 7);
        insertResults(TASK9, 8);
        insertResults(TASK10, 9);
        insertResults(TASK11, 10);
        insertResults(TASK12, 11);
        insertResults(TASK13, 12);
        insertResults(TASK14, 13);
        insertResults(TASK15, 14);
        insertResults(TASK16, 15);
        insertResults(TASK17, 16);
        insertResults(TASK18, 17);
        insertResults(TASK19, 18);
        insertResults(TASK20, 19);
    }

    private static void insertResults(List<List<Integer>> taskResults, int taskIndex) {
        for (List<Integer> group : taskResults) {
            RESULTS.get(taskIndex).addAll(group);
        }
    }

    private static void startAgreementRateCalculation(int[][] RESULTS) {
        List<Integer> participantCounts = new ArrayList<>();
        for (int j = 0; j < 20; j++) {

            for (int i : RESULTS[j]) {
                participantCounts.add(i);
            }
            double agreementRate = calculateAgreementRate(participantCounts);

            // Convert the agreement rate to a percentage
            agreementRate *= 100;
            // Round the agreement rate to two decimal places
            agreementRate = Math.round(agreementRate * 100.0) / 100.0;

            // Print the agreement rate
            String formattedAgreementRate = String.format(Locale.GERMANY, "%.2f", agreementRate);
            System.out.println(formattedAgreementRate);
            participantCounts.clear();

        }
    }

    public static double calculateAgreementRate(List<Integer> participantCounts) {
        double numerator = 0.0;
        double denominator;
        int totalParticipants = participantCounts.stream().mapToInt(Integer::intValue).sum();

        for (Integer count : participantCounts) {
            numerator += count * (count - 1) / 2.0;
        }

        denominator = totalParticipants * (totalParticipants - 1) / 2.0;

        return numerator / denominator;
    }

    private static void validateResults() {
        if (validateTask(TASK1) || validateTask(TASK2) || validateTask(TASK3) || validateTask(TASK4) || validateTask(TASK5) || validateTask(TASK6) || validateTask(TASK7) || validateTask(TASK8) || validateTask(TASK9) || validateTask(TASK10) || validateTask(TASK11) || validateTask(TASK12) || validateTask(TASK13) || validateTask(TASK14) || validateTask(TASK15) || validateTask(TASK16) || validateTask(TASK17) || validateTask(TASK18) || validateTask(TASK19) || validateTask(TASK20)) {
            System.out.println("Results are valid.");
        } else {
            System.out.println("Results are not valid.");
        }
    }

    private static boolean validateTask(List<List<Integer>> task) {
        List<Integer> expectedResults = IntStream.rangeClosed(1, 36).boxed().collect(Collectors.toList());
        List<Integer> currentResults = task.stream().flatMap(List::stream).sorted().collect(Collectors.toList());

        if (!currentResults.equals(expectedResults)) {
            System.out.println("Current results are not valid. CurrentResults: " + currentResults);
            return false;
        }
        return true;
    }
}
