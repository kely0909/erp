package com.cskaoyan.service;

import com.cskaoyan.domain.FinalCountCheck;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;

public interface FinalCountCheckService {
    DataGridResult findWithPageAndRows(Integer page, Integer rows);

    CustomResult deleteBatch(String[] ids);

    CustomResult update(FinalCountCheck finalCountCheck);

    CustomResult add(FinalCountCheck finalCountCheck);

    DataGridResult vagueSearchByCountCheckId(Integer page, Integer rows, String searchVagueId);

    DataGridResult vagueSearchByOrderId(Integer page, Integer rows, String searchVagueId);
}
