package com.atguigu.jf.console.common.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.atguigu.jf.console.baseapi.common.TestSqlMapper;
import com.atguigu.jf.console.common.bean.pojo.TestSql;
import com.atguigu.jf.console.common.service.CreateExcelService;
import com.sun.org.apache.bcel.internal.generic.RETURN;

@Service
public class CreateExcelServiceImpl implements CreateExcelService{
    
	@Autowired
	private TestSqlMapper testSqlMapper;
	
	@Override
	public String createExcel()throws Exception{
		
		//创建excel
		String path = "c:\\testExcel\\";
		
		File f_file = new File(path);
		if(!f_file.exists()){
			f_file.mkdirs();
		}
		//定义文件名
		String fileName = path + this.getFileName("Import_Log_");
	    File file = new File(fileName);
		
		Workbook wb = new HSSFWorkbook();
		CreationHelper createHelper = wb.getCreationHelper();
		Sheet sheet = wb.createSheet("new sheet");
		
		  Row row = sheet.createRow((short)0);
		  //构造表头
		  String title[] = {"积分导入流水","业务发生时间","积分供应商","导入积分","积分兑换比例","兑换手续费率","结算金额","平台利润","结算状态","结算日期","导出状态","导出时间"};
		  
		  
		  
		  //设置表格的样式和颜色
		  CellStyle style = wb.createCellStyle();
		  style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		  style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		  
		  //循环放入表头
		  for (int i = 0; i < title.length; i++) {
			    Cell cell = row.createCell(i);
		    	cell.setCellValue(createHelper.createRichTextString(title[i]));
		    	cell.setCellStyle(style);
		}
		  //循环创建单元格
		  Map<String,Object> map = new HashMap<>();
		  List<TestSql> list = testSqlMapper.selectOrderList(map);
		   
		  //第一次循环
		  for (int i = 0; i < list.size(); i++) {
		  //从第二行开始创建行对象
		  row = sheet.createRow(i+1);
		  TestSql testSql = list.get(i);
		  String exchange[] = this.createRowArray(testSql);
		  //第二次循环处理
		  for (int j = 0; j < exchange.length; j++) {
			  row.createCell(j).setCellValue(createHelper.createRichTextString(exchange[j]));
		}
		  
		}
	
	    FileOutputStream fileOut = new FileOutputStream(file);
	    wb.write(fileOut);
	    fileOut.close();
		
	    return fileName;
	}
	
	//获取文件名
	public String getFileName(String pre){
		
		String fileName = pre + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()) + ".xls";
	    return fileName;
	}
	
	//将兑换流水实体转化为数组
	public String[] createRowArray(TestSql bean){
		
		String str[] = {bean.getId(),bean.getImpDate(),bean.getProviderName(),bean.getImpPoint(),bean.getRate(),
				bean.getFee(),bean.getAmount(),bean.getProfit(),bean.getExchangeState(),bean.getExchangeDate(),bean.getExportState()
				,bean.getExportDate()};
		
		
		return str;
		
		
	}
	
}
