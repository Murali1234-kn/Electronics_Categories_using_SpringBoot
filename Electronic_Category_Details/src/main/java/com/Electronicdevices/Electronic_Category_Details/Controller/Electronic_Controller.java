package com.Electronicdevices.Electronic_Category_Details.Controller;

import com.Electronicdevices.Electronic_Category_Details.Model.Electronic_Category;
import com.Electronicdevices.Electronic_Category_Details.Model.Electronic_Item;
import com.Electronicdevices.Electronic_Category_Details.Service.ElectronicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/electronic")
public class Electronic_Controller {

    @Autowired
    public ElectronicService electronicService;

    @PostMapping("/saveall")
    public ResponseEntity<String> saveData(@RequestBody Allelectronic allelectronic)
    {
        return electronicService.saveitems(allelectronic);

    }
    @GetMapping("/items")
    public ResponseEntity<List<Electronic_Item>> getallitems()
    {
    Allelectronic allData = electronicService.readDataFromFile();

    List<Electronic_Item> allItems = allData.getElectronicItem();

    return ResponseEntity.ok(allItems);
    }
    @GetMapping("/category")
    public ResponseEntity<List<Electronic_Category>> getallcategory()
    {
        Allelectronic allData1 = electronicService.readDataFromFile();

        List<Electronic_Category> allcategory = allData1.getElectronicCategory();

        return ResponseEntity.ok(allcategory);
    }

    @GetMapping("/items/id/{eid}")
    public ResponseEntity<?> getItemById(@PathVariable int eid) {
        Allelectronic allData = electronicService.readDataFromFile();
        for (Electronic_Item item : allData.getElectronicItem()) {
            if (item.getEid() == eid)
            {
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.ok("No item with eid " + eid + " found in Electronic_Items");
    }

    @GetMapping("/items/productname/{productname}")
    public ResponseEntity<?> getItemsByProductname(@PathVariable String productname)
    {
        Allelectronic alldata1 = electronicService.readDataFromFile();
        List<Electronic_Item> matchingitems = new ArrayList<>();

        for (Electronic_Item item : alldata1.getElectronicItem())
        {
            if (item.getProductname().equals(productname))
            {
                matchingitems.add(item);
            }
        }
      return ResponseEntity.ok(matchingitems);
    }

    @GetMapping("/category/Ecategory/{Ecategory}")
    public ResponseEntity<?> getCategorybyname(@PathVariable String Ecategory)
    {
        Allelectronic alldata2 = electronicService.readDataFromFile();
        List<Electronic_Category> matchingcategory = new ArrayList<>();

        for (Electronic_Category category : alldata2.getElectronicCategory())
        {
            if (category.getEcategory().equals(Ecategory))
            {
                matchingcategory.add(category);
            }
        }
  return ResponseEntity.ok(matchingcategory);
    }
    @GetMapping("/category/cid/{cid}")
           public ResponseEntity<?> getCategoryById(@PathVariable int cid)
         {
             Allelectronic alldata = electronicService.readDataFromFile() ;
             for(Electronic_Category category :alldata.getElectronicCategory())
           {
         if(category.getCid() == cid)
         {
        return ok(category);
        }
     }
          return ok("with these cid : "+cid+" not in the Electronic_Category");
    }

    @GetMapping("/items/highcost")
    public ResponseEntity<?> getHighestcost()
    {
        Allelectronic alldata1 = electronicService.readDataFromFile();

        List<Electronic_Item> highestCostItems = new ArrayList<>();

        double highcost=0;

        for (Electronic_Item item : alldata1.getElectronicItem())
        {
            if (item.getProductcost() > highcost)
            {
              highestCostItems.clear();
                highestCostItems.add(item);
               highcost = item.getProductcost();
            }
            else if (item.getProductcost() == highcost)
            {
                highestCostItems.add(item);
            }
        }
            return ResponseEntity.ok(highestCostItems);
        }
    @GetMapping("/items/lowcost")
    public ResponseEntity<?> getLowestCost()
    {
        Allelectronic alldata2 = electronicService.readDataFromFile();

        List<Electronic_Item> lowestcostitems = new ArrayList<>();

        double lowestCost =1.7976931348623157E308;//very large posititve no.

        for (Electronic_Item item : alldata2.getElectronicItem())
        {
            if (item.getProductcost() < lowestCost)
            {
                lowestcostitems.clear();
                lowestcostitems.add(item);
                lowestCost = item.getProductcost();
            }
            else if (item.getProductcost() == lowestCost)
            {
                lowestcostitems.add(item);
            }
        }
        return ResponseEntity.ok(lowestcostitems);
    }
    @GetMapping("/items/productcost/{minCost}/{maxCost}")
    public ResponseEntity<?> getItemsByCostRange(@PathVariable int minCost, @PathVariable int maxCost)
    {//extract from url path

        Allelectronic alldata1 = electronicService.readDataFromFile();

        List<Electronic_Item> range = new ArrayList<>();

        for (Electronic_Item item : alldata1.getElectronicItem())
        {
            double  productCost = item.getProductcost();

            if (productCost >= minCost && productCost <= maxCost)
            {
            range.add(item);
            }
        }
        return ResponseEntity.ok(range);
    }

    @PutMapping("/items/{eid}")
    public ResponseEntity<String> updateItem(@PathVariable int eid, @RequestBody Electronic_Item Item)
    {
        Item.setEid(eid);
        return electronicService.updateItem(Item);

    }

    @PutMapping("/category/{cid}")
    public ResponseEntity<String> updateCategory(@PathVariable int cid, @RequestBody Electronic_Category Category)
    {
        Category.setCid(cid);
        return electronicService.updateCategory(Category);
    }


    @DeleteMapping("/items/{eid}")
    public ResponseEntity<String> deleteItem(@PathVariable int eid)
    {
        Allelectronic presentdata = electronicService.readDataFromFile();

        List<Electronic_Item> items = presentdata.getElectronicItem();

        for (Electronic_Item item : items)
        {
            if (item.getEid() == eid)
            {
                items.remove(item);
               electronicService. saveDataToFile(presentdata);
                return ok("Electronic_Item with id : "+ eid + " was successfully deleted");
            }
        }

        return ResponseEntity.ok("Given  eid is : " + eid + " not in the Electronic_Item");
    }
    @DeleteMapping("/category/{cid}")
    public ResponseEntity<String> deleteCategory(@PathVariable int cid)
    {
        Allelectronic presentdata=electronicService.readDataFromFile();
        List<Electronic_Category> items = presentdata.getElectronicCategory();
        for(Electronic_Category item :items)
        {
            if(item.getCid() == cid)
            {
                items.remove(item);
                electronicService.saveDataToFile(presentdata);
                return ResponseEntity.ok("Electronic_Category with id :" +cid+ " was Successfully deleted");

            }
        }
        return ok("Given cid is : " +cid+ "  not in the Electronic_Category");
    }

    }

