//package danekerscode.api.common.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import danekerscode.api.domain.repository.UserRepository;
//import danekerscode.api.domain.service.AESService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Arrays;
//
//import static danekerscode.api.config.SecurityConfig.insecureEndpoints;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class ApiKeyFilter extends OncePerRequestFilter {
//
//    private final UserRepository userRepository;
//    private final ObjectMapper objectMapper;
//    private final AESService aesService;
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    ) throws ServletException, IOException {
//
//        var requestUrl = request.getRequestURI();
//
//        if (
//               requestUrl.contains("api/v1/user")
//        ) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        final var APPLICATION_JSON = "application/json";
//
//        var apiKeyFromHeader = request.getHeader("api_key");
//
//
//        if (apiKeyFromHeader == null) {
//            var err = "Missing api key header";
//            response.setContentType(APPLICATION_JSON);
//            response.setStatus(401);
//            response.getWriter().print(objectMapper.writeValueAsString(new ErrorMessage(err)));
//
//            log.error(err);
//        }
//
//
//        var email = aesService.decrypt(apiKeyFromHeader);
//        var optionalUser = userRepository.findByEmail(email);
//
//        if (
//                optionalUser.isEmpty() ||
//                        !optionalUser.get().getApiKey()
//                                .equals(aesService.decrypt(apiKeyFromHeader))
//        ) {
//
//            response.setStatus(401);
//            response.setContentType(APPLICATION_JSON);
//            response.getWriter().print(objectMapper.writeValueAsString(new ErrorMessage("invalid credentials")));
//
//            return;
//        }
//
//        filterChain.doFilter(request, response);
//
//    }
//
//    record ErrorMessage(String error) {
//    }
//}
