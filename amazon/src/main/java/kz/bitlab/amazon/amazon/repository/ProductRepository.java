package kz.bitlab.amazon.amazon.repository;

import kz.bitlab.amazon.amazon.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByTitle(String title);
}
