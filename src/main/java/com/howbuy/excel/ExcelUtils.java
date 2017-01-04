package com.howbuy.excel;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.howbuy.database.jdbc.DataTableUtils;
import com.howbuy.database.model.SignCheck;

@Repository
public class ExcelUtils {
	
	@Autowired
	private DataTableUtils dataTableUtils;
	@Value("${initExcel}")
	private String excelName;
	
	@SuppressWarnings("resource")
	public void initTable(String filePath) throws Exception {

		Workbook xlsWorkBook = new HSSFWorkbook(new FileInputStream(filePath+excelName));
		// 取得第一页
		Sheet sheet = xlsWorkBook.getSheetAt(0);
		int rowSize = sheet.getLastRowNum() - 1;
		int columnSize = sheet.getRow(1).getPhysicalNumberOfCells();
		for (int i = 0; i <= rowSize; i++) {
			Row row = sheet.getRow(i + 1);
			int j = 0;
			SignCheck signCheck = new SignCheck();
			while (j < columnSize) {
				switch (j) {
				case 0:
					signCheck.setSignCode(getCellFormatValue(row.getCell((short) j)).trim());
					break;
				case 1:
					signCheck.setCustName(getCellFormatValue(row.getCell((short) j)).trim());
					break;
				case 2:
					signCheck.setConsName(getCellFormatValue(row.getCell((short) j)).trim());
					break;
				case 3:
					signCheck.setSection(getCellFormatValue(row.getCell((short) j)).trim());
					break;
				case 4:
					signCheck.setPlanNum(Integer.valueOf(getCellFormatValue(row.getCell((short) j)).trim()));
					break;
				default:
					break;
				}
				j++;
			}
			dataTableUtils.insert(signCheck);
		}
	}

	private String getStringCellValue(Cell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}

	private String getDateCellValue(Cell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == Cell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (new DateTime(date).toString("yyyy-MM-dd"));
			} else if (cellType == Cell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == Cell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	private String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
			case Cell.CELL_TYPE_FORMULA: {
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				} else {
					cellvalue = this.doubleToString(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING:
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}

	public String doubleToString(double d) {
		String i = DecimalFormat.getInstance().format(d);
		String result = i.replaceAll(",", "");
		return result;

	}

}
