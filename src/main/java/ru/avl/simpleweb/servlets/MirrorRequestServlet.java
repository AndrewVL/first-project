package ru.avl.simpleweb.servlets;

import ru.avl.simpleweb.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey on 01.04.2017.
 */
public class MirrorRequestServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("key", request.getParameter("key"));

//        response.getWriter().println(PageGenerator.getInstance().getPage("mirrorResponse.html", paramMap));
        response.getWriter().println(request.getParameter("key"));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
