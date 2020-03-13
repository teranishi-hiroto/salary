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

import models.Employee;
import models.Salary;
import models.validators.SalaryValidator;
import utils.DBUtil;

/**
 * Servlet implementation class SalaryCreateServlet
 */
@WebServlet("/salary/create")
public class SalaryCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryCreateServlet() {
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

            Salary s = new Salary();

            s.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

            Date job_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("job_date");
            if(rd_str != null && !rd_str.equals("")) {
                job_date = Date.valueOf(request.getParameter("job_date"));
            }
            s.setJob_date(job_date);

            s.setShop_name(request.getParameter("shop_name"));
            s.setHour(Integer.parseInt(request.getParameter("hour")));
            s.setHourly_wage(Integer.parseInt(request.getParameter("wage")));

            List<String> errors = SalaryValidator.validate(s);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("salary", s);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/salary/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(s);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "追加が完了しました。");

                response.sendRedirect(request.getContextPath() + "/index.html");
            }
        }
    }


}
