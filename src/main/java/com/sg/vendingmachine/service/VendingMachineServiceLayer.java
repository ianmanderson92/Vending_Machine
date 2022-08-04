package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;

public interface VendingMachineServiceLayer
{
    public void loadInventory() throws VendingMachinePersistenceException;
    public void writeInventory() throws VendingMachinePersistenceException;
    public boolean isNumeric( String str );
}
