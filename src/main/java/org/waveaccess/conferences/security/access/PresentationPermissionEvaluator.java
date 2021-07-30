package org.waveaccess.conferences.security.access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.waveaccess.conferences.repositories.PresentationRepository;
import org.waveaccess.conferences.security.details.UserDetailsImpl;

import java.io.Serializable;

@Component
public class PresentationPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private PresentationRepository presentationRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object presentationId, Object o1) {
        if (authentication == null || presentationId == null) {
            return false;
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if (presentationId.getClass().equals(Long.class)) {
            return presentationRepository.findByIdAndUserId((Long)presentationId, userDetails.getId()).isPresent();
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}