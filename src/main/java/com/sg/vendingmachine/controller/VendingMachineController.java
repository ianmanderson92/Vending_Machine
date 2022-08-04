package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;

public class VendingMachineController
{
    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    public VendingMachineController(VendingMachineServiceLayer myService, VendingMachineView myView)
    {
        this.service = myService;
        this.view = myView;
    }

    /**
     * main program loop
     * @throws VendingMachinePersistenceException
     */
    public void run() throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException
    {
        boolean exitProgram = false;
        service.loadInventory();

        while(!exitProgram)
        {
            String userInputMoney;
            boolean isNum = false;
            view.displayItems();
            while(!isNum && !exitProgram)
            {
                userInputMoney = getUserInput();

                if(userInputMoney.equals(""))
                {
                    exitProgram = true;
                } else
                {
                    isNum = service.isNumeric(userInputMoney);
                    if(isNum)
                    {
                        double userInputDouble = Double.parseDouble(userInputMoney);
                        userInputDouble *= 100.0;
                        int userInputInt = (int)userInputDouble;
                        boolean isItem = false;

                        try
                        {
                            vendItem(getUserChoice(), userInputInt);
                        } catch (Exception exceptionCaught)
                        {
                            view.displayException(exceptionCaught);
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

    public static int vendItem( String buttonID, int payment ) throws NoItemInventoryException, InsufficientFundsException, VendingMachinePersistenceException
    {
        Item vendedItem = Item.items.get( buttonID );
        if ( vendedItem ==  null || vendedItem.getInventory() <= 0 )
        {
            throw new NoItemInventoryException( "item not in inventory." );
        }
        if ( payment < vendedItem.getCost() )
        {
            throw new InsufficientFundsException( "insufficient funds to purchase item." );
        }
        vendedItem.decreaseInventory();
        //TODO: remove print statement
        //System.out.println("Change Due: " + (payment - vendedItem.getCost()));
        Change changeDue = new Change( payment - vendedItem.getCost() );
        return payment - vendedItem.getCost();
    }
}
