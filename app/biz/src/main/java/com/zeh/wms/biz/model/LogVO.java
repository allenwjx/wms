package com.zeh.wms.biz.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 后台操作日志模型
 * 
 * @author hxy43938
 * @version $Id: LogVO.java, v 0.1 2017年02月17日 下午13:58 hxy43938 Exp $
 */
@Getter
@Setter
public class LogVO extends BaseVO {

    /**  */
    private static final long serialVersionUID = 3161297937419692096L;

    /** 操作人 */
    private String            operator;

    /** 操作对象 */
    private String            target;

    /** 对象编号 */
    private String            targetId;

    /** 操作类型*/
    private String            operatorType;

    /** 操作部门 */
    private String            operatorDept;

    /** 操作内容 */
    private String            content;

    public LogVO() {
    }

    public LogVO(String operator, String target, String targetId) {
        this.operator = operator;
        this.target = target;
        this.targetId = targetId;
    }

    public LogVO(String operator, String target, String targetId, String content) {
        this.operator = operator;
        this.target = target;
        this.targetId = targetId;
        this.content = content;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperatorDept() {
        return operatorDept;
    }

    public void setOperatorDept(String operatorDept) {
        this.operatorDept = operatorDept;
    }
}
