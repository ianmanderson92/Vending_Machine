package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer
{
    private VendingMachineAuditDao auditDao;
    VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }
}
