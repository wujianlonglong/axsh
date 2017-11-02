package anxian.gateway.admin.module.base.repository;

import anxian.gateway.admin.module.base.domain.NewMenu;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;

public interface NewMenuRepository extends MongoRepository<NewMenu, String> {
    List<NewMenu> findByIsParentOrderBySortAsc(boolean isParent);

    List<NewMenu> findByParentIdAndLeafOrderBySortAsc(String parentId, boolean leaf);

    List<NewMenu> findByIdInOrderBySortAsc(Set<String> parentIdSet);

    List<NewMenu> findByIsParentAndLeafOrderBySortAsc(boolean parentLeaf, boolean isLeaf);

    Page<NewMenu> findByTextLike(String menuName, Pageable pageable);


    NewMenu findTopByIsParentOrderBySortDesc(boolean isParent);

    NewMenu findTopByParentIdOrderBySortDesc(String parentId);


    List<NewMenu> findByParentId(String parentId);

    List<NewMenu> findByIdIn(String[] menuIds);
}
