package com.zeh.wms.biz.service.impl;

import com.zeh.jungle.dal.paginator.PageList;
import com.zeh.jungle.dal.paginator.PageUtils;
import com.zeh.wms.biz.constants.ExcelConstant;
import com.zeh.wms.biz.error.BizErrorFactory;
import com.zeh.wms.biz.exception.ServiceException;
import com.zeh.wms.biz.mapper.ExpressOrderMapper;
import com.zeh.wms.biz.model.ExpressOrderVO;
import com.zeh.wms.biz.service.ExpressOrderService;
import com.zeh.wms.dal.daointerface.ExpressOrderDAO;
import com.zeh.wms.dal.daointerface.ExpressOrderItemDAO;
import com.zeh.wms.dal.dataobject.ExpressOrderDO;
import com.zeh.wms.dal.dataobject.ExpressOrderItemDO;
import com.zeh.wms.dal.operation.expressorder.FindPageQuery;
import com.zeh.wms.dal.operation.expressorder.GetAllByParsQuery;
import org.jxls.common.Context;
import org.jxls.template.SimpleExporter;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

/**
 * @author hzy24985
 * @version $Id: ExpressOrderServiceImpl, v 0.1 2018/2/7 21:48 hzy24985 Exp $
 */
@Service
public class ExpressOrderServiceImpl implements ExpressOrderService {
    /** 错误工厂 */
    private static final BizErrorFactory ERROR_FACTORY = BizErrorFactory.getInstance();

    private static Logger                logger        = LoggerFactory.getLogger(ExpressOrderServiceImpl.class);

    @Resource
    private ExpressOrderDAO              expressOrderDAO;

    @Resource
    private ExpressOrderItemDAO          expressOrderItemDAO;

    @Resource
    private ExpressOrderMapper           expressOrderMapper;

    @Override
    public PageList<ExpressOrderVO> pageQueryExpressOrders(FindPageQuery orderQuery) throws ServiceException {
        PageList<ExpressOrderDO> list = expressOrderDAO.findPage(orderQuery);
        Collection<ExpressOrderVO> result = expressOrderMapper.d2vs(list.getData());

        return PageUtils.createPageList(result, list.getPaginator());
    }

    @Override
    public ExpressOrderVO getOrderDetailInfo(Long id) throws ServiceException {
        ExpressOrderDO orderDO = expressOrderDAO.queryById(id);
        if (orderDO == null) {
            throw new ServiceException(ERROR_FACTORY.notFindExpressOrderDetail(id));
        }
        List<ExpressOrderItemDO> itemDOS = expressOrderItemDAO.getItemByOrderNo(orderDO.getOrderNo());
        return expressOrderMapper.do2voDetails(orderDO, itemDOS);
    }

    @Override
    public ResponseEntity<byte[]> export(GetAllByParsQuery query) throws ServiceException {
        List<ExpressOrderDO> list = expressOrderDAO.getAllByPars(query);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            new SimpleExporter().gridExport(ExcelConstant.SF_EXCEL_HEADER, list, ExcelConstant.SF_EXCEL_PROPERTY_NAMES, os);

            HttpHeaders headers = new HttpHeaders();
            String fileName = "导出" + System.currentTimeMillis() + ".xls";
            headers.setContentDispositionFormData("attachment", java.net.URLEncoder.encode(fileName, "UTF-8"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(os.toByteArray(), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("错误原因", e);
            return null;
        }
    }

    @Override
    public ResponseEntity<byte[]> export2(GetAllByParsQuery query, String templatePath) throws ServiceException {
        List<ExpressOrderDO> list = expressOrderDAO.getAllByPars(query);
        Collection<ExpressOrderVO> result = expressOrderMapper.d2vs(list);
        try (InputStream is = new FileInputStream(templatePath)) {
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                Context context = new Context();
                context.putVar("lists", result);
                JxlsHelper.getInstance().processTemplate(is, os, context);

                HttpHeaders headers = new HttpHeaders();
                String fileName = "导出" + System.currentTimeMillis() + ".xlsx";
                headers.setContentDispositionFormData("attachment", java.net.URLEncoder.encode(fileName, "UTF-8"));
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                return new ResponseEntity<>(os.toByteArray(), headers, HttpStatus.CREATED);
            }
        } catch (IOException e) {
            logger.error("错误原因", e);
            return null;
        }
    }


}
