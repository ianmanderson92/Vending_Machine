package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;

public class VendingMachineView
{
    private UserIO io;

    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }

    /**
     * Displays all the available items excluding items that are out of inventory.
     */
    public void displayItems()
    {
        io.print("=== Item's Available ===");
        for(Item currentItem : Item.items.values())
        {
            if (currentItem.getInventory() > 0)
            {
                String itemInfo = String.format("%s: %s, %d, stock:%d",
                        currentItem.getButtonID(),
                        currentItem.getName(),
                        currentItem.getCost(),
                        currentItem.getInventory());
                io.print(itemInfo);
            }
        }
    }
}
