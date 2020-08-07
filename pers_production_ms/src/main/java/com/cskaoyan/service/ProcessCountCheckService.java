package com.cskaoyan.service;

import com.cskaoyan.domain.ProcessCountCheck;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;

public interface ProcessCountCheckService {
    DataGridResult findWithPageAndRows(Integer page, Integer rows);

    DataGridResult vagueSearchById(Integer page, Integer rows, String searchVagueId);

    CustomResult deleteBatch(String[] ids);

    CustomResult update(ProcessCountCheck processMeasureCheck);

    CustomResult add(ProcessCountCheck processMeasureCheck);
}
