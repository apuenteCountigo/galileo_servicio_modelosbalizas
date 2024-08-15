package com.galileo.cu.servicio_modelosbalizas.repositorio;

import com.galileo.cu.commons.models.ModelosBalizas;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "modelosbalizas", path = "modelosbalizas")
public interface ModelosBalizasRepository extends PagingAndSortingRepository<ModelosBalizas, Long> {
    ModelosBalizas findFirstByDescripcion(String descripcion);
}
