package com.masivian.ruleta.repository;

import com.masivian.ruleta.model.Apuesta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ApuestaRepository extends CrudRepository<Apuesta, Integer> {
    List<Apuesta> findAllByRuleta(Integer idRuleta);
}
