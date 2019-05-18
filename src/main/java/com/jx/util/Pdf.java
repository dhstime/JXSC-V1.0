package com.jx.util;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Pdf {
	//数据表的字段数
	private static int colNum = 12;
	//设置表格的属性
	private static final int spacing = 2;
	private static final int padding = 2;
	//pdf文件路径名
	private static final String outPutFile = "/Users/jiangzhengdong/Desktop/项目/用户信息.pdf";
	//数据库连接参数
	private static String url="jdbc:mysql:///jtsys?useUnicode=true&characterEncoding=utf-8";
	private static String user="root";
	private static String password="root";
	public Pdf() throws SQLException {
		//定义一个文档对象,大小为1000*600像素
		Document document = new Document(new Rectangle(1000, 600), 10, 10, 10, 10);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//数据库连接
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from sys_users";
			//执行查询语句
			ps = conn.prepareStatement(sql);
			//获取结果集
			rs = ps.executeQuery();
			//获取表格的字段描述信息
			ResultSetMetaData metaData = rs.getMetaData();
			colNum = metaData.getColumnCount();
			//在文档中创建一个pdf文件
			PdfWriter.getInstance(document, new FileOutputStream(outPutFile));
			document.open();
			//创建含有colNum个字段的表格
			PdfPTable table = new PdfPTable(colNum);
			int[] cellsWidth = {2,5,8,8,5,5,4,4,6,6,6,6};
			//设置字段单元格的相对大小
			table.setWidths(cellsWidth);
			//设置表格占文档的大小比例
			table.setWidthPercentage(90);
			table.getDefaultCell().setPadding(padding);
			table.getDefaultCell().setBorderWidth(spacing);
			//设置单元格文本居中
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			//添加字段
			for(int i=0;i<colNum;i++){
				table.getDefaultCell().setBackgroundColor(BaseColor.GRAY);
				//					table.addCell(new Paragraph(tableHeader[i]));
				//动态获取字段名
				table.addCell(new Paragraph(metaData.getColumnLabel(i+1)));
			}
			table.setHeaderRows(1);//表头设置结束,表示第一行属于表头
			table.getDefaultCell().setBorderWidth(1);
			while(rs.next()){
				//将查询到的每一条数据作为单独的一行添加到pdf文件中
				for(int i=1;i<=colNum;i++){
					//设置背景颜色
					table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
					table.addCell(new Paragraph(rs.getString(i)));
				}
			}
			//将表格添加到文档中
			document.add(table);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			conn.close();
			document.close();
		}
	}
}
