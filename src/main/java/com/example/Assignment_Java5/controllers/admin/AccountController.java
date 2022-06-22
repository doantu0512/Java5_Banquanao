package com.example.Assignment_Java5.controllers.admin;

import com.example.Assignment_Java5.controllers.utils.EncryptUtil;
import com.example.Assignment_Java5.entitys.Account;
import com.example.Assignment_Java5.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
@Controller
@RequestMapping("/admin/user")
public class AccountController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    private IAccountService adminDao;

    @Autowired
    HttpSession session;

    @GetMapping("/index")
    public String index(@ModelAttribute("account") Account account, Model model, @RequestParam(name = "page", required = false, defaultValue = "0") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        model.addAttribute("list", adminDao.findPageAll(pageable));
        return "admin/user";
    }


    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("account") Account account, BindingResult result,Model model, @RequestParam(name = "page", required = false, defaultValue = "0") Optional<Integer> page, @RequestParam(name = "password") String password) {
        if (result.hasErrors()){
            Pageable pageable = PageRequest.of(page.orElse(0), 5);
            model.addAttribute("list", adminDao.findPageAll(pageable));
            model.addAttribute("account",account);
            return "admin/user";
        }else {
            try {
                String encrypted = EncryptUtil.encrypt(password);
                account.setActivated(1);
                account.setPassword(encrypted);
                this.adminDao.insert(account);
                session.setAttribute("message", "Thêm Mới Thành Công");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("error", "Thêm Mới Thất Bại");
            }
        return "redirect:/admin/user/index";
        }

    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Integer id, Model model, @ModelAttribute("account") Account account, @RequestParam(name = "page", required = false, defaultValue = "0") Optional<Integer> page) {
        model.addAttribute("account", adminDao.findById(id));
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        request.setAttribute("list", adminDao.findPageAll(pageable));
        return "admin/user";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("account") Account account, @RequestParam(name = "id") Integer id) {
        try {
            Account acc=this.adminDao.findById(id);
            account.setPassword(acc.getPassword());
            account.setActivated(acc.getActivated());
            this.adminDao.update(account);
            session.setAttribute("message", "Cập Nhật Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Cập Nhật Thất Bại");
        }
        return "redirect:/admin/user/index";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("admin") Account account, @RequestParam(name = "id") Integer id) {
        try {
            this.adminDao.delete(id);
            session.setAttribute("message", "Xóa Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Xóa Thất Bại");
        }
        return "redirect:/admin/user/index";
    }
}

