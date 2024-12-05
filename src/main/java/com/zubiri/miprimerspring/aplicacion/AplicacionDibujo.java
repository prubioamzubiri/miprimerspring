package com.zubiri.miprimerspring.aplicacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;


import org.springframework.core.io.InputStreamResource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import com.zubiri.miprimerspring.dominio.Dibujo;
import com.zubiri.miprimerspring.persistencia.RepositorioDibujo;


@Component
public class AplicacionDibujo{

    private RepositorioDibujo repositorioDibujo;


    public void upload(MultipartFile file) {

        
            Dibujo dibujo;
            try {
                dibujo = new Dibujo(file.getOriginalFilename().split(".")[0], file);
                repositorioDibujo.save(dibujo);
            } catch (IllegalStateException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            


    }

    public void download(String param) {
        try {
            Dibujo dibujo = repositorioDibujo.findByNombre(param);

            InputStream is = new FileInputStream(dibujo.getFile());

            InputStreamResource resource = new InputStreamResource(is);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
        public void list() {

        Region region = Region.US_EAST_1;
        S3Client s3 = S3Client.builder()
            .region(region)
            .build();

        listBuckets(s3);
    }

    public void listBuckets(S3Client s3) {
        try {
            ListBucketsResponse response = s3.listBuckets();
            List<Bucket> bucketList = response.buckets();
            bucketList.forEach(bucket -> {
                System.out.println("Bucket Name: " + bucket.name());
            });

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    

    public boolean uploadToS3 (String bucketName, String key, MultipartFile file) {

        Region region = Region.US_EAST_1;
        S3Client s3 = S3Client.builder()
            .region(region)
            .build();

        Path filePath = null;
        try {


            filePath = Path.of("./"+ file.getOriginalFilename());
            file.transferTo(filePath);
        } catch (IllegalStateException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

        s3.putObject(PutObjectRequest.builder().bucket(bucketName).key(key).build(), filePath);

        try {
            java.nio.file.Files.delete(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    public InputStreamResource downloadFromS3(String bucketName, String key) throws FileNotFoundException {
        
        Region region = Region.US_EAST_1;
        S3Client s3 = S3Client.builder()
            .region(region)
            .build();

        String filepath = "./auxiliar" + key;

        s3.getObject(GetObjectRequest.builder().bucket(bucketName).key(key).build(), Path.of(filepath));

        File file = new File(filepath);

        InputStream is = new FileInputStream(file);

        InputStreamResource resource = new InputStreamResource(is);

        return resource;

    }

    public boolean testUploadToS3 () {

        String bucketName = "myfirstbucketzubiri";
        String key = "prueba.txt";

        Region region = Region.US_EAST_1;
        S3Client s3 = S3Client.builder()
            .region(region)
            .build();

        String path = "/home/pablo/source/github/miprimerspring/prueba.txt";
        Path filePath = null;
        try {
            filePath = Path.of(path);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        s3.putObject(PutObjectRequest.builder().bucket(bucketName).key(key).build(), filePath);
        
        return true;
    }

    public void testDownloadFromS3() throws FileNotFoundException {

        String bucketName = "myfirstbucketzubiri";
        String key = "prueba.txt";
        
        Region region = Region.US_EAST_1;
        S3Client s3 = S3Client.builder()
            .region(region)
            .build();

        String filepath = "./auxiliar" + key;

        s3.getObject(GetObjectRequest.builder().bucket(bucketName).key(key).build(), Path.of(filepath));

    }

    
}
