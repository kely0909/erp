package com.cskaoyan.service;

import com.cskaoyan.domain.ProcessMeasureCheck;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;

public interface ProcessMeasureCheckService {
    DataGridResult findWithPageAndRows(Integer page, Integer rows);

    DataGridResult vagueSearchById(Integer page, Integer rows, String searchVagueId);

    CustomResult deleteBatch(String[] ids);

    CustomResult update(ProcessMeasureCheck processMeasureCheck);

    CustomResult add(ProcessMeasureCheck processMeasureCheck);
}
