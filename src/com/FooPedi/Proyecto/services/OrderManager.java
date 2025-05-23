package com.FooPedi.Proyecto.services;

import com.FooPedi.Proyecto.model.Client;
import com.FooPedi.Proyecto.model.Order;
import com.FooPedi.Proyecto.model.OrderItem;
import com.FooPedi.Proyecto.util.EmptyOrderException;
import com.FooPedi.Proyecto.util.OrderException;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private ArrayList<Order> orders;

    public OrderManager() {
        this.orders = new ArrayList<>(); // Inicializar la lista de órdenes
    }

    public Order createOrder(Client client, List<OrderItem> orderItems) throws EmptyOrderException {
        // Validar que la lista de productos no esté vacía
        if (orderItems == null || orderItems.isEmpty()) {
            throw new EmptyOrderException();
        }

        // Crear una nueva orden
        Order order = new Order(client);
        order.setId(generateOrderId());
        order.setOrderItems(new ArrayList<>(orderItems));

        // Calcular el costo total de la orden
        order.calculateTotalCost();

        // Agregar la orden a la lista de órdenes
        orders.add(order);

        // Agregar la orden a la lista de pedidos del cliente
        client.addOrder(order);

        return order;
    }

    public List<Order> listOrders(){
        List<Order> listReturn = new ArrayList<>();
        for (Order order : orders){
            listReturn.add(order);
        }

        return listReturn;
    }

    public List<Order> listClientOrders(Client client){
        List<Order> listClientsReturn = new ArrayList<>(); //lista de return
        
        for (Order order : client.getOrdersClient()){
            listClientsReturn.add(order);
        }
        
        return listClientsReturn;
    }

    public double calcTotalOrderCost(int id) throws OrderException{
        
        //Busco la orden por id
        Order orderAux = null;
        for (Order order : orders){
            if (order.getId() == id){
                orderAux = order;
                break;
            }
        }

        if (orderAux == null) {
            throw new OrderException("No se encontro una orden con el id: " + id);
        }

        //Calculo el costo de la orden
        double total = 0;
        for (OrderItem orderItem : orderAux.getOrderItems()){
            total += orderItem.calculateSubtotal();
        }

        return total;
    }

    private int generateOrderId(){
        return orders == null ? 1 : orders.size() + 1;
    }
}
