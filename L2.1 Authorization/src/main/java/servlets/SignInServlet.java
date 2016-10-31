package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accounts.AccountService;
import accounts.UserProfile;

public class SignInServlet extends HttpServlet {

    private final AccountService _accountService;

    public SignInServlet(AccountService accountService) {
        _accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserProfile userByLogin = _accountService.getUserByLogin(login);
        if (userByLogin != null && userByLogin.getPass().equals(password)) {
            resp.getWriter().append("Authorized");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.getWriter().append("Unauthorized");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
