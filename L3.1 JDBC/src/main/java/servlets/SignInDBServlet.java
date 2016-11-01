package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbService.dao.UsersDAO;
import dbService.dataSets.UsersDataSet;

public class SignInDBServlet extends HttpServlet {

    private UsersDAO _usersDAO;

    public SignInDBServlet(UsersDAO usersDAO) {
        _usersDAO = usersDAO;
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            UsersDataSet userByLogin = _usersDAO.read(login);
            if (userByLogin != null && userByLogin.getPassword().equals(password)) {
                resp.getWriter().append("Authorized");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.getWriter().append("Unauthorized");
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
