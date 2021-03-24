package mk.finki.ukim.lab.web.servlets.servlets;

import mk.finki.ukim.lab.model.Balloon;
import mk.finki.ukim.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "balloon-order-servlet", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final OrderService orderService;

    public BalloonOrderServlet(SpringTemplateEngine templateEngine, OrderService orderService) {
        this.templateEngine = templateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context =  new WebContext(req, resp, req.getServletContext());
        resp.setContentType("text/html;charset=UTF-8");
        templateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("clientName");
        String address = req.getParameter("clientAddress");

        if (name.isEmpty() || address.isEmpty()){
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("hasError", true);
            context.setVariable("error", "Please enter your information");
            resp.setContentType("text/html;charset=UTF-8");
            templateEngine.process("deliveryInfo.html", context, resp.getWriter());
        }

        req.getSession().setAttribute("name", name);
        req.getSession().setAttribute("address", address);

        Balloon b = (Balloon) req.getSession().getAttribute("balloon");
        orderService.placeOrder(b.getName(), name, address);
        resp.sendRedirect("/ConfirmationInfo");
    }
}
