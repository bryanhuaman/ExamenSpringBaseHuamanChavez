package com.codigo.HuamanBryan.controller;


import com.codigo.HuamanBryan.entity.EmpresaEntity;
import com.codigo.HuamanBryan.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/empresa")
@AllArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping("/crearEmpresa")
    public ResponseEntity<EmpresaEntity> crearEmpresa(@RequestBody EmpresaEntity empresaEntity){
        return ResponseEntity.ok(empresaService.crear(empresaEntity));
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
    public ResponseEntity<EmpresaEntity> actualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaEntity empresaEntity){
        return ResponseEntity.ok(empresaService.actualizar(id,empresaEntity));
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<EmpresaEntity> borrarEmpresa(@PathVariable Long id){
        return ResponseEntity.ok(empresaService.borrar(id));
    }
}
