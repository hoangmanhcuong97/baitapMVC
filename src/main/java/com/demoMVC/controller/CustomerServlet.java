package com.demoMVC.controller;

import com.demoMVC.model.Customer;
import com.demoMVC.service.CustomerService;
import com.demoMVC.service.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    private void listCustomers(HttpServletRequest request, HttpServletResponse response) {
        List<Customer> customers = customerService.findAll();
        request.setAttribute("customer1", customers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Customer/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createCustomer(HttpServletRequest request, HttpServletResponse response){

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                break;
            case "edit":
                break;
            case "delete":
                break;
            case "view":
                findByID(req,resp);
                break;
            default:
                listCustomers(req, resp);
                break;
        }
    }

    private void findByID(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        int id1 = Integer.parseInt(id);
        Customer customer2 = customerService.findById(id1);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Customer/view.jsp");
        req.setAttribute("co", customer2);
        try {
            requestDispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                break;
            case "edit":
                break;
            case "delete":
                break;
            default:
                break;
        }
    }
}
