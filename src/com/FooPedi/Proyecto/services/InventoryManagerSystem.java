package com.FooPedi.Proyecto.services;

import com.FooPedi.Proyecto.model.Client;
import com.FooPedi.Proyecto.model.Computer;
import com.FooPedi.Proyecto.model.Order;
import com.FooPedi.Proyecto.model.OrderItem;
import com.FooPedi.Proyecto.model.Product;
import com.FooPedi.Proyecto.model.Tablets;
import com.FooPedi.Proyecto.util.CustomerException;
import com.FooPedi.Proyecto.util.CustomerNotFoundException;
import com.FooPedi.Proyecto.util.InvalidEmailException;
import com.FooPedi.Proyecto.util.ProductNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryManagerSystem {
    private ProductsManager productsManager;
    private OrderManager orderManager;
    private ClientsManager clientsManager;
    private Scanner scanner;

    public InventoryManagerSystem() {
        this.productsManager = new ProductsManager();
        this.orderManager = new OrderManager();
        this.clientsManager = new ClientsManager();
        this.scanner = new Scanner(System.in);
    }

    public void initializeSystem(){

        while (true){
            showMainMenu();

            int option = scanner.nextInt();
            scanner.nextLine();

            switch(option){
                case 1:
                manageProducts();
                break;

                case 2:
                manageClients();
                break;

                case 3:
                manageOrders();
                break;

                case 4:
                System.out.println("Saliendo del sistema...");
                return;

                default:
                System.out.println("Opcion invalida. Intente nuevamente.");
            }
        }
    }

    
    public void showMainMenu() {
        System.out.println("\n--- SISTEMA DE GESTIÓN DE INVENTARIO ---");
        System.out.println("1. Gestionar Productos");
        System.out.println("2. Gestionar Clientes");
        System.out.println("3. Gestionar Pedidos");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }


    //Gestionar productos
    public void manageProducts(){
        while (true) {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Listar Productos");
            System.out.println("3. Buscar Producto");
            System.out.println("4. Actualizar Producto");
            System.out.println("5. Eliminar Producto");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            
            switch (opcion) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    listProducts();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    removeProduct();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private void addNewProduct(){
        System.out.println("Seleccione el tipo de producto a agregar:");
        System.out.println("1. Computadora");
        System.out.println("2. Tablet");
        System.out.print("Ingrese su opción: ");
        int tipoProducto = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        System.out.print("Ingrese el nombre del producto: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese el precio del producto: ");
        double price = scanner.nextDouble();

        System.out.print("Ingrese el stock del producto: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        Product product;

        switch (tipoProducto) {
            case 1: // Computadora
            System.out.print("Ingrese el tamaño de la pantalla en pulgadas: ");
            double inches = scanner.nextDouble();

            System.out.print("Ingrese la cantidad de RAM en GB: ");
            int ramGB = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            product = new Computer(name, price, stock, inches, ramGB);
            break;

            case 2: // Tablet
            System.out.print("Ingrese el tamaño de la pantalla en pulgadas: ");
            double tabletInches = scanner.nextDouble();
            scanner.nextLine(); // Consumir salto de línea

            product = new Tablets(name, price, stock, tabletInches);
            break;

            default:
            System.out.println("Opción inválida. No se agregó ningún producto.");
            return;
        }

        try {
            productsManager.addProduct(product);
            System.out.println("Producto agregado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar el producto: " + e.getMessage());
        }
    }

    private void listProducts(){
        List<Product> products = productsManager.listProducts();
        
        if (products.isEmpty()){
            System.out.println("No hay productos cargados");
            return;
        }

        System.out.println("\n--- LISTA DE PRODUCTOS ---\n");
        for (Product p : products){
            System.out.printf("ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d%n",
            p.getId(), p.getName(), p.getPrice(), p.getStock());
        }
    }

    private void searchProduct(){

        System.out.println("Buscar producto por:");
        System.out.println("1. ID");
        System.out.println("2. Nombre");
        System.out.print("Seleccione una opción: ");
        
        int option = scanner.nextInt();
        scanner.nextLine();

        try {
            Product product;
            if (option == 1){
                System.out.print("Ingrese ID del producto: ");
                int id = scanner.nextInt();
                product = productsManager.searchProductById(id);
            }else{
                System.out.print("Ingrese nombre del producto: ");
                String name = scanner.nextLine();
                name = formatString(name);
                product = productsManager.searchProductByName(name);
            }

            System.out.println("\nProducto encontrado");
            System.out.printf("ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d%n", 
                product.getId(), product.getName(), product.getPrice(), product.getStock());
            
        } catch (ProductNotFoundException e) {
            System.out.println("Ocurrio un error: " + e.getMessage());
        }
    }

    private void updateProduct() {
        try {
            System.out.print("Ingrese el ID del producto a actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            // Buscar el producto por ID
            Product product = productsManager.searchProductById(id);

            // Mostrar los valores actuales y pedir los nuevos
            System.out.printf("Nuevo precio (actual: %.2f): ", product.getPrice());
            double newPrice = scanner.nextDouble();

            System.out.printf("Nuevo stock (actual: %d): ", product.getStock());
            int newStock = scanner.nextInt();

            // Actualizar el producto
            productsManager.updateProduct(id, newPrice, newStock);
            System.out.println("Producto actualizado exitosamente.");

        } catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
            scanner.nextLine(); // Consumir entrada inválida
        }
    }

    private void removeProduct(){
        try{
            System.out.println("Ingrese el ID del producto a eliminar: ");
            int id = scanner.nextInt();

            System.out.print("¿Está seguro de eliminar este producto? (s/n): ");
            String confirm = scanner.next();
            
            if (confirm.toLowerCase().equals("s")) {
                productsManager.removeProduct(id);
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("Eliminación cancelada.");
            }
        }catch(Exception e){
            System.out.println("Ocurrio un error: " + e.getMessage());
            scanner.nextLine();
        }
    }


    //Gestionar clientes
    public void manageClients(){
        while (true) {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente");
            System.out.println("4. Actualizar Cliente");
            System.out.println("5. Eliminar Cliente");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (option) {
                case 1:
                    addNewClient();
                    break;
                case 2:
                    listClient();
                    break;
                case 3:
                    searchClient();
                    break;
                case 4:
                    updateClient();
                    break;
                case 5:
                    removeClient();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private void addNewClient(){
        System.out.print("Ingrese el nombre del nuevo cliente: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese el mail del nuevo cliente: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese el numero de telefono del nuevo cliente: ");
        String number = scanner.nextLine();

        try {
            clientsManager.addClient(new Client(name, email, number));
        } catch (InvalidEmailException e) {
            System.out.println("Ocurrio un error en el email: " + e.getMessage());
        }catch (CustomerException e){
            System.out.println("Ocurrio un error con la carga del cliente");
        }
    }

    private void listClient() {
        List<Client> clients = clientsManager.listClients();

        if (clients.isEmpty()) {
            System.out.println("No hay clientes cargados");
            return;
        }

        System.out.println("\n--- LISTA DE CLIENTES ---\n");
        for (Client client : clients) {
            System.out.printf("ID: %d | Nombre: %s | Email: %s | Teléfono: %s%n",
                client.getId(), client.getName(), client.getEmail(), client.getNumTelephone());
        }
    }

    private void searchClient(){
        System.out.println("Buscar cliente por:");
        System.out.println("1. ID");
        System.out.println("2. Nombre");
        System.out.print("Seleccione una opción: ");
        
        int option = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        
        try {
            Client client;
            if (option == 1) {
                System.out.print("Ingrese ID del cliente: ");
                int id = scanner.nextInt();
                client = clientsManager.searchClientById(id);
            } else {
                System.out.print("Ingrese nombre del cliente: ");
                String name = scanner.nextLine();
                client = clientsManager.searchClientByName(name);
            }
            
            System.out.println("\nCliente encontrado:");
            System.out.printf("ID: %d | Nombre: %s | Email: %s | Numero de telefono: %s", 
                client.getId(), client.getName(), client.getEmail(), client.getNumTelephone());
        } catch (CustomerNotFoundException e) {
            System.out.println("Cliente no encontrado: " + e.getMessage());
        }
    }

    private void updateClient(){
        
        try{
            System.out.println("Ingrese el ID del cliente a actualizar: ");
            int id = scanner.nextInt();

            Client client = clientsManager.searchClientById(id);

            System.out.println("Nuevo nombre (actual: " + client.getName() + "): ");
            String name = scanner.next();

            name = formatString(name);

            System.out.println("Nuevo email (actual: " + client.getEmail() + "): ");
            String newEmail = scanner.next();

            System.out.println("Nuevo numero de telefono (actual: " + client.getNumTelephone() + "): ");
            String newNumTel = scanner.next();

            clientsManager.updateClient(id, name, newEmail, newNumTel);

            System.out.println("Cliente actualizado exitosamente");

        }catch(CustomerNotFoundException e){
            System.out.println("Ocurrio un error: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        }

    }

    private void removeClient(){
        try{
            System.out.println("Ingrese el ID del cliente a eliminar: ");
            int id = scanner.nextInt();

            System.out.print("¿Está seguro de eliminar este cliente? (s/n): ");
            String confirm = scanner.next();
            
            if (confirm.toLowerCase().equals("s")) {
                clientsManager.removeClient(id);
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("Eliminación cancelada.");
            }
        }catch(Exception e){
            System.out.println("Ocurrio un error: " + e.getMessage());
            scanner.nextLine();
        }
    }


    //Gestionar pedidos
    public void manageOrders(){
        while (true) {
            System.out.println("\n--- GESTIÓN DE PEDIDOS ---");
            System.out.println("1. Crear Nuevo Pedido");
            System.out.println("2. Listar Pedidos");
            System.out.println("3. Listar pedidos de un cliente");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            
            switch (option) {
                case 1:
                    createNewOrder();
                    break;
                case 2:
                    listOrders();
                    break;
                case 3:
                    listClientOrders();
                    break; // Agregar el break aquí
                case 4:
                    return; // Salir del método y volver al menú principal
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void createNewOrder(){
        try {
            // Listar clientes para seleccionar
            List<Client> clients = clientsManager.listClients();

            System.out.println("\n--- SELECCIONE UN CLIENTE ---");
            for (Client c : clients) {
                System.out.printf("ID: %d | Nombre: %s%n", c.getId(), c.getName());
            }
            
            System.out.print("Ingrese ID del cliente: ");
            int clientId = scanner.nextInt();
            Client client = clientsManager.searchClientById(clientId);
            
            ArrayList<OrderItem> listItems = new ArrayList<>(); //Lista para productos de pedido

            // Seleccionar productos para el pedido
            while (true) {
                listProducts();
                System.out.print("Ingrese ID del producto (0 para terminar): ");
                int productId = scanner.nextInt();
                
                if (productId == 0) break;
                
                Product product = productsManager.searchProductById(productId);
                
                System.out.print("Ingrese cantidad: ");
                int amount = scanner.nextInt();
                
                // Validar stock
                if (amount > product.getStock()) {
                    System.out.println("Cantidad supera el stock disponible.");
                    continue;
                }
                
                OrderItem orderItem = new OrderItem(product, amount);

                listItems.add(orderItem);
            }
            
            // Confirmar pedido
            System.out.println("\nResumen del Pedido:");
            System.out.printf("Cliente: %s%n", client.getName());
            System.out.println("Productos:");
            for (OrderItem item : listItems) {
                System.out.printf("- %s x %d | Subtotal: $%.2f%n", 
                    item.getProduct().getName(), 
                    item.getAmount(), 
                    item.calculateSubtotal());
            }
            
            //Calcular costo total de linea de items
            double totalCostListItems = 0;
            for (OrderItem orderItem : listItems){
                totalCostListItems += orderItem.calculateSubtotal();
            }

            //Mostrar total del pedido
            System.out.printf("Total: $%.2f%n", totalCostListItems);
            
            System.out.print("¿Confirmar pedido? (s/n): ");
            String confirm = scanner.next();
            
            if (confirm.toLowerCase().equals("s")) {
                orderManager.createOrder(client, listItems);
                System.out.println("Pedido creado exitosamente.");
                
                // Actualizar stock
                for (OrderItem item : listItems) {
                    Product p = item.getProduct();
                    p.setStock(p.getStock() - item.getAmount());
                }
            } else {
                System.out.println("Pedido cancelado.");
            }
        } catch (Exception e) {
            System.out.println("Error al crear pedido: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void listOrders() {
        List<Order> orders = orderManager.listOrders();

        if (orders.isEmpty()) {
            System.out.println("No hay pedidos registrados");
            return;
        }

        System.out.println("\n--- LISTA DE PEDIDOS ---");
        for (Order order : orders) {
            System.out.printf("ID Pedido: %d | Cliente: %s | Total: $%.2f%n",
                order.getId(),
                order.getClient().getName(),
                order.getTotalCost());

            System.out.println("Productos:");
            for (OrderItem item : order.getOrderItems()) {
                System.out.printf("- %s x %d | Subtotal: $%.2f%n",
                    item.getProduct().getName(),
                    item.getAmount(),
                    item.calculateSubtotal());
            }
            System.out.println("---");
        }
    }
    
    private void listClientOrders() {
        try {
            // Listar clientes para seleccionar
            List<Client> clients = clientsManager.listClients();

            System.out.println("\n--- SELECCIONE UN CLIENTE ---");
            for (Client c : clients) {
                System.out.printf("ID: %d | Nombre: %s%n", c.getId(), c.getName());
            }

            System.out.print("Ingrese ID del cliente: ");
            int clientId = scanner.nextInt();
            Client clientSelect = clientsManager.searchClientById(clientId);

            List<Order> ordersClient = clientSelect.getOrdersClient();
            if (ordersClient.isEmpty()) {
                System.out.println("El cliente no tiene pedidos registrados.");
                return;
            }

            System.out.println("\n--- LISTA DE PEDIDOS ---");
            for (Order order : ordersClient) {
                System.out.printf("ID Pedido: %d | Cliente: %s | Total: $%.2f%n",
                    order.getId(),
                    order.getClient().getName(),
                    order.getTotalCost());
            }
        } catch (CustomerNotFoundException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }


    private static String formatString(String name){
        name = name.trim().toLowerCase();

        String[] words = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words){
            if (!word.isEmpty()){
                sb.append(Character.toUpperCase(word.charAt(0)))
                .append(word.substring(1)).append(" ");
            }
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) {
        InventoryManagerSystem ims = new InventoryManagerSystem();
        ims.initializeSystem();
    }
}
