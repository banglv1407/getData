// package bang.document;

// import java.io.FileNotFoundException;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.OutputStream;
// import java.io.OutputStreamWriter;
// import java.util.ArrayList;

// import org.jsoup.Connection;
// import org.jsoup.Jsoup;
// import org.jsoup.helper.StringUtil;
// import org.jsoup.nodes.Document;
// import org.jsoup.nodes.Element;
// import org.jsoup.select.Elements;
// import java.io.PrintWriter;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;


// public class GetDocumentFromURLbase   {
// 	static ArrayList<String> downServers = new ArrayList<String>();
// 	public static void main(String[] args) throws IOException {
// 		LocalDate startDate = LocalDate.parse("2020-01-01");
// 		LocalDate endDate = LocalDate.parse("2020-05-25");
// 		LocalDate d = startDate;
// 		while (d.isBefore(endDate) || d.equals(endDate)) {
// 			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
// 			String dateGetData = d.format(formatters);
// 			String dateGetDataEnd = d.plusDays(29).format(formatters);
// 			Document document = Jsoup.connect("https://halan.xeca.vn/ticket/viewRT203?mobile=&fromDate="+dateGetData+"&toDate="+dateGetDataEnd+"&status=&statusStr=T%E1%BA%A5t%20c%E1%BA%A3&sortType=1&sortTypeStr=S%E1%BB%91%20l%C6%B0%E1%BB%A3ng%20v%C3%A9&formatType=html")
// 					.cookie("SERVERID", "S3482")
// 					.cookie("SESSION", "8266e60c-b346-4e49-be48-b3d9aa14e957")
// 					.cookie("DWRSESSIONID", "T~ujGawm9XSHPEde0d8O9b7u7I2uk8rE00n")
// 					.maxBodySize(0)
// 					.timeout(30000)
// 					.get();
			
// 			getDataCustomer(document);
// 			System.out.println(dateGetData + " to " + dateGetDataEnd);
// 			 d = d.plusDays(30);
// 			}
		
// 	    try {
// 	    	OutputStream os = new FileOutputStream("C:/Users/Le Quynh/Desktop/dataHalanfull.csv");
// 	        os.write(239);
// 	        os.write(187);
// 	        os.write(191);
// 	        StringBuilder sb = new StringBuilder();
// 	        for (int i =0; i< downServers.size(); i++){
// 				sb.append(downServers.get(i));
// 				sb.append('\n');
// 			}
// 	        PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
// //	        sb.append(downServers);
// 	        w.print(sb);
// 	        w.flush();
// 	        w.close();
// 	        System.out.println("done!");

// 	      } catch (FileNotFoundException e) {
// 	        System.out.println(e.getMessage());
// 	      }
		
		
// 	}

// 	private static void getDataCustomer(Document d) {
// 		int count =0;
// 		Element tableRow = d.select("table").get(0);
// 		Elements rows5 = tableRow.select("tr");
// 		for (int i = 0; i < rows5.size(); i++) { 
// 		    Element row = rows5.get(i);
// 		    Elements cols = row.select("td");
// 		    Elements stt = row.select("td:eq(1)");
// 		    Elements sdt = row.select("td:eq(2)");
// 		    Elements hvt = row.select("td:eq(3)");
// 		    Elements sld = row.select("td:eq(4)");
// 		    Elements sv = row.select("td:eq(5)");
// 		    Elements tt = row.select("td:eq(6)");
// 		    String total = tt.text().toString().replace(",", "");
// 			    if (!sdt.text().equals("") && StringUtil.isNumeric(stt.text().replace(",", ""))){
// 			    	count++;
// 			    	downServers.add(sdt.text().toString()+ "," + hvt.text() + "," + sld.text() + "," + sv.text() + "," + total);
// //			    	System.out.println(count + ": " + sdt.text().toString()+ "," + hvt.text() + "," + sld.text() + "," + sv.text() + "," + total);
// 			    }
// 		}
// 	}
// }
