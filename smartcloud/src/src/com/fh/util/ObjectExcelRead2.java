package com.fh.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 从EXCEL导入到数据库
 * 创建人：FH Q313596790
 * 创建时间：2014年12月23日
 * @version
 */
public class ObjectExcelRead2 {
//
//	/**
//	 * @param filepath //文件路径
//	 * @param filename //文件名
//	 * @param startrow //开始行号
//	 * @param startcol //开始列号
//	 * @param sheetnum //sheet
//	 * @return list
//	 */
//	public static List<Object> readExcel(String filepath, String filename, int startrow, int startcol, int sheetnum) {
//		List<Object> varList = new ArrayList<Object>();
//
//		try {
//			File target = new File(filepath, filename);
//			FileInputStream fi = new FileInputStream(target);
//			HSSFWorkbook wb = new HSSFWorkbook(fi);
//			HSSFSheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
//			int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号
//
//			for (int i = startrow; i < rowNum; i++) {					//行循环开始
//				
//				PageData varpd = new PageData();
//				HSSFRow row = sheet.getRow(i); 							//行
//				int cellNum = row.getLastCellNum(); 					//每行的最后一个单元格位置
//
//				for (int j = startcol; j < cellNum; j++) {				//列循环开始
//					
//					HSSFCell cell = row.getCell(Short.parseShort(j + ""));
//					String cellValue = null;
//					if (null != cell) {
//						switch (cell.getCellType()) { 					// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
//						case 0:
//							cellValue = String.valueOf((int) cell.getNumericCellValue());
//							break;
//						case 1:
//							cellValue = cell.getStringCellValue();
//							break;
//						case 2:
//							cellValue = cell.getNumericCellValue() + "";
//							// cellValue = String.valueOf(cell.getDateCellValue());
//							break;
//						case 3:
//							cellValue = "";
//							break;
//						case 4:
//							cellValue = String.valueOf(cell.getBooleanCellValue());
//							break;
//						case 5:
//							cellValue = String.valueOf(cell.getErrorCellValue());
//							break;
//						}
//					} else {
//						cellValue = "";
//					}
//					
//					varpd.put("var"+j, cellValue);
//					
//				}
//				varList.add(varpd);
//			}
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//		return varList;
//	}
//	/**  
//	* 读取excel数据  
//	* @param path  
//	*/  
//	public static List<Object> readExcelToObj(String filepath, String filename, int startrow, int startcol, int sheetnum){  
//	Workbook wb = null;  
//	try {  
//		
//		//wb = WorkbookFactory.create(new File("E:\\test\\Users2.xls")); 
//		File target = new File(filepath, filename);
//		wb = WorkbookFactory.create(target);
//		readExcel(wb, 0, 0, 0);  
//	} catch (InvalidFormatException e) {  
//		e.printStackTrace();  
//	} catch (IOException e) {  
//		e.printStackTrace();  
//		}
//	return null;  
//	}  
//	
//	private static void readExcel(Workbook wb,int sheetIndex, int startReadLine, int tailLine) {  
//		Sheet sheet = wb.getSheetAt(sheetIndex);  
//		Row row = null;  
//		for(int i=startReadLine; i<sheet.getLastRowNum()-tailLine+1; i++) {  
//			row = sheet.getRow(i);  
//			for(Cell c : row) {  
//				boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());  
//				//判断是否具有合并单元格  
//				if(isMerge) {  
//					String rs = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());  
//					System.out.print(rs + "");  
//				}else {  
//					System.out.print(c.getRichStringCellValue()+"");  
//				}  
//			}  
//			System.out.println();  
//		}  
//	}  
//	/**   
//	* 获取合并单元格的值   
//	* @param sheet   
//	* @param row   
//	* @param column   
//	* @return   
//	*/    
//	public static String getMergedRegionValue(Sheet sheet ,int row , int column){    
//	    int sheetMergeCount = sheet.getNumMergedRegions();    
//	        
//	    for(int i = 0 ; i < sheetMergeCount ; i++){    
//	        CellRangeAddress ca = sheet.getMergedRegion(i);    
//	        int firstColumn = ca.getFirstColumn();    
//	        int lastColumn = ca.getLastColumn();    
//	        int firstRow = ca.getFirstRow();    
//	        int lastRow = ca.getLastRow();    
//	            
//	        if(row >= firstRow && row <= lastRow){    
//	                
//	            if(column >= firstColumn && column <= lastColumn){    
//	                Row fRow = sheet.getRow(firstRow);    
//	                Cell fCell = fRow.getCell(firstColumn);    
//	                return getCellValue(fCell) ;    
//	            }    
//	        }    
//	    }    
//	        
//	    return null ;    
//	}    
//	/**   
//	* 获取单元格的值   
//	* @param cell   
//	* @return   
//	*/    
//	public static String getCellValue(Cell cell){    
//	    if(cell == null) return "";    
//	    if(cell.getCellType() == Cell.CELL_TYPE_STRING){    
//	        return cell.getStringCellValue();    
//	    }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){    
//	        return String.valueOf(cell.getBooleanCellValue());    
//	    }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){    
//	        return cell.getCellFormula() ;    
//	    }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){    
//	        return String.valueOf(cell.getNumericCellValue());    
//	    }    
//	    return "";    
//	}    
//	  
//	/**  
//	* 判断指定的单元格是否是合并单元格  
//	* @param sheet   
//	* @param row 行下标  
//	* @param column 列下标  
//	* @return  
//	*/  
//	private static boolean isMergedRegion(Sheet sheet,int row ,int column) {  
//	  int sheetMergeCount = sheet.getNumMergedRegions();  
//	  for (int i = 0; i < sheetMergeCount; i++) {  
//		  CellRangeAddress range = sheet.getMergedRegion(i);  
//		  int firstColumn = range.getFirstColumn();  
//		  int lastColumn = range.getLastColumn();  
//		  int firstRow = range.getFirstRow();  
//		  int lastRow = range.getLastRow();  
//		  if(row >= firstRow && row <= lastRow){  
//			  if(column >= firstColumn && column <= lastColumn){  
//				  return true;  
//			  }  
//		  }  
//	  }  
//	  return false;  
//	}  
//	
	/**
	 * @param filepath //文件路径
	 * @param filename //文件名
	 * @param startrow //开始行号
	 * @param startcol //开始列号
	 * @param sheetnum //sheet
	 * @return list
	 */
	public static List<Object> readExcel(String filepath, String filename, int startrow, int startcol, int sheetnum) {
		List<Object> varList = new ArrayList<Object>();
		try {
			File target = new File(filepath,filename);
			FileInputStream fi = new FileInputStream(target);
			//Workbook wb = new Workbook(fi);  
			XSSFWorkbook wb = new XSSFWorkbook(fi);
			Sheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
			List<CellRangeAddress> list = new ArrayList<CellRangeAddress>();
			list = getCombineCell(sheet, list);
			int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号

			for (int i = startrow; i < rowNum; i++) {					//行循环开始
				
				PageData varpd = new PageData();
				Row row = sheet.getRow(i); 							//行
				int cellNum = row.getLastCellNum(); 					//每行的最后一个单元格位置
				for (int j = startcol; j < cellNum; j++) {				//列循环开始
					Cell cell = row.getCell(Short.parseShort(j + ""));
					String cellValue = null;
					if (null != cell) {
						if(cell.getCellType() == 3){
							PageData queryContain = isCombineCell(list, cell, sheet);
							if("true".equals(queryContain.getString("flag"))){
								Integer row1 = Integer.parseInt(queryContain.get("firstR").toString());
								Integer col1 = Integer.parseInt(queryContain.get("firstC").toString());
								Row tempRow = sheet.getRow(row1);
								cell = tempRow.getCell(Short.parseShort(col1 + ""));
							}
						}
						switch (cell.getCellType()) { 					// 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
						case 0:
							cellValue = String.valueOf((int) cell.getNumericCellValue());
							break;
						case 1:
							cellValue = cell.getStringCellValue();
							break;
						case 2:
							cellValue = cell.getNumericCellValue() + "";
							// cellValue = String.valueOf(cell.getDateCellValue());
							break;
						case 3:
							cellValue = "";
							break;
						case 4:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case 5:
							cellValue = String.valueOf(cell.getErrorCellValue());
							break;
						}
					} else {
						cellValue = "";
					}
					
					varpd.put("var"+j, cellValue);
					
				}
				varList.add(varpd);
			}
			
			
		} catch (Exception e) {
				System.out.println(e);
		}
		return varList;
	}
	/**
	* 合并单元格处理--加入list
	* 
	* @param sheet
	* @return
	*/
	public static List<CellRangeAddress> getCombineCell(Sheet sheet, List<CellRangeAddress> list) {
		// 获得一个 sheet 中合并单元格的数量
		int sheetmergerCount = sheet.getNumMergedRegions();
		// 遍历合并单元格
		for (int i = 0; i < sheetmergerCount; i++) {
			// 获得合并单元格加入list中
			CellRangeAddress ca = sheet.getMergedRegion(i);
			list.add(ca);
		}
		return list;
	}
/**
* 判断单元格是否为合并单元格
* 
* @param listCombineCell
*            存放合并单元格的list
* @param cell
*            需要判断的单元格
* @param sheet
*            sheet
* @return
*/
public static PageData isCombineCell(List<CellRangeAddress> listCombineCell,
		Cell cell, Sheet sheet) {
		int firstC = 0;
		int lastC = 0;
		int firstR = 0;
		int lastR = 0;
		String flag = "false";
		PageData pd = new PageData();
		for (CellRangeAddress ca : listCombineCell) {
			// 获得合并单元格的起始行, 结束行, 起始列, 结束列
			firstC = ca.getFirstColumn();
			lastC = ca.getLastColumn();
			firstR = ca.getFirstRow();
			lastR = ca.getLastRow();
			if (cell.getColumnIndex() <= lastC&& cell.getColumnIndex()>= firstC) {
				if (cell.getRowIndex() <= lastR && cell.getRowIndex() >= firstR) {
					flag = "true";
				}
			}
			if("true".equals(flag)){
				pd.put("firstR", firstR);
				pd.put("firstC", firstC);
			}
			pd.put("flag", flag);
			if("true".equals(flag)){
				return pd;
			}
		}
		return pd;
	}
	
}
