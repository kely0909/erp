package com.cskaoyan.service;

import com.cskaoyan.domain.UnqualifyApply;
import com.cskaoyan.domain.objUtils.CustomResult;
import com.cskaoyan.domain.objUtils.DataGridResult;

public interface UnqualifyApplyService {

    DataGridResult findWithPageAndRows(Integer page, Integer rows);

    DataGridResult vagueSearchById(Integer page, Integer rows, String vagueUnqualifyId);

    DataGridResult vagueSearchByName(Integer page, Integer rows, String vagueUnqualifyName);

    CustomResult deleteBatch(String[] ids);

    CustomResult update(UnqualifyApply unqualifyApply);

    CustomResult add(UnqualifyApply unqualifyApply);
}
