package com.java;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


interface BinarySearch {
    public int binarySearch(int key);
    public void printElements();
    public void remove(int index);
    public void add(int value);
    public boolean contains(int value);
    public void initializeArray();
    public void sort();
}

class BinarySearchArrayList implements BinarySearch {

    private ArrayList<Integer> arrList = new ArrayList();

    @Override
    public int binarySearch(int key) {
        Collections.binarySearch(arrList, key);

        return -1;
    }

    @Override
    public void sort() {
        Collections.sort(arrList);

    }

    @Override
    public void printElements() {
        System.out.println(arrList);
    }

    @Override
    public void remove(int index) {
       arrList.remove(index);
    }

    @Override
    public void add(int value) {
        if (!contains(value)) {
            arrList.add(value);
            sort();
            printElements();
        } else {
            System.out.println("Item " + value + " already exists in list. Not added.");
        }
    }

    @Override
    public boolean contains(int value) {
        return arrList.contains(Integer.valueOf(value));
    }

    @Override
    public void initializeArray() {
        Random rand = ThreadLocalRandom.current();
        int max = 25, min = 1;
        int added = 0;
        while (added < 10) {
            int num = rand.nextInt((max - min) + 1) + min;
            if (!contains(num)) {
                arrList.add(num);
                added++;
            }
        }
    }
}

public class BinarySearchArray implements BinarySearch {

    int arr[] = new int[15];

    public int binarySearch(int key) {
        return Arrays.binarySearch(arr, key);
    }

    public void sort() {
        Arrays.sort(arr);
    }

    public void printElements() {
        System.out.println("Search Elements:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println();
    }

    public void remove(int index) {
        for (int i = index; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = 0;
        Arrays.sort(arr);
        printElements();
    }


    public void add(int value) {
        if (contains(value)) {
            return;
        }
        if (!contains(0)) {
            System.out.println("No space available.");
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = value;
                break;
            }
        }
        sort();
        printElements();
    }

    public void initializeArray() {
        for (int i = 0; i < 15; i++) {
            arr[i] = 0;
        }
        Random rand = ThreadLocalRandom.current();
        int max = 25, min = 1;
        int numToAdd = 10, added = 0;
        while (added < 10) {
            int num = rand.nextInt((max - min) + 1) + min;
            if (!contains(num)) {
                for (int i = 0; i < arr.length; i++) {

                    if (arr[i] == 0) {
                        arr[i] = num;
                        break;
                    }
                }
                added++;
            }
        }
    }

    public boolean contains(int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value)
                return true;
        }
        return false;
    }


    public void printWelcomeMessage(String mode)
    {
        System.out.println("\nWelcome to the Binary Search Test (" + mode + "):\n");
    }


    public void testBinarySearch(BinarySearch searchObject) {
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        while (value != -1)
        {
            System.out.print("Enter an integer to search (or -1 to quit): ");
            String ss = scanner.nextLine();
            value = Integer.parseInt(ss);
            if (value == -1) {
                break;
            }
            int index;
            if ((index = searchObject.binarySearch(value)) >= 0) {
                System.out.println("Value " + value + " found." + " Do you want to remove it? y/n? ");
                String answer = scanner.nextLine();
                if (answer.equals("y")) {
                    searchObject.remove(index);
                }
            } else {
                System.out.println("Value " + value + " not found." + " Do you want to add it? y/n? ");
                String answer = scanner.nextLine();
                if (answer.equals("y"))
                    searchObject.add(value);
            }
        }
        System.out.println("Goodbye...");
    }

    public void binarySearchTestDriver()  {
        System.out.println();
        BinarySearch bsArr = new BinarySearchArray();
        bsArr.initializeArray();
        bsArr.sort();
        String mode = bsArr.getClass().getSimpleName();
        printWelcomeMessage(mode);
        bsArr.printElements();
        testBinarySearch(bsArr);

        bsArr = new BinarySearchArrayList();
        bsArr.initializeArray();
        bsArr.sort();
        mode = bsArr.getClass().getSimpleName();
        printWelcomeMessage(mode);
        bsArr.printElements();
        testBinarySearch(bsArr);
    }


    public static void main(String[] args) {
        BinarySearchArray bs = new BinarySearchArray();
        bs.binarySearchTestDriver();
    }
}