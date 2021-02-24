package com.dychao.forcastserver.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dychao.forcastserver.dao.LiuYaoDao;
import com.dychao.forcastserver.tools.BusinessUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LiuYaoService {
    @Autowired
    private LiuYaoDao liuYaoDao;
    /**
     * 根据标志查询八八六十四卦（全卦）
     * @return
     */
    public Map<String, Object> queryQuanGuaBySymbol(String symbol) {
        JSONObject map = new JSONObject();
        JSONArray list = liuYaoDao.queryQuanGuaBySymbol(symbol);
        if(null != list && !list.isEmpty()){
            map = list.getJSONObject(0);
            JSONObject waiGua = liuYaoDao.queryBaGuaBySymbol(map.getString("shangGua"));
            waiGua.put("waiGua", waiGua.getString("waiGua").split(","));
            map.put("waiGua",waiGua);

            JSONObject neiGua = liuYaoDao.queryBaGuaBySymbol(map.getString("xiaGua"));
            neiGua.put("neiGua", neiGua.getString("neiGua").split(","));
            map.put("neiGua",neiGua);
        }
        return map;
    }
}
