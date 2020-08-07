package com.cskaoyan.service.impl;

import com.cskaoyan.dao.FinalMeasuretCheckMapper;
import com.cskaoyan.domain.FinalMeasuretCheck;
import com.cskaoyan.domain.ProcessCountCheck;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cskaoyan.service.FinalMeasuretCheckService;

import java.util.HashMap;
import java.util.List;

@Service
public class FinalMeasuretCheckServiceImpl implements FinalMeasuretCheckService {
    
    @Autowired
    FinalMeasuretCheckMapper finalMeasuretCheckMapper;
    
    @Override
    public DataGridResult findWithPageAndRows(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<ProcessCountCheck> list = finalMeasuretCheckMapper.selectAll();

        DataGridResult dataGridResult = getDataGridResult(list);


        return dataGridResult;
    }

    @Override
    public DataGridResult vagueSearchById(Integer page, Integer rows, String searchVagueId) {
        PageHelper.startPage(page, rows);
        HashMap<String, String> map = new HashMap<>();
        map.put("searchVagueId", new String("%" + searchVagueId + "%"));
        List<ProcessCountCheck> list = finalMeasuretCheckMapper.selectFinalMeasuretChecksByPrimaryKeyId(map);

        DataGridResult dataGridResult = getDataGridResult(list);

        return dataGridResult;
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        CustomResult customResult = new CustomResult();
        int flag = finalMeasuretCheckMapper.deleteByIds(ids);

        if(flag > 0){
            customResult.setStatus(200);
            return customResult;
        }else{
            return null;
        }
    }

    @Override
    public CustomResult update(FinalMeasuretCheck finalMeasuretCheck) {
        CustomResult customResult = new CustomResult();
        int flag = finalMeasuretCheckMapper.updateByPrimaryKeySelective(finalMeasuretCheck);

        if(flag > 0){
            customResult.setStatus(200);
            return customResult;
        }else{
            return null;
        }
    }

    @Override
    public CustomResult add(FinalMeasuretCheck finalMeasuretCheck) {
        CustomResult customResult = new CustomResult();

        int flag = finalMeasuretCheckMapper.insert(finalMeasuretCheck);

        if(flag > 0){
            customResult.setStatus(200);
            return customResult;
        }else{
            return null;
        }
    }

    @Override
    public DataGridResult vagueSearchByOrderId(Integer page, Integer rows, String searchVagueId) {
        PageHelper.startPage(page, rows);
        HashMap<String, String> map = new HashMap<>();
        map.put("searchVagueId", new String("%" + searchVagueId + "%"));
        List<ProcessCountCheck> list = finalMeasuretCheckMapper.selectFinalMeasuretChecksByPrimaryKeyOrderId(map);

        DataGridResult dataGridResult = getDataGridResult(list);

        return dataGridResult;
    }

    private DataGridResult getDataGridResult(List<ProcessCountCheck> list) {
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(list);

        PageInfo<ProcessCountCheck> unqualifyApplyPageInfo = new PageInfo<>(list);

        dataGridResult.setTotal(unqualifyApplyPageInfo.getTotal());
        return dataGridResult;
    }
}
