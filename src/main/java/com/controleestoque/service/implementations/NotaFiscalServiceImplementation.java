package com.controleestoque.service.implementations;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import com.controleestoque.exception.ApiRequestException;
import com.controleestoque.model.Entrada;
import com.controleestoque.repository.EntradaRepository;
import com.controleestoque.service.NotaFiscalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class NotaFiscalServiceImplementation implements NotaFiscalService{

    private static final String dir = "uploads/";
    private static final String extensao = ".pdf";

    @Autowired
    EntradaRepository entradaRepository;

    @Override
    public ResponseEntity<?> uploadNotaFiscal(MultipartFile notaFiscal, Long idEntrada) {
        this.existsEntrada(idEntrada);
        Path path = Paths.get(dir+"entrada_"+idEntrada.toString()+extensao);
        try{
            Files.copy(notaFiscal.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            this.saveNotaFiscalEntrada(idEntrada);
        }catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body("Nota fiscal anexada com sucesso!");
    }

    @Override
    public ResponseEntity<?> downloadNotaFiscal(Long idEntrada) {
        this.existsEntrada(idEntrada);
        Path path = Paths.get(dir+"entrada_"+idEntrada.toString()+extensao);
        Resource resource = null;
        try{
            resource = new UrlResource(path.toUri());
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Override
    public ResponseEntity<?> deleteNotaFiscal(Long idEntrada) {
        File deleteFile = new File(dir+"entrada_"+idEntrada+extensao);
        if(deleteFile.exists()){
            if(deleteFile.delete()){
                return ResponseEntity.status(HttpStatus.OK).body("Arquivo deletado com sucesso");
            }
            throw new ApiRequestException("Não foi possivel deletar o arquivo!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Arquivo não vinculado!");
    }

    //private methods
    private void existsEntrada(Long id){
        if(!entradaRepository.existsById(id)){
            throw new ApiRequestException("Entrada inexistente!");
        }
    }

    private void saveNotaFiscalEntrada(Long id){
        Entrada entrada = entradaRepository.findById(id).get();
        String path = ServletUriComponentsBuilder.fromCurrentContextPath()
			.path("/nota-fiscal/download/")
			.path(id.toString())
			.toUriString();
        entrada.setNotaFiscal(path);
        entradaRepository.save(entrada);
    }

}
