package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.NewAuthority;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NewAuthorityRepository extends MongoRepository<NewAuthority, ObjectId> {
    List<NewAuthority> findByEnabled(boolean enaled);
}
