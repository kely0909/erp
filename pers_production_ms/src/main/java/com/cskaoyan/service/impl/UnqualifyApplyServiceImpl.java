package com.cskaoyan.service.impl;

import com.cskaoyan.dao.UnqualifyApplyMapper;
import com.cskaoyan.domain.UnqualifyApply;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;
import com.cskaoyan.service.UnqualifyApplyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class UnqualifyApplyServiceImpl implements UnqualifyApplyService{


    @Autowired
    UnqualifyApplyMapper unqualifyApplyMapper;

    @Override
    public DataGridResult findWithPageAndRows(Integer page, Integer rows) {

        PageHelper.startPage(page, rows);
        List<UnqualifyApply> list = unqualifyApplyMapper.selectAll();

        DataGridResult dataGridResult = getDataGridResult(list);


        return dataGridResult;
    }

    @Override
    public DataGridResult vagueSearchById(Integer page, Integer rows, String vagueUnqualifyId) {

        PageHelper.startPage(page, rows);
        HashMap<String, String> map = new HashMap<>();
        map.put("vagueUnqualifyId", new String("%" + vagueUnqualifyId + "%"));
        List<UnqualifyApply> list = unqualifyApplyMapper.selectUnqualifyApplysByPrimaryKeyId(map);

        //System.out.println("'%"+ 0 +"%'");

        DataGridResult dataGridResult = getDataGridResult(list);

        return dataGridResult;
    }

    @Override
    public DataGridResult vagueSearchByName(Integer page, Integer rows, String vagueUnqualifyName) {
        PageHelper.startPage(page, rows);
        HashMap<String, String> map = new HashMap<>();
        map.put("vagueUnqualifyName", new String("%" + vagueUnqualifyName + "%"));
        List<UnqualifyApply> list = unqualifyApplyMapper.selectUnqualifyApplysByPrimaryKeyName(map);

        DataGridResult dataGridResult = getDataGridResult(list);

        return dataGridResult;
    }

    @Override
    public CustomResult deleteBatch(String[] ids) {
        CustomResult customResult = new CustomResult();
        int flag = unqualifyApplyMapper.deleteByIds(ids);

        //System.out.println(flag);

        if(flag > 0){
            customResult.setStatus(200);
            return customResult;
        }else{
            return null;
        }
    }

    @Override
    public CustomResult update(UnqualifyApply unqualifyApply) {
        CustomResult customResult = new CustomResult();
        int flag = unqualifyApplyMapper.updateByPrimaryKeySelective(unqualifyApply);

        if(flag > 0){
            customResult.setStatus(200);
            return customResult;
        }else {
            return null;
        }
    }

    @Override
    public CustomResult add(UnqualifyApply unqualifyApply) {
        CustomResult customResult = new CustomResult();

        int flag = unqualifyApplyMapper.insert(unqualifyApply);


        if(flag > 0){
            customResult.setStatus(200);
            return customResult;
        }else {
            return null;
        }
    }

    private DataGridResult getDataGridResult(List<UnqualifyApply> list) {
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(list);

        PageInfo<UnqualifyApply> unqualifyApplyPageInfo = new PageInfo<>(list);

        dataGridResult.setTotal(unqualifyApplyPageInfo.getTotal());
        return dataGridResult;
    }
}
