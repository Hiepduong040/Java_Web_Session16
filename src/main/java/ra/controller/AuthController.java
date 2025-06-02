package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.service.LoginService;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {

        int rs = loginService.checkLogin(username, password);

        switch(rs) {
            case 1:
                session.setAttribute("username", username);
                return "redirect:/home";
            case 3:
                session.setAttribute("username", username);
                session.setAttribute("role", "admin");
                return "redirect:/admin";
            case 2:
                model.addAttribute("error", "Tài khoản đã bị khóa");
                return "auth/login";
            default:
                model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu");
                return "auth/login";
        }

    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String email,
                             Model model) {

        int rs = loginService.register(username, password, email);

        if (rs == 1) {
            model.addAttribute("message", "Đăng ký thành công");
            model.addAttribute("redirect", true);
            return "auth/login";
        } else {
            model.addAttribute("error", "Đăng ký thất bại");
            return "auth/register";
        }
    }

}
