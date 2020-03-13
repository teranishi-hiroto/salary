package controllers.salary;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Salary;
import utils.DBUtil;

/**
 * Servlet implementation class SalaryIndexServlet
 */
@WebServlet("/salary/edit")
public class SalaryEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Salary s = em.find(Salary.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        if(s != null && login_employee.getId() == s.getEmployee().getId()) {
            request.setAttribute("salary", s);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("salary_id", s.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/salary/edit.jsp");
        rd.forward(request, response);
    }

}
