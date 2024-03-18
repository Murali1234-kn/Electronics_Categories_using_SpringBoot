package com.Electronicdevices.Electronic_Category_Details.Service;

import com.Electronicdevices.Electronic_Category_Details.Controller.Allelectronic;
import com.Electronicdevices.Electronic_Category_Details.Model.Electronic_Item;
import com.Electronicdevices.Electronic_Category_Details.Model.Electronic_Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ElectronicService
{

    public String filepath = "C:\\Users\\91630\\Downloads\\Electronic_Category_Details\\Electronic_Category_Details\\electronic.json";
    //public String filepath = "C:\\Users\\91630\\Downloads\\Electronic_Category_Details\\Electronic_Category_Details\\electronics.xml";

    ObjectMapper objectMapper = new ObjectMapper();
    Allelectronic existingData = new Allelectronic();

   StringBuilder Message = new StringBuilder();
    //String Message;
    public ResponseEntity<String> saveitems(Allelectronic allelectronic)
    {
        Allelectronic existingData = readDataFromFile();

        for (Electronic_Item newItem : allelectronic.getElectronicItem())
         {

            if (checking1(existingData.getElectronicItem(), newItem.getEid()))
            {
                Message.append("Already Electronic item with eid: ").append(newItem.getEid()).append("\n");
                //Message = "Already Electronic item with eid: " + newItem.getEid() + "\n";

            }
            else
            {
                existingData.getElectronicItem().add(newItem);
                Message.append("Successfully added Electronic item with eid: ").append(newItem.getEid()).append("\n");
               //  Message = "Successfully added Electronic item with eid: " + newItem.getEid() + "\n";

            }
        }

        for (Electronic_Category newCategory : allelectronic.getElectronicCategory())
        {
            if (checking2(existingData.getElectronicCategory(), newCategory.getCid()))
            {
              Message.append("Already Electronic category with cid: ").append(newCategory.getCid()).append("\n");
             //    Message = "Already Electronic category with cid: " + newCategory.getCid() + "\n";


            }
            else
            {
                existingData.getElectronicCategory().add(newCategory);
               Message.append("Successfully added Electronic category with cid: ").append(newCategory.getCid()).append("\n");
               // Message = "Successfully added Electronic category with cid: " + newCategory.getCid() + "\n";

            }
        }

        try
        {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(filepath), existingData);
        }
           catch (IOException e)
          {
            e.printStackTrace();
            return ResponseEntity.ok("Error while saving data to the file.");
          }

        return ResponseEntity.status(HttpStatus.CREATED).body(Message.toString());
    }


    public boolean checking1(List<Electronic_Item> having, int eid)
    {
        for (Electronic_Item item : having)
        {
            if (item.getEid() == eid)
            {
                return true;
            }
        }
        return false;
    }

    public boolean checking2(List<Electronic_Category> having2, int cid)
    {
        for (Electronic_Category category : having2)
        {
            if (category.getCid() == cid) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity<String> updateItem(Electronic_Item updatedItem)
    {
        Allelectronic existingData = readDataFromFile();

        List<Electronic_Item> items = existingData.getElectronicItem();

        for (int i = 0; i < items.size(); i++)
        {
            if (items.get(i).getEid() == updatedItem.getEid())
            //    if (items.get(i).getEid() == updatedItemId) {
            // Update the item and save the data to the file
            //     items.set(i, updatedItem);
            //       saveDataToFile(existingData);
            {
                items.set(i, updatedItem);

                saveDataToFile(existingData);

                return ResponseEntity.ok("Electronic_Item was  successfully updated");
            }
        }
        return ResponseEntity.ok("With these Id not found any Electroni_items in your Json file");

    }

    public ResponseEntity<String> updateCategory(Electronic_Category update_Category)
    {
        Allelectronic existingData = readDataFromFile();

        List<Electronic_Category> category = existingData.getElectronicCategory();

        for (int i = 0; i < category.size(); i++)
        {
            if (category.get(i).getCid() == update_Category.getCid())
            {
                category.set(i, update_Category);

                saveDataToFile(existingData);
                return ResponseEntity.ok(" Electronic_Category was successfully updated");
            }
        }

        return ResponseEntity.ok("With these Id not found any Electroni_items in your Json file");
    }

    /////////////////////////////////////////////////////////////////////////////////////;
    public void saveDataToFile(Allelectronic save)

    {
        try {
            //   objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            objectMapper.writeValue(new File(filepath), save); //writing to a file
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Allelectronic readDataFromFile()
    //reading data from a file
    {
        try {
            File file = new File(filepath);
            if (file.exists())
            {
                existingData = objectMapper.readValue(file, Allelectronic.class); // reading from a file
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return existingData;
    }

}