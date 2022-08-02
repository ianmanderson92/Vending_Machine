package com.sg.vendingmachine.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO
{
    Scanner inputReader = new Scanner(System.in);

    public void print(String message)
    {
        System.out.println(message);
    }

    public String readString(String prompt)
    {
        String inString;
        System.out.println(prompt);
        inString = inputReader.nextLine();
        return inString;
    }

    public int readInt(String prompt)
    {
        int inNum;
        System.out.println(prompt);
        inNum = Integer.parseInt(inputReader.nextLine());
        return inNum;
    }

    public int readInt(String prompt, int min, int max)
    {
        int inNum;
        System.out.println(prompt);
        inNum = Integer.parseInt(inputReader.nextLine());
        while (inNum < min || inNum > max)
        {
            System.out.println("Input number must be within range [" + min + " - " + max + "]");
            inNum = Integer.parseInt(inputReader.nextLine());
        }
        return inNum;
    }

    public double readDouble(String prompt)
    {
        double inNum;
        System.out.println(prompt);
        inNum = Double.parseDouble(inputReader.nextLine());
        return inNum;
    }

    public double readDouble(String prompt, double min, double max)
    {
        double inNum;
        System.out.println(prompt);
        inNum = Double.parseDouble(inputReader.nextLine());
        while (inNum < min || inNum > max)
        {
            System.out.println("Input number must be within range [" + min + " - " + max + "]");
            inNum = Double.parseDouble(inputReader.nextLine());
        }
        return inNum;
    }

    public float readFloat(String prompt)
    {
        float inNum;
        System.out.println(prompt);
        inNum = Float.parseFloat(inputReader.nextLine());
        return inNum;
    }

    public float readFloat(String prompt, float min, float max)
    {
        float inNum;
        System.out.println(prompt);
        inNum = Float.parseFloat(inputReader.nextLine());
        while (inNum < min || inNum > max)
        {
            System.out.println("Input number must be within range [" + min + " - " + max + "]");
            inNum = Float.parseFloat(inputReader.nextLine());
        }
        return inNum;
    }

    public long readLong(String prompt)
    {
        long inNum;
        System.out.println(prompt);
        inNum = Long.parseLong(inputReader.nextLine());
        return inNum;
    }

    public long readLong(String prompt, long min, long max)
    {
        long inNum;
        System.out.println(prompt);
        inNum = Long.parseLong(inputReader.nextLine());
        while (inNum < min || inNum > max)
        {
            System.out.println("Input number must be within range [" + min + " - " + max + "]");
            inNum = Long.parseLong(inputReader.nextLine());
        }
        return inNum;
    }
}
