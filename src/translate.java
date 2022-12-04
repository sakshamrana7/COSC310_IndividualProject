import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class translate {
    public static void main(String[] args) throws Exception {
        System.out.println(translate("Team 5: Inventory System", "fr", "en"));
    }

    public static String translate(String text, String to_lang, String from_lang) {
        try {
            String temp = String.format("source=%s&target=%s&q=%s", from_lang, to_lang, text);
            temp = temp.replace(" ", "%20");
            System.out.println(temp);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://google-translate1.p.rapidapi.com/language/translate/v2"))
                    .header("content-type", "application/x-www-form-urlencoded")
                    .header("Accept-Encoding", "application/gzip")
                    .header("X-RapidAPI-Key", "cdf3508a51mshbe5af6a133567edp158282jsn632c685a4d38")
                    .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                    .method("POST", HttpRequest.BodyPublishers.ofString(temp))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofString());

            temp = response.body();

            temp = temp
                    .substring(temp.indexOf("translatedText") + 16, temp.indexOf("}", temp.indexOf("translatedText")))
                    .replace("\"", "");

            return temp;
        } catch (Exception e) {
            return "";
        }

    }
}