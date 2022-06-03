package marcat.aws;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marcat.board.dto.FileUploadDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AwsS3ServiceImpl implements AwsS3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("#{aws['cloud.aws.s3.bucket']}")
    public String bucket;  // S3 버킷 이름
    @Value("#{aws['cloud.aws.s3.bucket.url']}")
    public String bucketUrl;

    //파일로 변환 + 로컬에 저장
    @Override
    public String upload(MultipartFile multipartFile, String dirName, int width, int height) {
        File uploadFile = convert(multipartFile, width, height)  // 파일 변환할 수 없으면 에러
                .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));

        return upload(uploadFile, dirName);
    }

    // 경로 이름 생성 + S3로 업로드 + 로컬 파일 삭제

    @Override
    public String upload(File uploadFile, String dirName) {

        String fileName = dirName + "/" + UUID.randomUUID() + uploadFile.getName();// S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드

//        removeNewFile(uploadFile);//로컬 파일 삭제
        return uploadImageUrl;
    }

//     S3로 업로드
    @Override
    public String putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(bucket, fileName, uploadFile);

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    //s3 단일파일 삭제
    @Override
    public void deleteFileInS3(String fileName) {
        String replaceFileName = null;
        replaceFileName = fileName.replace("https://animallover.s3.ap-northeast-2.amazonaws.com/", "");
        amazonS3Client.deleteObject(bucket, replaceFileName);
    }


    //로컬에 파일 삭제
    @Override
    public void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("File delete fail");
    }

    // 로컬에 변환된 파일 업로드
    @Override
    public Optional<File> convert(MultipartFile file, int width, int height) {
        File convertFile = null;
        try {
            convertFile = resizeImage(file, width, height);

            return Optional.of(convertFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    //이미지 리사이징 메서드
    public File resizeImage(MultipartFile file, int targetWidth, int targetHeight) throws IOException {

        //1. MltipartFile 에서 BufferedImage로 변환
        BufferedImage originalImage = ImageIO.read(file.getInputStream());


        //2. Graphics2D 로 리사이징
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();


        //3. BufferedImage 로 File 생성
        File outputfile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
        try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                String type = file.getContentType().substring(file.getContentType().indexOf("/")+1);
                ImageIO.write(resizedImage, type, bos);

                InputStream inputStream = new ByteArrayInputStream(bos.toByteArray());

                Files.copy(inputStream, outputfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                return outputfile;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
