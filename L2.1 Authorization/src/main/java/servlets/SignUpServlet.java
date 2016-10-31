package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import accounts.AccountService;
import accounts.UserProfile;

public class SignUpServlet extends HttpServlet {

    private final AccountService _accountService;

    public SignUpServlet(AccountService accountService) {
        _accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        _accountService.addNewUser(new UserProfile(login, password));

        //resp.getWriter().append("Authorized");
    }
}
