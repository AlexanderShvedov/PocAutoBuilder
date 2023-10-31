package modules.xmlworkers;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleUrlCreater implements UrlCreaterInterface{
    @Override
    public String getJson(String packageName) {
        URL url;
        try {
            url = new URL("https://search.maven.org/solrsearch/select?q=" + packageName + "&rows=20&wt=json");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try (InputStream input = url.openStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            return json.toString();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
