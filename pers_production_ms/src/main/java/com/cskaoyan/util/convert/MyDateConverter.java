package com.cskaoyan.util.convert;

import org.springframework.core.convert.converter.Converter;

import java.util.Calendar;
import java.util.Date;

/**
 * 指定日期类型
 * 还需要在XML文件中注册
 *
 * @author Sayen Cool
 */
public class MyDateConverter implements Converter<String[], Date> {

    /*
        如果是enctype="multipart/form-data"形式提交的表单数据，Converter<int/Integer[], Date>都不可以
        会造成 400 bad request
        但是如果不是此multipart/form-data的表单数据提交，也不可以！！！！可能修改了之后没有redeploy，导致成功的幻觉

        convert(String[] o)参数名可以不与实际封装的参数名称一致
    */

    /*@Override
    public Date convert(int[] birthday) {

        int year = birthday[0];
        int month = birthday[1] - 1;
        int date = birthday[2];

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);

        return calendar.getTime();
    }*/

    @Override
    public Date convert(String[] o) {

        int year = Integer.parseInt(o[0]);
        int month = Integer.parseInt(o[1]) - 1;
        int date = Integer.parseInt(o[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);

        return calendar.getTime();
    }
}
