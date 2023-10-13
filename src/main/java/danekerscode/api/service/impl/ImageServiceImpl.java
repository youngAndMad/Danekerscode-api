package danekerscode.api.service.impl;

import danekerscode.api.exception.EntityNotFoundException;
import danekerscode.api.model.Image;
import danekerscode.api.repository.ImageRepository;
import danekerscode.api.service.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public String upload(MultipartFile file) {
        var image = new Image();
        try {
            image.setBase64Data(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        image.setName(file.getName());
        return convertToDownloadUrl(imageRepository.save(image));
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public void download(HttpServletResponse response, Long id) {
        var image = this.findById(id);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + image.getName() + "\"");

        var fileBytes = Base64.getDecoder().decode(image.getBase64Data());

        try (
                var outputStream = response.getOutputStream()
        ) {
            outputStream.write(fileBytes);
            outputStream.flush();
        } catch (Exception e) {
            log.error("error in image download, {}", e.getMessage());
        }
    }

    @Override
    public Image findById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Image.class, id));
    }

    private String convertToDownloadUrl(Image image) {
        return null; // TODO: 10/13/2023
    }
}
