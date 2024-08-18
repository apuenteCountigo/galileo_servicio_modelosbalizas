package com.galileo.cu.servicio_modelosbalizas.controladores;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galileo.cu.commons.models.AccionEntidad;
import com.galileo.cu.commons.models.ModelosBalizas;
import com.galileo.cu.commons.models.TipoEntidad;
import com.galileo.cu.commons.models.Trazas;
import com.galileo.cu.commons.models.Usuarios;
import com.galileo.cu.servicio_modelosbalizas.repositorio.TrazasRepository;
import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RepositoryEventHandler(ModelosBalizas.class)
public class ModelosBalizasEventHandler {

    @Autowired
    EntityManager entMg;

    @Autowired
    HttpServletRequest req;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TrazasRepository trazasRepo;

    @HandleBeforeCreate
    public void handleModelosBalizasCreate(ModelosBalizas modelo) {
        /* Validando Autorización */
        try {
            log.info("*****HandleBeforeCreate MODELOS BALIZAS*****");
            ValidateAuthorization val = new ValidateAuthorization();
            val.setObjectMapper(objectMapper);
            val.setReq(req);
            if (!val.Validate()) {
                log.error("Fallo el Usuario Enviado no Coincide con el Autenticado ");
                throw new RuntimeException("Fallo el Usuario Enviado no Coincide con el Autenticado ");
            }
        } catch (Exception e) {
            log.error("Fallo Antes de Crear el Elemento Validando Autorización: ", e.getMessage());
            throw new RuntimeException("Fallo Antes de Crear el Elemento Validando Autorización: " + e.getMessage());
        }
    }

    @HandleAfterCreate
    public void handleModelosBalizasAfterCreate(ModelosBalizas modelo) {
        /* Validando Autorización */
        ValidateAuthorization val = new ValidateAuthorization();
        try {
            val.setObjectMapper(objectMapper);
            val.setReq(req);
            if (!val.Validate()) {
                log.error("Fallo el Usuario Enviado no Coincide con el Autenticado ");
                throw new RuntimeException("Fallo el Usuario Enviado no Coincide con el Autenticado ");
            }
        } catch (Exception e) {
            log.error("Fallo Antes de Crear el Elemento Validando Autorización: ", e.getMessage());
            throw new RuntimeException("Fallo Antes de Crear el Elemento Validando Autorización: " + e.getMessage());
        }

        try {
            String descripcionTraza = "Fue creado un nuevo modelo: " + modelo.getDescripcion();
            log.info("*****HandleAfterCreate MODELOS BALIZAS*****");
            ActualizarTraza(val, modelo.getId().intValue(), 1, 1, descripcionTraza,
                    "Fallo al Actualizar el Modelo en la Trazabilidad");

        } catch (Exception e) {
            log.error("Fallo al Actualizar el Modelo en la Trazabilidad", e.getMessage());
            throw new RuntimeException("Fallo al Actualizar el Modelo en la Trazabilidad");
        }
    }

    private void ActualizarTraza(ValidateAuthorization val, int idEntidad, int idTipoEntidad,
            int idAccion, String trazaDescripcion, String errorMessage) {
        try {
            log.info("Actualizar Traza " + trazaDescripcion);
            Trazas traza = new Trazas();
            AccionEntidad accion = new AccionEntidad();
            Usuarios usuario = new Usuarios();
            TipoEntidad entidad = new TipoEntidad();

            entidad.setId(idTipoEntidad);
            accion.setId(idAccion);
            // usuario.setId(Long.parseLong(val.getJwtObjectMap().getId()));
            usuario.setId(Long.valueOf(val.getJwtObjectMap().getId()));

            traza.setAccionEntidad(accion);
            traza.setTipoEntidad(entidad);
            traza.setUsuario(usuario);
            traza.setIdEntidad(idEntidad);
            traza.setDescripcion(trazaDescripcion);
            trazasRepo.save(traza);
        } catch (Exception e) {
            log.error(errorMessage, e.getMessage());
            throw new RuntimeException(errorMessage);
        }
    }
}
