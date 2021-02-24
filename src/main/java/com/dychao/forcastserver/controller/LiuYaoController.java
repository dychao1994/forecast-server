package com.dychao.forcastserver.controller;

import com.dychao.forcastserver.model.ReturnEnity;
import com.dychao.forcastserver.service.LiuYaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/liuYao")
public class LiuYaoController {


    @Autowired
    private LiuYaoService liuYaoService;
    /**
     * 查询六爻排盘
     * @return
     */

    @PostMapping("/queryGua")
    @ResponseBody
    public ReturnEnity queryGua() {
        ReturnEnity result = new ReturnEnity();
        Map<String, Object> zhuGua = liuYaoService.queryQuanGuaBySymbol("111111");
        Map<String, Object> bianGua = liuYaoService.queryQuanGuaBySymbol("101010");

        LinkedHashMap dataMap = new LinkedHashMap();
        dataMap.put("zhuGua", zhuGua);
        dataMap.put("bianGua", bianGua);
        result.setStatus(true);
        result.setData(dataMap);
        result.setInfo("查询六爻排盘成功！");
        return result;
    }
}
