import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class testExcelTwo {

	public static void main(String[] args) throws IOException {
		
		File inputWorkbook = new File("C:\\Users\\gcruz\\git\\Translate\\Translate\\misc\\dbEntries.xls");		
		Workbook w;
		
		try{
			
			w = Workbook.getWorkbook(inputWorkbook);			
			Sheet sheet = w.getSheet(0);
			int count = 0;
			for (int i = 0; i < sheet.getRows(); i++) {		
				int localeid = 0;
				
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j, i);
					count++;
					if(j==0){
						localeid = 140;
					}else if(j==1){
						localeid = 149;
					}else if(j==2){
						localeid = 195;
					}else if(j==3){
						localeid = 164;
					}
										
					System.out.println("INSERT INTO `world`.`word`(`localeid`,`word`)VALUES(" + localeid + ",\"" + cell.getContents().trim() + "\");");
										
				}
			}			
			
		} catch (BiffException e) {
			e.printStackTrace();
		}
		
		try{
			
			w = Workbook.getWorkbook(inputWorkbook);			
			Sheet sheet = w.getSheet(0);
			int count = 0;
			for (int i = 0; i < sheet.getRows(); i++) {								
				for (int j = 0; j < sheet.getColumns(); j++) {					
					count++;					
					if( j == (sheet.getColumns()-1) ){
						System.out.println("INSERT INTO `world`.`wordmapping`(`wordid`,`towordid`)VALUES(" + (count-3) + "," + (count-2) + ");");
						System.out.println("INSERT INTO `world`.`wordmapping`(`wordid`,`towordid`)VALUES(" + (count-3) + "," + (count-1) + ");");
						System.out.println("INSERT INTO `world`.`wordmapping`(`wordid`,`towordid`)VALUES(" + (count-3) + "," + (count) + ");");
						
						System.out.println("INSERT INTO `world`.`wordmapping`(`wordid`,`towordid`)VALUES(" + (count-2) + "," + (count-3) + ");");
						System.out.println("INSERT INTO `world`.`wordmapping`(`wordid`,`towordid`)VALUES(" + (count-2) + "," + (count-1) + ");");
						System.out.println("INSERT INTO `world`.`wordmapping`(`wordid`,`towordid`)VALUES(" + (count-2) + "," + (count) + ");");
						
						System.out.println("INSERT INTO `world`.`wordmapping`(`wordid`,`towordid`)VALUES(" + (count-1) + "," + (count-3) + ");");
						System.out.println("INSERT INTO `world`.`wordmapping`(`wordid`,`towordid`)VALUES(" + (count-1) + "," + (count-2) + ");");
						System.out.println("INSERT INTO `world`.`wordmapping`(`wordid`,`towordid`)VALUES(" + (count-1) + "," + (count) + ");");
						
						System.out.println("INSERT INTO `world`.`wordmapping`(`wordid`,`towordid`)VALUES(" + (count) + "," + (count-3) + ");");
						System.out.println("INSERT INTO `world`.`wordmapping`(`wordid`,`towordid`)VALUES(" + (count) + "," + (count-2) + ");");
						System.out.println("INSERT INTO `world`.`wordmapping`(`wordid`,`towordid`)VALUES(" + (count) + "," + (count-1) + ");");
						
					}					
				}
			}						
			
			
		} catch (BiffException e) {
			e.printStackTrace();
		}		

	}

}
