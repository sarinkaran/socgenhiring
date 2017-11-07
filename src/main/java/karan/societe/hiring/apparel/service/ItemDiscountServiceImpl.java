package karan.societe.hiring.apparel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemDiscountServiceImpl implements DiscountService {

    private DiscountEngine engine;

    @Autowired
    public ItemDiscountServiceImpl(DiscountEngine engine) {
        this.engine = engine;
    }

    @Override
    public void applyDiscount(ItemBasket basket) {
        basket.getItems().forEach(item -> engine.applyDiscountOnitem(basket, item));
        // can perform other related operations, if needed
    }
}
