package karan.societe.hiring.apparel.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import karan.societe.hiring.apparel.domain.Product;

@Component
public class ProductDaoImpl implements ProductDao {
    private Map<Integer, Product> products = new ConcurrentHashMap<>();

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product getById(Integer id) {
        return products.get(id);
    }

}
