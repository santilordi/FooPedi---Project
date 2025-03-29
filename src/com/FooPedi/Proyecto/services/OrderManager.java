package com.FooPedi.Proyecto.services;

import com.FooPedi.Proyecto.model.Client;
import com.FooPedi.Proyecto.model.Order;
import com.FooPedi.Proyecto.model.OrderItem;
import com.FooPedi.Proyecto.util.EmptyOrderException;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private ArrayList<Order> orders;

    public Order createOrder(Client client, List<OrderItem> orderItems) throws EmptyOrderException{
        
        //Validar que la lista de prodcutos no este vacia
        if (orderItems == null || orderItems.isEmpty()){
            throw new EmptyOrderException();
        }
        
        //Crear una nueva orden
        Order order = new Order(generateOrderId(), client);
        order.setOrderItems(new ArrayList<>(orderItems));

        if (orders == null){
            orders = new ArrayList<>();
        }

        orders.add(order);
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
        List<Client> listClientsReturn = new ArrayList<>;
        
    }

    public double calcTotalOrderCost(){

    }

    private int generateOrderId(){
        return orders == null ? 1 : orders.size() + 1;
    }
}
