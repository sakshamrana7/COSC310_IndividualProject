import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class Weather {

	public static void main(String[] args) throws Exception {
		System.out.println(Arrays.toString(getWeather()));
	}

	public static String[] getWeather() throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://weatherapi-com.p.rapidapi.com/current.json?q=Kelowna"))
				.header("X-RapidAPI-Key", "cdf3508a51mshbe5af6a133567edp158282jsn632c685a4d38")
				.header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		String temp = response.body();
		String current_weather = temp.substring(temp.indexOf("condition") + 20,
				temp.indexOf("\",\"", temp.indexOf("condition") + 20));
		String current_temp = temp.substring(temp.indexOf("\"temp_c\"") + 9,
				temp.indexOf(",", temp.indexOf("\"temp_c\"") + 9));
		return new String[] { current_weather, current_temp };
	}

}