package com.ecommerce.monolith.product.service;
import com.ecommerce.monolith.product.dto.CreateProductRequest;
import com.ecommerce.monolith.product.dto.ProductDTO;
import com.ecommerce.monolith.product.mapper.ProductMapper;
import com.ecommerce.monolith.product.model.Product;
import com.ecommerce.monolith.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        return mapper.toDTOList(repository.findAll());
    }
    @Override
    public ProductDTO getProductById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
        return mapper.toDTO(product);
    }
    @Override
    public ProductDTO createProduct(CreateProductRequest request) {
        return mapper.toDTO(repository.save(mapper.toEntity(request)));
    }
    @Override
    public ProductDTO updateProduct(Long id, CreateProductRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
        mapper.updateEntity(request, product);
        return mapper.toDTO(repository.save(product));
    }
    @Override
    public void deleteProduct(Long id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Product not found: " + id);
        repository.deleteById(id);
    }
}