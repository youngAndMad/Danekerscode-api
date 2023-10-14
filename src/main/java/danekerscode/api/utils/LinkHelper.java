package danekerscode.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LinkHelper {

    @Value("${spring.application.image-download-url-prefix}")
    private String imageDownloadUrlPrefix;

    public String getImageDownloadLink(Long imageId) {
        var template = imageDownloadUrlPrefix.concat("%d");
        return template.formatted(imageId);
    }

}
