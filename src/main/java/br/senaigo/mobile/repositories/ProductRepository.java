package br.senaigo.mobile.repositories;

import br.senaigo.mobile.entities.Order;
import br.senaigo.mobile.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
