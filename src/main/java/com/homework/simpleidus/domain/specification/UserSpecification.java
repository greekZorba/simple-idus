package com.homework.simpleidus.domain.specification;

import com.homework.simpleidus.domain.entity.user.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> from(String name, String email) {
        Specification specification = Specification.where(notDeleted());
        if (name != null && name.isEmpty()) {
            specification.and(byName(name));
        }

        if (email != null && email.isEmpty()) {
            specification.and(byEmail(email));
        }

        return specification;
    }

    private static Specification<User> notDeleted() {
        return (root, query, builder) -> builder.isFalse(root.get("deleted"));
    }

    private static Specification<User> byName(String name) {
        return (root, query, builder) -> builder.equal(root.get("name"), name);
    }

    private static Specification<User> byEmail(String email) {
        return (root, query, builder) -> builder.equal(root.get("email"), email);
    }

}
