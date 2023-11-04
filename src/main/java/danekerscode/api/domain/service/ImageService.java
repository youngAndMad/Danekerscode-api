package danekerscode.api.domain.service;

import danekerscode.api.domain.model.Image;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String upload(MultipartFile file);

    void delete(Long id);

    void download(HttpServletResponse response, Long id);

    Image findById(Long id);
}
