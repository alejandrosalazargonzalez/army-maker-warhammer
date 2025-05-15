package es.alejandrosalazargonzalez.army_maker_warhammer.model.abstractas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EjercitoRepository extends JpaRepository<Object, Long>{
    
}
