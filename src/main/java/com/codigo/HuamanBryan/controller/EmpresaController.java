package com.codigo.HuamanBryan.controller;


import com.codigo.HuamanBryan.entity.EmpresaEntity;
import com.codigo.HuamanBryan.service.EmpresaService;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/empresa")
@AllArgsConstructor
@Tag(
        name = "API PARA EL MANTENIMIENTO DE EMPRESA",
        description = "Esta api contiene todos los end poins para realizar el mantenimiento de la entidad Empresa"
)
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping("/crearEmpresa")
    @Operation(summary = "Crear una nueva Empresa",
            description = "Para usar enPoint debes enviar un objeto Empresa, lo cual se va a guardar en base de datos previa validacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa creada exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Bad request no se crea Empresa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Exception.class))
                    })
    })
    public ResponseEntity<?> crearEmpresa(@Valid @RequestBody EmpresaEntity empresaEntity){
        try {
        return ResponseEntity.ok(empresaService.crear(empresaEntity));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Revise que los datos cumplan con las validaciones y cantidad de caracteres\"}");
        }
    }

    @GetMapping("/buscarxId/{id}")
    @Operation(summary = "buscar una Empresa",
            description = "Para usar enPoint debes enviar un id de una Empresa, lo cual se va a buscar en base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa buscada con exito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))
                    })
    })
    public ResponseEntity<Optional<EmpresaEntity>> buscarEmpresaxId(@Parameter(description = "ID de la Empresa a buscar", required = true) @PathVariable Long id){
        return ResponseEntity.ok(empresaService.buscarxId(id));
    }

    @GetMapping("/buscarTodos")
    @Operation(summary = "Listar todas las Empresas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresas buscadas con exito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = EmpresaEntity.class))
                    })
    })
    public ResponseEntity<List<EmpresaEntity>> buscarTodos(){
        return ResponseEntity.ok(empresaService.buscarAll());
    }

    @PutMapping("/actualizarEmpresa/{id}")
    @Operation(summary = "Actualizar datos de Empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa actualizada exitosamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Bad request no se actualizo la Empresa por la validacion de campos",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Exception.class))
                    })
    })
    public ResponseEntity<?> actualizarEmpresa(@Parameter(description = "ID de la Empresa a buscar para actualizar datos", required = true) @PathVariable Long id,@Valid @RequestBody EmpresaEntity empresaEntity){
        try {
            EmpresaEntity empresaEntity1 = empresaService.actualizar(id,empresaEntity);
            return ResponseEntity.ok(empresaEntity1);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Revise que los datos cumplan con las validaciones y cantidad de caracteres\"}");
        }

    }

    @DeleteMapping("/borrar/{id}")
    @Operation(summary = "Eliminar una Empresa",
            description = "Para usar enPoint debes enviar un id de una Empresa, lo cual va a cambiar el estado de esta en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa eliminada con exito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))
                    })
    })
    public ResponseEntity<?> borrarEmpresa(@Parameter(description = "ID de la Empresa para proceder a eliminar", required = true) @PathVariable Long id){
    try {
        return ResponseEntity.ok(empresaService.borrar(id));
    }catch (Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Ocurrio un error\"}");
    }
    }
}
