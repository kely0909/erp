package com.cskaoyan.service;

import com.cskaoyan.domain.FinalMeasuretCheck;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;

public interface FinalMeasuretCheckService {
    DataGridResult findWithPageAndRows(Integer page, Integer rows);

    DataGridResult vagueSearchById(Integer page, Integer rows, String searchVagueId);

    CustomResult deleteBatch(String[] ids);

    CustomResult update(FinalMeasuretCheck finalMeasuretCheck);

    CustomResult add(FinalMeasuretCheck finalMeasuretCheck);

    DataGridResult vagueSearchByOrderId(Integer page, Integer rows, String searchVagueId);
}
