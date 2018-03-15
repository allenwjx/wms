package com.zeh.wms.biz.service.impl;

import com.zeh.wms.biz.exception.BookServiceException;
import com.zeh.wms.biz.model.BookVO;

/**
 * @author allen
 * @create $ ID: UserBookServiceImpl, 18/3/15 15:33 allen Exp $
 * @since 1.0.0
 */
public class UserBookServiceImpl extends AbstractBookService {
    /**
     * 计算快递单价格
     *
     * @param bookVO
     * @return
     * @throws BookServiceException
     */
    @Override
    protected ExpressOrderPrice calculatePrice(BookVO bookVO) throws BookServiceException {
        // 获取用户关联的物流公司运费定价

        return null;
    }
}