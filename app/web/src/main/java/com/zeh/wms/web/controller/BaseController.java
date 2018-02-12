package com.zeh.wms.web.controller;

import com.zeh.jungle.core.support.ExceptionUtils;
import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.biz.model.UserBgVO;
import com.zeh.wms.web.error.WebErrorFactory;
import com.zeh.wms.web.exception.WebException;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * The constant ERROR_FACTORY.
     */
    protected static final WebErrorFactory ERROR_FACTORY = WebErrorFactory.getInstance();

    /**
     * 日志
     */
    protected final Logger                 logger        = LoggerFactory.getLogger(getClass());

    /**
     * 常量定义
     */
    protected static final String          SUCCESS       = "操作成功";
    /**
     * The constant FAILED.
     */
    protected static final String          FAILED        = "操作失败";

    /**
     * 获取当前用户工号
     *
     * @return 登录用户ID current user id
     */
    protected String getCurrentUserID() {
        // TODO
        return "TODO";
    }

    /**
     * 获取当前登录人姓名
     *
     * @return 登录用户名称 current user name
     */
    protected String getCurrentUserName() {
        // TODO
        return "TODO";
    }

    /**
     * 获取当前登录人(姓名+工号)
     *
     * @return operator current user
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
     * @param <T>  the type parameter
     * @param data the data
     * @return SingleResult single result
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
     * @param <T> the type parameter
     * @return SingleResult single result
     */
    protected <T> SingleResult<T> createSuccessResult() {
        return createSuccessResult(null);
    }

    /**
     * 创建失败结果
     *
     * @param <T> the type parameter
     * @param e   异常
     * @return SingleResult single result
     */
    protected <T> SingleResult<T> createErrorResult(Exception e) {
        return ExceptionUtils.getErrorResult(e, SingleResult.class);
    }

    /**
     * 创建失败结果
     *
     * @param <T>          the type parameter
     * @param errorMessage 异常
     * @return SingleResult single result
     */
    protected <T> SingleResult<T> createErrorResult(String errorMessage) {
        SingleResult<T> result = new SingleResult<>();
        result.setSuccess(true);
        result.setErrorCode("-1");
        result.setErrorMessage(errorMessage);
        return result;
    }

    /**
     * 获取实际路径
     *
     * @param request          the request
     * @param relativeFileName the relative file name
     * @return real file name
     */
    protected String getRealFileName(HttpServletRequest request, String relativeFileName) {
        String contextRealPath = request.getSession().getServletContext().getRealPath("/");
        File realFile = new File(contextRealPath, relativeFileName);
        if (!realFile.exists()) {
            return "";
        }
        return realFile.getAbsolutePath();
    }

    /**
     * Assert null.
     *
     * @param obj           the obj
     * @param parameterName the parameter name
     * @throws WebException the web exception
     */
    protected void assertNull(Long obj, String parameterName) throws WebException {
        if (obj == null) {
            throw new WebException(ERROR_FACTORY.parameterEmptyError(parameterName));
        }
    }

    /**
     * Assert empty.
     *
     * @param obj           the obj
     * @param parameterName the parameter name
     * @throws WebException the web exception
     */
    protected void assertEmpty(String obj, String parameterName) throws WebException {
        if (StringUtils.isBlank(obj)) {
            throw new WebException(ERROR_FACTORY.parameterEmptyError(parameterName));
        }
    }

    /**
     * Assert object null.
     *
     * @param obj           the obj
     * @param parameterName the parameter name
     * @throws WebException the web exception
     */
    protected void assertObjectNull(Object obj, String parameterName) throws WebException {
        if (obj == null) {
            throw new WebException(ERROR_FACTORY.parameterEmptyError(parameterName));
        }
    }

    /**
     * Assert equals.
     *
     * @param one     the one
     * @param two     the two
     * @param message the message
     * @throws WebException the web exception
     */
    protected void assertEquals(String one, String two, String message) throws WebException {
        if (!StringUtils.equals(one, two)){
            throw new WebException(ERROR_FACTORY.parameterNotEqualsError(message));
        }
    }
}
