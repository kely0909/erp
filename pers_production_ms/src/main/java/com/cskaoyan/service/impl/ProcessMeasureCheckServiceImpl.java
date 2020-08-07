package com.cskaoyan.service.impl;

import com.cskaoyan.dao.ProcessCountCheckMapper;
import com.cskaoyan.dao.ProcessMeasureCheckMapper;
import com.cskaoyan.domain.ProcessCountCheck;
import com.cskaoyan.domain.ProcessMeasureCheck;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;
import com.cskaoyan.service.ProcessMeasureCheckService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ProcessMeasureCheckServiceImpl implements ProcessMeasureCheckService {

    @Autowired
    ProcessMeasureCheckMapper processMeasureCheckMapper;

    @Override
    public DataGridResult findWithPageAndRows(Integer page, Integer rows) {

        PageHelper.startPage(page, rows);
        List<ProcessCountCheck> list = processMeasureCheckMapper.selectAll();

        DataGridResult dataGridResult = getDataGridResult(list);


        return dataGridResult;
    }

    @Override
    public DataGridResult vagueSearchById(Integer page, Integer rows, String searchVagueId) {
        PageHelper.startPage(page, rows);
        HashMap<String, String> map = new HashMap<>();
        map.put("searchVagueId", new String("%" + searchVagueId + "%"));
        List<ProcessCountCheck> list = processMeasureCheckMapper.selectProcessMeasureChecksByPrimaryKeyId(map);

        DataGridResult dataGridResult = getDataGridResult(list);

        return dataGridResult;
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {

        CustomResult customResult = new CustomResult();
        int flag = processMeasureCheckMapper.deleteByIds(ids);

        if(flag > 0){
            customResult.setStatus(200);
            return customResult;
        }else{
            return null;
        }
    }

    @Override
    public CustomResult update(ProcessMeasureCheck processMeasureCheck) {

        CustomResult customResult = new CustomResult();
        int flag = processMeasureCheckMapper.updateByPrimaryKeySelective(processMeasureCheck);

        if(flag > 0){
            customResult.setStatus(200);
            return customResult;
        }else{
            return null;
        }
    }

    @Override
    public CustomResult add(ProcessMeasureCheck processMeasureCheck) {

        CustomResult customResult = new CustomResult();

        int flag = processMeasureCheckMapper.insert(processMeasureCheck);

        if(flag > 0){
            customResult.setStatus(200);
            return customResult;
        }else{
            return null;
        }
    }

    private DataGridResult getDataGridResult(List<ProcessCountCheck> list) {
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(list);

        PageInfo<ProcessCountCheck> unqualifyApplyPageInfo = new PageInfo<>(list);

        dataGridResult.setTotal(unqualifyApplyPageInfo.getTotal());
        return dataGridResult;
    }
}
