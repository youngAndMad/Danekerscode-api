package danekerscode.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String upload(MultipartFile file);

    void delete();
}
