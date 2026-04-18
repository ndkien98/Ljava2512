package com.t3h.uniqlo.security;

import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * KeycloakAdminService
 *
 * Service này minh hoạ cách Spring Boot có thể tự động quản lý User trong
 * Keycloak
 * thông qua Keycloak Admin REST Client - mà không cần vào giao diện Admin UI.
 *
 * Use cases thực tế:
 * - Tạo tài khoản hàng loạt khi onboard nhân viên mới.
 * - Reset mật khẩu theo request từ admin nội bộ.
 * - Tự động gán vai trò khi user nâng cấp gói trong ứng dụng.
 * - Xóa hoặc disable tài khoản khi người dùng yêu cầu xóa dữ liệu (GDPR).
 *
 * Yêu cầu: Client "uniqlo-backend" trong Keycloak phải được cấu hình:
 * - Client Authentication: ON (Confidential)
 * - Service Accounts: ON
 * - Service Account có role "realm-management > manage-users"
 */
@Service
public class KeycloakAdminService {

    @Value("${keycloak.admin.server-url}")
    private String serverUrl;

    @Value("${keycloak.admin.realm}")
    private String realm;

    @Value("${keycloak.admin.client-id}")
    private String clientId;

    @Value("${keycloak.admin.client-secret}")
    private String clientSecret;

    /**
     * Tạo Keycloak Admin Client instance.
     * Dùng Client Credentials Grant (không cần username/password admin).
     */
    private Keycloak getKeycloakInstance() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType("client_credentials") // Xác thực bằng Client ID + Secret
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }

    /**
     * Tạo một User mới trong Keycloak.
     *
     * @param username  Tên đăng nhập
     * @param email     Email
     * @param firstName Họ
     * @param lastName  Tên
     * @param password  Mật khẩu ban đầu (sẽ được set là temporary = false)
     * @return HTTP Status Code từ Keycloak (201 = Created, 409 = Conflict nếu đã
     *         tồn tại)
     */
    public int createUser(String username, String email, String firstName, String lastName, String password) {
        try (Keycloak keycloak = getKeycloakInstance()) {
            // Xây dựng đối tượng User
            UserRepresentation user = new UserRepresentation();
            user.setUsername(username);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEnabled(true);
            user.setEmailVerified(true);

            // Thiết lập mật khẩu
            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(password);
            credential.setTemporary(false); // User không cần đổi mật khẩu lần đầu
            user.setCredentials(Collections.singletonList(credential));

            // Gửi request tạo user lên Keycloak
            Response response = keycloak.realm(realm).users().create(user);
            return response.getStatus();
        }
    }

    /**
     * Gán Realm Role cho User.
     *
     * @param username Tên đăng nhập của user
     * @param roleName Tên role cần gán (ví dụ: "ROLE_ADMIN")
     */
    public void assignRealmRole(String username, String roleName) {
        try (Keycloak keycloak = getKeycloakInstance()) {
            // Tìm ID của user theo username
            List<UserRepresentation> users = keycloak.realm(realm).users()
                    .searchByUsername(username, true);

            if (users.isEmpty()) {
                throw new RuntimeException("User không tìm thấy: " + username);
            }

            String userId = users.get(0).getId();

            // Tìm Role theo tên
            RoleRepresentation role = keycloak.realm(realm).roles().get(roleName).toRepresentation();

            // Gán role cho user
            keycloak.realm(realm).users().get(userId)
                    .roles().realmLevel()
                    .add(Collections.singletonList(role));
        }
    }

    /**
     * Xóa User khỏi Keycloak (ví dụ: xử lý yêu cầu xóa tài khoản).
     *
     * @param username Tên đăng nhập của user cần xóa
     */
    public void deleteUser(String username) {
        try (Keycloak keycloak = getKeycloakInstance()) {
            List<UserRepresentation> users = keycloak.realm(realm).users()
                    .searchByUsername(username, true);

            if (users.isEmpty()) {
                throw new RuntimeException("User không tìm thấy: " + username);
            }

            String userId = users.get(0).getId();
            keycloak.realm(realm).users().get(userId).remove();
        }
    }

    /**
     * Disable User (thay vì xóa hẳn, để bảo toàn lịch sử giao dịch).
     *
     * @param username Tên đăng nhập
     */
    public void disableUser(String username) {
        try (Keycloak keycloak = getKeycloakInstance()) {
            List<UserRepresentation> users = keycloak.realm(realm).users()
                    .searchByUsername(username, true);

            if (users.isEmpty())
                return;

            UserRepresentation user = users.get(0);
            user.setEnabled(false);
            keycloak.realm(realm).users().get(user.getId()).update(user);
        }
    }
}
