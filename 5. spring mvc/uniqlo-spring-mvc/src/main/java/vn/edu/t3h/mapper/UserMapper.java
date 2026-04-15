package vn.edu.t3h.mapper;

import vn.edu.t3h.dto.request.UserCreateRequest;
import vn.edu.t3h.dto.request.UserUpdateRequest;
import vn.edu.t3h.dto.response.UserListItemResponse;
import vn.edu.t3h.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class UserMapper {

    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private UserMapper() {
    }

    public static User fromCreateRequest(UserCreateRequest req) {
        User u = new User();
        u.setFullName(req.getFullName());
        u.setEmail(req.getEmail());
        u.setGender(req.getGender());
        u.setRole(req.getRole());
        u.setAvatar(req.getAvatar());
        if (req.getBirthday() != null && !req.getBirthday().isBlank()) {
            u.setBirthday(LocalDate.parse(req.getBirthday(), DATE));
        }
        return u;
    }

    public static User fromUpdateRequest(UserUpdateRequest req) {
        User u = new User();
        u.setId(req.getId());
        u.setFullName(req.getFullName());
        u.setEmail(req.getEmail());
        u.setGender(req.getGender());
        u.setRole(req.getRole());
        u.setAvatar(req.getAvatar());
        if (req.getBirthday() != null && !req.getBirthday().isBlank()) {
            u.setBirthday(LocalDate.parse(req.getBirthday(), DATE));
        }
        return u;
    }

    public static UserListItemResponse toListItem(User u) {
        UserListItemResponse r = new UserListItemResponse();
        r.setId(u.getId());
        r.setFullName(u.getFullName());
        r.setEmail(u.getEmail());
        r.setGender(u.getGender());
        r.setRole(u.getRole());
        r.setAvatar(u.getAvatar());
        r.setBirthday(u.getBirthday() == null ? null : u.getBirthday().toString());
        r.setCreatedAt(u.getCreatedAt() == null ? null : u.getCreatedAt().toString());
        return r;
    }
}

