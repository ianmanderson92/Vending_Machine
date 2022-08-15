package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.dto.Vend;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;

public class VendingMachineController
{
    private final VendingMachineView view;
    private final VendingMachineServiceLayer service;
    private final VendingMachineAuditDao auditDao;

    public VendingMachineController( VendingMachineServiceLayer myService, VendingMachineView myView,
                                     VendingMachineAuditDao myAuditDao )
    {
        this.service = myService;
        this.view = myView;
        this.auditDao = myAuditDao;
    }

    /**
     main program loop
     @throws VendingMachinePersistenceException if the audit cannot write the vend entry to the audit file.
     */
    public void run() throws VendingMachinePersistenceException
    {
        boolean exitProgram = false;
        service.loadInventory();

        while ( !exitProgram )
        {
            String userInputMoney;
            boolean isNum = false;
            view.displayItems();
            while ( !isNum && !exitProgram )
            {
                userInputMoney = getUserInput();

                if ( userInputMoney.equals( "" ) )
                {
                    exitProgram = true;
                }
                else
                {
                    isNum = service.isNumeric( userInputMoney );
                    if ( isNum )
                    {
                        double userInputDouble = Double.parseDouble( userInputMoney );
                        userInputDouble *= 100.0;

                        int userInputInt = (int) userInputDouble;
                        String userChoice = getUserChoice();
                        try
                        {
                            Vend vendedItem = vendItem( userChoice, userInputInt );
                            auditDao.writeVendAuditEntry( userChoice, userInputInt, vendedItem );
                            view.displayVendedItem( vendedItem.getVendedItem() );
                            view.displayChange( vendedItem.getChangeDue() );
                            view.displayWaitMessage();
                        } catch ( Exception exceptionCaught )
                        {
                            auditDao.writeErrorAuditEntry( userChoice, userInputInt, exceptionCaught );
                            view.displayException( exceptionCaught );
                        }
                    }
                }
            }
        }
        service.writeInventory();
    }

    private String getUserInput()
    {
        return view.getUserInput();
    }

    public String getUserChoice()
    {
        return view.getUserChoice();
    }

    /**
     Method used to initiate and carry out the vending of the chosen item.  Performing operation validation along the
     way.
     @param buttonID String representation of the button ID linked to the item in the items hashmap.
     @param payment  int representation of the amount of money inputted by the user in cents.
     @return the vended item stored in a Vend object.
     @throws NoItemInventoryException   if there is no item associated with the input buttonId or the inventory level is
     0.
     @throws InsufficientFundsException if the user input an insufficient amount of funds to vend the selected item.
     */
    public static Vend vendItem( String buttonID, int payment ) throws NoItemInventoryException,
        InsufficientFundsException
    {
        Item vendedItem = Item.items.get( buttonID );
        if ( vendedItem == null || vendedItem.getInventory() <= 0 )
        {
            throw new NoItemInventoryException( "item not in inventory." );
        }
        if ( payment < vendedItem.getCost() )
        {
            throw new InsufficientFundsException( "insufficient funds to purchase item." );
        }
        vendedItem.decreaseInventory();
        Change changeDue = new Change( payment - vendedItem.getCost() );
        return new Vend( vendedItem, changeDue );
    }
}
