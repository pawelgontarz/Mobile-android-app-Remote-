package com.mechatronika.magazyn;

public class Product {

    private int product;

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public Product()
    {
        product = 0;
    }

    public Product(int product)
    {
        this.product = product;
    }


}
