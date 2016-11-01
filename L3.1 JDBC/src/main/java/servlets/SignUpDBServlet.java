package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;

public class SignUpDBServlet extends HttpServlet {
    private UsersDAO _usersDAO;

    public SignUpDBServlet(UsersDAO usersDAO) {
        _usersDAO = usersDAO;
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            UsersDataSet usersDataSet = _usersDAO.create(new UsersDataSet(login, password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
