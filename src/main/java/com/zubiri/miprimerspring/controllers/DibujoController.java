package com.zubiri.miprimerspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zubiri.miprimerspring.aplicacion.AplicacionDibujo;
import com.zubiri.miprimerspring.dominio.Dibujo;

import com.zubiri.miprimerspring.persistencia.RepositorioDibujo;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestPart;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@RestController
@RequestMapping("/dibujo")
@AllArgsConstructor
@CrossOrigin
public class DibujoController {

    @Autowired
    private RepositorioDibujo repositorioDibujo;
    @Autowired
    private AplicacionDibujo aplicacionDibujo;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestPart("file") MultipartFile file) {
        try {
            Dibujo dibujo = new Dibujo(file.getOriginalFilename().split(".")[0], file);

            repositorioDibujo.save(dibujo);

            return new ResponseEntity<>("Dibujo subido correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al subir el dibujo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{param}")
    public ResponseEntity<InputStreamResource> download(@PathVariable String param) {
        try {
            Dibujo dibujo = repositorioDibujo.findByNombre(param);

            InputStream is = new FileInputStream(dibujo.getFile());

            InputStreamResource resource = new InputStreamResource(is);

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=" + dibujo.getFile().getName())
                    .body(resource); 

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listBuckets")
    public void list() {
        
        aplicacionDibujo.list();

    }

    @PostMapping("/uploadS3")
   // @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> uploadS3(//@AuthenticationPrincipal Usuario usuario,
                                            @RequestPart("file") MultipartFile file) {

        try {
            //aplicacionDibujo.uploadToS3("myfirstbuvketzubiri", usuario.getUsername()+ "/" + file.getOriginalFilename(), file);
            aplicacionDibujo.uploadToS3("myfirstbuvketzubiri", file.getOriginalFilename(), file);
            return new ResponseEntity<>("Dibujo subido correctamente", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al subir el dibujo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tests3")
    public void testS3() throws FileNotFoundException {
        aplicacionDibujo.testUploadToS3();
        aplicacionDibujo.testDownloadFromS3();
    }
}
