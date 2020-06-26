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
		
		final String url = "https://www.redfin.com/city/11970/CA/Merced";
		
		// https://www.zillow.com/homes/Merced,-CA_rb/  link for Zillow 
		
		try {
			Document page = Jsoup.connect(url).get();
			Elements img = page.select(".HomeCard.MapHomeCardReact > .interactive.v2 > .homecardv2 > .cover-all > .photoContainer.PhotoSlider > .scrollable > a.slider-item:nth-of-type(1)");
			// page.select("img")
			String src = "";
			
			for (Element el : img) {
				
				if (el.getAllElements().get(1).hasAttr("src")){
					System.out.println("Image Found!");
					
					System.out.println("src attribute is : " + el.getAllElements().get(1).attr("src"));
				}
				else {
					System.out.println("Image Found on else!");
					System.out.println("src attribute is : " + el.getAllElements().get(1).toString().substring(51, 129));
				}

//                getImages(src);
                
                
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
