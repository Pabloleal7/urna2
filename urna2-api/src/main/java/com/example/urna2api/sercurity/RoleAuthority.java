package com.example.urna2api.sercurity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
@Getter
@Setter
public class RoleAuthority implements GrantedAuthority {

    private String authority;
    @Override
    public String getAuthority() {
        return authority;
    }
}
