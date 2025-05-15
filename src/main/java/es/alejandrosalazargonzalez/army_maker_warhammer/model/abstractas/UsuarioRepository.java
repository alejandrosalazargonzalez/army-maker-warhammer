package es.alejandrosalazargonzalez.army_maker_warhammer.model.abstractas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

}