package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;

public class VendingMachineView
{
    private UserIO io;

    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }

    public void displayItems()
    {
        io.print("=== Item's Available ===");
        for(Item currentItem : Item.items.values())
        {
            String itemInfo = String.format("%s: %s, %d, inv:%d",
                    currentItem.getButtonID(),
                    currentItem.getName(),
                    currentItem.getCost(),
                    currentItem.getInventory());
            io.print(itemInfo);
        }
    }
}
