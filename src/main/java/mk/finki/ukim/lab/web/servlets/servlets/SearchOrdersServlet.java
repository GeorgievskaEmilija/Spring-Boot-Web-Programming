package mk.finki.ukim.lab.web.servlets.servlets;

import mk.finki.ukim.lab.model.Order;
import mk.finki.ukim.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "search-orders-servlet", urlPatterns = "/userOrders")
public class SearchOrdersServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;
    private final OrderService orderService;

    public SearchOrdersServlet(SpringTemplateEngine templateEngine, OrderService orderService) {
        this.templateEngine = templateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        resp.setContentType("text/html;charset=UTF-8");
        templateEngine.process("userOrders.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("client-name");
        String clientAddress = req.getParameter("client-address");

        List<Order> clientorders = orderService.searchByClient(clientName, clientAddress);
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("orders", clientorders);
        context.setVariable("flag", true);

        resp.setContentType("text/html;charset=UTF-8");
        templateEngine.process("userOrders.html", context, resp.getWriter());
    }
}
