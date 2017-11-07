package anxian.gateway.admin.module.business.controller.user;

import anxian.gateway.admin.module.base.model.ResponseMessage;
import client.api.category.CategoryApiClient;
import client.api.category.domain.Category;
import client.api.crm.CrmApiClient;
import client.api.crm.domain.ScoreCrmRepose;
import client.api.user.UserApiClient;
import client.api.user.domain.User;
import client.api.user.utils.page.PageForAdmin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 商城用户控制器
 * Created by jiangzhe on 15-12-1.
 */
@RestController
@RequestMapping("/anxian/sjes_users")
public class AnxianUserController {

    private static final Logger log = LoggerFactory.getLogger(AnxianUserController.class);
    @Autowired
    private UserApiClient userApiClient;
    @Autowired
    private CategoryApiClient categoryApiClient;

    @Autowired
    private CrmApiClient crmApiClient;

    /**
     * 分页列表
     */
    @RequestMapping("/list")
    public PageForAdmin list(int page, int limit, String username, String mobile, String email, String cardNum) {
        return userApiClient.pageGetUserList(username, mobile, email, cardNum, page - 1, limit);
    }

    /**
     * 　根据登录名取得用户信息
     *
     * @param username 　用户名
     * @return 用户对象
     */
    @RequestMapping(value = "/viewUser", method = RequestMethod.POST)
    public User viewUser(String username) throws JsonProcessingException {
        User user = userApiClient.findByUsername(username);
        if (null != user) {
            String categories = user.getCategories();
            if (StringUtils.isNotEmpty(categories)) {
                List<Long> categoryIdList = new ArrayList<>();
                String[] arr = categories.split(",");
                for (String strCategoryId : arr) {
                    categoryIdList.add(Long.parseLong(strCategoryId));
                }

                List<Category> categoryList = categoryApiClient.listByIds(categoryIdList);
                String categoryNames = StringUtils.EMPTY;
                for (Category category : categoryList) {
                    categoryNames += "," + category.getName();
                }
                user.setCategoryNames(categoryNames.substring(1));
            }
            String cardNum = user.getCardNum();
            if (StringUtils.isNotEmpty(cardNum)) {
                ResponseMessage responseMessage = userApiClient.memberPointEnquiry(cardNum);
//                ScoreCrmRepose scoreCrmRepose = crmApiClient.getTotalScore(cardNum);
                if (responseMessage.getCode() == 1) {
                    user.setScoreTotal(String.valueOf(responseMessage.getData()));
                    user.setScoreEnable(String.valueOf(responseMessage.getData()));
                } else {
                    user.setScoreTotal(String.valueOf(responseMessage.getErrMessage()));
                    user.setScoreEnable(String.valueOf(responseMessage.getErrMessage()));
                }
            }
            log.info(new ObjectMapper().writeValueAsString(user));
        }
        return user;
    }

    /**
     * 更新用户信息
     *
     * @return　用户对象
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Integer updateUser(Long id, String username, String mobile, String email) {
        User currentUser = userApiClient.findById(id);
        if (StringUtils.isNotEmpty(mobile) && !mobile.equals(currentUser.getMobile())
                && userApiClient.findUser(mobile) != null) {
            return -1;
        }

        if (StringUtils.isNotEmpty(email) && !email.equals(currentUser.getEmail())
                && userApiClient.findUser(email) != null) {
            return -2;
        }
        return userApiClient.updateMobileAndEmailAndCardNum(id, username, mobile, email, null);
    }

}
