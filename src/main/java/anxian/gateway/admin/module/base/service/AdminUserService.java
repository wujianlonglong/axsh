package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.model.ResponseMessage;
import anxian.gateway.admin.module.base.repository.UserRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class AdminUserService {

    @Autowired
    private MongoOperations mongoOperations;

    // TODO 用户维护

    /**
     * 用户列表
     *
     * @param searchName
     * @param page
     * @param size
     * @return
     */
    public ResponseMessage<Page<User>> list(String searchName, int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        Query query = new Query();
        query.with(pageable);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        query.with(sort);
        List<Criteria> criteriaList = new ArrayList<>();
        if (StringUtils.isNotEmpty(searchName)) {
            criteriaList.add(where("username").regex(searchName));
        }
        if (CollectionUtils.isNotEmpty(criteriaList)) {
            if (criteriaList.size() > 1) {
                query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList
                        .size()])));
            } else {
                query.addCriteria(criteriaList.get(0));
            }
        }

        List<User> userList = mongoOperations.find(query, User.class);
        long total = mongoOperations.count(query, User.class);
        Page<User> userPage = new PageImpl<>(userList, pageable, total);


        return ResponseMessage.defaultSuccess(userPage);
    }
}
