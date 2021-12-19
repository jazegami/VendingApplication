package ajun.webapp.vebnding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ajun.webapp.vebnding.entity.TVendingProduct;

@Repository
public interface TVendingProductRepository extends JpaRepository<TVendingProduct, Integer> {
	
	TVendingProduct findByVendingProductId(Long vendingProductId);

}
