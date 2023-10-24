package itmo.project;

import itmo.project.bean.EntriesBean;
import itmo.project.bean.Entry;
import itmo.project.exception.NumberException;
import itmo.project.exception.TimeFormatException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "ControllerServlet", urlPatterns = "")
public class ControllerServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("ControllerServlet starts");
        var x = request.getHeader("x");
        var y = request.getHeader("y");
        var r = request.getHeader("r");
        var requestTime = request.getHeader("currentTime");
        if (x == null && y == null && r == null && requestTime == null) {
            var dispatcher = request.getRequestDispatcher("/index.jsp");
            log.info("forward response to /index.jsp");
            dispatcher.forward(request, response);
            return;
        }
            var valueX = Double.parseDouble(x);

            var valueY = Double.parseDouble(y);

            var valueR = Double.parseDouble(r);

            if (requestTime == null) {

                throw new TimeFormatException("REQUEST TIME IS NULL");

            }
            request.getRequestDispatcher("/checkServlet").forward(request, response);

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