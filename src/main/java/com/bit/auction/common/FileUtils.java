package com.bit.auction.common;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.bit.auction.configuration.NaverConfiguration;
import com.bit.auction.goods.dto.AuctionImgDTO;
import com.bit.auction.admin.dto.FaqAttachedFileDTO;
import com.bit.auction.user.dto.InquiryFileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class FileUtils {
    private final AmazonS3 s3;
    @Value("${ncp.accessKey}")
    private String accessKey;

    @Value("${ncp.bucket}")
    private String bucketName;

    @Value("${ncp.endPoint}")
    private String storageUrl;


    public FileUtils(NaverConfiguration naverConfiguration) {
        s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        naverConfiguration.getEndPoint(), naverConfiguration.getRegionName()
                ))
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(
                                naverConfiguration.getAccessKey(), naverConfiguration.getSecretKey()
                        )
                ))
                .build();
    }

    public AuctionImgDTO parseFileInfo(MultipartFile multipartFile, String directory, String representativeImgName) {
        AuctionImgDTO auctionImgDTO = new AuctionImgDTO();

        String auctionImgOrigin = multipartFile.getOriginalFilename();

        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmsss");
        Date nowDate = new Date();

        String nowDateStr = formater.format(nowDate);

        UUID uuid = UUID.randomUUID();

        String imgName = nowDateStr + "_" + uuid.toString() + "_" + auctionImgOrigin;

        String imgPath = directory + imgName;

        try (InputStream fileIputStream = multipartFile.getInputStream()) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName,
                    directory + imgName,
                    fileIputStream,
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            s3.putObject(putObjectRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        auctionImgDTO.setFileName(imgName);
        auctionImgDTO.setFileUrl("https://kr.object.ncloudstorage.com/"+bucketName+"/" + imgPath);
        if (auctionImgOrigin.equals(representativeImgName)) {
            auctionImgDTO.setRepresentative(true);
        }

        return auctionImgDTO;
    }

    public void deleteObject(String url) {
        s3.deleteObject(new DeleteObjectRequest(bucketName, url));
    }


    //*********************************************************************************************************************************************
    // 김종범 꺼. (Inquiry File)

    public InquiryFileDTO parseFileInfo(MultipartFile multipartFile, String directory) {
        String bucketName = "bitcamp-bucket-22";

        InquiryFileDTO inquiryFileDTO = new InquiryFileDTO();

        String inquiryFileOrigin = multipartFile.getOriginalFilename();

        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmsss");
        Date nowDate = new Date();

        String nowDateStr = formater.format(nowDate);

        UUID uuid = UUID.randomUUID();

        String inquiryFileName = nowDateStr + "_" + uuid.toString() + "_" + inquiryFileOrigin;

        String inquiryFilePath = directory;

        try (InputStream fileInputStream = multipartFile.getInputStream()) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName,
                    directory + inquiryFileName,
                    fileInputStream,
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            s3.putObject(putObjectRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        File checkImage = new File(inquiryFileOrigin);

        String type = "";

        try {
            type = Files.probeContentType(checkImage.toPath());
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }

        if (type != "") {
            if (type.startsWith("image")) {
                inquiryFileDTO.setInquiryFileCate("image");
            } else {
                inquiryFileDTO.setInquiryFileCate("etc");
            }
        } else {
            inquiryFileDTO.setInquiryFileCate("etc");
        }

        inquiryFileDTO.setInquiryFileName(inquiryFileName);
        inquiryFileDTO.setInquiryFilePath(inquiryFilePath);
        inquiryFileDTO.setInquiryFileOrigin(inquiryFileOrigin);

        return inquiryFileDTO;
    }


    //*********************************************************************************************************************************************


    public FaqAttachedFileDTO parseFaqAttachedFileInfo(MultipartFile multipartFile, String directory) {

        FaqAttachedFileDTO faqAttachedFileDTO = new FaqAttachedFileDTO();

        String fileOrigin = multipartFile.getOriginalFilename();

        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmsss");
        Date nowDate = new Date();

        String nowDateStr = formater.format(nowDate);

        UUID uuid = UUID.randomUUID();

        String fileName = nowDateStr + "_" + uuid.toString() + "_" + fileOrigin;

        String filePath = storageUrl + "/" + bucketName + "/" + directory + fileName;

        try(InputStream fileInputStream = multipartFile.getInputStream()) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    bucketName,
                    directory + fileName,
                    fileInputStream,
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            s3.putObject(putObjectRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        File checkImage = new File(fileOrigin);
        String type = "";

        try {
            type = Files.probeContentType(checkImage.toPath());
        } catch(IOException ie) {
            System.out.println(ie.getMessage());
        }

        if(type != "") {
            if(type.startsWith("image")) {
                faqAttachedFileDTO.setFileType("img");
            } else {
                faqAttachedFileDTO.setFileType("doc");
            }
        } else {
            faqAttachedFileDTO.setFileType("etc");
        }

        faqAttachedFileDTO.setFileName(fileOrigin);
        faqAttachedFileDTO.setFilePath(filePath);

        return faqAttachedFileDTO;
    }

    public ResponseEntity<byte[]> getObject(String storedFileName) throws IOException{
        byte[] bytes = null;
        storedFileName = storedFileName.replace(storageUrl, "");
        S3Object s3Object = s3.getObject(new GetObjectRequest(bucketName, storedFileName));
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        bytes = IOUtils.toByteArray(inputStream);

        String fileName = URLEncoder.encode(storedFileName, "UTF-8").replaceAll("\\+", "%20");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }


}
