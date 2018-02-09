package com.zeh.wms.web.controller;

import com.zeh.jungle.core.support.ExceptionUtils;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.model.UserBgVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * The type Base controller.
 *
 * @author allen
 * @version Id : BaseController, v 0.1 2017/2/17 11:20 hxy43938 Exp $
 */
public abstract class BaseController {

    /** 日志*/
    protected final Logger        logger  = LoggerFactory.getLogger(getClass());

    /** 常量定义 */
    protected static final String SUCCESS = "操作成功";
    protected static final String FAILED  = "操作失败";

    /**
     * 获取当前用户工号
     *
     * @return 登录用户ID
     */
    protected String getCurrentUserID() {
        // TODO
        return "TODO";
    }

    /**
     * 获取当前登录人姓名
     *
     * @return 登录用户名称
     */
    protected String getCurrentUserName() {
        // TODO
        return "TODO";
    }

    /**
     * 获取当前登录人(姓名+工号)
     *
     * @return operator
     */
    protected UserBgVO getCurrentUser() {
        // TODO
        UserBgVO bgVO = new UserBgVO();
        bgVO.setPassword("TODO");
        bgVO.setUsername("TODO");
        return bgVO;
    }

    /**
     * 创建成功结果
     *
     * @param data the data
     * @return SingleResult
     */
    protected <T> SingleResult<T> createSuccessResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setSuccess(true);
        result.setErrorMessage(SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 创建成功结果
     *
     * @return SingleResult
     */
    protected <T> SingleResult<T> createSuccessResult() {
        return createSuccessResult(null);
    }

    /**
     * 创建失败结果
     *
     * @param e 异常
     * @return SingleResult
     */
    protected <T> SingleResult<T> createErrorResult(Exception e) {
        return ExceptionUtils.getErrorResult(e, SingleResult.class);
    }

    /**
     * 创建失败结果
     *
     * @param errorMessage 异常
     * @return SingleResult
     */
    protected <T> SingleResult<T> createErrorResult(String errorMessage) {
        SingleResult<T> result = new SingleResult<>();
        result.setSuccess(true);
        result.setErrorCode("-1");
        result.setErrorMessage(errorMessage);
        return result;
    }

    /**
     *
     * @param request
     * @param relativeFileName
     * @return
     */
    protected String getRealFileName(HttpServletRequest request, String relativeFileName) {
        String contextRealPath = request.getSession().getServletContext().getRealPath("/");
        File realFile = new File(contextRealPath, relativeFileName);
        if (!realFile.exists()) {
            return "";
        }
        return realFile.getAbsolutePath();
    }
}
