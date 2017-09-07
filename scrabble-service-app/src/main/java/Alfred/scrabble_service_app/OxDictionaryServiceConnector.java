package Alfred.scrabble_service_app;

import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public final class OxDictionaryServiceConnector {
	
	public OxDictionaryServiceConnector(){}
	
    private String buildRequest(String word){
        final String language = "en";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }
	
    private boolean sendRequest(String requestURL) {

        final String app_id = "50eb829a";
        final String app_key = "0f1e72e3bbacb7bccc75d879a972d2a1";
        try {
            URL url = new URL(requestURL);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);
            urlConnection.getResponseCode();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
	public boolean searchWord(String word){
		String request = buildRequest(word);
		boolean response = sendRequest(request);
		return response;
	}
	
}
