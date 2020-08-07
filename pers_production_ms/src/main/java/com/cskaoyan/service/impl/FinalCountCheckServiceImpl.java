package com.cskaoyan.service.impl;

import com.cskaoyan.dao.FinalCountCheckMapper;
import com.cskaoyan.domain.FinalCountCheck;
import com.cskaoyan.domain.ProcessCountCheck;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;
import com.cskaoyan.service.FinalCountCheckService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FinalCountCheckServiceImpl implements FinalCountCheckService {
    
    @Autowired
    FinalCountCheckMapper finalCountCheckMapper;
    
    @Override
    public DataGridResult findWithPageAndRows(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<ProcessCountCheck> list = finalCountCheckMapper.selectAll();

        DataGridResult dataGridResult = getDataGridResult(list);


        return dataGridResult;
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        CustomResult customResult = new CustomResult();
        int flag = finalCountCheckMapper.deleteByIds(ids);

        if(flag > 0){
            customResult.setStatus(200);
            return customResult;
        }else{
            return null;
        }
    }

    @Override
    public CustomResult update(FinalCountCheck finalCountCheck) {
        CustomResult customResult = new CustomResult();
        int flag = finalCountCheckMapper.updateByPrimaryKeySelective(finalCountCheck);

        if(flag > 0){
            customResult.setStatus(200);
            return customResult;
        }else{
            return null;
        }
    }

    @Override
    public CustomResult add(FinalCountCheck finalCountCheck) {
        CustomResult customResult = new CustomResult();

        int flag = finalCountCheckMapper.insert(finalCountCheck);

        if(flag > 0){
            customResult.setStatus(200);
            return customResult;
        }else{
            return null;
        }
    }

    @Override
    public DataGridResult vagueSearchByCountCheckId(Integer page, Integer rows, String searchVagueId) {
        PageHelper.startPage(page, rows);
        HashMap<String, String> map = new HashMap<>();
        map.put("searchVagueId", new String("%" + searchVagueId + "%"));
        List<ProcessCountCheck> list = finalCountCheckMapper.selectCountChecksByPrimaryKeyId(map);

        DataGridResult dataGridResult = getDataGridResult(list);

        return dataGridResult;
    }

    @Override
    public DataGridResult vagueSearchByOrderId(Integer page, Integer rows, String searchVagueId) {
        PageHelper.startPage(page, rows);
        HashMap<String, String> map = new HashMap<>();
        map.put("searchVagueId", new String("%" + searchVagueId + "%"));
        List<ProcessCountCheck> list = finalCountCheckMapper.selectOrdersByPrimaryKeyId(map);

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
