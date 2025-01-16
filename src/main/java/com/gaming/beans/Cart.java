package com.gaming.beans;

import java.util.ArrayList;
import java.util.List;

public class Cart implements java.io.Serializable {
    private List<Product> items;
    private double total;
    private double tax;
    
    public Cart() {
        items = new ArrayList<>();
        total = 0.0;
        tax = 0.0;
    }
    
    public void addItem(Product product) {
        items.add(product);
        calculateTotal();
    }
    
    public void removeItem(int productId) {
        items.removeIf(p -> p.getId() == productId);
        calculateTotal();
    }
    
    private void calculateTotal() {
        total = items.stream().mapToDouble(Product::getPrice).sum();
        tax = total * 0.13; // 13% tax
    }
    
    public List<Product> getItems() { return items; }
    public double getTotal() { return total; }
    public double getTax() { return tax; }
    public double getFinalTotal() { return total + tax; }
}