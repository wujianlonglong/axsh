package anxian.gateway.admin.module.business.controller.user;

import client.api.category.CategoryApiClient;
import client.api.category.domain.Category;
import client.api.crm.CrmApiClient;
import client.api.crm.domain.ScoreCrmRepose;
import client.api.user.SjesUserApiClient;
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
@RequestMapping("/sjes/sjes_users")
public class SjesUserController {

    private static final Logger log = LoggerFactory.getLogger(SjesUserController.class);
    @Autowired
    private SjesUserApiClient sjesUserApiClient;
    @Autowired
    private CategoryApiClient categoryApiClient;

    @Autowired
    private CrmApiClient crmApiClient;

    /**
     * 分页列表
     */
    @RequestMapping("/list")
    public PageForAdmin list(int page, int limit, String username, String mobile, String email, String cardNum) {
        return sjesUserApiClient.pageGetUserList(username, mobile, email, cardNum, page - 1, limit);
    }

    /**
     * 　根据登录名取得用户信息
     *
     * @param username 　用户名
     * @return 用户对象
     */
    @RequestMapping(value = "/viewUser", method = RequestMethod.POST)
    public User viewUser(String username) throws JsonProcessingException {
        User user = sjesUserApiClient.findByUsername(username);
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
                ScoreCrmRepose scoreCrmRepose = crmApiClient.getTotalScore(cardNum);
                if (scoreCrmRepose.getErrorCode().equals("0")) {
                    user.setScoreTotal(String.valueOf(scoreCrmRepose.getValue()));
                    user.setScoreEnable(String.valueOf(scoreCrmRepose.getValue()));
                } else {
                    user.setScoreTotal(String.valueOf(scoreCrmRepose.getMessage()));
                    user.setScoreEnable(String.valueOf(scoreCrmRepose.getMessage()));
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
        User currentUser = sjesUserApiClient.findById(id);
        if (StringUtils.isNotEmpty(mobile) && !mobile.equals(currentUser.getMobile())
                && sjesUserApiClient.findUser(mobile) != null) {
            return -1;
        }

        if (StringUtils.isNotEmpty(email) && !email.equals(currentUser.getEmail())
                && sjesUserApiClient.findUser(email) != null) {
            return -2;
        }
        return sjesUserApiClient.updateMobileAndEmailAndCardNum(id, username, mobile, email, null);
    }

}
