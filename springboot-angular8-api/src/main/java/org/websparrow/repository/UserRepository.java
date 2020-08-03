package org.websparrow.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.websparrow.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
}
