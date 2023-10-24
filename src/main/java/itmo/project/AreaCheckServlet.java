package itmo.project;

import itmo.project.bean.EntriesBean;
import itmo.project.bean.Entry;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Slf4j
@WebServlet(name = "AreaCheckServlet", urlPatterns = "/checkServlet")
public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        log.info(" Check x,y,r ");

        final long startTime = System.nanoTime();
        var x = Double.parseDouble(request.getHeader("x"));
        var y = Double.parseDouble(request.getHeader("y"));
        var r = Double.parseDouble(request.getHeader("r"));
        var requestTime = request.getHeader("currentTime");
        var result = isInRange(x, y, r);
        response.addHeader("inRange",result?"1":"0");
        var currentTime = new SimpleDateFormat("HH::mm::ss").format(new Date());
        EntriesBean<Entry> entries = (EntriesBean) request.getSession().getAttribute("entries");
        response.setCharacterEncoding("UTF-8");
        String executionTime = String.valueOf((System.nanoTime() - startTime));
        entries.add(Optional.of(Entry
                .builder()
                .x(x)
                .y(y)
                .r(r)
                .requestTime(requestTime)
                .responseTime(currentTime)
                .inRange(result)
                .executionTime(executionTime)
                .build()));
        log.info("Forward response to index.jsp");
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private boolean isInRange(double x, double y, double r) {
        return isInRectangle(x, y, r) || isInSquare(x, y, r) || isInCircle(x, y, r);
    }

    private boolean isInRectangle(double x, double y, double r) {
        return x <= r && x >= 0 && y <= r / 2 && y >= 0;
    }

    private boolean isInCircle(double x, double y, double r) {
        return x >= -r / 2 && x <= 0 && y <= r / 2 && y >= 0;
    }

    private boolean isInSquare(double x, double y, double r) {
        return x <= r && x >= 0 && y >= -r / 2 && y <= 0;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

}
