package bang.document;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class GetDocumentFromURL   {
	public static void main(String[] args) throws IOException {
        try{
			int chunk=1;
			int multiple = 50;
        	for(int k=multiple*chunk; k<2000; k+=multiple) {
				int beginPage = (chunk-1) * multiple;
				int endPage = k;
	        	List<String> listApi = new ArrayList<String>();
				List<String> listDetail = new ArrayList<String>();
				List<String> listDetailSell = new ArrayList<String>();
				List<String> listDetailHire = new ArrayList<String>();
	        	String baseUrl = "http://khotinchinhchu.vn/";
	        	for (int j = beginPage; j<endPage; j++) {
		            String url = "http://khotinchinhchu.vn/trangchu.aspx?page="+j;
		            Document document  = Jsoup.connect(url)
		            		.timeout(50000)
		                    .cookie("XSRF-TOKEN", "eyJpdiI6InJmcFNmdkZyaXB1N2JUOHNhOVNRRGc9PSIsInZhbHVlIjoidHU5OTA2TFwvNDFMTk1acmZXUncxamVtOThnZGpGT3RxT3VZMFFVZW51U3Fha1ROd3VZTHBTQlJlMWxYTlExVzVrUDFQK3JSV0JiK1Bsd1daOVNNTXRBPT0iLCJtYWMiOiI4ZDA4YzVjOGFkYTVjMDFkNTU5MTMwOTZmOTM3ODUzNDViNWE0YjVlYWQ0OTliMWYyYWFkMDE5YTIzZjAyMjBiIn0%3D")
		                    .cookie("laravel_session", "eyJpdiI6ImFVUGs4bWhaR0xQcjRxbnpiN0dKUlE9PSIsInZhbHVlIjoiSTk4MFM3QmFlOVlubEtWOTk5TkZrNVRTdlJuc2FPUzJFelwvbElVM2l1TEY1MWxmRmZFM2tzVHZcL0w4VHF0VDRpdERYTUljR3ZqMUZDbmdOczJZUmd4UT09IiwibWFjIjoiYTcxYjE5ZjdlMmMyZWEyMjc3MTQ4OTYwYjJkZmY5ODE2NmE1MzY3YmNjM2YxZjdlZjAzOWE0ZDMxYWZmNmJhYiJ9")
		                    .method(Connection.Method.POST)
		                    .followRedirects(true)
		                    .get();
		            Element table = document.select("#theloaibds").first();
		            Elements dataSrc = table.select("[data-src]");
		            for(int i = 0;i<dataSrc.size();i++) {
		            	String a = dataSrc.get(i).toString();
		                String b = a.substring(a.indexOf("data-src=\"")+10, a.indexOf("href")-2);
		                listApi.add(baseUrl+b);
		                System.out.println(baseUrl+b);
		            }
		            System.out.println("page done:" + j);
	        	}
	        	
	        	for(int i = 0; i<listApi.size(); i++ ) {
	        		StringBuilder sb = new StringBuilder();
	        		Document document = Jsoup.connect(listApi.get(i))
	        				.timeout(50000)
	        				.method(Method.GET)
	        				.cookie("XSRF-TOKEN", "eyJpdiI6InJmcFNmdkZyaXB1N2JUOHNhOVNRRGc9PSIsInZhbHVlIjoidHU5OTA2TFwvNDFMTk1acmZXUncxamVtOThnZGpGT3RxT3VZMFFVZW51U3Fha1ROd3VZTHBTQlJlMWxYTlExVzVrUDFQK3JSV0JiK1Bsd1daOVNNTXRBPT0iLCJtYWMiOiI4ZDA4YzVjOGFkYTVjMDFkNTU5MTMwOTZmOTM3ODUzNDViNWE0YjVlYWQ0OTliMWYyYWFkMDE5YTIzZjAyMjBiIn0%3D")
	        				.cookie("laravel_session", "eyJpdiI6ImFVUGs4bWhaR0xQcjRxbnpiN0dKUlE9PSIsInZhbHVlIjoiSTk4MFM3QmFlOVlubEtWOTk5TkZrNVRTdlJuc2FPUzJFelwvbElVM2l1TEY1MWxmRmZFM2tzVHZcL0w4VHF0VDRpdERYTUljR3ZqMUZDbmdOczJZUmd4UT09IiwibWFjIjoiYTcxYjE5ZjdlMmMyZWEyMjc3MTQ4OTYwYjJkZmY5ODE2NmE1MzY3YmNjM2YxZjdlZjAzOWE0ZDMxYWZmNmJhYiJ9")
	        				.get();
					Elements data = document.select(".odd.gradeX");
					Elements fetchData = data.select("td");
					String a = fetchData.get(0).toString();
					a = a.replaceAll(",", "-");
					String tieuDe = a.substring(a.indexOf("#055699;\">")+10, a.indexOf("</big>"));
					String noiDung = a.substring(a.indexOf("dung: </b> <span>")+17, a.indexOf("</td>")-25);
						
					String b = fetchData.get(1).toString();
					b = b.replaceAll(",", "-");
					String theLoai = b.substring(26, b.indexOf("</strong></p> <p><strong>Danh"));
					String danhMuc = b.substring(b.indexOf("Danh mục: ")+10, b.indexOf("</strong></p> <p><strong>Tỉnh"));
					String tinh = b.substring(b.indexOf("Tỉnh/TP: ")+9, b.indexOf("</strong></p> <p><strong>Quận:"));
					String quan = b.substring(b.indexOf("Quận:")+6, b.indexOf("</strong></p> <p><strong>Diện"));
					String dienTich = b.substring(b.indexOf("Diện tích:")+11, b.indexOf("</strong></p> <p><strong>Giá"));
					dienTich = dienTich.replaceAll("&nbsp;", " ");
					String gia = b.substring(b.indexOf("Giá:")+5, b.indexOf("</strong></p> <p style=\"color: red;\""));
					gia = gia.replaceAll("-", ".");
					String lienHe = b.substring(b.indexOf("LHCC:")+6, b.indexOf(" </big></strong><a class=\"link-phone "));
					// sb.append(i+1);
					// sb.append(",");
					sb.append(tieuDe);
					sb.append(",");
					sb.append(noiDung);
					sb.append(",");
					sb.append(theLoai);
					sb.append(",");
					sb.append(danhMuc);
					sb.append(",");
					sb.append(tinh);
					sb.append(",");
					sb.append(quan);
					sb.append(",");
					sb.append(dienTich);
					sb.append(",");
					sb.append(gia);
					sb.append(",");
					sb.append(lienHe);
	//				sb.append("\n");
					// listDetail.add(sb.toString());
					if(theLoai.equalsIgnoreCase("BĐS Bán")){
						listDetailSell.add(sb.toString());
					} else if (theLoai.equalsIgnoreCase("BĐS Cho Thuê")){
						listDetailHire.add(sb.toString());
					}
					System.out.println(sb.toString());
					System.out.println(i +":done");
				}
				exportData("sell" + chunk, listDetailSell);
				exportData("hire" + chunk, listDetailHire);
				// try {
				// 	OutputStream os = new FileOutputStream("C:\\Users\\Thanh\\Desktop\\data\\data" + chunk + ".csv");
				// 	os.write(239);
				// 	os.write(187);
				// 	os.write(191);
				// 	StringBuilder sb = new StringBuilder();
				// 	for (int i = 0; i < listDetail.size(); i++) {
				// 		sb.append(listDetail.get(i));
				// 		sb.append('\n');
				// 	}
				// 	PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
				// 	// sb.append(downServers);
				// 	w.print(sb);
				// 	w.flush();
				// 	w.close();
				// 	System.out.println("done!");

				// } catch (FileNotFoundException e) {
				// 	System.out.println(e.getMessage());
				// }

				System.out.println("doneeeee");
				chunk++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void exportData(String type, List<String> listDetail) throws IOException {
		try {
			OutputStream os = new FileOutputStream("C:\\Users\\Thanh\\Desktop\\data\\data"+ type +".csv");
			os.write(239);
			os.write(187);
			os.write(191);
			StringBuilder sb = new StringBuilder();
			for (int i =0; i< listDetail.size(); i++){
				sb.append(listDetail.get(i));
				sb.append('\n');
			}
			PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
//    	        sb.append(downServers);
			w.print(sb);
			w.flush();
			w.close();
			System.out.println("done!");
		  } catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		  }
		System.out.println("doneeeee");
	}
	
}
