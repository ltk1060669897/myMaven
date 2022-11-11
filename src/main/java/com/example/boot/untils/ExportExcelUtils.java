package com.example.boot.untils;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.thymeleaf.util.StringUtils;

/** @author ltk */
public class ExportExcelUtils {

  /**
   * 下载文件
   *
   * @param response
   * @param fileName
   * @param data
   * @throws Exception
   */
  public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data)
      throws Exception {
    // 告诉浏览器用什么软件可以打开此文件
    response.setHeader("content-Type", "application/vnd.ms-excel");
    // 下载文件的默认名称
    response.setHeader(
        "Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
    exportExcel(data, response.getOutputStream());
  }

  /**
   * 创建 表格
   *
   * @param data
   * @param out
   * @throws Exception
   */
  public static void exportExcel(ExcelData data, OutputStream out) throws Exception {
    XSSFWorkbook wb = new XSSFWorkbook();
    try {
      String sheetName = data.getName();
      if (null == sheetName) {
        sheetName = "Sheet1";
      }
      XSSFSheet sheet = wb.createSheet(sheetName);
      writeExcel(wb, sheet, data);
      wb.write(out);
    } finally {
      wb.close();
    }
  }

  /**
   * 将数据写入表格
   *
   * @param wb
   * @param sheet
   * @param data
   */
  private static void writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {
    int rowIndex = 0;
    rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());
    writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
    autoSizeColumns(sheet, data.getTitles().size() + 1);
  }

  /**
   * 写入表头
   *
   * @param wb
   * @param sheet
   * @param titles
   * @return
   */
  private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
    int rowIndex = 0;
    int colIndex;
    // 获取字体
    XSSFFont titleFont = wb.createFont();
    // 设置字体名称（宋体）
    titleFont.setFontName("simsun");
    // 设置字体加粗
    titleFont.setBold(true);

    //    String str = "#3366FF";
    //    //处理把它转换成十六进制并放入一个数
    //    int[] color=new int[3];
    //    color[0]=Integer.parseInt(str.substring(1, 3), 16);
    //    color[1]=Integer.parseInt(str.substring(3, 5), 16);
    //    color[2]=Integer.parseInt(str.substring(5, 7), 16);
    //    HSSFWorkbook hw = new HSSFWorkbook();
    //    //wb HSSFWorkbook对象
    //    HSSFPalette palette = hw.getCustomPalette();
    //    palette.setColorAtIndex(HSSFColor.HSSFColorPredefined.BROWN.getIndex(), (byte) color[0],
    //        (byte) color[1], (byte) color[2]);

    // 设置字体颜色
    titleFont.setColor(createXssfColor("#c25908"));
    // 获取单元格样式
    XSSFCellStyle titleStyle = wb.createCellStyle();
    // 设置单元格的水平对齐类型(这里是水平居中)
    titleStyle.setAlignment(HorizontalAlignment.CENTER);
    // 设置单元格的垂直对齐类型（这里是居中）
    titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    // 设置单元格前景色（白色）
    titleStyle.setFillForegroundColor(createXssfColor("#FFFFFF"));
    // 指定图案和纯色单元格填充的单元格填充信息（实心前景）
    titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    // 设置字体样式
    titleStyle.setFont(titleFont);
    // 设置边框样式（细线、黑色）
    setBorder(titleStyle, BorderStyle.THIN, createXssfColor("#000000"));
    // 在该工作簿中创建第一行.
    Row titleRow = sheet.createRow(rowIndex);
    colIndex = 0;
    // 循环创建列
    for (String field : titles) {
      Cell cell = titleRow.createCell(colIndex);
      cell.setCellValue(field);
      cell.setCellStyle(titleStyle);
      colIndex++;
    }
    // 将行数++ 返回用于下面添加数据
    rowIndex++;
    return rowIndex;
  }

  /**
   * 将数据写入
   *
   * @param wb
   * @param sheet
   * @param rows
   * @param rowIndex
   * @return
   */
  private static int writeRowsToExcel(
      XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
    int colIndex;
    // 获取字体
    Font dataFont = wb.createFont();
    // 设置字体名称（宋体）
    dataFont.setFontName("simsun");
    // 设置字体颜色 黑色
    dataFont.setColor(IndexedColors.BLACK.index);
    // 获取单元格样式
    XSSFCellStyle dataStyle = wb.createCellStyle();
    // 设置单元格的水平对齐类型(这里是水平居中)
    dataStyle.setAlignment(HorizontalAlignment.CENTER);
    // 设置单元格的垂直对齐类型（这里是居中）
    dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    // 设置字体样式
    dataStyle.setFont(dataFont);
    // 设置边框样式（细线、黑色）
    setBorder(dataStyle, BorderStyle.THIN, createXssfColor("#000000"));
    // 循环写入数据
    for (List<Object> rowData : rows) {
      Row dataRow = sheet.createRow(rowIndex);
      colIndex = 0;
      for (Object cellData : rowData) {
        Cell cell = dataRow.createCell(colIndex);
        if (cellData != null) {
          cell.setCellValue(cellData.toString());
        } else {
          cell.setCellValue("");
        }

        cell.setCellStyle(dataStyle);
        colIndex++;
      }
      rowIndex++;
    }
    return rowIndex;
  }

  /**
   * 自动调整大小
   *
   * @param sheet
   * @param columnNumber
   */
  private static void autoSizeColumns(Sheet sheet, int columnNumber) {
    for (int i = 0; i < columnNumber; i++) {
      int orgWidth = sheet.getColumnWidth(i);
      sheet.autoSizeColumn(i, true);
      int newWidth = (int) (sheet.getColumnWidth(i) + 100);
      if (newWidth > orgWidth) {
        sheet.setColumnWidth(i, newWidth);
      } else {
        sheet.setColumnWidth(i, orgWidth);
      }
    }
  }

  /**
   * 设置表格样式
   *
   * @param style
   * @param border
   * @param color
   */
  private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
    style.setBorderTop(border);
    style.setBorderLeft(border);
    style.setBorderRight(border);
    style.setBorderBottom(border);
    style.setBorderColor(XSSFCellBorder.BorderSide.TOP, color);
    style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, color);
    style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, color);
    style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, color);
  }

  /**
   * 将rgb颜色码 转换为 XSSFColor
   *
   * @param color
   * @return
   */
  private static XSSFColor createXssfColor(String color) {
    int[] rgbColor = hexToRgb(color);
    XSSFColor xssfColor =
        new XSSFColor(
            new java.awt.Color(rgbColor[0], rgbColor[1], rgbColor[2]),
            new DefaultIndexedColorMap());
    return xssfColor;
  }

  /**
   * 将颜色码 转换为 r g b
   *
   * @param hex
   * @return
   */
  public static int[] hexToRgb(String hex) {
    String colorStr = hex;
    if (hex.startsWith("#")) {
      colorStr = hex.substring(1);
    }
    if (StringUtils.length(colorStr) == 8) {
      colorStr = hex.substring(2);
    }
    int r = Integer.valueOf(colorStr.substring(0, 2), 16);
    int g = Integer.valueOf(colorStr.substring(2, 4), 16);
    int b = Integer.valueOf(colorStr.substring(4, 6), 16);

    return new int[] {r, g, b};
  }
}
