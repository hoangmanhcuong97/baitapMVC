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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            case "delete":
                showDeleteForm(req,resp);
                break;
            case "view":
                viewCustomer(req,resp);
                break;
            default:
                listCustomers(req, resp);
                break;
        }
    }

    private void viewCustomer(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.findById(id);
        req.setAttribute("customer3", customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Customer/view.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.findById(id);
        req.setAttribute("customer2", customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Customer/delete.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.findById(id);
        req.setAttribute("customer", customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Customer/edit.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("Customer/create.jsp");
        try {
            dispatcher.forward(req,resp);
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
                createCustomer(req,resp);
                break;
            case "edit":
                updateCustomer(req, resp);
                break;
            case "delete":
                deleteCustomer(req,resp);
                break;
            default:
                break;
        }
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerService.findById(id);
        customerService.remove(id);
        try {
            resp.sendRedirect("/customers");//chuyen huong den 1 URL khac
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateCustomer(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        Customer customer = customerService.findById(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        customerService.update(id, customer);
        req.setAttribute("customer", customer);
        req.setAttribute("message", "Customer information was updated");
        RequestDispatcher dispatcher = req.getRequestDispatcher("Customer/edit.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        int id = (int)(Math.random() * 10000);
        Customer customer = new Customer(id,name, email, address);
        customerService.save(customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Customer/create.jsp");
        req.setAttribute("message","New customer was created");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
