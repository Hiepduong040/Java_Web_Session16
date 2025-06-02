package data.repository;

public interface LoginRepository {
    int checkLogin(String username, String password);
    int register(String username, String password, String email);
}
