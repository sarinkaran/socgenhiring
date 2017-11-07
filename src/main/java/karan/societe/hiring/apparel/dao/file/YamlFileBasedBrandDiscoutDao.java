package karan.societe.hiring.apparel.dao.file;

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

import karan.societe.hiring.apparel.dao.ProductBrandDao;
import karan.societe.hiring.apparel.domain.ProductBrand;


@Component
public class YamlFileBasedBrandDiscoutDao {
	
	 private Resource resource;
	 private ProductBrandDao productBrandDao;

	    @Autowired
	    public YamlFileBasedBrandDiscoutDao(@Value("${brand.filePath}") Resource resource ,ProductBrandDao
	    		productBrandDao) {
	        this.resource = resource;
	        this.productBrandDao = productBrandDao;

	        Assert.isTrue(resource.exists(), "specified resource does not exists:" + resource);
	        Assert.isTrue(resource.isReadable(), "specified resource is not readable");

	    }

	    @PostConstruct
	    public void init() throws IOException {
	        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	        InputStream inputStream = resource.getInputStream();
	        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
	        
	        List<ProductBrand> brands = mapper.readValue(reader, new TypeReference<List<ProductBrand>>() {
	        });
	        brands.forEach(brand->{ productBrandDao.saveBrand(brand); });
	    }
}
