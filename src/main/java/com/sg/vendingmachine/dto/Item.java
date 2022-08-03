package com.sg.vendingmachine.dto;

import java.util.ArrayList;

public class Item
{
    private String name;
    private float cost;
    private int inventory;

    public static ArrayList<Item> Items = new ArrayList<>();

    public Item(String name, float cost, int inventory)
    {
        this.name = name;
        this.cost = cost;
        this.inventory = inventory;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public float getCost()
    {
        return cost;
    }

    public void setCost(float cost)
    {
        this.cost = cost;
    }

    public int getInventory()
    {
        return inventory;
    }

    public void setInventory(int inventory)
    {
        this.inventory = inventory;
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", inventory=" + inventory +
                '}';
    }
}
