package com.example.Assignment_Java5.controllers.cart;

import com.example.Assignment_Java5.entitys.Order;
import com.example.Assignment_Java5.entitys.OrderDetail;
import com.example.Assignment_Java5.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/home")
public class RemoveToCartController {
    @Autowired
    private IProductService productDao;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/removecart")
    public String RemoveToCart(@RequestParam(name = "id") Integer id){
        Order order= (Order) session.getAttribute("order");
        if (order!=null){
            List<OrderDetail> listOrder=order.getOrderdetails();
            for (OrderDetail item:listOrder) {
                if (item.getProduct().getId()==id){
                    listOrder.remove(item);
                    break;
                }
            }
            if (listOrder.isEmpty()){
                session.removeAttribute("order");
            }
            session.setAttribute("order",order);
        }
        return "redirect:/home/cart";
    }
}
