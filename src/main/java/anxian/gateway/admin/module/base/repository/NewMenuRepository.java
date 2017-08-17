package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.NewMenu;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;

public interface NewMenuRepository extends MongoRepository<NewMenu, String> {
    List<NewMenu> findByIsParentOrderBySortAsc(boolean isParent);

    List<NewMenu> findByParentIdAndLeafOrderBySortAsc(ObjectId parentId, boolean leaf);

    List<NewMenu> findByIdInOrderBySortAsc(Set<ObjectId> parentIdSet);
}
