package com.example.Assignment_Java5.controllers.cart;

import com.example.Assignment_Java5.entitys.Account;
import com.example.Assignment_Java5.entitys.Order;
import com.example.Assignment_Java5.entitys.OrderDetail;
import com.example.Assignment_Java5.entitys.Product;
import com.example.Assignment_Java5.service.IOrderDetailsService;
import com.example.Assignment_Java5.service.IOrderService;
import com.example.Assignment_Java5.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/home")
public class CartOrderController {
    @Autowired
    private HttpSession session;
    @Autowired
    private Order order;

    @Autowired
    private IProductService productDao;

    @Autowired
    private IOrderService orderDao;

    @Autowired
    private IOrderDetailsService orderDetailsDao;

    @GetMapping("addtoorder")
    public String addOrder(@RequestParam(name = "name") String name, @RequestParam(name = "phone") String phone, @RequestParam(name = "address") String address) {
        Order orderSession = (Order) session.getAttribute("order");
        if (orderSession != null) {
            Account user = (Account) session.getAttribute("user");
            List<OrderDetail> listOrder = orderSession.getOrderdetails();
            if (user == null) {
                order.setUserDatHang(null);
            } else {
                order.setUserDatHang(user);
            }
            order.setOrderdetails(listOrder);
            order.setCreateDate(new Date());
            order.setStatus(0);
            order.setAddress(address);
            order.setPhone(phone);
            order.setName(name);
            double total = 0;
            for (OrderDetail item : listOrder) {
                total += item.getPrice() * item.getQuantity();
            }
            order.setTotal(total);
            try {
                this.orderDao.insert(order);
                for (OrderDetail item : listOrder) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setQuantity(item.getQuantity());
                    orderDetail.setPrice(item.getPrice());
                    orderDetail.setProduct(item.getProduct());
                    this.orderDetailsDao.insert(orderDetail);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.removeAttribute("order");
            return "redirect:/home/index";
        } else {
            return "redirect:/home/cart";
        }
    }
}
