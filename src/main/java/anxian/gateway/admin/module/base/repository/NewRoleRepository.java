package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.NewMenu;
import anxian.gateway.admin.module.base.domain.NewRole;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NewRoleRepository extends MongoRepository<NewRole, String> {
    Page<NewRole> findByDisplayNameLike(String roleName, Pageable pageable);

    List<NewRole> findByIsValid(boolean isValid);
}
