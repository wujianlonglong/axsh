package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.NewRole;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewRoleRepository extends MongoRepository<NewRole, ObjectId> {
}
