package com.FooPedi.Proyecto.services;

import com.FooPedi.Proyecto.model.Product;
import com.FooPedi.Proyecto.util.InvalidPriceException;
import com.FooPedi.Proyecto.util.ProductException;
import com.FooPedi.Proyecto.util.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ProductsManager {
    private ArrayList<Product> products;

    public ProductsManager() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) throws ProductException {
        // Validar que el precio no sea menor que 0
        if (product.getPrice() < 0) {
            throw new InvalidPriceException(product.getPrice());
        }

        // Validar que el producto no esté duplicado
        for (Product product1 : products) {
            if (product == product1) {
                throw new ProductException("El producto ya se encuentra cargado");
            }
        }

        products.add(product);
    }

    public void removeProduct(int id){
        try {
            Product searchId = searchProductById(id); //Valida que se encuentre el cliente
            if (searchId != null){
                products.remove(searchId);
            }
        } catch (ProductNotFoundException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
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
