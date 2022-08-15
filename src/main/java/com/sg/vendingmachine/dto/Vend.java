package com.sg.vendingmachine.dto;

public class Vend
{
    private final Item vendedItem;
    private final Change changeDue;

    public Vend( Item vendedItem, Change changeDue )
    {
        this.vendedItem = vendedItem;
        this.changeDue = changeDue;
    }

    public Item getVendedItem()
    {
        return vendedItem;
    }

    public Change getChangeDue()
    {
        return changeDue;
    }

    @Override
    public String toString()
    {
        return
            (   vendedItem.toString()
                + changeDue.toString()
            );
    }
}
