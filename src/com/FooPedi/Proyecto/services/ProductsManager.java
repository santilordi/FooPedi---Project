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
        this.products = new ArrayList<>(); // Inicializar la lista de productos
    }

    public void addProduct(Product product) throws ProductException {
        // Validar que el precio no sea menor que 0
        if (product.getPrice() < 0) {
            throw new InvalidPriceException(product.getPrice());
        }

        // Validar que el producto no esté duplicado
        for (Product product1 : products) {
            if (product.getName().equalsIgnoreCase(product1.getName())) {
                throw new ProductException("El producto ya se encuentra cargado");
            }
        }

        // Generar un ID único para el producto
        product.setId(generateProductId());

        products.add(product);
    }

    // Método para generar un ID único para los productos
    private int generateProductId() {
        return products.isEmpty() ? 1 : products.get(products.size() - 1).getId() + 1;
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

    public Product searchProductById(int id) throws ProductNotFoundException {
        for (Product product : products) {
            if (product.getId() == id) {
                return product; // Devuelve el producto si se encuentra
            }
        }
        // Lanza una excepción si no se encuentra el producto
        throw new ProductNotFoundException(id);
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

    public void updateProduct(int id, double price, int stock) throws ProductNotFoundException {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setPrice(price);
                product.setStock(stock);
                return; // Salimos del método después de actualizar el producto
            }
        }
        // Si no se encuentra el producto, lanzamos la excepción
        throw new ProductNotFoundException(id);
    }
}
