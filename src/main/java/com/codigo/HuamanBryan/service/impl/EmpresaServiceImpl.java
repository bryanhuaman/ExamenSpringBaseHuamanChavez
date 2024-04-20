package com.codigo.HuamanBryan.service.impl;

import com.codigo.HuamanBryan.constants.Constans;
import com.codigo.HuamanBryan.dao.EmpresaRepository;
import com.codigo.HuamanBryan.entity.EmpresaEntity;
import com.codigo.HuamanBryan.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Override
    public EmpresaEntity crear(EmpresaEntity empresaEntity) {
        empresaEntity.setEstado(Constans.ESTADO);
        empresaEntity.setUsuaCrea(Constans.USU);
        empresaEntity.setDateCreate(getTimeStamp());
        return empresaRepository.save(empresaEntity);
    }

    @Override
    public Optional<EmpresaEntity> buscarxId(Long id) {
        return empresaRepository.findById(id);
    }

    @Override
    public List<EmpresaEntity> buscarAll() {
        return empresaRepository.findAll();
    }

    @Override
    public EmpresaEntity actualizar(Long id, EmpresaEntity empresaEntity) {
        Optional<EmpresaEntity> empresarecuperada = empresaRepository.findById(id);
        if(empresarecuperada.isPresent() && empresarecuperada.get().getEstado() == 1){
            return empresaRepository.save(getUpdate(empresarecuperada.get(),empresaEntity));
        }
        return null;
    }

    private EmpresaEntity getUpdate(EmpresaEntity empresarecuperada, EmpresaEntity empresarequest){
        empresarecuperada.setCondicion(empresarequest.getCondicion());
        empresarecuperada.setDireccion(empresarequest.getDireccion());
        empresarecuperada.setDepartamento(empresarequest.getDepartamento());
        empresarecuperada.setDistrito(empresarequest.getDistrito());
        empresarecuperada.setProvincia(empresarequest.getProvincia());
        empresarecuperada.setTipoDocumento(empresarequest.getTipoDocumento());
        empresarecuperada.setRazonSocial(empresarequest.getRazonSocial());
        empresarecuperada.setNumeroDocumento(empresarequest.getNumeroDocumento());
        empresarecuperada.setEsAgenteRetencion(empresarequest.isEsAgenteRetencion());
        empresarecuperada.setUsuaModif(Constans.USU);
        empresarecuperada.setDateModif(getTimeStamp());

        return empresarecuperada;

    }

    @Override
    public EmpresaEntity borrar(Long id) {
        Optional<EmpresaEntity> empresaRecuperada = empresaRepository.findById(id);

        if(empresaRecuperada.isPresent()){
            empresaRecuperada.get().setEstado(0);
            empresaRecuperada.get().setUsuaDelet(Constans.USU);
            empresaRecuperada.get().setDateDelet(getTimeStamp());
            return empresaRepository.save(empresaRecuperada.get());
        }
        return null;
    }

    private Timestamp getTimeStamp(){
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
