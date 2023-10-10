import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    private static final String KEY_1500 = "cr1500";
    private static final String KEY_500 = "cr500";
    private static final String KEY_60 = "cr60";
    private static final String KEY_50 = "cr50";
    private static final String KEY_10 = "cr10";

    private static HashMap<String, Long> results = new HashMap<>();

    public static void main(String[] args) {

        System.out.println("===================================\n" +
                "===============BINGO===============\n" +
                "===================================\n");

        SecureRandom rand = new SecureRandom();
        //rand.setSeed(System.currentTimeMillis());

        int[] card = {1, 14, 26, 39, 50, 3, 19, 28, 45, 55, 7, 20, 30, 47, 58};

        int[] pattern_1500 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        int[] pattern_500 = {0, 1, 2, 3, 4, 5, 9, 10, 11, 12, 13, 14};
        int[] pattern_60 = {0, 1, 2, 3, 4, 5, 9, 10, 14};
        int[] pattern_50 = {0, 4, 5, 9, 10, 14};
        int[] pattern_10 = {0, 1, 2, 3, 4};

        HashMap<String, Long> results = new HashMap<>();
        results.put(KEY_1500, 0L);
        results.put(KEY_500, 0L);
        results.put(KEY_60, 0L);
        results.put(KEY_50, 0L);
        results.put(KEY_10, 0L);

        ArrayList<Integer> arrayRandom = new ArrayList<>();

        // change next variable in order to simulate bingo games
        Long totalGameNumber = 10_000_000L;
        double rtp = 0;
        for (Long i = 0L; i < totalGameNumber; i++) {

            arrayRandom = runBingoExtraction(rand);

            if (checkBingoPattern(arrayRandom, pattern_1500, card)) {
                Long value = results.get(KEY_1500);
                results.put(KEY_1500, value + 1);
                rtp +=1500;
            } else if (checkBingoPattern(arrayRandom, pattern_500, card)) {
                Long value = results.get(KEY_500);
                results.put(KEY_500, value + 1);
                rtp += 500;
            } else if (checkBingoPattern(arrayRandom, pattern_60, card)) {
                Long value = results.get(KEY_60);
                results.put(KEY_60, value + 1);
                rtp += 60;
            } else if (checkBingoPattern(arrayRandom, pattern_50, card)) {
                Long value = results.get(KEY_50);
                results.put(KEY_50, value + 1);
                rtp += 50;
            } else if (checkBingoPattern(arrayRandom, pattern_10, card)) {
                Long value = results.get(KEY_10);
                results.put(KEY_10, value + 1);
                rtp += 10;
            }
        }

        printResult(results, totalGameNumber, rtp);
    }

    private static void printResult(HashMap<String, Long> map, Long totalGameNumber, double rtp) {
        double prob = 0.0;
        prob = (double) map.get(KEY_1500) / totalGameNumber;

        System.out.printf("cr_1500 -> %d  -> %4.7f \n", map.get(KEY_1500), prob);
        prob = (double) map.get(KEY_500) / totalGameNumber;
        System.out.printf("cr_500 -> %d  -> %4.6f \n", map.get(KEY_500), prob);
        prob = (double) map.get(KEY_60) / totalGameNumber;
        System.out.printf("cr_60 -> %d  -> %4.6f \n", map.get(KEY_60), prob);
        prob = (double) map.get(KEY_50) / totalGameNumber;
        System.out.printf("cr_50 -> %d  -> %4.6f \n", map.get(KEY_50), prob);
        prob = (double) map.get(KEY_10) / totalGameNumber;
        System.out.printf("cr_10 -> %d  -> %4.6f \n", map.get(KEY_10), prob);

        System.out.printf("total games -> %d \n", totalGameNumber);
        System.out.printf("total wins -> %d \n", (int) rtp);
        System.out.printf("rtp -> %4.2f \n", rtp / totalGameNumber);
    }

    private static ArrayList<Integer> runBingoExtraction(SecureRandom rand) {
        int N = 60;
        int n = 30;

        // to give the player one extra ball (uncomment the next line)
        // n = 31;
        ArrayList<Integer> arrayRandom = new ArrayList<Integer>();

        while (arrayRandom.size() < n) {
            Integer r = rand.nextInt(N) + 1;
            if (!arrayRandom.contains(r)) {
                arrayRandom.add(r);
            }
        }

        // uncomment to see the extracted numbers
//        for (int num : arrayRandom) {
//            System.out.print(num + "  ");
//        }
//        System.out.print("  \n\n");

        return arrayRandom;
    }

    private static Boolean checkBingoPattern(ArrayList<Integer> extractedNumbers, int[] patternToCheck, int[] card) {
        for (int i : patternToCheck) {
            if (!extractedNumbers.contains(card[i])) {
                return false;
            }
        }
        return true;
    }
}