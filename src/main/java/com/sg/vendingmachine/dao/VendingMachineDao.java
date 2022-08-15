package com.sg.vendingmachine.dao;

public interface VendingMachineDao
{
    /**
     Loads the Inventory of Items from the CSV file.
     @throws VendingMachinePersistenceException
     */
    void loadInventory() throws VendingMachinePersistenceException;

    /**
     Overwrites the current CSV file with the current state of the items held in the DTO
     @throws VendingMachinePersistenceException
     */
    void writeInventory() throws VendingMachinePersistenceException;
}
