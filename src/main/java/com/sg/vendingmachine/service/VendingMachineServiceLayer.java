package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;

public interface VendingMachineServiceLayer
{
    void loadInventory() throws VendingMachinePersistenceException;

    void writeInventory() throws VendingMachinePersistenceException;

    boolean isNumeric( String str );
}
