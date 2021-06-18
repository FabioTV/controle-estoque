package com.controleestoque.service.implementations;

import java.util.ArrayList;
import java.util.List;

import com.controleestoque.dto.EmpresaDTO;
import com.controleestoque.exception.ApiRequestException;
import com.controleestoque.model.CNPJ;
import com.controleestoque.model.Empresa;
import com.controleestoque.repository.EmpresaRepository;
import com.controleestoque.service.EmpresaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImplementation implements EmpresaService{

    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public List<EmpresaDTO> listarEmpresas() {
        List<EmpresaDTO> empresasDto = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa ->{
            EmpresaDTO buildDto = EmpresaDTO.builder()
                    .id(empresa.getId())
                    .cnpj(empresa.getCnpj())
                    .razaoSocial(empresa.getRazaoSocial())
                    .unidades(empresa.getUnidades())
                    .build(); 
            empresasDto.add(buildDto);
        });
        return empresasDto;
    }

    @Override
    public EmpresaDTO listarEmpresa(Long id) {
        if(empresaRepository.existsById(id)){
            Empresa empresa = empresaRepository.findById(id).get();
            EmpresaDTO buildDto = EmpresaDTO.builder()
                    .id(empresa.getId())
                    .cnpj(empresa.getCnpj())
                    .razaoSocial(empresa.getRazaoSocial())
                    .unidades(empresa.getUnidades())
                    .build(); 
            return buildDto;
        }
        throw new ApiRequestException("Empresa inexistente!");
    }

    @Override
    public ResponseEntity<?> cadastrarEmpresa(EmpresaDTO empresaDTO) {
        CNPJ cnpj = new CNPJ(empresaDTO.getCnpj());
        if(cnpj.isCNPJ()){
            if(!empresaRepository.existsByCnpj(cnpj.getCNPJ(true)) && !empresaRepository.existsByRazaoSocial(empresaDTO.getRazaoSocial())){
                Empresa buildEmpresa = Empresa.builder()
                        .cnpj(cnpj.getCNPJ(true))
                        .razaoSocial(empresaDTO.getRazaoSocial().toUpperCase())
                        .build();
                empresaRepository.save(buildEmpresa);
                return ResponseEntity.status(HttpStatus.OK).body("Empresa cadastrada com sucesso!");
            }
            throw new ApiRequestException("Empresa existente!");
        }
        throw new ApiRequestException("CNPJ inválido");
    }

    @Override
    public ResponseEntity<?> editarEmpresa(EmpresaDTO empresaDTO) {
        CNPJ cnpj = new CNPJ(empresaDTO.getCnpj());
        if(cnpj.isCNPJ()){
            if(empresaRepository.existsById(empresaDTO.getId())){
                this.cnpjJaCadastradoEmOutroEmpresa(empresaDTO.getId(), cnpj);
                this.razaoSocialJaCadastrado(empresaDTO.getId(), empresaDTO.getRazaoSocial());
                Empresa buildEmpresa = Empresa.builder()
                        .id(empresaDTO.getId())
                        .cnpj(cnpj.getCNPJ(true))
                        .razaoSocial(empresaDTO.getRazaoSocial().toUpperCase())
                        .build();
                empresaRepository.save(buildEmpresa);
                return ResponseEntity.status(HttpStatus.OK).body("Empresa editada com sucesso!");
            }
            throw new ApiRequestException("Empresa inexistente!");
        }
        throw new ApiRequestException("CNPJ inválido");
    }

    @Override
    public ResponseEntity<?> deletarEmpresa(Long id) {
        if(empresaRepository.existsById(id)){
            empresaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Empresa deletada com sucesso!");
        }
        throw new ApiRequestException("Empresa inexistente!");
    }
    
    //private methods
    private void cnpjJaCadastradoEmOutroEmpresa(Long id, CNPJ cnpj){
        if(empresaRepository.existsByCnpj(cnpj.getCNPJ(true))){
            Empresa empresaRequest = empresaRepository.findById(id).get();
            Empresa empresaCnpj = empresaRepository.findByCnpj(cnpj.getCNPJ(true));
            if(empresaRequest.getId() != empresaCnpj.getId()){
                throw new ApiRequestException("CNPJ já utilizado em outra Empresa!");
            }
        }
    }

    private void razaoSocialJaCadastrado(Long id, String razaoSocial){
        if(empresaRepository.existsByRazaoSocial(razaoSocial)){
            Empresa empresaRequest = empresaRepository.findById(id).get();
            Empresa empresaRazaoSocial = empresaRepository.findByRazaoSocial(razaoSocial);
            if(empresaRequest.getId() != empresaRazaoSocial.getId()){
                throw new ApiRequestException("Razão social já utilizada!");
            }
        }
    }
}
