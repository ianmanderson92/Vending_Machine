package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer
{
    private VendingMachineAuditDao auditDao;
    VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    public void loadInventory() throws VendingMachinePersistenceException
    {
        dao.loadInventory();
    }

    public void writeInventory() throws VendingMachinePersistenceException
    {
        dao.writeInventory();
    }

    /**
     * Checks to see if a string contains only numeric values.
     * Algorithm taken from https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java,
     * User 'CraigTP'
     * @param string to be checked for numeric value.
     * @return true if string is numeric, false if not.
     */
    public boolean isNumeric(String string)
    {
        try
        {
            Double.parseDouble(string);
            return true;
        } catch(NumberFormatException e)
        {
            return false;
        }
    }
}
