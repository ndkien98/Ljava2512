package com.t3h.uniqlo.config.aop;

import com.t3h.uniqlo.entity.User;
import com.t3h.uniqlo.entity.UserActionLog;
import com.t3h.uniqlo.repository.UserActionLogRepository;
import com.t3h.uniqlo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ActionLoggingAspect {

    private final UserActionLogRepository actionLogRepository;
    private final UserRepository userRepository;

    @Around("@annotation(com.t3h.uniqlo.config.aop.LogUserAction)")
    public Object logAction(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogUserAction logAction = method.getAnnotation(LogUserAction.class);

        // Lay ten action tu annotation, neu khong co thi dung ten method
        String actionName = logAction.value().isEmpty() ? method.getName() : logAction.value();
        String status = "SUCCESS";
        Object result = null;

        try {
            // Thuc thi method goc
            result = joinPoint.proceed();
            return result;
        } catch (Throwable e) {
            status = "FAILED: " + e.getMessage();
            throw e;
        } finally {
            // Luu log vao database sau khi method chay xong (du thanh cong hay that bai)
            try {
                saveLog(actionName, status);
            } catch (Exception ex) {
                log.error("Failed to save action log", ex);
            }
        }
    }

    private void saveLog(String actionName, String status) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getRemoteAddr();
        String endpoint = request.getRequestURI();

        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            String email = authentication.getName();
            user = userRepository.findByEmail(email).orElse(null);
        }

        UserActionLog actionLog = UserActionLog.builder()
                .action(actionName)
                .endpoint(endpoint)
                .ipAddress(ipAddress)
                .status(status)
                .user(user)
                .build();
        actionLogRepository.save(actionLog);
    }
}
