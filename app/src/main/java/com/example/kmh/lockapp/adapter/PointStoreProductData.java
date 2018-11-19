package com.example.kmh.lockapp.adapter;

public class PointStoreProductData {
    public String price;
    public String product_Image;
    public String PointStoreProductID;
    public PointStoreProductData(String PointStoreProductID,String price, String product_Image){
        this.PointStoreProductID = PointStoreProductID;
        this.price = price;
        this.product_Image= product_Image;
    }

}
