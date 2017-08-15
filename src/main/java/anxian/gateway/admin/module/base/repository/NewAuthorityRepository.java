package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.NewAuthority;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewAuthorityRepository extends MongoRepository<NewAuthority, ObjectId> {
}
