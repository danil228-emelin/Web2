package itmo.project.servlet;

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
@WebServlet("")
@Slf4j
public class ControllerServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("Entry point crossed");
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

        if (requestTime == null) {
            throw new TimeFormatException("REQUEST TIME IS NULL");
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
