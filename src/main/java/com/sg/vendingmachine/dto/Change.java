package com.sg.vendingmachine.dto;

public class Change
{

    public int changeArray[] = new int[Denomination.values().length];

    public Change(int cents)
    {
        for(Denomination denom: Denomination.values())
        {
            changeArray[denom.index] = cents / denom.value;
            cents = cents % denom.value;
        }
    }
}
