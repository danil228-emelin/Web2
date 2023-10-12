package itmo.project.servlet;

import itmo.project.bean.EntriesBean;
import itmo.project.bean.Entry;
import itmo.project.exception.NumberException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
@WebServlet("")
@Slf4j
public class ControllerServlet extends HttpServlet {
    private final String ERROR_DIGIT = "Value must be digit .Has ";
    private final String ERROR_X = "X must be in range [-3;5].Has ";
    private final String ERROR_Y = "Y must be in range [-5;3].Has ";
    private final String ERROR_R = "R must be a value from set {1;1.5;2;2.5;3}.Has ";
    private final String ERROR_TIME = "REQUEST TIME IS NULL";

    private void initialization(HttpServletRequest request) {
        EntriesBean<Entry> entries = (EntriesBean) request.getSession().getAttribute("entries");
        if (entries == null) {
            entries = new EntriesBean<>();
            log.info("Initialize EntriesBean");
            request.getSession().setAttribute("entries", entries);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("Entry point crossed");
        initialization(request);
        var x = request.getHeader("x");
        var y = request.getHeader("y");
        var r = request.getHeader("r");
        var requestTime = request.getHeader("currentTime");
        if (x == null && y == null && r == null && requestTime == null) {
            var dispatcher = request.getRequestDispatcher("/template.jsp");
            log.info("forward response to /template.jsp");
            dispatcher.forward(request, response);
            return;
        }
        try {
            var valueX = Double.parseDouble(x);

            var valueY = Double.parseDouble(y);

            var valueR = Double.parseDouble(r);


            if (requestTime == null) {
                throw new NumberException(ERROR_TIME);
            }
            request.getRequestDispatcher("/checkServlet").forward(request, response);
        } catch (NumberFormatException | NullPointerException exception) {
            response.setStatus(400);
            response.getWriter().println(ERROR_DIGIT.concat(exception.getMessage()));
            log.info(ERROR_DIGIT.concat(exception.getMessage()));
        } catch (NumberException e) {
            response.setStatus(400);
            log.info(e.getMessage());
            response.getWriter().println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntriesBean<Entry> entries = (EntriesBean) request.getSession().getAttribute("entries");
        if (entries != null) {
            entries.entryList().clear();
            log.info("Clear bean");
        }
    }
}
