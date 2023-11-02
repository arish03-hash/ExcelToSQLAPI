package com.api.exceltosql.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.api.exceltosql.model.Students;

public class ExcelHelper {

	//check that file type is excel or not
	public static boolean checkExcelFormat(MultipartFile file) {
		
		String contentType = file.getContentType();
		return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}
	
	public static List<Students> convertExcelToListOfStudents(InputStream is){
		
		List<Students> list = new ArrayList<>();
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("data");
			
			int rowNumber=0;
			
			Iterator<Row> iterator = sheet.iterator();
			while(iterator.hasNext()) {
				Row row = iterator.next();
				
				if(rowNumber==0) {
					rowNumber++;
					continue;
				}
				
				Iterator<Cell> cells = row.iterator();
				
				int cid=0;
				
				Students s = new Students();
				
				while(cells.hasNext()) {
					Cell cell = cells.next();
					
					switch (cid) {
//					case 0:
//						s.setId((long)cell.getNumericCellValue());
//						break;
					case 0:
						s.setFirst(cell.getStringCellValue());
						break;
					case 1:
						s.setLast(cell.getStringCellValue());
						break;
					case 2:
						s.setMonth(cell.getStringCellValue());
						break;
					case 3:
						s.setMarks((int)cell.getNumericCellValue());
						break;
					default:
						break;
					}
					cid++;
				}
				list.add(s);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
