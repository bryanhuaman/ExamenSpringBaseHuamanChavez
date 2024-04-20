package com.codigo.HuamanBryan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


import java.sql.Timestamp;

@Entity
@Table(name = "empresa")
@Getter
@Setter
public class EmpresaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "razon_social")

    private String razonSocial;

    @Column(name = "tipo_documento", length = 1)
    @NotBlank
    @Size(max = 1)
    private String tipoDocumento;

    @Column(name = "numero_documento", unique = true, length = 11)
    @NotBlank
    @Size(min = 8, max = 11)
    private String numeroDocumento;

    @Column(name = "condicion", length = 10)
    @NotBlank
    private String condicion;

    @Column(name = "direccion")
    @NotBlank
    private String direccion;

    @Column(name = "distrito")
    @NotBlank
    private String distrito;

    @Column(name = "provincia")
    @NotBlank
    private String provincia;

    @Column(name = "departamento")
    @NotBlank
    private String departamento;

    @Column(name = "es_agente_retencion")
    private boolean esAgenteRetencion;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "usua_crea", length = 50)
    private String usuaCrea;

    @Column(name = "date_create")
    private Timestamp dateCreate;

    @Column(name = "usua_modif")
    private String usuaModif;

    @Column(name = "date_modif")
    private Timestamp dateModif;

    @Column(name = "usua_delet")
    private String usuaDelet;

    @Column(name = "date_delet")
    private Timestamp dateDelet;




}
