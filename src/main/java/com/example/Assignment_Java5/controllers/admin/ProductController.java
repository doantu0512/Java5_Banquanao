package com.example.Assignment_Java5.controllers.admin;

import com.example.Assignment_Java5.entitys.Product;
import com.example.Assignment_Java5.service.ICategoryService;
import com.example.Assignment_Java5.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("admin/product")
public class ProductController {
    @Autowired
    ServletContext app;

    @Autowired
    private IProductService dao;

    @Autowired
    private ICategoryService categoryDao;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    @GetMapping("/index")
    public String index(@ModelAttribute("product") Product product, Model model, @RequestParam(name = "page", required = false, defaultValue = "0") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        model.addAttribute("list", dao.findPageAll(pageable));
        model.addAttribute("listCate",categoryDao.getAll());
        return "admin/form";
    }


    @PostMapping("/add")
    public String add( @ModelAttribute("product") Product product,@RequestParam("attach") MultipartFile attach) {
        try {
            product.setCreatedDate(new Date());
            product.setAvailable(1);
            if (!attach.isEmpty()) {
                String filename = attach.getOriginalFilename();
                File file = new File(app.getRealPath("/images/products/" + filename));
                product.setImage("/images/products/" + filename);
                try {
                    attach.transferTo(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            this.dao.insert(product);
            session.setAttribute("message", "Thêm Mới Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Thêm Mới Thất Bại");
        }
        return "redirect:/admin/product/index";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Integer id, Model model, @ModelAttribute("product") Product product, @RequestParam(name = "page", required = false, defaultValue = "0") Optional<Integer> page) {
        model.addAttribute("pro", dao.findById(id));
        model.addAttribute("listCate",categoryDao.getAll());
        Pageable pageable = PageRequest.of(page.orElse(0), 5);
        request.setAttribute("list", dao.findPageAll(pageable));
        return "admin/form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("product") Product product, @RequestParam(name = "id") Integer id,@RequestParam("attach") MultipartFile attach) {
        try {
            Product p = dao.findById(id);
            product.setCreatedDate(p.getCreatedDate());
            product.setAvailable(p.getAvailable());
            if (!attach.isEmpty()) {
                String filename = attach.getOriginalFilename();
                File file = new File(app.getRealPath("/images/products/" + filename));
                product.setImage("/images/products/" + filename);
                try {
                    attach.transferTo(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                product.setImage(p.getImage());
            }
            this.dao.update(product);
            session.setAttribute("message", "Cập Nhật Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Cập Nhật Thất Bại");
        }
        return "redirect:/admin/product/index";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("product") Product product, @RequestParam(name = "id") Integer id) {
        try {
            this.dao.delete(id);
            session.setAttribute("message", "Xoa Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Xoa Thất Bại");
        }
        return "redirect:/admin/product/index";
    }

    @GetMapping("/search")
    public String search(@ModelAttribute("product") Product product, @RequestParam(name = "search") String name, @RequestParam(name = "page") Optional<Integer> page) {
            try {
                List<Product> list = dao.findAllByName(name);
                Pageable pageable = PageRequest.of(page.orElse(0), 5);
                int start = (int) pageable.getOffset();
                int end = Math.min((start + pageable.getPageSize()), list.size());
                request.setAttribute("list", new PageImpl<>(list.subList(start, end), pageable, list.size()));
                if (new PageImpl<>(list.subList(start, end), pageable, list.size()).getTotalPages()-1<0){
                    session.setAttribute("error", "Tim Thất Bại");
                    return "redirect:/admin/product/index";
                }
                session.setAttribute("message", "Tim Thành Công");
            } catch (Exception e) {
                e.printStackTrace();
                session.setAttribute("error", "Tim Thất Bại");
                return "redirect:/admin/product/index";
            }
        return "admin/form";
    }
}
