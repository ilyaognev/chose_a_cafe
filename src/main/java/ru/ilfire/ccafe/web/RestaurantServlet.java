package ru.ilfire.ccafe.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import ru.ilfire.ccafe.model.Restaurant;
import ru.ilfire.ccafe.service.RestaurantService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class RestaurantServlet extends HttpServlet {
    private ConfigurableApplicationContext springContext;
    private RestaurantService restaurantService;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        restaurantService = springContext.getBean(RestaurantService.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Restaurant restaurant = new Restaurant(
                request.getParameter("name"));

        if (StringUtils.isEmpty(request.getParameter("id"))) {
            restaurantService.create(restaurant);
        } else {
            restaurantService.update(restaurant);
        }
        response.sendRedirect("restaurant");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                restaurantService.delete(id);
                response.sendRedirect("restaurants");
                break;
            case "create":
            case "update":
                final Restaurant restaurant = "create".equals(action) ?
                        new Restaurant("New Restaurant") :
                        restaurantService.get(getId(request));
                request.setAttribute("restaurant", restaurant);
                request.getRequestDispatcher("/restaurantForm.jsp").forward(request, response);
                break;
            case "filter":
//                LocalDate votingDate = parseLocalDate(request.getParameter("votingDate"));
//                request.setAttribute("restaurant", restaurantService.getRestaurantPerDay(votingDate));
//                request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
//                break;
            case "all":
            default:
                request.setAttribute("restaurant", restaurantService.getAll());
                request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
