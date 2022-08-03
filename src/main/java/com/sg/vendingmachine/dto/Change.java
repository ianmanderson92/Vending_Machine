package com.sg.vendingmachine.dto;

import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;

public class Change
{
    private UserIO io;

    public int[] changeArray = new int[Denomination.values().length];

    /**
     * Takes in an int that represents the amount of change to dispense.  It calculated the amount of each type
     * of coin to be dispensed and then prints the amount of each coin to the user.  Uses the Denomination
     * ENUM to calculate these values.
     * @param cents int value that represents the amount of change due in cents.
     */
    public Change(int cents)
    {
        io = new UserIOConsoleImpl();

        io.print("Change Due: ");
        for(Denomination denom: Denomination.values())
        {
            changeArray[denom.index] = cents / denom.value;
            cents = cents % denom.value;
        }
        for(Denomination denom: Denomination.values())
        {
            io.print(denom.name() + "- " + changeArray[denom.index] + ",");
        }
        io.readString("Please press ENTER to continue.");
    }
}
