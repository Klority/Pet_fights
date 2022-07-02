package grsu.java.pets.util;

import java.util.Random;

public class Randomizer {

    private static final Random random = new Random();

    public static int getInt(int left, int right) {
        return random.nextInt(right - left + 1) + left;
    }
}
