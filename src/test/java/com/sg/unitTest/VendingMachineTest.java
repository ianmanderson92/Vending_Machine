package com.sg.unitTest;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Denomination;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.Vend;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VendingMachineTest
{
    UserIO io;
    VendingMachineView view;

    public VendingMachineTest()
    {
        this.io = new UserIOConsoleImpl();
        this.view = new VendingMachineView( io );
    }

    /**
     * Creates a test item and vends it making sure that it was vended and the right
     * amount of change was given.
     */
    @Test
    public void testVendItem()
    {
        Item testItem = new Item( "ZZ", "testItem", 100, 1 );
        Item.items.put( testItem.getButtonID(), testItem );
        try
        {
            Vend resultVend = VendingMachineController.vendItem( "ZZ", 105 );
            Change resultChange = resultVend.getChangeDue();
            assertEquals
                (   0
                ,   resultChange.changeArray[Denomination.PENNY.index]
                ,   "Incorrect change given."
                );
            assertEquals
                (   1
                ,   resultChange.changeArray[Denomination.NICKEL.index]
                ,   "Incorrect change given."
                );
            assertEquals
                (   0
                ,   resultChange.changeArray[Denomination.DIME.index]
                ,   "Incorrect change given."
                );
            assertEquals
                (   0
                ,   resultChange.changeArray[Denomination.QUARTER.index]
                ,   "Incorrect change given."
                );
        } catch ( Exception caughtException )
        {
            fail( "Threw exception: " + caughtException.getMessage() );
        }
    }

    /**
     * Tries to vend an item that is not in inventory to make sure the NoItemInventoryException
     * is thrown.
     */
    @Test
    public void testVendNoInventory()
    {
        try
        {
            VendingMachineController.vendItem( "ZX", 88 );
            fail( "Vended a nonexsisting item.");
        } catch ( Exception caughtException )
        {
            //don't display anything because catching is expected to pass.
        }
    }

    /**
     * Tries to vend an item with insufficient funds to make sure the InsufficientFundsException
     * is thrown.
     */
    @Test
    public void testVendInsufficientFunds()
    {
        Item testItem = new Item( "ZZ", "testItem", 100, 1 );
        Item.items.put( testItem.getButtonID(), testItem );
        try
        {
            VendingMachineController.vendItem( "ZZ", 95 );
            fail( "Vended with insufficient funds (test fail)." );
        } catch ( Exception caughtException )
        {
            //don't display anything because catching is expected to pass.
        }

        try
        {
            VendingMachineController.vendItem( "ZZ", 100 );
        } catch ( Exception ignored )
        {
        }
    }
}
