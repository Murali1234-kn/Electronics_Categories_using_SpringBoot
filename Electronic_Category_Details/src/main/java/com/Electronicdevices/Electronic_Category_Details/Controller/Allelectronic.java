package com.Electronicdevices.Electronic_Category_Details.Controller;

import com.Electronicdevices.Electronic_Category_Details.Model.Electronic_Category;
import com.Electronicdevices.Electronic_Category_Details.Model.Electronic_Item;




import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Data
@Setter
@Getter
public class Allelectronic {

    private List<Electronic_Item> electronicItem = new ArrayList<>();


    private List<Electronic_Category> electronicCategory = new ArrayList<>();

    public List<Electronic_Item> getElectronicItem() {
        return electronicItem;
    }

    public List<Electronic_Category> getElectronicCategory() {
        return electronicCategory;
    }

}


