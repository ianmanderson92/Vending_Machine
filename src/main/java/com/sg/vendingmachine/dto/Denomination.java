package com.sg.vendingmachine.dto;

public enum Denomination
{
    QUARTER( 3,25 ),
    DIME( 2,10 ),
    NICKEL( 1,5 ),
    PENNY( 0,1 );

    public final int index;
    public final int value;

    private Denomination( int index, int value )
    {
        this.index = index;
        this.value = value;
    }
}
