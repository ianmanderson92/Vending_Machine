package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Vend;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao
{
    public static final String AUDIT_FILE = "audit.txt";

    /**
     helper method that writes any audit message to the audit file.
     * @param entry
     * @throws VendingMachinePersistenceException
     */
    @Override
    public void writeAuditEntry( String entry ) throws VendingMachinePersistenceException
    {
        PrintWriter out;

        try
        {
            out = new PrintWriter( new FileWriter(AUDIT_FILE, true) );
        } catch ( IOException e )
        {
            throw new VendingMachinePersistenceException( "Could not persist audit information.", e );
        }
        LocalDateTime timestamp = LocalDateTime.now();
        out.println( timestamp.toString() + " : " + entry );
        out.flush();
    }

    /**
     writes an audit entry when an item is successfully vended.
     * @param userChoice
     * @param userInputInt
     * @param vendToLog
     * @throws VendingMachinePersistenceException
     */
    public void writeVendAuditEntry( String userChoice, int userInputInt, Vend vendToLog ) throws VendingMachinePersistenceException
    {
        writeAuditEntry
            (   "userChoice: " + userChoice + ","
            +   "userInputInt: " + userInputInt + ",\n"
            +   vendToLog.toString()
            );
    }

    /**
     writes an audit entry that documents a thrown exception
     * @param userChoice
     * @param userInputInt
     * @param exceptionCaught
     * @throws VendingMachinePersistenceException
     */
    public void writeErrorAuditEntry( String userChoice, int userInputInt, Exception exceptionCaught ) throws VendingMachinePersistenceException
    {
        writeAuditEntry
                (   "userChoice: " + userChoice + ","
                +   "userInputInt: " + userInputInt + ",\n"
                +   exceptionCaught.getMessage() + "\n"
                );
    }
}
