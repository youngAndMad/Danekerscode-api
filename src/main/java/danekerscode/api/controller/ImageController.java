package danekerscode.api.controller;

import danekerscode.api.service.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("{id}")
    void download(
            @PathVariable Long id,
            HttpServletResponse response
    ) {
        imageService.download(response, id);
    }

}
