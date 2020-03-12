package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "salaries")
@NamedQueries({
    @NamedQuery(
            name = "getAllSalaries",
            query = "SELECT s FROM Salary AS s ORDER BY s.id DESC"
            ),
    @NamedQuery(
            name = "getSalariesCount",
            query = "SELECT COUNT(s) FROM Salary AS s"
            ),
    @NamedQuery(
            name = "getMonth",
            query = "SELECT s FROM Salary AS s  WHERE s.job_date BETWEEN :startdate AND :enddate"
          )
})
@Entity
public class Salary {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "job_date", nullable = false)
    private Date job_date;

    @Column(name = "shop_name", length = 255, nullable = false)
    private String shop_name;

    @Column(name = "hour", nullable = false)
    private Integer hour;

    @Column(name = "hourly_wage", nullable = false)
    private Integer hourly_wage;

//    @Lob
//    @Column(name = "content")
//    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getJob_date() {
        return job_date;
    }

    public void setJob_date(Date job_date) {
        this.job_date = job_date;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getHourly_wage() {
        return hourly_wage;
    }

    public void setHourly_wage(Integer hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }

}
