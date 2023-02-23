/**
 * @author: Jiahui Zhu
 * @email address: jiahuiz2@andrew.cmu.edu
 * @Project Number: Project1 Task2
 */
package ds.project1task2;

//Import the necessary library for Task2

import com.google.gson.Gson;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WorldCupModel {

    Message emojiPic[];

    // Generate class for Gson
    class Message {
        // The elements in Json array
        String name;
        String code;
        String emoji;
        String unicode;
        String image;
    }

    /**
     * This method is used to scrape the nickname from certain url.
     * @param doc The doc we transform for the certain url
     * @param search The country that user select
     * @return String The nickname of selected country
     */
    public String getNickName(Document doc, String search) {
        String nickName = "Not found";
        try {
            // Find the selected country from the table in the doc
            Element nickTable = doc.select("td:contains(" + search + ")").first();
            if (nickTable != null) {
                // Find the nickname of selected country from the table, just next element of the country
                nickName = nickTable.nextElementSibling().text();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return nickName;
    }

    /**
     * Code source: https://stackoverflow.com/questions/32294795/cannot-resolve-symbol-error-when-using-url
     * Code source: https://www.runoob.com/java/java-url-processing.html
     * Code source: This method is from InterestingPictureModel.java in the lab 2.
     *
     * This method is used to get the capital city information/
     * @param url The url link is used to scrape the capital city
     * @return String the capital city of selected country
     */
    public String getCapCity(URL url) {
        String capCity = null;
        try{
            /*
             * Create an HttpURLConnection.  This is useful for setting headers
             * and for getting the path of the resource that is returned (which
             * may be different from the URL above if redirected).
             * HttpsURLConnection (with an "s") can be used if required by the site.
             */
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuilder urlString = new StringBuilder();
            String current;
            // Read each line of "in" until done, adding each to "urlString"
            while((current = in.readLine()) != null)
            {
                urlString.append(current);
            }
            // Check if this url is valid
            // If not, return null
            if (urlString.length() == 0) {
                return null;
            }
            JSONArray jsArray = new JSONArray(urlString.toString());
            JSONObject jsObj = jsArray.getJSONObject(0);
            // Find the capital city info from the JsonObject
            capCity = jsObj.getJSONArray("capital").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return capCity.replaceAll("\"", "").replace("[", "").replace("]", "").replace(",", ", ");
    }

    /**
     * This method is used to find the top scorer and top score.
     * @param doc The doc we transform for the certain url
     * @param search The country that user select
     * @return String the top scorer and score info of selected country
     */
    public String getTopScorer(Document doc, String search) {
        // Initial the related score info parameters
        String scoreInfo = null;
        String scorer;
        String score;
        try {
            // Find the row of selected country info from the table which contains the country name
            Element scoreTable = doc.select("td:contains(" + search + ")").first();
            if (scoreTable != null) {
                // Find the scorer's info
                scorer = scoreTable.previousElementSibling().text();
                // Find the scorer's goals number
                score = scoreTable.nextElementSibling().nextElementSibling().text();
                // concatenate the outcome
                scoreInfo = scorer + ", " + score + " goals";
            } else {
                // If the country does not have a player in this list, display "N/A".
                scoreInfo = "N/A";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreInfo;
    }

    /**
     * This method is used to find the flag picture of selected country.
     * @param search The country that user select
     * @return String the flag url info of selected country
     */
    public String getPic(String search) {
        // Handle the situation when there exists space in the country name
        if (search.contains(" ")) {
            search = search.replace(" ", "-");
        }
        String picURL = "https://www.cia.gov/the-world-factbook/countries/" + search.toLowerCase() + "/flag";
        String imageURL = null;
        try {
            Document doc = Jsoup.connect(picURL).get();
            // Find the flag url info according to the certain element class
            Element picSection = doc.getElementsByClass("image-detail-block-image gatsby-image-wrapper").first();
            if (picSection != null) {
                // Find the flag url source info
                imageURL = picSection.select("img").get(1).attr("src");
            }
            // concatenate the flag image url
            imageURL = "https://www.cia.gov" + imageURL;
        } catch (IOException e) {
          e.printStackTrace();
        }
        return imageURL;
    }

    /**
     * Code source: https://www.tutorialspoint.com/gson/gson_streaming.htm
     * This method is used to fetch the emoji flag url info, and create an array to save it.
     *
     * @param url The URL of the request
     * @param search The country we are looking for
     * @return A string of the response from the HTTP GET.
     */
    public String getEmoji(String url, String search) {
        // Initial the emoji flag url info
        String emojiFlag = null;
        String json = fetch(url);
        Gson gson = new Gson();
        // Gson object from json
        Message emojiPic[] = gson.fromJson(json, Message[].class);
        // Iterate each item in the json array
        for (Message item: emojiPic) {
            // Find the matched emoji image url
            if (item.name.equalsIgnoreCase(search)) {
                emojiFlag = item.image;
                System.out.println(emojiFlag);
            }
        }
        return emojiFlag;
    }


    /**
     * Code source: This method is from InterestingPictureModel.java in the lab 2.
     * Make an HTTP request to a given URL
     *
     * @param urlString The URL of the request
     * @return A string of the response from the HTTP GET.  This is identical
     * to what would be returned from using curl on the command line.
     */
    private String fetch(String urlString) {
        String response = "";
        try {
            URL url = new URL(urlString);
            /*
             * Create an HttpURLConnection.  This is useful for setting headers
             * and for getting the path of the resource that is returned (which
             * may be different from the URL above if redirected).
             * HttpsURLConnection (with an "s") can be used if required by the site.
             */
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            // Read each line of "in" until done, adding each to "response"
            while ((str = in.readLine()) != null) {
                // str is one line of text readLine() strips newline characters
                response += str;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Eeek, an exception");
            // Do something reasonable.  This is left for students to do.
        }
        return response;
    }
}
