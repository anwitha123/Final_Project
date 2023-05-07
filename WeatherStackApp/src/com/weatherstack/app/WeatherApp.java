package com.weatherstack.app; //package name

import java.util.*; //for Scanner class, set, HashMap, HashSet etc
import java.io.IOException;//This import statement provides access to the IOException class, which is used to handle input/output exceptions that can occur during the runtime of the program.

import com.google.gson.JsonObject; //This import statement is used to access the JsonObject class from the Google Gson library. This class represents a JSON object, which can be used to manipulate JSON data in Java.
import com.google.gson.JsonParser; //This import statement is used to access the JsonParser class from the Google Gson library. This class is used to parse JSON data in Java.

/***
what is JSON?
JSON stands for JavaScript Object Notation. Its a light weight format of storing data. 
JSON is often used when data is sent from web server to web page. JSON is self-descriptive and easy to understand.  		
***/

import org.apache.http.HttpEntity; //This import statement provides access to the HttpEntity class from the Apache HttpClient library. This class represents the content of an HTTP response or request.
import org.apache.http.HttpResponse; //This import statement provides access to the HttpResponse class from the Apache HttpClient library. This class represents an HTTP response.
import org.apache.http.client.HttpClient; //This import statement provides access to the HttpClient interface from the Apache HttpClient library. This interface defines methods for sending HTTP requests and receiving HTTP responses.
import org.apache.http.client.ClientProtocolException; //This import statement provides access to the ClientProtocolException class from the Apache HttpClient library. This class is used to handle exceptions that can occur during the execution of an HTTP request.
import org.apache.http.client.methods.HttpGet; //  This import statement provides access to the HttpGet class from the Apache HttpClient library. This class represents an HTTP GET request.
import org.apache.http.impl.client.HttpClients; //This import statement provides access to the HttpClients class from the Apache HttpClient library. This class provides methods for creating HttpClient instances.
import org.apache.http.util.EntityUtils; // This import statement provides access to the EntityUtils class from the Apache HttpClient library. This class provides methods for working with HttpEntity objects.

public class WeatherApp {
	String weatherInfo; 
	
    public WeatherApp(String location) throws ClientProtocolException, IOException {
        String apiKey = "d40cc4aa44f52b2939e9169a57804d6d";
              
        // Create an HttpClient instance to represent http request and http response
        HttpClient httpclient = HttpClients.createDefault();

        // Construct the URL for the weather API request
        String url = "http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + location.replace(" ", "%20");

        // Create an HTTP GET request object
        HttpGet httpGet = new HttpGet(url);

        // Execute the request and get the response
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Extract the response body as a String
        HttpEntity entity = response.getEntity();
        String responseBody = EntityUtils.toString(entity);

        // Parse the JSON response to retrieve the weather data
        JsonObject jsonObject = new JsonParser().parse(responseBody).getAsJsonObject();
        JsonObject currentWeather = jsonObject.getAsJsonObject("current");
        String temperature = currentWeather.get("temperature").getAsString();
        String weatherDescriptions = currentWeather.get("weather_descriptions").getAsString();
        String locationName = jsonObject.getAsJsonObject("location").get("name").getAsString();
        String country = jsonObject.getAsJsonObject("location").get("country").getAsString();
        String weatherInfo = String.format("%s, %s: %s°C, %s", locationName, country, temperature, weatherDescriptions);
        this.weatherInfo=weatherInfo;
        //        return weatherInfo;
    }
    public String toString() {
    	return weatherInfo;
    }
//    public static void main(String[] args) throws Exception {
//        WeatherApp obj=new WeatherApp("india");
//        System.out.println(obj);
//    }
}
