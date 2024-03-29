package br.senaigo.mobile.repositories;

import br.senaigo.mobile.entities.Order;
import br.senaigo.mobile.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

}
