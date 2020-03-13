package controllers.salary;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Salary;
import models.validators.SalaryValidator;
import utils.DBUtil;

/**
 * Servlet implementation class SalaryCreateServlet
 */
@WebServlet("/salary/update")
public class SalaryUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Salary s = em.find(Salary.class, (Integer)(request.getSession().getAttribute("salary_id")));

            s.setJob_date(Date.valueOf(request.getParameter("job_date")));
            s.setShop_name(request.getParameter("shop_name"));
            s.setHour(Integer.parseInt(request.getParameter("hour")));
            s.setHourly_wage(Integer.parseInt(request.getParameter("wage")));

            List<String> errors = SalaryValidator.validate(s);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("salary", s);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/salary/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("salary_id");

                response.sendRedirect(request.getContextPath() + "/index.html");
            }
        }
    }

}
