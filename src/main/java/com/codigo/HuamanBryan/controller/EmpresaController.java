package com.codigo.HuamanBryan.controller;


import com.codigo.HuamanBryan.entity.EmpresaEntity;
import com.codigo.HuamanBryan.service.EmpresaService;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
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
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping("/crearEmpresa")
    public ResponseEntity<?> crearEmpresa(@Valid @RequestBody EmpresaEntity empresaEntity){
        try {
        return ResponseEntity.ok(empresaService.crear(empresaEntity));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Revise que los datos cumplan con las validaciones y cantidad de caracteres\"}");
        }
    }

    @GetMapping("/buscarxId/{id}")
    public ResponseEntity<Optional<EmpresaEntity>> buscarEmpresaxId(@PathVariable Long id){
        return ResponseEntity.ok(empresaService.buscarxId(id));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<EmpresaEntity>> buscarTodos(){
        return ResponseEntity.ok(empresaService.buscarAll());
    }

    @PutMapping("/actualizarEmpresa/{id}")
    public ResponseEntity<?> actualizarEmpresa(@PathVariable Long id,@Valid @RequestBody EmpresaEntity empresaEntity){
        try {
            return ResponseEntity.ok(empresaService.actualizar(id,empresaEntity));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Revise que los datos cumplan con las validaciones y cantidad de caracteres\"}");
        }

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarEmpresa(@PathVariable Long id){
    try {
        return ResponseEntity.ok(empresaService.borrar(id));
    }catch (Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Ocurrio un error\"}");
    }
    }
}
