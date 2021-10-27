package com.example.compoundinterest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

// @WebServlet(name = "CalculatorServlet", value = "/CalculatorServlet")
@WebServlet(urlPatterns = {"", "/"})
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Used for GETting the specified page
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * `request.getParameter` will retrieve the value inside of each <input> by finding the specified String argument
         * using each <input>'s `name` attribute
         */
        String principalAmount = request.getParameter("principalAmount");
        String interestRate = request.getParameter("interestRate");
        String numberOfYears = request.getParameter("numberOfYears");
        String compoundingPeriod = request.getParameter("compoundingPeriod");

        String error;   // Will be used to store errors, if any

        // Check to ensure values were provided
        if (principalAmount == null || interestRate == null || numberOfYears == null ||
                compoundingPeriod == null || principalAmount.isBlank() || interestRate.isBlank() ||
                numberOfYears.isBlank() || compoundingPeriod.isBlank()) {

            error = "One or more of the input boxes were blank. Try again.";    // Sets an error message

            request.setAttribute("error", error);   // Sets request as the error message, which we'll want to be sent back to the user (see statement at bottom of method)

        }
        else {
            // Values were provided, calculate amount
            double result = Utilities.calculateCompoundInterest(Double.parseDouble(principalAmount),
                    (Double.parseDouble(interestRate) / 100), Integer.parseInt(numberOfYears), Integer.parseInt(compoundingPeriod));

            request.setAttribute("result", result);     // Sets request to the result of the calculation, which we'll send back to the user (see statement at bottom of method)
        }

        // Attributes that will be forwarded back to the JSP that we'll grab and put back into the <input>'s using the `value` attribute
        request.setAttribute("principalAmount", principalAmount);
        request.setAttribute("interestRate", interestRate);
        request.setAttribute("numberOfYears", numberOfYears);
        request.setAttribute("compoundingPeriod", compoundingPeriod);

        // Forwards everything back to the specified page to be displayed
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
