package education.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Commons {

	public static HashMap<String, Object> addPagination(int currentpage,int totalRecords,int recordPerPage){
		int[] totalPage = null;
		int totalPageCount = 0;
		
		if( totalRecords % recordPerPage != 0 )
			totalPageCount = totalRecords / recordPerPage +1;
		else 
			totalPageCount = totalRecords / recordPerPage;
		
		totalPage = new int[totalPageCount];
		
		for (int i = 0; i <= totalPageCount-1; i++) {
			totalPage[i] = i+1;
		}
	
		HashMap<String, Object> paginationmap = new HashMap<String, Object>();
		paginationmap.put("totalpage", totalPage);

		
		return paginationmap;
	}
	public static String changedateformat(Object createddate){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String newdate = sdf.format(createddate);
		System.out.println("new date format=>"+newdate); 
		return newdate;
	}
}
