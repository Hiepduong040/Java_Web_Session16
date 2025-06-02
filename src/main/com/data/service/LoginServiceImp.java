package data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import data.repository.LoginRepository;

@Service
public class LoginServiceImp implements LoginService{

    @Autowired

    private LoginRepository loginRepository;
    @Override
    public int checkLogin(String username, String password) {
        return loginRepository.checkLogin(username, password);
    }

    @Override
    public int register(String username, String password, String email) {
        return loginRepository.register(username, password, email);
    }


}
