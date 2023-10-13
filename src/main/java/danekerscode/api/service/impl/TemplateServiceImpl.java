package danekerscode.api.service.impl;

import danekerscode.api.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private final SpringTemplateEngine templateEngine;

    @Override
    public MimeMessageHelper addInlineCommonImages(MimeMessageHelper helper) {
        return null;
    }

    @Override
    public Context setVariables(Context ctx, Map<String, Object> variables) {
        ctx.setVariables(variables);
        return ctx;
    }

    @Override
    public String getTemplate(String templateName, Map<String, Object> variables) {
        var ctx = new Context(Locale.ENGLISH);
        ctx = setVariables(ctx, variables);
        return templateEngine.process(templateName,ctx);
    }

}
