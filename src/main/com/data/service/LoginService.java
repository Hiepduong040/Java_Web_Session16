package data.service;

public interface LoginService {
    int checkLogin(String username, String password);
    int register(String username, String password, String email);
}
