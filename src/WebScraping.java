import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebScraping {

	static final String folderPath = "C:\\Users\\Alberto\\Desktop\\REU\\DataExtractionProject\\img";
	
	public WebScraping() {
	}

	public static void main(String[] args) {
		
		final String url = "https://www.zillow.com/homes/Merced,-CA_rb/";
		
		try {
			Document page = Jsoup.connect(url).get();
			Elements img = page.select("img");
			
			for (Element el : img) {
				 
                //for each element get the src url
                String src = el.absUrl("src");
               
                System.out.println("Image Found!");
                System.out.println("src attribute is : "+ src);
 
                getImages(src);
                
                
            }		
		}
		catch (Exception ex) {
			System.err.println("There was an error");
            Logger.getLogger(WebScraping.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private static void getImages(String src) throws IOException {
		 
		String folder = null;
		 
	        //Exctract the name of the image from the src attribute
	        int indexname = src.lastIndexOf("/");
	        System.out.println(indexname);
	 
	        if (indexname == src.length()) {
	            src = src.substring(1, indexname);
	        }
	 
	        indexname = src.lastIndexOf("/");
	        String name = src.substring(indexname, src.length());
	 
	        System.out.println(name);
	 
	        //Open a URL Stream
	        URL url = new URL(src);
	        InputStream in = url.openStream();
	 
	        OutputStream out = new BufferedOutputStream(new FileOutputStream( folderPath + name));
	 
	        for (int b; (b = in.read()) != -1;) {
	            out.write(b);
	        }
	        out.close();
	        in.close();
	}

}
