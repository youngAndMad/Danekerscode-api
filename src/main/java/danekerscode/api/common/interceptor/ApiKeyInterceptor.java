package danekerscode.api.common.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import danekerscode.api.domain.dto.ApiKeyDetailsDTO;
import danekerscode.api.domain.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApiKeyInterceptor implements HandlerInterceptor {

    private static final String APPLICATION_JSON = "application/json";
    private static final String API_KEY_PREFIX = "API_KEY ";

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) throws Exception {
        if (!request.getRequestURI().contains("user")) {
            var apiKey = request.getHeader("Authorization");
            ApiKeyDetailsDTO apiKeyDetails = validateApiKey(apiKey, response);

            if (apiKeyDetails == null) {
                return false;
            }

            return checkUserCredentials(apiKeyDetails, response);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private ApiKeyDetailsDTO validateApiKey(String apiKey, HttpServletResponse response) throws IOException {
        if (apiKey == null) {
            sendErrorResponse(response,  "Missing authorization header");
            return null;
        }

        if (!apiKey.startsWith(API_KEY_PREFIX)) {
            sendErrorResponse(response, "Authorization header should start with API_KEY");
            return null;
        }

        apiKey = apiKey.substring(API_KEY_PREFIX.length());

        try {
            return objectMapper.convertValue(apiKey, ApiKeyDetailsDTO.class);
        } catch (IllegalArgumentException e) {
            sendErrorResponse(response, "Invalid API key format");
            return null;
        }
    }

    private boolean checkUserCredentials(ApiKeyDetailsDTO apiKeyDetails, HttpServletResponse response) throws Exception {
        var optionalUser = userRepository.findByEmail(apiKeyDetails.email());
        return optionalUser.map(user -> true)
                .orElseGet(() -> {
                    try {
                        sendErrorResponse(response, "Invalid credentials");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return false;
                });
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType(APPLICATION_JSON);
        response.setStatus(401);
        response.getWriter().print(new ErrorMessage(message));
    }

    record ErrorMessage(String error) {
    }
}
