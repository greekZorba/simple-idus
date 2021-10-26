package com.homework.simpleidus.domain.specification;

import com.homework.simpleidus.domain.entity.user.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    public static Specification<User> from(String name, String email) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.and(builder.isFalse(root.get("deleted"))));

            if (name != null && !name.isEmpty()) {
                predicates.add(builder.and(builder.equal(root.get("name"), name)));
            }
            if (email != null && !email.isEmpty()) {
                predicates.add(builder.and(builder.equal(root.get("email"), email)));
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
