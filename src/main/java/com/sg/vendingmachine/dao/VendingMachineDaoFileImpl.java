package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class VendingMachineDaoFileImpl implements VendingMachineDao
{
    public static final String INVENTORY_FILE = "vendingInventory.txt";
    public static final String DELIMITER = ",";

    private Item unmarshallItem(String itemAsText)
    {
        String[] itemTokens = itemAsText.split(DELIMITER);
        return new Item(itemTokens[0], itemTokens[1], Integer.parseInt(itemTokens[2]), Integer.parseInt(itemTokens[3]));
    }

    public void loadInventory() throws VendingMachinePersistenceException
    {
        Scanner scanner;

        try
        {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e)
        {
            throw new VendingMachinePersistenceException(
                    "Could not load inventory data into memory.", e);
        }

        while (scanner.hasNextLine()) {
            Item.addItem
            (   unmarshallItem
                (   scanner.nextLine()
                )
            );
        }
        scanner.close();
    }


}
