package itmo.project.filter;

import itmo.project.bean.EntriesBean;
import itmo.project.bean.Entry;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter("/")
@Slf4j
public class FilterInitialization implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("FilterInitialization starts");

        EntriesBean<Entry> entries = (EntriesBean<Entry>) ((HttpServletRequest)servletRequest).getSession().getAttribute("entries");
        if (entries == null) {
            entries = new EntriesBean<>();
            ((HttpServletRequest)servletRequest).getSession().setAttribute("entries", entries);
            log.info("Initialize EntriesBean");
    }
        log.info("FilterInitialization finish");

filterChain.doFilter(servletRequest,servletResponse);
    }
}
