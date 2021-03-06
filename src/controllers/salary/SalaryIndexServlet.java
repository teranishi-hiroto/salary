package controllers.salary;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Salary;
import utils.DBUtil;

/**
 * Servlet implementation class SalaryIndexServlet
 */
@WebServlet("/salary/index")
public class SalaryIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e) {
            page = 1;
        }

        List<Salary> month = em.createNamedQuery("getMonth", Salary.class)
                .setParameter("startDate",  2020-3-1)
                .setParameter("endDate", 2020-3-31)
                .getResultList();

        List<Salary> salaries = em.createNamedQuery("getAllSalaries", Salary.class)
                                  .setFirstResult(15 * (page - 1))
                                  .setMaxResults(15)
                                  .getResultList();

        long salaries_count = (long)em.createNamedQuery("getSalariesCount", Long.class)
                                     .getSingleResult();

        em.close();

        request.setAttribute("month", month);
        request.setAttribute("salaries", salaries);
        request.setAttribute("salaries_count", salaries_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/salary/index.jsp");
        rd.forward(request, response);
    }

}
