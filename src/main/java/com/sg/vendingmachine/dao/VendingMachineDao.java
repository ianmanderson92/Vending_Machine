package com.sg.vendingmachine.dao;

public interface VendingMachineDao
{
    void loadInventory() throws VendingMachinePersistenceException;
}
