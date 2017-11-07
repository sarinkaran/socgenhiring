package karan.societe.hiring.apparel.input;

import java.math.BigDecimal;
import java.util.function.Function;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import karan.societe.hiring.apparel.domain.Product;
import karan.societe.hiring.apparel.domain.ProductBrand;
import karan.societe.hiring.apparel.domain.ProductCategory;

@Component
public class InventoryDataReader implements Function<String, Product> {
    private ConversionService conversionService;

    public InventoryDataReader(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Product apply(String line) {
        String[] list = StringUtils.commaDelimitedListToStringArray(line);
        Product product = new Product(
                conversionService.convert(list[0], Integer.class),
                // hypothetically name brand + cat
                String.format("%s - %s", list[1], list[2]),
                conversionService.convert(list[1], ProductBrand.class),
                conversionService.convert(list[2], ProductCategory.class),
                conversionService.convert(list[3], BigDecimal.class));
        return product;
    }

}
