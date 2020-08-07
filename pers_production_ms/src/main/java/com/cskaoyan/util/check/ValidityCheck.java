package com.cskaoyan.util.check;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Set;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author Sayen Cool
 */
public class ValidityCheck {

    private static Logger logger = LogManager.getLogger(ValidityCheck.class);
    private static final String FILE_CHECK_PASS = null;
    private static final String[] PIC_TYPE = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};

    public static boolean isFileExist(String rootPath, String fileSaveRelativeUrl) {
        return new File(rootPath, fileSaveRelativeUrl).exists();
    }

    public static String checkUploadFile(MultipartFile file, String prefix) {
        logger.info("enter ValidityCheck checkUploadFile");

        String msg = FILE_CHECK_PASS;

        if (null == file) {
            msg = "未上传文件！";
        } else if (0 == file.getSize()) {
            msg = "未上传文件或本地文件损坏，请重新上传！";
        } else if ("/pic".equals(prefix)) {
            String[] check = PIC_TYPE;
            String name = file.getOriginalFilename();
            int i;
            for (i = 0; i < check.length; i++) {
                if (name.endsWith(check[i])) {
                    break;
                }
            }
            if (i == check.length) {
                msg = "图片上传格式错误！<br>应为" + check;
            }
        }

        return msg;
    }

    public static String checkUpdateFile(MultipartFile file, String rootPath, String fileSaveRelativeUrl) {
        logger.info("enter ValidityCheck checkUpdateFile");

        String msg = FILE_CHECK_PASS;

        if (null == file) {
            msg = "未上传文件！";
        } else if (0 == file.getSize()) {
            msg = "未上传文件或本地文件损坏，请重新上传！";
        } else {
            int index = fileSaveRelativeUrl.lastIndexOf("/");
            String originalFileName = fileSaveRelativeUrl.substring(index + 37);
            if (originalFileName.endsWith(file.getOriginalFilename()) || new File(rootPath, fileSaveRelativeUrl).length() == file.getSize()) {
                msg = "文件无变化！";
            }
        }

        return msg;
    }

    /**
     * 使用hibernate的注解来进行验证
     *
     */
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    /**
     * 功能描述: <br>
     * 〈注解验证参数〉
     *
     * @param obj
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> Set<ConstraintViolation<T>> validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        return constraintViolations;
    }

//    public static Object findObject(Object targetObj, String fieldName) throws SQLException, IllegalAccessException {
//        Object findObj = null;
//
//        Class<?> targetObjClass = targetObj.getClass();
//
//        String tableName = getTableName(targetObj);
//
//        Field[] declaredFields = targetObj.getClass().getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            declaredField.setAccessible(true);
//            if (fieldName.equals(declaredField.getName())) {
//                ComboPooledDataSource dataSource = C3P0Utils.getDataSource();
//                QueryRunner queryRunner = new QueryRunner(dataSource);
//
////                System.out.println(targetObjClass);
////                System.out.println(declaredField.getName());
//
//                // 注意SQL语句中的where id='' 单引号问题
//                findObj = queryRunner.query("select * from "
//                                + tableName + " WHERE " + fieldName + "='" + declaredField.get(targetObj) + "';",
//                        new BeanHandler<>(targetObjClass));
//
////                System.out.println(findObj);
//
//                break;
//            }
//        }
//        return findObj;
//    }
}
