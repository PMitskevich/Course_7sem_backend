package com.mitskevich.course_7sem.security;

import com.mitskevich.course_7sem.exception.JwtAuthenticationException;
import com.mitskevich.course_7sem.exception.detail.ErrorInfo;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;
    private final MessageSource messageSource;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, MessageSource messageSource) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.messageSource = messageSource;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (AuthenticationException exception) {
            SecurityContextHolder.clearContext();
//            ((HttpServletResponse) servletResponse).sendError(exception.getStatus().value());
            throw new JwtAuthenticationException(ErrorInfo.AUTHENTICATION_EXCEPTION,
                    messageSource.getMessage("message.AuthenticationException", new Object[]{null}, LocaleContextHolder.getLocale()));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
