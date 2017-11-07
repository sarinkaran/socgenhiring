package karan.societe.hiring.apparel.dao.file;

import static java.util.stream.Collectors.toMap;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import karan.societe.hiring.apparel.dao.ProductCategoryDao;
import karan.societe.hiring.apparel.domain.ProductCategory;

@Component
public class YamlFileBasedDiscountDao {
    private Resource resource;
    private ProductCategoryDao productCategoryDao;

    @Autowired
    public YamlFileBasedDiscountDao(@Value("${catalog.filePath}") Resource resource ,ProductCategoryDao
    		productCategoryDao) {
        this.resource = resource;
        this.productCategoryDao =  productCategoryDao;

        Assert.isTrue(resource.exists(), "specified resource does not exists:" + resource);
        Assert.isTrue(resource.isReadable(), "specified resource is not readable");

    }

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        InputStream inputStream = resource.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        
        List<ProductCategory> categories = mapper.readValue(reader, new TypeReference<List<ProductCategory>>() {
        });
        
        categories.forEach(category->{ productCategoryDao.saveCategory(category);  }); 
    }
}
