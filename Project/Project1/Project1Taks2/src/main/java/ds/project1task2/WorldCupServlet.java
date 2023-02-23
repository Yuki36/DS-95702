/**
 * @author: Jiahui Zhu
 * @email address: jiahuiz2@andrew.cmu.edu
 * @Project Number: Project1 Task2
 */
package ds.project1task2;
// Import required library for task2
import java.io.IOException;
import java.net.URL;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


@WebServlet(name = "WorldCupServlet", value = "/WorldCupServlet")
public class WorldCupServlet extends HttpServlet {
    // The data model for this app
    WorldCupModel wcm = null;

    // Initiate this servlet by initiating the model
    public void init() {
        wcm = new WorldCupModel();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get the search parameter
        String search = request.getParameter("country");
        // Set up the parameter for the next page
        String nextView;
        // Check if the search country is present.
        // If not, then give the user instructions and origin page.
        // If found, get the info of related country.
        if (search != null) {
            // Search for nickname according to user's selection
            Document countriesDoc = Jsoup.connect("https://www.topendsports.com/sport/soccer/team-nicknames-women.htm").get();
            request.setAttribute("nickName", wcm.getNickName(countriesDoc, search));

            // Search for the capital city according to user's selection
            // Handle the edge case when select England
            if (search.equalsIgnoreCase("england")) {
                // search = "united%20kingdom";
                String fullName = "https://restcountries.com/v3.1/name/united%20kingdom/?fullText=true";
                URL fullNameUrl = new URL(fullName);
                request.setAttribute("capitalCity", wcm.getCapCity(fullNameUrl));
            } else {
                // Normal situation
                String fullName = "https://restcountries.com/v3.1/name/" + search.toLowerCase() + "/?fullText=true";
                URL fullNameUrl = new URL(fullName.replace(" ", "%20"));
                request.setAttribute("capitalCity", wcm.getCapCity(fullNameUrl));
            }

            // Find the top scorer and the top score according to user's selection
            // Handle the edge case when select England
            if (search.equalsIgnoreCase("england")) {
                search = "England";
                Document topScorer = Jsoup.connect("https://www.espn.com/soccer/stats/_/league/FIFA.WWC/season/2019/view/scoring").get();
                request.setAttribute("topScorer", wcm.getTopScorer(topScorer, search));
            } else {
                // Normal situation
                Document topScorer = Jsoup.connect("https://www.espn.com/soccer/stats/_/league/FIFA.WWC/season/2019/view/scoring").get();
                request.setAttribute("topScorer", wcm.getTopScorer(topScorer, search));
            }

            // Find the flag according to user's selection
            // Handle the edge case when select England
            if (search.equalsIgnoreCase("england")) {
                String flagName = wcm.getPic("united-kingdom");
                request.setAttribute("flag", flagName);
            } else if (search.equalsIgnoreCase("south korea")) {
                // Handle the edge case when select south korea
                String flagName = wcm.getPic("korea-south");
                request.setAttribute("flag", flagName);
            } else {
                // Normal situation
                String flagName = wcm.getPic(search.toLowerCase());
                request.setAttribute("flag", flagName);
            }

            // Find the flag according to user's selection
            String emoji = "https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/index.json";
            String flagEmojiURL = wcm.getEmoji(emoji, search.toLowerCase());
            request.setAttribute("flagEmoji", flagEmojiURL);
            // Pass all the information to the result view.
            nextView = "result.jsp";
        } else {
            // No selection so choose the origin view
            nextView ="index.jsp";
        }
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }
    public void destroy() {
    }
}