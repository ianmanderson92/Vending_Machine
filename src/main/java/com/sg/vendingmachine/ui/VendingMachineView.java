package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachineView
{
    private UserIO io;

    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }

    /**
     * Displays all the available items excluding items that are out of inventory.
     * Uses BigDecimal to display the price to the User.
     */
    public void displayItems()
    {
        io.print("=== Item's Available ===");
        for(Item currentItem : Item.items.values())
        {
            if (currentItem.getInventory() > 0)
            {
                double itemCostDouble = (currentItem.getCost()/100.0);
                BigDecimal itemCostDecimalInit = new BigDecimal(itemCostDouble);
                BigDecimal itemCostDecimalFinal = itemCostDecimalInit.setScale(2, RoundingMode.HALF_UP);
                String itemInfo = String.format("%s: %s, %f, stock:%d",
                        currentItem.getButtonID(),
                        currentItem.getName(),
                        itemCostDecimalFinal,
                        currentItem.getInventory());
                io.print(itemInfo);
            }
        }
    }

    public String getUserInput()
    {
        return io.readString("Please input amount of money or press ENTER to exit program");
    }

    public String getUserChoice()
    {
        return io.readString("Please input button code for item.");
    }

    public void displayException(Exception exceptionToDisplay)
    {
        io.print(exceptionToDisplay.getMessage());
        io.readString("Press ENTER to continue.");
    }
}
