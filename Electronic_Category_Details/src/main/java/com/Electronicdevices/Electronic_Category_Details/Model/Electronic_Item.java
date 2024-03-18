package com.Electronicdevices.Electronic_Category_Details.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Electronic_Item
{

    private int eid;
    private String productname;

    private double productcost;

    private int productquantity;
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public double getProductcost() {
        return productcost;
    }

    public void setProductcost(double productcost) {
        this.productcost = productcost;
    }

    public int getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(int productquantity) {
        this.productquantity = productquantity;
    }
}

