package com.zeh.wms.web.controller.user;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.UserAgentLinkVO;
import com.zeh.wms.biz.model.UserVO;
import com.zeh.wms.biz.model.enums.UserLinkTypeEnum;
import com.zeh.wms.biz.service.UserService;
import com.zeh.wms.dal.operation.user.GetAllUserPageQuery;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.exception.WebException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * The type User controller.
 *
 * @author hzy24985
 * @version $Id : UserController, v 0.1 2018/2/10 19:39 hzy24985 Exp $
 */
@Controller
@RequestMapping("/user/front")
public class UserController extends BaseController {

    /**
     * The User service.
     */
    @Resource
    private UserService userService;

    /**
     * 分页查询
     *
     * @param query the query
     * @return page list
     * @throws ServiceException the service exception
     */
    @RequestMapping(value = "list")
    @ResponseBody
    public PageList<UserVO> list(GetAllUserPageQuery query) throws ServiceException {
        return userService.pageQueryUser(query);
    }

    /**
     * 查看详情页面
     *
     * @param id 订单ID
     * @return user vo
     * @throws ServiceException the service exception
     * @throws WebException     the web exception
     */
    @RequestMapping("one")
    @ResponseBody
    public UserVO one(Long id) throws ServiceException, WebException {
        assertNull(id, "id");
        UserVO userVO = new UserVO();
        if (id != null) {
            userVO = userService.getUserDetailInfo(id);
        }
        return userVO;
    }

    /**
     * Update password.
     *
     * @param id       the id
     * @param password the password
     * @return the single result
     * @throws ServiceException the service exception
     * @throws WebException     the web exception
     */
    @RequestMapping(value = "pwd", method = RequestMethod.PUT)
    @ResponseBody
    public SingleResult updatePassword(Long id, String password) throws ServiceException, WebException {
        assertNull(id, "id");
        assertEmpty(password, "密码");

        userService.updatePassword(password, id);
        return createSuccessResult();
    }

    /**
     * 更新用户所属类型，是大客户还是代理商.
     *
     * @param userId the user id
     * @param type   the type
     * @param code   the code
     * @return the single result
     * @throws ServiceException the service exception
     * @throws WebException     the web exception
     */
    @RequestMapping(value = "type", method = RequestMethod.PUT)
    @ResponseBody
    public SingleResult updateUserType(Long userId, String type, String code) throws ServiceException, WebException {
        assertNull(userId, "userId");
        assertEmpty(type, "商户类型");
        assertObjectNull(UserLinkTypeEnum.getEnumByCode(type), "商户类型");
        assertEmpty(code, "商户code");

        UserAgentLinkVO vo = new UserAgentLinkVO();
        vo.setUserId(userId);
        vo.setType(UserLinkTypeEnum.getEnumByCode(type));
        vo.setCode(code);
        vo.setModifyBy(getCurrentUserName());
        userService.updateType(vo);
        return createSuccessResult();
    }

}
