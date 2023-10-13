package danekerscode.api.service;

import danekerscode.api.model.Image;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String upload(MultipartFile file);

    void delete(Long id);

    void download(HttpServletResponse response, Long id);

    Image findById(Long id);
}
