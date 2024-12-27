package com.jst.clyycx;


import com.alibaba.fastjson.JSON;
import com.jst.clyycx.dao.ClyydatecodeMapper;
import com.jst.clyycx.utils.DateUtil;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spring.web.json.Json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest()
public class HolidayGenerate {

    static SimpleDateFormat yyyyMMddFMT = new SimpleDateFormat("yyyy-MM-dd") ;
    private static int YEAR = 2025;


    /**
     * 全年假期集合 = [查询全年的双休周末] + [得到所有的法定节假日]
     */
   @Test
   public void Generate2025(){
       List<String> yearRoundHolidayCollection = getYearRoundHolidayCollection();

       System.out.printf(insertSql(yearRoundHolidayCollection));
    }

    /**
     * 根据日期生成插入sql
     * @return
     */
    public String insertSql(List<String> list){
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach( l ->{
            stringBuilder.append(
                    String.format("INSERT INTO `date_code`( DATE_CODE, DATE_WEEK, DATE_TYPE, YEAR, CREATE_TIME, CREATE_USERCODE, CREATE_USERNAME, CREATE_IP ) " +
                                    "VALUES ('%s', '%s', '%s', '%s', NOW(), 'admin', '管理员', '127.0.0.1');"
                            , l , stringToWeek(l),"1",YEAR)).append("\n");
        } );
        return stringBuilder.toString();
    }

    public String stringToWeek(String s) {
        try {
            Date date = yyyyMMddFMT.parse(s);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            // 映射星期日为7，其他天向前移
            String[] weeks = {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
            return weeks[dayOfWeek];
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 全年假期集合
     * @return
     */
    public List<String> getYearRoundHolidayCollection(){
        Set<String> allHolidays = new HashSet<>();
        // [+]全年的双休周末
        Set<String> yearDoubleWeekend = getWeekDayList(YEAR);
        allHolidays.addAll(yearDoubleWeekend);
        // [+] 法定节假日
        Set<String> legalHolidays = getLegalHoliday();
        allHolidays.addAll(legalHolidays);
        List<String> collect = allHolidays.stream().sorted().collect(Collectors.toList());
        return  collect;
    }

    /**
     * 2024年的法定节假日
     * @return
     */
    public Set<String> getLegalHoliday(){
        Set<String> holidays = new HashSet<String>();
        // 添加2025年的所有非工作日
        holidays.add("2025-01-01"); // 元旦
        holidays.add("2025-01-04"); // 周末
        holidays.add("2025-01-05"); // 周末
        holidays.add("2025-01-11"); // 周末
        holidays.add("2025-01-12"); // 周末
        holidays.add("2025-01-18"); // 周末
        holidays.add("2025-01-19"); // 周末
        holidays.add("2025-01-25"); // 周末
        holidays.add("2025-01-28"); // 春节
        holidays.add("2025-01-29"); // 春节
        holidays.add("2025-01-30"); // 春节
        holidays.add("2025-01-31"); // 春节
        holidays.add("2025-02-01"); // 春节
        holidays.add("2025-02-02"); // 春节
        holidays.add("2025-02-03"); // 春节
        holidays.add("2025-02-04"); // 春节
        holidays.add("2025-02-09"); // 周末
        holidays.add("2025-02-15"); // 周末
        holidays.add("2025-02-16"); // 周末
        holidays.add("2025-02-22"); // 周末
        holidays.add("2025-02-23"); // 周末
        holidays.add("2025-03-01"); // 周末
        holidays.add("2025-03-02"); // 周末
        holidays.add("2025-03-08"); // 周末
        holidays.add("2025-03-09"); // 周末
        holidays.add("2025-03-15"); // 周末
        holidays.add("2025-03-16"); // 周末
        holidays.add("2025-03-22"); // 周末
        holidays.add("2025-03-23"); // 周末
        holidays.add("2025-03-29"); // 周末
        holidays.add("2025-03-30"); // 周末
        holidays.add("2025-04-04"); // 清明节
        holidays.add("2025-04-05"); // 清明节
        holidays.add("2025-04-06"); // 清明节
        holidays.add("2025-04-12"); // 周末
        holidays.add("2025-04-13"); // 周末
        holidays.add("2025-04-19"); // 周末
        holidays.add("2025-04-20"); // 周末
        holidays.add("2025-04-26"); // 周末
        holidays.add("2025-05-01"); // 劳动节
        holidays.add("2025-05-02"); // 劳动节
        holidays.add("2025-05-03"); // 劳动节
        holidays.add("2025-05-04"); // 劳动节
        holidays.add("2025-05-05"); // 劳动节
        holidays.add("2025-05-10"); // 周末
        holidays.add("2025-05-11"); // 周末
        holidays.add("2025-05-17"); // 周末
        holidays.add("2025-05-18"); // 周末
        holidays.add("2025-05-24"); // 周末
        holidays.add("2025-05-25"); // 周末
        holidays.add("2025-05-31"); // 端午节
        holidays.add("2025-06-01"); // 端午节
        holidays.add("2025-06-02"); // 端午节
        holidays.add("2025-06-07"); // 周末
        holidays.add("2025-06-08"); // 周末
        holidays.add("2025-06-14"); // 周末
        holidays.add("2025-06-15"); // 周末
        holidays.add("2025-06-21"); // 周末
        holidays.add("2025-06-22"); // 周末
        holidays.add("2025-06-28"); // 周末
        holidays.add("2025-06-29"); // 周末
        holidays.add("2025-07-05"); // 周末
        holidays.add("2025-07-06"); // 周末
        holidays.add("2025-07-12"); // 周末
        holidays.add("2025-07-13"); // 周末
        holidays.add("2025-07-19"); // 周末
        holidays.add("2025-07-20"); // 周末
        holidays.add("2025-07-26"); // 周末
        holidays.add("2025-07-27"); // 周末
        holidays.add("2025-08-02"); // 周末
        holidays.add("2025-08-03"); // 周末
        holidays.add("2025-08-09"); // 周末
        holidays.add("2025-08-10"); // 周末
        holidays.add("2025-08-16"); // 周末
        holidays.add("2025-08-17"); // 周末
        holidays.add("2025-08-23"); // 周末
        holidays.add("2025-08-24"); // 周末
        holidays.add("2025-08-30"); // 周末
        holidays.add("2025-08-31"); // 周末
        holidays.add("2025-09-06"); // 周末
        holidays.add("2025-09-07"); // 周末
        holidays.add("2025-09-13"); // 周末
        holidays.add("2025-09-14"); // 周末
        holidays.add("2025-09-20"); // 周末
        holidays.add("2025-09-21"); // 周末
        holidays.add("2025-09-27"); // 周末
        holidays.add("2025-10-01"); // 国庆节、中秋节
        holidays.add("2025-10-02"); // 国庆节、中秋节
        holidays.add("2025-10-03"); // 国庆节、中秋节
        holidays.add("2025-10-04"); // 国庆节、中秋节
        holidays.add("2025-10-05"); // 国庆节、中秋节
        holidays.add("2025-10-06"); // 国庆节、中秋节
        holidays.add("2025-10-07"); // 国庆节、中秋节
        holidays.add("2025-10-08"); // 国庆节、中秋节
        holidays.add("2025-10-12"); // 周末
        holidays.add("2025-10-18"); // 周末
        holidays.add("2025-10-19"); // 周末
        holidays.add("2025-10-25"); // 周末
        holidays.add("2025-10-26"); // 周末
        holidays.add("2025-11-01"); // 周末
        holidays.add("2025-11-02"); // 周末
        holidays.add("2025-11-08"); // 周末
        holidays.add("2025-11-09"); // 周末
        holidays.add("2025-11-15"); // 周末
        holidays.add("2025-11-16"); // 周末
        holidays.add("2025-11-22"); // 周末
        holidays.add("2025-11-23"); // 周末
        holidays.add("2025-11-29"); // 周末
        holidays.add("2025-11-30"); // 周末
        holidays.add("2025-12-06"); // 周末
        holidays.add("2025-12-07"); // 周末
        holidays.add("2025-12-13"); // 周末
        holidays.add("2025-12-14"); // 周末
        holidays.add("2025-12-20"); // 周末
        holidays.add("2025-12-21"); // 周末
        holidays.add("2025-12-27"); // 周末
        holidays.add("2025-12-28"); // 周末
        return holidays;
    }



    /**
     * 查询全年的双休周末
     * @param year
     * @return
     */
    public static Set<String> getWeekDayList(int year) {
        Set<String> listDate = new HashSet<>();
        int i = 1;
        Calendar calendar = new GregorianCalendar(year, 0, 1);

        while (calendar.get(Calendar.YEAR) < year + 1) {
            calendar.set(Calendar.WEEK_OF_YEAR, i++);

            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            if (calendar.get(Calendar.YEAR) == year) {
                listDate.add(yyyyMMddFMT.format(calendar.getTime()));
            }
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            if (calendar.get(Calendar.YEAR) == year) {
                listDate.add(yyyyMMddFMT.format(calendar.getTime()));
            }
        }
        return listDate;
    }

}
