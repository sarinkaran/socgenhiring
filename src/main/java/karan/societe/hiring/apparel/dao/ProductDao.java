package karan.societe.hiring.apparel.dao;

import karan.societe.hiring.apparel.domain.Product;

public interface ProductDao {
    void save(Product product);

    Product getById(Integer valueOf);
}
