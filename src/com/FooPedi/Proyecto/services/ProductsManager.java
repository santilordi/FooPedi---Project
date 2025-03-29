package com.FooPedi.Proyecto.services;

import com.FooPedi.Proyecto.model.Product;
import com.FooPedi.Proyecto.util.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ProductsManager {
    private ArrayList<Product> products;

    public void addProduct(Product product){

    }

    public void removeProduct(int id){

    }

    public Product searchProductById(int id) throws ProductNotFoundException{
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }

        throw new ProductNotFoundException("Producto con ID: " + id + "no encontrado.");
    }
    
    public Product searchProductByName(String name) throws ProductNotFoundException{
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }

        throw new ProductNotFoundException("Producto con nombre: " + name + "no encontrado.");
    }

    public List<Product> listProducts(){
        return new ArrayList<>(products);
    }

    public void updateProduct(int id, double price, int stock) throws ProductNotFoundException{
        
        for (Product product : products) {
            if (product.getId() == id) {
            product.setPrice(price);
            product.setStock(stock);
            }
        }
        throw new ProductNotFoundException("Producto con ID: " + id + " no encontrado.");
    }
}
