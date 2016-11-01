package dbService.dataSets;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
@SuppressWarnings("UnusedDeclaration")
public class UsersDataSet {
    private long _id;
    private String _login;
    private String _password;

    public UsersDataSet(long id) {
        _id = id;
    }

    public UsersDataSet(String login,
                        String password) {
        _login = login;
        _password = password;
    }

    public UsersDataSet(long id,
                        String login,
                        String password) {
        _id = id;
        _login = login;
        _password = password;
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public String getLogin() {
        return _login;
    }

    public void setLogin(String login) {
        _login = login;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersDataSet)) return false;

        UsersDataSet that = (UsersDataSet) o;

        if (_id != that._id) return false;
        if (_login != null ? !_login.equals(that._login) : that._login != null) return false;
        return _password != null ? _password.equals(that._password) : that._password == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (_id ^ (_id >>> 32));
        result = 31 * result + (_login != null ? _login.hashCode() : 0);
        result = 31 * result + (_password != null ? _password.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "UsersDataSet{" +
                "_id=" + _id +
                ", _login='" + _login + '\'' +
                ", _password='" + _password + '\'' +
                '}';
    }
}
