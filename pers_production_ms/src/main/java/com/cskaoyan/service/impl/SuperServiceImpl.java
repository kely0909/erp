package com.cskaoyan.service.impl;

import com.cskaoyan.dao.*;
import com.cskaoyan.dao.authority.*;
import com.cskaoyan.domain.PageBean;
import com.cskaoyan.domain.authority.SysRole;
import com.cskaoyan.domain.authority.SysUserRole;
import com.cskaoyan.service.SuperService;
import com.cskaoyan.util.file.MyFileUtils;
import com.cskaoyan.util.mapping.ParseDomainConfigFile;
import com.cskaoyan.util.string.StringUtil;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Chen Xiaoyu
 * @author Sayen Cool
 */
@Service
public class SuperServiceImpl implements SuperService {

    private Logger logger = LogManager.getLogger(SuperServiceImpl.class);

    @Autowired
    private CustomMapper customMapper;
    @Autowired
    private COrderMapper cOrderMapper;
    @Autowired
    private ManufactureMapper manufactureMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private WorkMapper workMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private TechnologyMapper technologyMapper;
    @Autowired
    private TechnologyPlanMapper technologyPlanMapper;
    @Autowired
    private TechnologyRequirementMapper technologyRequirementMapper;
    @Autowired
    private ProcessMapper processMapper;
    @Autowired
    private DeviceFaultMapper deviceFaultMapper;
    @Autowired
    private DeviceCheckMapper deviceCheckMapper;
    @Autowired
    private DeviceTypeMapper deviceTypeMapper;
    @Autowired
    private DeviceMaintainMapper deviceMaintainMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private FinalCountCheckMapper finalCountCheckMapper;
    @Autowired
    private FinalMeasuretCheckMapper finalMeasuretCheckMapper;
    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private MaterialConsumeMapper materialConsumeMapper;
    @Autowired
    private MaterialReceiveMapper materialReceiveMapper;
    @Autowired
    private ProcessCountCheckMapper processCountCheckMapper;
    @Autowired
    private ProcessMeasureCheckMapper processMeasureCheckMapper;
    @Autowired
    private UnqualifyApplyMapper unqualifyApplyMapper;


    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    private static final String PACAKEGE_NAME = "com.cskaoyan.domain.";
    private static final String EXAMPLE = "Example";
    private static final String MAPPER = "Mapper";
    private static final String LIKE = "Like";
    private static final String IN = "In";
    private static final String EQUAL_TO = "EqualTo";
    private static String fileRootPath;
    private static String[] SAVE_FILE = {"image", "file"};

    static {
        // 上传的文件在WEB-INF之外
        fileRootPath = Objects.requireNonNull(SuperServiceImpl.class.getClassLoader().getResource("../../")).getPath();
        // 防止抛异常
        new File(fileRootPath).mkdirs();
    }

    @Override
    public Object selectByCustom(String domain, String name, String searchValue) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException {
        // 自定义精确查询条件，根据条件查找全部，不分页
        Object example = getExample(domain);
        Map domainIfMulti = ParseDomainConfigFile.getDomainIfMulti(domain);
        try {
            setSingleExample(name, searchValue, EQUAL_TO, String.class, example);
        } catch (NoSuchMethodException e) {
            setSingleExample(name, searchValue, EQUAL_TO, Integer.class, example);
        }
        List selectByExample = (List) changeAndFind(domain, "selectByExample", example.getClass(), example);
        List dealExampleResult = dealExampleResults(selectByExample, domainIfMulti);
        return dealExampleResult;
    }

    @Override
    public Integer deleteByCustom(String domain, String ids) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        return (Integer) changeAndFind(domain, "deleteByCustom", String.class, ids);
    }

    @Override
    public Object selectByPrimaryKey(String domain, String id) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Object selectByPrimaryKey = selectByPrimaryKeyFromOnlyOneTable(domain, id);
        Map domainIfMulti = ParseDomainConfigFile.getDomainIfMulti(domain);
        return dealExampleResult(domainIfMulti, selectByPrimaryKey);
    }

    private Object selectByPrimaryKeyFromOnlyOneTable(String domain, String id) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        Object o = null;
        try {
            o = changeAndFind(domain, "selectByPrimaryKey", String.class, id);
        } catch (NoSuchMethodException e) {
            o = changeAndFind(domain, "selectByPrimaryKey", Integer.class, id);
        }
        return o;
    }

    @Override
    public Integer insert(String domain, Object object) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        return (Integer) changeAndFind(domain, "insertSelective", object.getClass(), object);
    }

    @Override
    public Integer updateByPrimaryKey(String domain, Object object) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        return (Integer) changeAndFind(domain, "updateByPrimaryKeySelective", object.getClass(), object);
    }

    @Override
    public Object findAll(String domain) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException {
        Object example = getExample(domain);
        return changeAndFind(domain, "selectByExample", example.getClass(), example);
    }

    @Override
    @Transactional
    public Integer deleteByPrimaryKeyS(String domain, String[] ids) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        int deleteResult = 0;
        for (String id : ids) {
            for (String saveFile : SAVE_FILE) {
                try {
                    Object byId = selectByPrimaryKeyFromOnlyOneTable(domain, id);
                    Field declaredField = byId.getClass().getDeclaredField(saveFile);
                    declaredField.setAccessible(true);
                    Object o = declaredField.get(byId);
                    if (null != o) {
                        String[] splits = ((String) o).split(",");
                        for (String split : splits) {
                            MyFileUtils.deleteFile(fileRootPath, split);
                        }
                    }
                } catch (NoSuchFieldException e) {
                    logger.info(domain + "没有" + saveFile + "属性");
                }
            }
            if (1 == (Integer) changeAndFind(domain, "deleteByPrimaryKey", String.class, id)) {
                deleteResult++;
            }
        }
        return deleteResult;
    }

    @Override
    public PageBean findBySearchCondition(String domain, String name, String searchValue, Integer page, Integer rows) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        PageBean pageBean = null;
        if ("roleName".equals(name)) {
            List<Map> list = new ArrayList<>();
            domain = "sysRole";
            Object example = getExample(domain);
            Map domainIfMulti = ParseDomainConfigFile.getDomainIfMulti(domain);
            setExample(name, "%" + searchValue + "%", example, domainIfMulti);
            PageHelper.startPage(page, rows, true);
            List selectByExample = (List) changeAndFind(domain, "selectByExample", example.getClass(), example);

            for (Object o : selectByExample) {
                String id = null;
                try {
                    List selectByCustom = (List) selectByCustom("sysUserRole", "roleId", ((SysRole) o).getRoleId());
                    id = ((SysUserRole) selectByCustom.get(0)).getId();
                    list.add((Map) selectByPrimaryKey("sysUser", id));
                } catch (IndexOutOfBoundsException e) {
                    logger.info(e.getMessage());
                }
            }
            // 伪分页，其实会显示全部的
            pageBean = new PageBean<>(selectByExample);
            pageBean.setRows(list);
            pageBean.setSize(list.size());
            pageBean.setTotal(list.size());
        } else {
            Object example = getExample(domain);
            try {
                Map domainIfMulti = ParseDomainConfigFile.getDomainIfMulti(domain);
                setExample(name, "%" + searchValue + "%", example, domainIfMulti);
                PageHelper.startPage(page, rows, true);
                List selectByExample = (List) changeAndFind(domain, "selectByExample", example.getClass(), example);
                List dealExampleResult = dealExampleResults(selectByExample, domainIfMulti);
                pageBean = new PageBean<>(selectByExample);
                pageBean.setRows(dealExampleResult);
//        logger.info("pageBean = " + pageBean);
            } catch (IllegalArgumentException e) {
                logger.info("查不到" + e.getMessage());
                pageBean = new PageBean<>(new ArrayList());
            }
        }

        /*catch (NullPointerException e) {
            logger.info("role表查找");
            Field field = o.getClass().getDeclaredFields()[0];
            field.setAccessible(true);
            SysUserRole search = (SysUserRole) field.get(o);

            Object selectByCustom = selectByCustom("sysUserRole", "roleId", search.getRoleId());
        }*/
        return pageBean;
    }

    private List dealExampleResults(List selectByExample, Map domainIfMulti) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException, InstantiationException {
        ArrayList<Object> dealExampleResult = new ArrayList<>();
        for (Object o : selectByExample) {
            try {
                Object newObject = dealExampleResult(domainIfMulti, o);
                dealExampleResult.add(newObject);
            } catch (NullPointerException e) {
                logger.info("对应的字段为空。" + e.getMessage());
            }
        }
        return dealExampleResult;
    }

    private Object dealExampleResult(Map domainIfMulti, Object o) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Object newObject = o;
        String flag = (String) domainIfMulti.get("flag");
        if (flag.startsWith("多表")) {
            newObject = getNewObject(domainIfMulti, o);
        }
        if (flag.startsWith("中间表")) {
            newObject = getNewObject2(domainIfMulti, o);
        }
        return newObject;
    }

    private Object getNewObject2(Map domainIfMulti, Object o) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException, InstantiationException {
        Map transferToMap = transferDomainToMap(o);
        Map extendMap = (Map) domainIfMulti.get("extendMap");
        String jointTable = (String) domainIfMulti.get("jointTable");
        List<String> jointNeed = (List) domainIfMulti.get("jointNeed");
        String multiTable = (String) domainIfMulti.get("multiTable");
        List extendNeed = (List) domainIfMulti.get("extendNeed");

        List selectByCustom = (List) selectByCustom(jointTable, jointNeed.get(0), (String) transferToMap.get(extendMap == null ? jointNeed.get(0) : extendMap.get(jointNeed.get(0))));
//        Object selectByPrimaryKeyFromOnlyOneTable = selectByPrimaryKeyFromOnlyOneTable(jointTable, (String) transferToMap.get(extendMap == null ? jointNeed.get(0) : extendMap.get(jointNeed.get(0))));

        try {
            Object jointByCustom = selectByCustom.get(0);
            Field declaredField = jointByCustom.getClass().getDeclaredField(jointNeed.get(1));
            declaredField.setAccessible(true);
            Object jointVal = declaredField.get(jointByCustom);
            transferToMap.put(extendMap == null ? jointNeed.get(1) : extendMap.get(jointNeed.get(1)), jointVal);

            Object resultVal = selectByPrimaryKeyFromOnlyOneTable(multiTable, (String) jointVal);
            setExtendField(transferToMap, extendMap, extendNeed, resultVal);
        } catch (IndexOutOfBoundsException e) {
            logger.info("抓一下" + e.getMessage());
        }

        return transferToMap;
    }

    private Object getNewObject(Map domainIfMulti, Object o) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Map transferToMap = transferDomainToMap(o);
        List<String> multiNeed = (List) domainIfMulti.get("multiNeed");
        String flag = (String) domainIfMulti.get("flag");
        Map extendMap = (Map) domainIfMulti.get("extendMap");
        for (String s : multiNeed) {
            String domainFromAnotherTable = getDomainMappingTable(s);
            Object selectByPrimaryKeyFromOnlyOneTable = selectByPrimaryKeyFromOnlyOneTable(domainFromAnotherTable, (String) transferToMap.get(extendMap == null ? s : extendMap.get(s)));
            switch (flag) {
                case "多表嵌套":
                    transferToMap.put(domainFromAnotherTable, selectByPrimaryKeyFromOnlyOneTable);
                    break;
                case "多表平铺":
                case "多表平铺字段重新映射":
                    List<String> extendNeed = (List<String>) domainIfMulti.get("extendNeed");
                    setExtendField(transferToMap, extendMap, extendNeed, selectByPrimaryKeyFromOnlyOneTable);
                    break;
                default:
            }
        }
        return transferToMap;
    }

    private void setExtendField(Map transferToMap, Map extendMap, List<String> extendNeed, Object selectByPrimaryKeyFromOnlyOneTable) throws IllegalAccessException {
        Iterator<String> iterator = extendNeed.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            try {
                Field declaredField = selectByPrimaryKeyFromOnlyOneTable.getClass().getDeclaredField(name);
                declaredField.setAccessible(true);
                transferToMap.put(extendMap == null ? name : extendMap.get(name), declaredField.get(selectByPrimaryKeyFromOnlyOneTable));
//                iterator.remove();
            } catch (NoSuchFieldException e) {
                logger.info(e);
            }
        }
    }

    private HashMap<String, Object> transferDomainToMap(Object o) throws IllegalAccessException {
        Field[] fields = o.getClass().getDeclaredFields();
        HashMap<String, Object> newObject = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(o);
            newObject.put(field.getName(), fieldValue);
        }
        return newObject;
    }

    private Object getExample(String domain) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> paramType = Class.forName(PACAKEGE_NAME + StringUtil.upperCaseFirstLatter(domain) + EXAMPLE);
        return paramType.newInstance();
    }

    private void setExample(String name, String searchValue, Object example, Map domainIfMulti) throws NullPointerException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        try {
            setSingleExample(name, searchValue, LIKE, String.class, example);
        } catch (NoSuchMethodException e) {
            System.out.println(e.toString());
            setMutiExampleWithoutNewMapping(name, searchValue, example, (List<String>) domainIfMulti.get("multiNeed"));
//            String flag = (String) domainIfMulti.get("flag");
//            switch (flag) {
//                case "多表嵌套":
//                case "多表平铺":
//
//                    break;
//                case "多表平铺字段重新映射":
//                    setMutiExampleWithNewMapping(name, searchValue, example, domainIfMulti);
//                    break;
//            }
        }
    }

    private void setMutiExampleWithNewMapping(String name, String searchValue, Object example, Map domainIfMulti) {
    }

    private void setSingleExample(String name, Object searchValue, String searchMethod, Class searchConditionClass, Object example) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        Object or = example.getClass().getDeclaredMethod("or").invoke(example);
        Method declaredMethod = null;
        try {
            declaredMethod = or.getClass().getDeclaredMethod("and" + StringUtil.upperCaseFirstLatter(name) + searchMethod, searchConditionClass);
            declaredMethod.invoke(or, searchValue);
        } catch (IllegalAccessException | InvocationTargetException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.toString());
        }
    }

    private void setMutiExampleWithoutNewMapping(String name, Object searchValue, Object example, List<String> multiNeed) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, NullPointerException {
        String domainFromAnotherTable = getDomainMappingTable(name);
        domainFromAnotherTable = "role".equals(domainFromAnotherTable) ? "sysRole" : domainFromAnotherTable;
        Object exampleFromAnotherTable = getExample(domainFromAnotherTable);
        setSingleExample(name, searchValue, LIKE, String.class, exampleFromAnotherTable);
        List selectByExampleFromAntoherTable = (List) changeAndFind(domainFromAnotherTable, "selectByExample", exampleFromAnotherTable.getClass(), exampleFromAnotherTable);
        String intersectKey = "";
        if (selectByExampleFromAntoherTable.size() > 0) {
            Object o = selectByExampleFromAntoherTable.get(0);
            for (String s : multiNeed) {
                try {
                    o.getClass().getDeclaredField(s);
                    intersectKey = s;
                    break;
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
        ArrayList<String> keys = new ArrayList<>();
        for (Object o : selectByExampleFromAntoherTable) {
            Field declaredField = o.getClass().getDeclaredField(intersectKey);
            declaredField.setAccessible(true);
            keys.add((String) declaredField.get(o));
        }
        if (keys.size() > 0) {
            setSingleExample(intersectKey, keys, IN, List.class, example);
        } else {
            throw new IllegalArgumentException("找不到结果");
        }
    }

    private String getDomainMappingTable(String name) {
        String[] split = name.split("(?<!^)(?=[A-Z])");
        StringBuilder domainMappingTable = new StringBuilder();
        for (int i = 0; i < split.length - 1; i++) {
            if ("order".equals(split[i])) {
                split[i] = "cOrder";
            } else if ("emp".equals(split[i])) {
                split[i] = "employee";
            }
            domainMappingTable.append(split[i]);
        }
        return domainMappingTable.toString();
    }

    private Object changeAndFind(String domain, String methodName, Class paramType, Object... object) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        Object mapper = getMapper(domain);
        Method declaredMethod = mapper.getClass().getDeclaredMethod(methodName, paramType);
        return declaredMethod.invoke(mapper, object);
    }

    private Object getMapper(String domain) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(domain + "Mapper");
        field.setAccessible(true);
        return field.get(this);
    }
}
