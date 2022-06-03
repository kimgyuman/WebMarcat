package marcat.aws;

import marcat.board.dto.FileUploadDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.util.Optional;

public interface AwsS3Service {
    String upload(MultipartFile multipartFile, String dirName, int width, int height);

    String upload(File uploadFile, String dirName);

    String putS3(File uploadFile, String fileName);

    void deleteFileInS3(String fileName);

    void removeNewFile(File targetFile);

    Optional<File> convert(MultipartFile file, int width, int height);

}
