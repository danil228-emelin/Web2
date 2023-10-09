package itmo.project.servlet;

import itmo.project.bean.EntriesBean;
import itmo.project.bean.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@WebServlet("/checkServlet")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("In AreaCheckServlet get request");

        final long startTime = System.nanoTime();
        var x = Double.parseDouble(request.getHeader("x"));
        var y = Double.parseDouble(request.getHeader("y"));
        var r = Double.parseDouble(request.getHeader("r"));
        var result = isInRange(x, y, r);
        var currentTime = new SimpleDateFormat("HH::mm::ss").format(new Date());
        EntriesBean<Entry> entries = (EntriesBean) request.getSession().getAttribute("entries");
        if (entries == null) {
            entries = new EntriesBean<>();
            request.getSession().setAttribute("entries", entries);
        }
        response.setCharacterEncoding("UTF-8");
        String executionTime = String.valueOf((System.nanoTime() - startTime));
        entries.add(Optional.of(new Entry(x, y, r, currentTime, executionTime, result)));
        getServletContext().getRequestDispatcher("/template.jsp").forward(request, response);
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
