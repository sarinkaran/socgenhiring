package karan.societe.hiring.apparel.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import karan.societe.hiring.apparel.domain.Item;

public class ItemBasket {
    private String id;
    private List<Item> items = new ArrayList<>();

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public BigDecimal calculateTotalPrice() {
        AtomicReference<BigDecimal> total = new AtomicReference<BigDecimal>(BigDecimal.ZERO);
        // simply add up calculated price for each item
        items.forEach(p -> total.set(total.get().add(p.calculatePrice())));
        return total.get();
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
