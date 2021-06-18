package com.controleestoque.service.implementations;

import java.util.ArrayList;
import java.util.List;

import com.controleestoque.dto.FornecedorDTO;
import com.controleestoque.exception.ApiRequestException;
import com.controleestoque.model.CNPJ;
import com.controleestoque.model.Fornecedor;
import com.controleestoque.repository.FornecedorRepository;
import com.controleestoque.service.FornecedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FornecedorServiceImplementation implements FornecedorService{

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Override
    public List<FornecedorDTO> listarFornecedores() {
        List<FornecedorDTO> fornecedorDTOs = new ArrayList<>();
        fornecedorRepository.findAll().forEach(fornecedor -> {
            FornecedorDTO builderDto = FornecedorDTO.builder()
                    .id(fornecedor.getId())
                    .nome(fornecedor.getNome())
                    .cnpj(fornecedor.getCnpj())
                    .email(fornecedor.getEmail())
                    .telefone(fornecedor.getTelefone())
                    .build();
            fornecedorDTOs.add(builderDto);
                });
        return fornecedorDTOs;
    }

    @Override
    public FornecedorDTO listarFornecedor(Long id) {
        if(fornecedorRepository.existsById(id)){
            Fornecedor fornecedor = fornecedorRepository.findById(id).get();
            FornecedorDTO builderDto = FornecedorDTO.builder()
                    .id(fornecedor.getId())
                    .nome(fornecedor.getNome())
                    .cnpj(fornecedor.getCnpj())
                    .email(fornecedor.getEmail())
                    .telefone(fornecedor.getTelefone())
                    .build();
            return builderDto;
        }
        throw new ApiRequestException("Fornecedor não existe!");
    }

    @Override
    public ResponseEntity<?> cadastrarFornecedor(FornecedorDTO fornecedorDTO) {
        CNPJ cnpj = new CNPJ(fornecedorDTO.getCnpj());
        if (cnpj.isCNPJ()){
            if(!fornecedorRepository.existsByCnpj(fornecedorDTO.getCnpj())){
                Fornecedor fornecedor = Fornecedor.builder()
                    .email(fornecedorDTO.getEmail())
                    .nome(fornecedorDTO.getNome())
                    .cnpj(cnpj.getCNPJ(true))
                    .telefone(fornecedorDTO.getTelefone())
                    .build();
                fornecedorRepository.save(fornecedor);
                return ResponseEntity.status(HttpStatus.OK).body("Cadastrado com Sucesso!");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Fornecedor já cadastrado!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("CNPJ inválido!");
    }

    @Override
    public ResponseEntity<?> editarFornecedor(FornecedorDTO fornecedorDTO) {
        CNPJ cnpj = new CNPJ(fornecedorDTO.getCnpj());
        if(cnpj.isCNPJ()){
            if(fornecedorRepository.existsById(fornecedorDTO.getId())){
                this.cnpjJaCadastradoEmOutroFornecedor(fornecedorDTO.getId(), cnpj);
                Fornecedor fornecedor = Fornecedor.builder()
                        .id(fornecedorDTO.getId())
                        .email(fornecedorDTO.getEmail())
                        .nome(fornecedorDTO.getNome())
                        .cnpj(cnpj.getCNPJ(true))
                        .telefone(fornecedorDTO.getTelefone())
                        .build();
                    fornecedorRepository.save(fornecedor);
                return ResponseEntity.status(HttpStatus.OK).body("Editado com sucesso!");
            }
            throw new ApiRequestException("Fornecedor não existe!");
        }
        throw new ApiRequestException("CNPJ inválido");
    }

    @Override
    public ResponseEntity<?> deletarFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Fornecedor deletado");
    }

    //private methods
    private void cnpjJaCadastradoEmOutroFornecedor(Long id, CNPJ cnpj){
        if(fornecedorRepository.existsByCnpj(cnpj.getCNPJ(true))){
            Fornecedor fornecedorRequest = fornecedorRepository.findById(id).get();
            Fornecedor fornecedorCnpj = fornecedorRepository.findByCnpj(cnpj.getCNPJ(true));
            if(fornecedorRequest.getId() != fornecedorCnpj.getId()){
                throw new ApiRequestException("CNPJ já utilizado em outro fornecedor!");
            }
        }
    }
}
