/**
 * @author: Jiahui Zhu
 * @email address: jiahuiz2@andrew.cmu.edu
 * @Project number：project1 task1
 * Last Modified: Feb 9, 2023
 *
 * This application is to compute two different hashes according to
 * user's input and selected computation method.
 */
package ds.project1task1;

// Imports required for task1
import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
 // The following WebServlet annotation gives instructions to the web container.
 @WebServlet(urlPatterns = {"/ComputeHashes"})
public class ComputeHashes extends HttpServlet {
    // Message used to display the goal of the web page
    private String message;
    // To set up the init info
    public void init() {
        message = "Compute Hashes in two different way! ";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get user input text data
            String textVar = request.getParameter("textContent");
            // Get the hash function from user selection
            String buttonVar = request.getParameter("comp_function");
            // Set up the web page will show after computing the input text data
            String nextView;
            // Set up the default hash function as "MD5"
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Check if the input text is present.
            // If not then give the user instructions and prompt for a text input.
            // If there is a text input, then do the computation and return the result.
            if (textVar != null) {
                request.setAttribute("textContent", textVar);
                request.setAttribute("comp_function", buttonVar);

                // Since the default hash function is "MD5".
                // When the user don't select the hash function or select "MD5" button, it will work in a same way.
                if (buttonVar == null || buttonVar.equalsIgnoreCase("MD5")) {
                    md = MessageDigest.getInstance("MD5");
                } else if (buttonVar.equalsIgnoreCase("SHA-256")) {
                    // When user select hash function as "SHA-256", it will work in this way.
                    md = MessageDigest.getInstance("SHA-256");
                }
                // Compute the digest
                md.update(textVar.getBytes());
                // Compute the input text data in base64 form
                String res_64 = javax.xml.bind.DatatypeConverter.printBase64Binary(md.digest());
                // Recompute the digest
                md.update(textVar.getBytes());
                // Compute the input text data in hexadecimal form
                String res_hex = javax.xml.bind.DatatypeConverter.printHexBinary(md.digest());
                
                // Pass the result to the next view.
                request.setAttribute("res_base64", res_64);
                request.setAttribute("res_hex", res_hex);
                // Pass all the information to the result view.
                nextView = "result.jsp";
            } else {
                // No text input so choose the origin view
                nextView = "index.jsp";
            }
            RequestDispatcher view = request.getRequestDispatcher(nextView);
            view.forward(request, response);

            // Handle NoSuchAlgorithm Exceptions
        } catch(NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }
    public void destroy() {
    }
}
