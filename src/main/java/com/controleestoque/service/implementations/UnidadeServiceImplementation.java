package com.controleestoque.service.implementations;

import java.util.ArrayList;
import java.util.List;

import com.controleestoque.dto.UnidadeDTO;
import com.controleestoque.exception.ApiRequestException;
import com.controleestoque.model.Unidade;
import com.controleestoque.repository.EmpresaRepository;
import com.controleestoque.repository.UnidadeRepository;
import com.controleestoque.service.UnidadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UnidadeServiceImplementation implements UnidadeService{

    @Autowired
    UnidadeRepository unidadeRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public List<UnidadeDTO> listarUnidades() {
        List<UnidadeDTO> listaUnidadeDTOs = new ArrayList<>();
        unidadeRepository.findAll().forEach(unidade -> {
            UnidadeDTO builderDto = UnidadeDTO.builder()
                    .id(unidade.getId())
                    .nome(unidade.getNome())
                    .empresa(empresaRepository.findById(unidade.getEmpresa().getId()).get())
                    .build(); 
            listaUnidadeDTOs.add(builderDto);
        });
        return listaUnidadeDTOs;
    }

    @Override
    public UnidadeDTO listarUnidade(Long id) {
        if(unidadeRepository.existsById(id)){
            Unidade unidade = unidadeRepository.findById(id).get();
            UnidadeDTO builderDto = UnidadeDTO.builder()
                    .id(unidade.getId())
                    .nome(unidade.getNome())
                    .empresa(empresaRepository.findById(unidade.getEmpresa().getId()).get())
                    .build();
            return builderDto;
        }
        throw new ApiRequestException("Unidade inexistente!");
    }

    @Override
    public ResponseEntity<?> cadastrarUnidade(UnidadeDTO unidadeDTO) {
        if(!unidadeRepository.existsByNome(unidadeDTO.getNome().toUpperCase())){
            if(!empresaRepository.existsById(unidadeDTO.getEmpresa().getId())){
                throw new ApiRequestException("Empresa a ser vinculada inexistente!");
            }
            Unidade unidadeBuilder = Unidade.builder()
                    .nome(unidadeDTO.getNome().toUpperCase())
                    .empresa(unidadeDTO.getEmpresa())
                    .build();
            unidadeRepository.save(unidadeBuilder);
            return ResponseEntity.status(HttpStatus.OK).body("Unidade cadastrada com sucesso!");
        }
        throw new ApiRequestException("Já existe uma unidade cadastrada com este nome!");
    }

    @Override
    public ResponseEntity<?> editarUnidade(UnidadeDTO unidadeDTO) {
        if(unidadeRepository.existsById(unidadeDTO.getId())){
            this.nomeJaCadastrado(unidadeDTO.getId(), unidadeDTO.getNome().toUpperCase());
            if(!empresaRepository.existsById(unidadeDTO.getEmpresa().getId())){
                throw new ApiRequestException("Empresa a ser vinculada inexistente!");
            }
            Unidade unidadeBuilder = Unidade.builder()
                    .id(unidadeDTO.getId())
                    .nome(unidadeDTO.getNome().toUpperCase())
                    .empresa(unidadeDTO.getEmpresa())
                    .build();
            unidadeRepository.save(unidadeBuilder);
            return ResponseEntity.status(HttpStatus.OK).body("Unidade cadastrada com sucesso!");
        }
        throw new ApiRequestException("Unidade inexistente!");
    }

    @Override
    public ResponseEntity<?> deletarUnidade(Long id) {
        if(unidadeRepository.existsById(id)){
            unidadeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Unidade deletada com sucesso!");
        }
        throw new ApiRequestException("Unidade inexistente!");
    }

    //private methods
    private void nomeJaCadastrado(Long id, String nome){
        if(unidadeRepository.existsByNome(nome)){
            Unidade unidadeRequest = unidadeRepository.findById(id).get();
            Unidade unidadeNome = unidadeRepository.findByNome(nome);
            if(unidadeRequest.getId() != unidadeNome.getId()){
                throw new ApiRequestException("Unidade já utilizada!");
            }
        }
    }
    
}
