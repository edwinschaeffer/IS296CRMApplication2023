package crm.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import crm.vo.PotentialLead;

@Service
public class ExcelUtility {

	public List<PotentialLead> transformExceltoPL(MultipartFile file) throws IOException {
		InputStream is = file.getInputStream();
		Workbook wb = WorkbookFactory.create(is);
		Sheet sheet = wb.getSheet("extradata");
		List<PotentialLead> plList = new ArrayList<PotentialLead>();
		DataFormatter formatter = new DataFormatter();
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i); 
			PotentialLead pl = new PotentialLead();
			for(int j = 0; j < 17; j++) {
				Cell cell = row.getCell(j);
				String cellValue = formatter.formatCellValue(cell).trim();
				if(cellValue == null) {
					continue;
				}
				switch(j) {
					case 0: 
						pl.setId(transformString(cellValue));
					break;
					case 1: 
						pl.setAgeOfBusiness(cellValue);
					break;
					case 2:
						pl.setCity(cellValue);
					break;
					case 3: 
						pl.setCompany(cellValue);
					break;
					case 5: 
						pl.setArea(cellValue);
					break;
					case 6:
						pl.setEmployeeCount(cellValue);
					break;
					case 7:
						pl.setIndustry(cellValue);
					break;
					case 8:
						pl.setPhone(cellValue);
					break;
					case 9:
						pl.setPotentialLeadLocationLatitude(transformString(cellValue));
					break;
					case 10:
						pl.setPotentialLeadLocationLongitude(transformString(cellValue));
					break;
					case 11: 
						pl.setSector(cellValue);
					break;
					case 13: 
						pl.setStreet(cellValue);
					break;
					case 14:
						pl.setWebsite(transformString(cellValue));
					break;
					case 15:
						pl.setZipCode(cellValue);
					break;
				}
			}
			plList.add(pl);
		}
		
		return plList;
	}
	private String transformString(String s) {
		return s.replaceAll("\'", "");
	}
}
