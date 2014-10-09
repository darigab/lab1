import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class MVCexample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		HttpServer server = HttpServer.create(new InetSocketAddress(1236), 0);
		server.createContext("/fashion", new MyHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
		
		News model = retrieveNewsFromDatabase();
		NewsView view= new NewsView();
		NewsController controller = new NewsController(model, view);
		controller.updateView();

	}
	
	private static News retrieveNewsFromDatabase(){
		News news1= new News();
		news1.setTitle("KFW 2014");
		news1.setAuthor("Евгений Овчинников");
		
		return news1;
	}
	
	static class MyHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			 	
		    	String response = " ";
		    	BufferedReader br3 = new BufferedReader(new FileReader("fas.txt"));
		    		    	
                String line3=" ";
                line3=br3.readLine();
                while (line3!=null)
                {
                	response+=line3+ "\n";
                	line3 =br3.readLine();
                }
                br3.close();
                
		    	BufferedReader br = new BufferedReader(new FileReader("news1.txt"));
		            String line=" ";
		            line=br.readLine();
		            while (line!=null)
		            {
		            	response=response+line+ "\n";
		            	line =br.readLine();
		            }
		            br.close();

		            BufferedReader br2 = new BufferedReader(new FileReader("new2.txt"));
		            String line2=" ";
		            line2=br2.readLine();
		            while (line2!=null)
		            {
		            	response+=line2 + "\n";
		            	line2 =br2.readLine();
		            }
		            br2.close();
		            
		            BufferedReader br4 = new BufferedReader(new FileReader("fas2.txt"));
	                String line4=" ";
	                line4=br4.readLine();
	                while (line4!=null)
	                {
	                	response+=line4+ "\n";
	                	line4 =br4.readLine();
	                }
	                br4.close();

		            t.sendResponseHeaders(200, response.length());
		            	OutputStream os = t.getResponseBody();
		            	os.write(response.getBytes());
		            	os.close();
		            	                
		}
		}

	
}
