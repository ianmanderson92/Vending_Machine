package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class VendingMachineDaoFileImpl implements VendingMachineDao
{
    public static final String INVENTORY_FILE = "vendingInventory.txt";
    public static final String DELIMITER = "::";

    private Item unmarshallItem(String itemAsText)
    {
        String[] itemTokens = itemAsText.split(DELIMITER);
        return new Item(itemTokens[0], Float.parseFloat(itemTokens[1]), Integer.parseInt(itemTokens[2]));
    }

    private void loadInventory() throws VendingMachinePersistenceException
    {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "Could not load inventory data into memory.", e);
        }
        String currentLine;
        Item currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);

            Item.Items.add(currentItem);
        }
        scanner.close();
    }
}
