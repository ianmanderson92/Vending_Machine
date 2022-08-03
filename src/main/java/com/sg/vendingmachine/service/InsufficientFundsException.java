package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;

public class InsufficientFundsException extends Exception
{
    UserIO io;
    VendingMachineDao dao;

    public InsufficientFundsException() throws VendingMachinePersistenceException
    {
        this.io = new UserIOConsoleImpl();
        this.dao = new VendingMachineDaoFileImpl();
        dao.writeInventory();
        io.print("Insufficient funds to purchase item.");
    }

}
