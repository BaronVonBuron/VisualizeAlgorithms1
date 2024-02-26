package visualizealgorithms.bll.algorithm.sorting;

import visualizealgorithms.bll.algorithm.AlgorithmType;
import visualizealgorithms.bll.algorithm.GenericAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ACBSort extends GenericAlgorithm {
    private int[] temparr1;
    private int[] temparr2;
    private int[] temparr3;
    private int[] temparr4;

    public ACBSort() {
        super("ACBSort", "Complex shitty sorting algorithm", AlgorithmType.SORTING);
    }

    @Override
    public void doWork() {
        int[] b = (int[]) super.getData();
        int min = b[0];
        int max = b[0];

        for (int i : b) {
            if (i < min){
                min = i;
            } else if (i > max){
                max = i;
            }
        }
        System.out.println(" ");
        System.out.println("min: "+min);
        System.out.println("max: "+max);

        List<Integer> temp1 = new ArrayList<>();
        List<Integer> temp2 = new ArrayList<>();
        List<Integer> temp3 = new ArrayList<>();
        List<Integer> temp4 = new ArrayList<>();
        int comparator = max / 4;

        for (int i : b) {
            if (i <= comparator){
                temp1.add(i);
            } else if (i > comparator && i <= comparator * 2) {
                temp2.add(i);
            } else if (i > comparator * 2 && i <= comparator * 3) {
                temp3.add(i);
            }else {
                temp4.add(i);
            }
        }

        final CountDownLatch latch = new CountDownLatch(4);

        List<Integer> arrayToSort = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            temparr1 = smallBubble(temp1);
            latch.countDown();
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            temparr2 = smallBubble(temp2);
            latch.countDown();
        });
        t2.start();
        Thread t3 = new Thread(() -> {
            temparr3 = smallBubble(temp3);
            latch.countDown();
        });
        t3.start();
        Thread t4 = new Thread(() -> {
            temparr4 = smallBubble(temp4);
            latch.countDown();
        });
        t4.start();


        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i : temparr1) {
            arrayToSort.add(i);
        }
        for (int i : temparr2) {
            arrayToSort.add(i);
        }
        for (int i : temparr3) {
            arrayToSort.add(i);
        }
        for (int i : temparr4) {
            arrayToSort.add(i);
        }
        Collections.sort(arrayToSort);
        //System.out.println(arrayToSort);
    }

    public int[] smallBubble(List<Integer> b1){
        int[] b = new int[b1.size()];
        for (int i = 0; i < b1.size(); i++) {
            b[i] = b1.get(i);
        }
        for (int i = 1; i < b.length; i++) {
            for (int j = 0; j < b.length - 1; j++) {
                if (b[j] > b[j + 1]) {
                    int tmp = b[j];
                    b[j] = b[j + 1];
                    b[j + 1] = tmp;
                }
            }
        }
        return b;
    }
}
