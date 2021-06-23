package com.controleestoque.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface NotaFiscalService {
    
    ResponseEntity<?> uploadNotaFiscal(MultipartFile notaFiscal, Long idEntrada);
    ResponseEntity<?> downloadNotaFiscal(Long idEntrada);
    ResponseEntity<?> deleteNotaFiscal(Long idEntrada);
}
