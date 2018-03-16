package com.zeh.wms.biz.service;

import com.zeh.wms.biz.exception.BookServiceException;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.model.BookVO;
import com.zeh.wms.biz.model.ExpressOrderVO;

/**
 * The interface Book service.
 *
 * @author allen
 * @create $ ID: BookServiceException, 18/3/13 16:21 allen Exp $
 * @since 1.0.0
 */
public interface BookService {

    /**
     * 快递预定
     *
     * @param bookVO the book vo
     * @return express order vo
     * @throws BookServiceException the book service exception
     */
    ExpressOrderVO book(BookVO bookVO) throws BookServiceException;

    /**
     * 下单，扣减库存.
     *
     * @param bookVO      the book vo
     * @param commodityId the commodity id
     * @param mobile      the mobile
     * @return the express order vo
     * @throws BookServiceException the book service exception
     * @throws ServiceException     the service exception
     */
    ExpressOrderVO inventoryBook(BookVO bookVO, Long commodityId, String mobile) throws BookServiceException, ServiceException;

}
