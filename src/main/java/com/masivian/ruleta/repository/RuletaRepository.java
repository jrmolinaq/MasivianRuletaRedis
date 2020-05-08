package com.masivian.ruleta.repository;

import com.masivian.ruleta.model.Ruleta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface RuletaRepository extends CrudRepository<Ruleta, Integer> {
}
