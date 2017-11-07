package karan.societe.hiring.apparel;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.ClassUtils;

import karan.societe.hiring.apparel.converter.ProductCategoryConverter;
import karan.societe.hiring.apparel.dao.ProductCategoryDao;
import karan.societe.hiring.apparel.dao.ProductDao;
import karan.societe.hiring.apparel.input.InventoryDataReader;
import karan.societe.hiring.apparel.input.ItemBasketCreator;
import karan.societe.hiring.apparel.service.DiscountService;
import karan.societe.hiring.apparel.service.ItemBasket;
import karan.societe.hiring.apparel.util.ConsoleInputReader;

@Configuration
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private ConsoleInputReader reader;

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) throws IOException {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            startSpringApplicationContext(context);

            Application application = context.getBean(Application.class);

            application.runIt();

            log.info("Application finished");
        }

    }

    private static void startSpringApplicationContext(AnnotationConfigApplicationContext context) throws IOException {
        StandardEnvironment env = new StandardEnvironment();
        env.getPropertySources().addFirst(new ResourcePropertySource("classpath:/application.properties"));
        context.setEnvironment(env);
        context.scan(ClassUtils.getPackageName(Application.class));
        context.refresh();
        registerCustomConvertors(context);
    }

    private static void registerCustomConvertors(AnnotationConfigApplicationContext context) {
        ConfigurableConversionService service = context.getBean(ConfigurableConversionService.class);
        ProductCategoryDao categoryDao = context.getBean(ProductCategoryDao.class);
        Converter<?, ?> converter = new ProductCategoryConverter(categoryDao);
        service.addConverter(converter);
    }

    public void runIt() {
        loadInventoryData();
        DiscountService discountService = applicationContext.getBean(DiscountService.class);

        List<ItemBasket> baskets = readCustomerBaskets();

        // apply discounts
        baskets.forEach(b -> {
            discountService.applyDiscount(b);
        });

        // print total price on console
        baskets.forEach(b -> System.out.println(b.calculateTotalPrice()));
    }

    private List<ItemBasket> readCustomerBaskets() {
        // input reader
        int line = reader.readInt();
        ItemBasketCreator basketCreator = applicationContext.getBean(ItemBasketCreator.class);

        // read baskets
        List<ItemBasket> baskets = reader.getLines(line, basketCreator);
        return baskets;
    }

    private void loadInventoryData() {
        // input reader
        int line = reader.readInt();
        InventoryDataReader productReader = applicationContext.getBean(InventoryDataReader.class);
        ProductDao productDao = applicationContext.getBean(ProductDao.class);
        reader.getLines(line, productReader)
                .forEach(p -> productDao.save(p));

        reader.readLine();// consumer blank line
    }
}
