package com.zeh.wms.web.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wordnik.swagger.annotations.Api;

/**
 * @author hzy24985
 * @version $Id: ListenerController, v 0.1 2018/2/24 14:10 hzy24985 Exp $
 */
@Api(value = "微信回调")
@Controller
@RequestMapping("/api/callback")
public class CallbackController {
}
