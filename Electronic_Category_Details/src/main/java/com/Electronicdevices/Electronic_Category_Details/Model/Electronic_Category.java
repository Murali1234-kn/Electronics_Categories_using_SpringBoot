package com.Electronicdevices.Electronic_Category_Details.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class Electronic_Category
{
    private int cid;
    private String Ecategory;
    private String Emodel;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getEcategory()
    {
        return Ecategory;
    }

    public void setEcategory(String Ecategory) {
        this.Ecategory = Ecategory;
    }

    public String getEmodel() {
        return Emodel;
    }

    public void setEmodel(String Emodel) {
        this.Emodel = Emodel;
    }
}
