package be.afelio.babell.tp_babell.api.controller;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*
@Component
public class CheckConnection {

    protected boolean checkConnection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();
        System.out.println("FrontController.checkConnection() " + pathInfo);
        boolean authorization = false;
        if (null == session.getAttribute("id")) {
            session.setAttribute("Authorization", false);
            if (!authorization && pathInfo.startsWith("/connection")) {
                authorization = loginController.getConnection(request, response);
                session.setAttribute("Authorization", authorization);
                System.out.println("FrontController.checkConnection() fin de connection : " + authorization);
                if (authorization) {
                    response.setHeader("Authorization", "true");
                }
            } else if (pathInfo.startsWith("/person/add")) {
                personController.add(request, response);
                authorization = Boolean.valueOf((String) session.getAttribute("Authorization"));
                System.out.println("FrontController.checkConnection() person add authorization : " + authorization);
            }
        } else {
            System.out.println("FrontController.checkConnection() : " + session.getAttribute("Authorization"));
            authorization = (boolean) session.getAttribute("Authorization");
        }
        return authorization;
    }
}
*/
