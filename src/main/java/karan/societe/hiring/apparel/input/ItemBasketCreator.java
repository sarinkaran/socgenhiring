package karan.societe.hiring.apparel.input;

import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import karan.societe.hiring.apparel.dao.ProductDao;
import karan.societe.hiring.apparel.domain.Item;
import karan.societe.hiring.apparel.domain.Product;
import karan.societe.hiring.apparel.service.ItemBasket;

@Component
public class ItemBasketCreator implements Function<String, ItemBasket> {
    private ProductDao productDao;

    public ItemBasketCreator(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ItemBasket apply(String t) {
        ItemBasket basket = new ItemBasket();
        String[] items = StringUtils.commaDelimitedListToStringArray(t);

        for (String id : items) {

            Product product = productDao.getById(Integer.valueOf(id));
            Item item = new Item(product);
            basket.addItem(item);
        }

        return basket;
    }

}
