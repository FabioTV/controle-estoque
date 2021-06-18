package com.controleestoque.service.implementations;

import java.util.ArrayList;
import java.util.List;

import com.controleestoque.dto.MarcaDTO;
import com.controleestoque.exception.ApiRequestException;
import com.controleestoque.model.Marca;
import com.controleestoque.repository.MarcaRepository;
import com.controleestoque.service.MarcaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImplementation implements MarcaService{

    @Autowired
    MarcaRepository marcaRepository;

    @Override
    public List<MarcaDTO> listarMarcas() {
        List<MarcaDTO> listaMarcasDtos = new ArrayList<>();
        marcaRepository.findAll().forEach(marca -> {
            MarcaDTO builderDto = MarcaDTO.builder()
                    .id(marca.getId())
                    .nome(marca.getNome())
                    .build();
            listaMarcasDtos.add(builderDto);
        });
        return listaMarcasDtos;
    }

    @Override
    public MarcaDTO listarMarca(Long id) {
        if(marcaRepository.existsById(id)){
            Marca marca = marcaRepository.findById(id).get();
            MarcaDTO builderDto = MarcaDTO.builder()
                    .id(marca.getId())
                    .nome(marca.getNome())
                    .produtos(marca.getProdutos())
                    .build();
            return builderDto;
        }
        throw new ApiRequestException("Marca não existe!");
    }

    @Override
    public ResponseEntity<?> cadastrarMarca(MarcaDTO marcaDTO) {
        if(!marcaRepository.existsByNome(marcaDTO.getNome())){
            Marca builder = Marca.builder()
                    .id(marcaDTO.getId())
                    .nome(marcaDTO.getNome().toUpperCase())
                    .build();
            marcaRepository.save(builder);
            return ResponseEntity.status(HttpStatus.OK).body("Cadastrada com sucesso!");
        }
        throw new ApiRequestException("Marca já cadastrada!");
    }

    @Override
    public ResponseEntity<?> editarMarca(MarcaDTO marcaDTO) {
        if(marcaRepository.existsById(marcaDTO.getId())){
            this.nomeJaCadastrado(marcaDTO.getId(), marcaDTO.getNome());
            Marca marca = Marca.builder()
                    .id(marcaDTO.getId())
                    .nome(marcaDTO.getNome().toUpperCase())
                    .build();
            marcaRepository.save(marca);
            return ResponseEntity.status(HttpStatus.OK).body("Editada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Marca não existe!");
    }

    @Override
    public ResponseEntity<?> deletarMarca(Long id) {
        if(marcaRepository.existsById(id)){
            marcaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deletada com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Marca não existe!");
    }
    
    //private methods
    private void nomeJaCadastrado(Long id, String nome){
        if(marcaRepository.existsByNome(nome)){
            Marca marcaRequest = marcaRepository.findById(id).get();
            Marca marcaNome = marcaRepository.findByNome(nome);
            if(marcaRequest.getId() != marcaNome.getId()){
                throw new ApiRequestException("Marca já utilizada!");
            }
        }
    }
}
