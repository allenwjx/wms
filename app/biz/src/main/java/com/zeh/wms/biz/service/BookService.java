package com.zeh.wms.biz.service;

import com.zeh.wms.biz.exception.BookServiceException;
import com.zeh.wms.biz.model.BookVO;
import com.zeh.wms.biz.model.ExpressOrderVO;

/**
 * @author allen
 * @create $ ID: BookServiceException, 18/3/13 16:21 allen Exp $
 * @since 1.0.0
 */
public interface BookService {

    /**
     * 快递预定
     * 
     * @param bookVO
     * @return
     * @throws BookServiceException
     */
    ExpressOrderVO book(BookVO bookVO) throws BookServiceException;

}
