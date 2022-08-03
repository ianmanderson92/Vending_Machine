package com.sg.vendingmachine.dto;

import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;

import java.util.HashMap;

public class Item
{
    private String buttonID;
    private String name;
    private int cost;
    private int inventory;

    public static HashMap<String,Item> items = new HashMap<String, Item>();

    public Item(String buttonID, String name, int cost, int inventory)
    {
        this.buttonID = buttonID;
        this.name = name;
        this.cost = cost;
        this.inventory = inventory;
    }

    public String getButtonID()
    {
        return buttonID;
    }

    public void setButtonID(String buttonID)
    {
        this.buttonID = buttonID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
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

    public static void addItem(Item newItem)
    {
        Item.items.put(newItem.getButtonID(), newItem);
    }

    public static int vendItem(String buttonID, int payment) throws NoItemInventoryException, InsufficientFundsException
    {
        Item vendedItem = Item.items.get(buttonID);
        if (vendedItem ==  null || vendedItem.inventory <= 0)
        {
            throw new NoItemInventoryException();
        }
        if (payment < vendedItem.cost)
        {
            throw new InsufficientFundsException();
        }
        vendedItem.inventory--;
        return payment-vendedItem.cost;
    }

    @Override
    public String toString()
    {
        return "Item{" +
                "buttonID='" + buttonID + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", inventory=" + inventory +
                '}';
    }
}
