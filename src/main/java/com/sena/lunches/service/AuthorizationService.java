package com.sena.lunches.service;

import com.sena.lunches.entities.Authorization;
import com.sena.lunches.entities.Authorization;

import java.util.List;

public interface AuthorizationService {
    public List<Authorization> getAuthorization();

    public Authorization saveAuthorization(Authorization authorization);

    public Authorization getAuthorizationById(Integer id);

    public Authorization updateAuthorization(Integer id, Authorization authorization);

    public void deleteAuthorization(Integer id);
}
