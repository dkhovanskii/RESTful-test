package com.silverrailtech.controller;

import com.silverrailtech.entity.Session;
import com.silverrailtech.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletRequest;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class SessionParameterResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Session.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String browserId = nativeWebRequest.getNativeRequest(HttpServletRequest.class).getParameter("id");
        if (isNotBlank(browserId)) {
            Session existingSession = sessionRepository.findOne(browserId);
            if (existingSession != null) {
                return existingSession;
            } else {
                Session session = new Session();
                session.setId(browserId);
                session.setState("");
                sessionRepository.save(session);
                return session;
            }
        }
        return null;
    }
}
