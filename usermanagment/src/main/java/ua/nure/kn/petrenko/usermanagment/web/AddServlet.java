package main.java.ua.nure.kn.vitalii.petrenko.usermanagment.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.ua.nure.kn.vitalii.petrenko.usermanagment.User;
import main.java.ua.nure.kn.vitalii.petrenko.usermanagment.database.DaoFactory;
import main.java.ua.nure.kn.vitalii.petrenko.usermanagment.database.DatabaseCustomException;

public class AddServlet extends EditServlet {
    protected void processUser(User user) throws DatabaseCustomException {
        DaoFactory.getInstance().getUserDao().create(user);
    }
    protected void showPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/add.jsp").forward(req, resp);
    }
} 