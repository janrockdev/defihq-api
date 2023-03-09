package org.defihq.defihq_api.repos;

import org.defihq.defihq_api.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

    User findByUserNameIgnoreCase(String userName);

    Page<User> findAllByUserNameRegex(String userName, Pageable pageable);

    boolean existsByUserNameIgnoreCase(String userName);

}
