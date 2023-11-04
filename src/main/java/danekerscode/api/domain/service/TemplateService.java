package danekerscode.api.domain.service;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;

import java.util.Map;
import java.util.Objects;

public interface TemplateService {

    MimeMessageHelper addInlineCommonImages(MimeMessageHelper helper);

    Context setVariables(Context ctx, Map<String, Object> variables);

    String getTemplate(String templateName, Map<String,Object> variables);

}
