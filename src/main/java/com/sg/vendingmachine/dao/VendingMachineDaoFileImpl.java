package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.io.*;
import java.util.List;
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

    private String marshallItem(Item item)
    {
        String itemAsText = item.getButtonID() + DELIMITER;
        itemAsText += item.getName() + DELIMITER;
        itemAsText += item.getCost() + DELIMITER;
        itemAsText += item.getInventory();
        return itemAsText;
    }

    /**
     * Loads the Inventory of Items from the CSV file.
     * @throws VendingMachinePersistenceException
     */
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

    /**
     * Overwrites the current CSV file with the current state of the items held in the DTO
     * @throws VendingMachinePersistenceException
     */
    public void writeInventory() throws VendingMachinePersistenceException
    {
        PrintWriter out;

        try
        {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e)
        {
            throw new VendingMachinePersistenceException("Could not write inventory data.", e);
        }

        String itemAsText;
        for (Item currentItem : Item.items.values())
        {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }

}
