package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

    Page<User> findByUsernameLike(String workerId, Pageable pageable);
}
