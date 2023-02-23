/**
 * @author: Jiahui Zhu
 * @email address: jiahuiz2@andrew.cmu.edu
 * @Project Number: Project1 Task3
 */
package ds.project1task3;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

@WebServlet(name = "ClickerServlet", urlPatterns = {"/submit", "/getResults"})
public class ClickerServlet extends HttpServlet {

    // Initial the choice parameters
    int[] choices = {0, 0, 0, 0};
    // The "business model" for this app
    ClickerModel clm;

    // Initiate this servlet by instantiating the model that it will use.
    public void init() {
        clm = new ClickerModel();
    }

    /**
     * Source cite: This method is from InterestingPictureServlet.java in the Interesting Picture Lab.
     *
     * This method is used to check if the current browser is in the mobile.
     * @param request The request sent from HTTP.
     */
    public boolean mobileCheck (HttpServletRequest request) {
        // determine what type of device our user is
        String ua = request.getHeader("User-Agent");

        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;
            /*
             * This is the latest XHTML Mobile doctype. To see the difference it
             * makes, comment it out so that a default desktop doctype is used
             * and view on an Android or iPhone.
             */
            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }
        return mobile;
    }

    /**
     * This servlet will reply to HTTP GET requests via this doGet method.
     *
     * @param request The request sent from HTTP.
     * @param response The response sent to HTTP.
     * @throws IOException handle the IOException
     * @throws ServletException handle the ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // In order to determine within the servlet which path was actually requested
        String path = request.getServletPath();
        mobileCheck(request);
        String nextView;

        request.setAttribute("AChoice", choices[0]);
        request.setAttribute("BChoice", choices[1]);
        request.setAttribute("CChoice", choices[2]);
        request.setAttribute("DChoice", choices[3]);

        // The getResults path was requested, show the result.jsp and clear the previous results.
        if (path.contains("/getResults")) {
            nextView = "result.jsp";
            //clears the number of click for each choice
            ClickerModel.choices= new int[]{0, 0, 0, 0};
            ClickerModel.AChoice=0;
            ClickerModel.BChoice=0;
            ClickerModel.CChoice=0;
            ClickerModel.DChoice=0;
        } else {
            nextView = "index.jsp";
        }
        // Transfer control over the correct "view"
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }

    /**
     * This method is used to increase the number of the choice that the user select.
     *
     * @param request  The request sent from HTTP.
     * @param response The response sent to HTTP.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        mobileCheck(request);

        // Get the choice from user selection
        try {
            String choice = request.getParameter("choice");
            if (choice != null) {
                choices = clm.getChoice(choice);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        view.forward(request, response);
    }

    public void destroy() {
    }
}