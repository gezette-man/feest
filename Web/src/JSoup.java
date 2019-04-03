import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JSoup {

	public static void main(String[] args) throws IOException {
		// start http connection
		Document doc = Jsoup.connect("http connection adress").get();

		Elements element = doc.select("input div here");
		System.out.println(element);
		element.html("the insert");
	}
}
