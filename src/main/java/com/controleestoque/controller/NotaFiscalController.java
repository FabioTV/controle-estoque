package com.controleestoque.controller;


import com.controleestoque.service.NotaFiscalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/nota-fiscal")
public class NotaFiscalController {

	@Autowired
	NotaFiscalService notaFiscalService;
    
	@GetMapping("/download/{id}")
    public ResponseEntity<?> downloadNotaFiscal(@PathVariable(value = "id") Long id){
        return notaFiscalService.downloadNotaFiscal(id);
    }

    @PostMapping("/{id}")
	public ResponseEntity<?> uploadNotaFiscal(@RequestParam("file") MultipartFile notaFiscal, @PathVariable(value = "id") Long id) {
		return notaFiscalService.uploadNotaFiscal(notaFiscal, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteNotaFiscal(@PathVariable(value = "id") Long id){
		return notaFiscalService.deleteNotaFiscal(id);
	}


}
