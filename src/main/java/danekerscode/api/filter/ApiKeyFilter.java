package danekerscode.api.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import danekerscode.api.repository.UserRepository;
import danekerscode.api.service.AESService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

import static danekerscode.api.config.SecurityConfig.insecureEndpoints;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final AESService aesService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        var requestUrl = request.getRequestURI();

        if (
                Arrays.stream(insecureEndpoints)
                        .anyMatch(endpoint -> endpoint.endsWith(requestUrl))
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        var emailFromHeader = request.getHeader("email");
        var apiKeyFromHeader = request.getHeader("api_key");

        if (emailFromHeader == null || apiKeyFromHeader == null) {
            var sb = new StringBuilder();

            if (emailFromHeader == null) {
                sb.append("Missing email header. ");
            }

            if (apiKeyFromHeader == null) {
                sb.append("Missing api key header");
            }

            response.setContentType("application/json");
            response.setStatus(401);
            response.getWriter().print(objectMapper.writeValueAsString(new ErrorMessage(sb.toString())));

            log.error(sb.toString());
            return;
        }

        var optionalUser = userRepository.findByEmail(emailFromHeader);

        if (optionalUser.isEmpty() || !optionalUser.get().getApiKey().equals(aesService.decrypt(apiKeyFromHeader))) {

            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().print(objectMapper.writeValueAsString(new ErrorMessage("invalid credentials")));

            return;
        }

        filterChain.doFilter(request, response);

    }

    record ErrorMessage(String error) {
    }
}
