package lab4;

import java.util.Arrays;

public class Test {
    public static void main(String[] a) {
        Memory memory = new Memory(5);

        memory.setMemory(0, Long.valueOf("0d0050d005", 16));
        // System.out.println(memory);

        memory.setLeft(0, Long.valueOf("0d0050d00f", 16));
        // System.out.println(memory);

        memory.setRight(0, Long.valueOf("0d0050d00f", 16));
        // System.out.println(memory);

        int[] sort = new int[] { 2, 5, 6, 3, 8, 1, 9 };
        sort = sort(sort);

    }

    private static int[] sort(int[] sort) {
        int hold = 0;
        int lowPos = 0;
        System.out.println(Arrays.toString(sort));

        for (int i = 0; i < sort.length - 1; i++) {
            hold = sort[i];
            lowPos = i;
            for (int j = i + 1; j < sort.length; j++) {
                if (hold > sort[j]) {
                    hold = sort[j];
                    lowPos = j;
                }
            }
            sort[lowPos] = sort[i];
            sort[i] = hold;
            System.out.println(Arrays.toString(sort));
        }

        return sort;
    }
}
