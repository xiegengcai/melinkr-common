package com.melinkr.common.poi.reader;

import com.melinkr.common.poi.writer.IRowWriter;
import com.melinkr.common.utils.ExcelUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Created by <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a> on 16-10-15.
 */
public class ExcelUtilTest {

    private IRowReader rowReader;
    private String excel2003;
    private String excel2007;

    @Before
    public void init(){
        excel2003 = "/tmp/2003.xls";
        excel2007 = "/tmp/2007.xlsx";
        rowReader = (sheetIndex, curRow, rowlist) -> {
            System.out.print(curRow+" ");
            for (int i = 0; i < rowlist.size(); i++) {
                System.out.print(rowlist.get(i) + " ");
            }
            System.out.println();
        };
    }


    @Test
    public void readeExcel2003ByName() throws Exception {
        ExcelUtil.readExcel(rowReader, excel2003);
    }

    @Test
    public void readeExcel2003ByFile() throws Exception {
        ExcelUtil.readExcel(rowReader, new File(excel2003));
    }

    @Test
    public void readeExcel2003ByInputStream() throws Exception {
        ExcelUtil.readExcel(rowReader, new FileInputStream(excel2003), excel2003);
    }

    @Test
    public void readeExcel2007ByName() throws Exception {
        ExcelUtil.readExcel(rowReader, excel2007);
    }

    @Test
    public void readeExcel2007ByFile() throws Exception {
        ExcelUtil.readExcel(rowReader, new File(excel2007));
    }

    @Test
    public void readeExcel2007ByInputStream() throws Exception {
        ExcelUtil.readExcel(rowReader, new FileInputStream(excel2007), excel2007);
    }

    @Test
    public void writeExcelTest() throws Exception {
        writeExcel(excel2003);
        writeExcel(excel2007);
    }

    private void writeExcel(String fileName) throws Exception {
        Map<String, List<User>> sheetDateMap = new HashMap<>();
        List<User> sheet1 = new ArrayList<>();
        sheet1.add(new User("张三", 12, DateUtils.parseDate("1990-11-02", DateFormatUtils.ISO_DATE_FORMAT.getPattern())));
        sheet1.add(new User("李四", 11, DateUtils.parseDate("1990-09-02", DateFormatUtils.ISO_DATE_FORMAT.getPattern())));
        sheet1.add(new User("王五", 13, DateUtils.parseDate("1990-12-02", DateFormatUtils.ISO_DATE_FORMAT.getPattern())));
        sheetDateMap.put("一班", sheet1);
        List<User> sheet2 = new ArrayList<>();
        sheet2.add(new User("Lily", 12, DateUtils.parseDate("1990-06-30", DateFormatUtils.ISO_DATE_FORMAT.getPattern())));
        sheet2.add(new User("Andy", 11, DateUtils.parseDate("1990-09-12", DateFormatUtils.ISO_DATE_FORMAT.getPattern())));
        sheet2.add(new User("Jack", 13, DateUtils.parseDate("1990-12-12", DateFormatUtils.ISO_DATE_FORMAT.getPattern())));
        sheetDateMap.put("二班", sheet2);
        List<String> headers = new ArrayList<>();
        headers.add("姓名");
        headers.add("年龄");
        headers.add("生日");
        ExcelUtil.writeExcel(fileName, (row, user) -> {
            int cellIndex = 0;
            row.createCell(cellIndex++).setCellValue(user.getName());
            row.createCell(cellIndex++).setCellValue(user.getAge());
            row.createCell(cellIndex++).setCellValue(DateFormatUtils.format(user.getBirthDay(), DateFormatUtils.ISO_DATE_FORMAT.getPattern()));
        }, headers, sheetDateMap);
    }

    class User {
        private String name;
        private int age;
        private Date birthDay;

        public User(String name, int age, Date birthDay) {
            this.name = name;
            this.age = age;
            this.birthDay = birthDay;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Date getBirthDay() {
            return birthDay;
        }

        public void setBirthDay(Date birthDay) {
            this.birthDay = birthDay;
        }
    }

}
