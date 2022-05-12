package com.kabir.mydemoproject.service;



import com.kabir.mydemoproject.models.ERole;
import com.kabir.mydemoproject.models.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(ERole name);
}
