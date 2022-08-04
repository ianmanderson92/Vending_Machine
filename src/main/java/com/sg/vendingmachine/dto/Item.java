package com.sg.vendingmachine.dto;

import java.util.HashMap;

public class Item
{
    private String buttonID;
    private String name;

    //TODO: potentially refactor cost to BigDecimal Type.
    private int cost;
    private int inventory;

    public static HashMap<String,Item> items = new HashMap<String, Item>();

    public Item( String buttonID, String name, int cost, int inventory )
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

    public void setButtonID( String buttonID )
    {
        this.buttonID = buttonID;
    }

    public String getName()
    {
        return name;
    }

    public int getCost()
    {
        return cost;
    }

    public int getInventory()
    {
        return inventory;
    }

    public void decreaseInventory()
    {
        inventory--;
    }

    public static void addItem( Item newItem )
    {
        Item.items.put( newItem.getButtonID(), newItem );
    }


    @Override
    public String toString()
    {
        return
            (   "Item{"
            +   "buttonID='" + buttonID + "'"
            +   ", name='" + name + "'"
            +   ", cost=" + cost
            +   ", inventory=" + inventory
            +   "}"
            );
    }
}
