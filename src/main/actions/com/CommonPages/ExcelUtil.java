package com.CommonPages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	XSSFSheet ExcelWSheet;
	XSSFWorkbook ExcelWBook;
	Cell Cell;
	XSSFRow Row;

	public void setExcelFile(String path) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {
		}
	}

	public String getCellData(int rowNumber, int columnNumber) {
		ExcelWSheet = ExcelWBook.getSheet("Account");
		Cell = ExcelWSheet.getRow(rowNumber).getCell(columnNumber);
		String cellData = Cell.getStringCellValue();
		return cellData;
	}

	public int getRowCount() {
		int iNumber = 0;
		try {
			ExcelWSheet = ExcelWBook.getSheet("Account");
			iNumber = ExcelWSheet.getLastRowNum() + 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return iNumber;
	}

	@SuppressWarnings("static-access")
	public void setAddressWallet(String path, int rowNumber, int columnNumber, String valueUpdate) throws Exception {
		try {
			ExcelWSheet = ExcelWBook.getSheet("Account");
			Row = ExcelWSheet.getRow(rowNumber);
			Cell = Row.getCell(columnNumber, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(columnNumber);
				Cell.setCellValue(valueUpdate);
				System.out.println("Fill successfully");
			} else {
				Cell.setCellValue(valueUpdate);
				System.out.println("Fill successfully");
			}
			FileOutputStream fileOut = new FileOutputStream(path);
			ExcelWBook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {

		}
	}

	public List<String> getAccountInfo(String path) {

		List<String> listAccount = new ArrayList<String>();
		ExcelUtil readExcel = new ExcelUtil();
		try {
			readExcel.setExcelFile(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int rowCount = readExcel.getRowCount();
		System.out.println("Row = " + rowCount);

//		String username;
//		String password;
		int rowAddress;

//		String account = "";
		for (int i = 1; i < rowCount; i++) {
			String status = readExcel.getCellData(i, 5).trim();

			if (!status.equals("True")) {
//				username = readExcel.getCellData(i, 1).trim();
//				listAccount.add(username);
//				password = readExcel.getCellData(i, 2).trim();
//				listAccount.add(password);
				rowAddress = i;

				listAccount.add(String.valueOf(rowAddress));
//				System.out.println("username = " + account);
//				System.out.println("password = " + password);
				System.out.println("rowAddress = " + rowAddress);

				break;
			}

		}
		return listAccount;
	}

	public void updateAddressWallet(String path, int rowAddress, String username, String kavaAddress,
			String memoAddress) {
		ExcelUtil readExcel = new ExcelUtil();
		try {
			readExcel.setExcelFile(path);
			readExcel.setAddressWallet(path, rowAddress, 1, username);
			readExcel.setAddressWallet(path, rowAddress, 2, "$Hoangxuan68@$");
			readExcel.setAddressWallet(path, rowAddress, 3, kavaAddress);
			readExcel.setAddressWallet(path, rowAddress, 4, memoAddress);
			readExcel.setAddressWallet(path, rowAddress, 5, "True");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		ExcelUtil readExcel = new ExcelUtil();
		String excelPath = System.getProperty("user.dir").concat("/data/BinanceAccount.xlsx");
		List<String> data = new ArrayList<String>();
		data = readExcel.getAccountInfo(excelPath);
		System.out.println("Password = " + data.get(0));
		System.out.println("Dong hien tai = " + data.get(1));
		readExcel.updateAddressWallet(excelPath, Integer.parseInt(data.get(1)),"123123", "VU", "NGUYEN");
	}

}