package com.jx.util;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class Excel {
	// 导出的Excel表的sheet名字设置为:用户信息
	public static String tables = "用户信息";
	// sql语句 选择导出数据库的哪个表
	public static String sqls = "select * from sys_users";
	// 导出表的csv文件保存的地址
	public static String outputFile = "/Users/jiangzhengdong/Desktop/项目/用户信息表.xls";

	public static Connection con = null;

	//连接数据库的代码根据自己可以更改  还有表的名字都可以更改

	public void excel() {
		try {
			con = DriverManager.getConnection("jdbc:mysql:///jtsys?useUnicode=true&characterEncoding=utf-8",
					"root", "root");
		} catch (SQLException sqlE) {
			sqlE.printStackTrace();
		}
		// PreparedStatement 预编译
		PreparedStatement ps_struts = null;
		ResultSet rs_struts = null;
		try {
			//HSSFWorkBook：操作2003版本以前的（包括2003版本），扩展名.xls
			//创建Excel文件(Workbook)
			HSSFWorkbook workbook = new HSSFWorkbook();
			String[] tempo;
			int rowNum = 1;
			try {
				//执行数据库的sq语句 
				ps_struts = con.prepareStatement(sqls);
				rs_struts = ps_struts.executeQuery();
				//获取Connection对象所连接的数据库的元数据。元数据包括关于数据库的表
				ResultSetMetaData rsm = rs_struts.getMetaData();
				//创建一个名字为(tables)的表原名字默认为sheet
				HSSFSheet sheet = workbook.createSheet(tables);
				//getRowCount 获取数据库表行的数量  getColumnCount获取列的数量
				int columnCount = rsm.getColumnCount();
				//把表头填好
				try {
					//在Excel里创建行,从0开始
					HSSFRow row1 = sheet.createRow(0);
					for (int i = 1;i<=columnCount;i++) {
						//让Excel自动适应列宽
						sheet.autoSizeColumn(i);
						//获取表列头的名字
						String columnName = rsm.getColumnName(i);
						//创建行的单元格,也是从0开始
						HSSFCell cell1 = row1.createCell(i - 1);
						//设置单元格内容
						cell1.setCellValue(columnName);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				//循环数据库表中的每一行,来得到每一行的数据
				while (rs_struts.next()) {
					//每循环一次创建一行从 1 开始
					HSSFRow row = sheet.createRow(rowNum);
					tempo = new String[columnCount];
					for (int i = 0; i < columnCount; i++) {
						//创建行的单元格,从0开始
						HSSFCell cell = row.createCell(i);
						//让Excel自动适应列宽
						sheet.autoSizeColumn(i);
						//getMetaData().getColumnName(i);字段名
						String columnName = rs_struts.getMetaData().getColumnName(i + 1);
						tempo[i] = rs_struts.getString(columnName);
						//设置单元格内容
						cell.setCellValue(tempo[i]);
					}
					//每次循环结束后要使数组为空，方便存储数据库表中下一行的数值
					tempo = null;
					//rowNum++ 下一次循环创建的行向下
					rowNum++;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			FileOutputStream fOut = new FileOutputStream(outputFile);
			workbook.write(fOut);//保存Excel文件
			//fOut.flush();
			fOut.close();//关闭文件流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Excel l = new Excel();
		l.excel();
	}
}

