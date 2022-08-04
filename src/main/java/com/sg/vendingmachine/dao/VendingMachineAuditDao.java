package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Vend;

public interface VendingMachineAuditDao
{
    /**
     helper method that writes any audit message to the audit file.
     * @param entry
     * @throws VendingMachinePersistenceException
     */
    public void writeAuditEntry( String entry ) throws VendingMachinePersistenceException;

    /**
     writes an audit entry when an item is successfully vended.
     * @param userChoice
     * @param userInputInt
     * @param vendToLog
     * @throws VendingMachinePersistenceException
     */
    public void writeVendAuditEntry( String userChoice, int userInputInt, Vend vendToLog ) throws VendingMachinePersistenceException;

    /**
     writes an audit entry that documents a thrown exception
     * @param userChoice
     * @param userInputInt
     * @param exceptionCaught
     * @throws VendingMachinePersistenceException
     */
    public void writeErrorAuditEntry( String userChoice, int userInputInt, Exception exceptionCaught ) throws VendingMachinePersistenceException;

}
