package itmo.project.servlet;

import itmo.project.exception.NumberException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class ControllerServlet extends HttpServlet {
    private final String ERROR_DIGIT = "Value must be digit .Has ";
    private final String ERROR_X = "X must be in range [-3;5]";
    private final String ERROR_Y = "Y must be in range [-5;3]";
    private final String ERROR_R = "R must be a value from set {1;1.5;2;2.5;3}";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var x = request.getHeader("x");
        var y = request.getHeader("y");
        var r = request.getHeader("r");
        if (x == null && y == null && r == null) {
            var dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
            return;
        }
        try {
            var valueX = Double.parseDouble(x);
            if (valueX < -3 || valueX > 5) {
                throw new NumberException(ERROR_X);
            }
            var valueY = Double.parseDouble(y);
            if (valueY < -5 || valueY > 3) {
                throw new NumberException(ERROR_Y);
            }
            var valueR = Double.parseDouble(r);

            if (valueR < 1 || valueR > 3 || valueR * 10 % 5 != 0) {
                throw new NumberException(ERROR_R);
            }
            request.getRequestDispatcher("/checkServlet").forward(request,response);
        } catch (NumberFormatException | NullPointerException exception) {
            response.setStatus(400);
            response.getWriter().println(ERROR_DIGIT.concat(exception.getMessage()));
        } catch (NumberException e) {
            response.setStatus(400);
            response.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
