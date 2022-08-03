package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;

public class NoItemInventoryException extends Exception
{
    UserIO io;
    VendingMachineDao dao;

    public NoItemInventoryException() throws VendingMachinePersistenceException
    {
        this.io = new UserIOConsoleImpl();
        this.dao = new VendingMachineDaoFileImpl();
        dao.writeInventory();
        io.print("Item not found in inventory.");
    }
}
