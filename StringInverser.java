package com.sample;

public class StringInverser {

    public static void main(String[] args) {
        StringInverser.withoutInbuildFunction2("John Sooraj");
    }

    // using StringBuffer
    public static void useStringFunction1(String input) {
        StringBuffer stringBuffer = new StringBuffer(input);
        System.out.println(stringBuffer.reverse());
    }

    // using StringBuilder
    public static void useStringFunction2(String input) {
        StringBuilder stringBuilder = new StringBuilder(input);
        System.out.println(stringBuilder.reverse());
    }

    // using String.toCharArray()
    public static void withoutInbuildFunction1(String input) {
        char inputArray[] = input.toCharArray();
        for (int i = inputArray.length - 1; i >= 0; i--) {
            System.out.print(inputArray[(i)]);
        }
    }

    // using String.split()
    public static void withoutInbuildFunction2(String input) {
        String inputArray[] = input.split("");
        for (int i = inputArray.length - 1; i >= 0; i--) {
            System.out.print(inputArray[(i)]);
        }
    }
}
