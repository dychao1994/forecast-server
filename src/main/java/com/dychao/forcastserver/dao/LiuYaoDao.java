package com.dychao.forcastserver.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dychao.forcastserver.tools.BusinessUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class LiuYaoDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public JSONArray queryQuanGuaBySymbol(String symbol){
        String sql = "SELECT * FROM quan_gua where symbol = :symbol";
        MapSqlParameterSource ps = new MapSqlParameterSource();
        ps.addValue("symbol", symbol);
        List<Map<String, Object>> sqlList = jdbcTemplate.queryForList(sql, ps);
        JSONArray resultList =  JSONArray.parseArray(JSON.toJSONString(sqlList));
        resultList = BusinessUtility.transformField(resultList);
        return resultList;
    }

    public JSONObject queryBaGuaBySymbol(String symbol){
        String sql = "SELECT * FROM ba_gua where symbol = :symbol";
        MapSqlParameterSource ps = new MapSqlParameterSource();
        ps.addValue("symbol", symbol);

        List<Map<String, Object>> sqlList = jdbcTemplate.queryForList(sql, ps);
        JSONArray resultList =  JSONArray.parseArray(JSON.toJSONString(sqlList));
        resultList = BusinessUtility.transformField(resultList);
        return resultList.getJSONObject(0);
    }
}
