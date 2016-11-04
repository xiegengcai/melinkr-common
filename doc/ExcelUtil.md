## Excel文件读取帮助类使用指南
## 支持文件类型
1. Excle 2003及以下版本（.xls)。
1. Excel 2007及以上版本（.xlsx)。
1. 读取时按文件名后缀自动区分是Excle 2003还是Excle 2007
1. 写入时自动根据保存文件名后缀自动区分保存为Excle 2003还是Excle 2007格式

### 引入依赖

```xml
<dependency>
    <groupId>com.melinkr</groupId>
    <artifactId>melinkr-common</artifactId>
    <version>${melinkr-common.version}</version>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    </dependency>
<dependency>
    <groupId>xerces</groupId>
    <artifactId>xercesImpl</artifactId>
</dependency>
```

### 写Excel文件
1. 实现IRowWriter接口。该接口定义如何写入每一行数据
    ```java
    public class UserRowWriter implements IRowWriter<User>{
      @Override
      public void writeRow(Row row, User user) {
          int cellIndex = 0;
          row.createCell(cellIndex++).setCellValue(user.getName());
          row.createCell(cellIndex++).setCellValue(user.getAge());
          row.createCell(cellIndex++).setCellValue(DateFormatUtils.format(user.getBirthDay(), DateFormatUtils.ISO_DATE_FORMAT.getPattern()));
      }
    }
    // 或Java 8匿名内部类
    IRowWriter<User> rowWriter = (row, user) -> {
         int cellIndex = 0;
         row.createCell(cellIndex++).setCellValue(user.getName());
         row.createCell(cellIndex++).setCellValue(user.getAge());
         row.createCell(cellIndex++).setCellValue(DateFormatUtils.format(user.getBirthDay(), DateFormatUtils.ISO_DATE_FORMAT.getPattern()));
    }
    ```
    
1. 封装数据
    ```java
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
    ```

1. 写入Excel
    ```java
    // 2003
    ExcelUtil.writeExcel("/tmp/测试.xls", rowWriter, headers, sheetDateMap);
    // 2007
    ExcelUtil.writeExcel("/tmp/测试.xlsx", rowWriter, headers, sheetDateMap);
    ```
1. 通过模板写Excel
    ```java
    // fileName方式
    ExcelUtil.writeExcelByTemplate("模板文件.xlsx", "/tmp/结果文件.xls", rowWriter, sheetDateMap);
    // File方式
    ExcelUtil.writeExcelByTemplate(模板文件, "/tmp/结果文件.xls", rowWriter, sheetDateMap);
    ```

### 读取Excel文件
1. 实现IRowReader。该接口定义如何读取一行数据
    ```java
    List<User> userList = new ArrayList();
    IRowReader rowReader = (sheetIndex, curRow, rowlist) -> {
        logger.info("读取第{}个工作薄的第{}行数据", sheetIndex, curRow);
        int cellIndex = 0;
        userList.add(new User(rowList.get(cellIndex++)
            , Integer.parseInt(rowList.get(cellIndex++))
            , DateUtils.parseDate(rowList.get(cellIndex++), DateFormatUtils.ISO_DATE_FORMAT.getPattern())));
    };
    ```
    
1. 读取Excel内容
    ```java
    // 文件名读取
    ExcelUtil.readExcel(rowReader, "/tmp/测试.xls");
    ExcelUtil.readExcel(rowReader, "/tmp/测试.xlsx");
    // 用File对象读取
    ExcelUtil.readExcel(rowReader, new File("/tmp/测试.xlsx"));
    // 用流读取
    MultipartFile file = ;
    ExcelUtil.readExcel(rowReader, file.getInputStream(),file.getOriginalFilename());
    ```