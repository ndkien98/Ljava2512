package com.t3h.uniqlo.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * KeycloakRoleConverter
 *
 * Mục đích: Đọc JWT từ Keycloak và trích xuất danh sách Roles từ claim
 * "realm_access.roles",
 * sau đó chuyển thành các GrantedAuthority mà Spring Security có thể sử dụng.
 *
 * Cấu trúc JWT Keycloak:
 * {
 * "realm_access": {
 * "roles": ["ROLE_USER", "ROLE_ADMIN"]
 * }
 * }
 *
 * Với converter này, @PreAuthorize("hasRole('ADMIN')") sẽ hoạt động đúng.
 */
@Component
public class KeycloakRoleConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    // Các role nội bộ của Keycloak, không cần map vào Spring Security
    private static final List<String> KEYCLOAK_INTERNAL_ROLES = List.of(
            "offline_access",
            "uma_authorization",
            "default-roles-uniqlo-realm");

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = extractRealmRoles(jwt);
        return new JwtAuthenticationToken(jwt, authorities);
    }

    /**
     * Trích xuất roles từ claim "realm_access.roles" trong Keycloak JWT.
     *
     * @param jwt Token JWT từ Keycloak
     * @return Danh sách GrantedAuthority có dạng "ROLE_<TÊN_ROLE_IN_HOA>"
     */
    private Collection<GrantedAuthority> extractRealmRoles(Jwt jwt) {
        // Lấy map "realm_access" từ payload của JWT
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");

        if (realmAccess == null || !realmAccess.containsKey("roles")) {
            return Collections.emptyList();
        }

        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) realmAccess.get("roles");

        return roles.stream()
                // Loại bỏ các role nội bộ của Keycloak
                .filter(role -> !KEYCLOAK_INTERNAL_ROLES.contains(role))
                // Map sang SimpleGrantedAuthority với tiền tố "ROLE_" (chữ hoa)
                // Ví dụ: "ROLE_USER" → ROLE_ROLE_USER hoặc "user" → ROLE_USER
                // Convention: nếu role đã có tiền tố ROLE_ thì không thêm nữa
                .map(role -> new SimpleGrantedAuthority(
                        role.startsWith("ROLE_") ? role : "ROLE_" + role.toUpperCase()))
                .collect(Collectors.toList());
    }
}
