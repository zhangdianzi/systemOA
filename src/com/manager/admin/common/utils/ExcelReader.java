package com.manager.admin.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelReader {
    
	/***
	 * 
	 *   姓名                           年龄                              班级                             语文                        数学                          英语       
		1111111       2113132       2141213       2615114       2116125       2157116       
		1111112       2113133       2141214       2615115       2116126       2157117       
		1111113       2113134       2141215       2615116       2116127       2157118       
		1111114       2113135       2141216       2615117       2116128       2157119       
		1111115       2113136       2141217       2615118       2116129       2157120       
		1111116       2113137       2141218       2615119       2116130       2157121       
		1111117       2113138       2141219       2615120       2116131       2157122       
		1111118       2113139       2141220       2615121       2116132       2157123       
		1111119       2113140       2141221       2615122       2116133       2157124       
		1111120       2113141       2141222       2615123       2116134       2157125       
		1111121       2113142       2141223       2615124       2116135       2157126       
		1111122       2113143       2141224       2615125       2116136       2157127       
		1111123       2113144       2141225       2615126       2116137       2157128       
		1111124       2113145       2141226       2615127       2116138       2157129       
		1111125       2113146       2141227       2615128       2116139       2157130      
	 * **/
    public static void main(String[] args) {
    	List<String[]> list;
		try {
			list = readExcel("F:/xls/test.xls");
			for(String[] s :list)
	    	{
	    		for(int i = 0 ;i < s.length ; i++)
	    		{
	    			System.out.print(s[i]+  "       ");
	    		}
	    		System.out.println();
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static List<String[]> readExcel(String excelFileName) throws BiffException, IOException 
    {
        // 创建一个list 用来存储读取的内容
        List<String[]> list = new ArrayList<String[]>();
        Workbook rwb = null;
        Cell cell = null;

        // 创建输入流
        InputStream stream = new FileInputStream(excelFileName);
        // 获取Excel文件对象
        rwb = Workbook.getWorkbook(stream);

        // 获取文件的指定工作表 默认的第一个
        Sheet sheet = rwb.getSheet(0);

        // 行数(表头的目录不需要，从1开始)
        for (int i = 0; i < sheet.getRows(); i++) {
            // 创建一个数组 用来存储每一列的值
            String[] str = new String[sheet.getColumns()];
            // 列数
            for (int j = 0; j < sheet.getColumns(); j++) {
                // 获取第i行，第j列的值
                cell = sheet.getCell(j, i);
                str[j] = cell.getContents();
                //System.out.print(str[j]+"\t");
            }
            // 把刚获取的列存入list
            list.add(str);
        }
        //内容读取结束后     删除本地的该文件
//        FileUtil.delAllFile(excelFileName);
        // 返回值集合
        return list;
    }
    
    public static List<String> getExcelData(String url)
    {
    	List<String> resList = new ArrayList<String>();
    	List<String[]> list;
		try {
			list = readExcel(url);
			for(String[] s:list)
	        {
	        	for(int i = 0;i<s.length;i++)
	        	{
	        		resList.add(s[i]);
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resList;
    }
    
    public static List<String[]> readExcel(InputStream stream) throws BiffException, IOException{
	    //创建一个list 用来存储读取的内容
	    List<String[]> list = new ArrayList<String[]>();
	    Workbook rwb = null;
	    Cell cell = null;
	    //获取Excel文件对象
	    rwb = Workbook.getWorkbook(stream);
	    //获取文件的指定工作表 默认的第一个
	    Sheet sheet = rwb.getSheet(0);  
	    //行数(表头的目录不需要，从1开始)
	    for(int i=1; i<sheet.getRows(); i++){
	     //创建一个数组 用来存储每一列的值
	     String[] str = new String[sheet.getColumns()];
	     //列数
	     for(int j=0; j<sheet.getColumns(); j++){
	      //获取第i行，第j列的值
	      cell = sheet.getCell(j,i);    
	      str[j] = cell.getContents();
	     }
	     //把刚获取的列存入list
	     list.add(str);
	    }
	    //返回值集合
	    return list;
	   }
 public static List<String[]> readCSV(InputStream stream) throws Exception{
	 //创建一个list 用来存储读取的内容
	 List<String[]> list = new ArrayList<String[]>();
	 CSVReader csvReader = new CSVReader(new InputStreamReader( stream)); 
	 String[] csvRow= null;
	 while((csvRow = csvReader.readNext()) != null)
		{
		 list.add(csvRow);
		}
	 csvReader.close();
	 return list;
 }
 
}
