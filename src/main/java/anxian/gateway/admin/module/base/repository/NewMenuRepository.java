package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.NewMenu;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewMenuRepository extends MongoRepository<NewMenu, String> {
}
