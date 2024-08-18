package com.galileo.cu.servicio_modelosbalizas.repositorio;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.galileo.cu.commons.models.Trazas;

@RepositoryRestResource(exported = false, collectionResourceRel = "trazas", path = "listar")
public interface TrazasRepository extends PagingAndSortingRepository<Trazas, Long> {

}
