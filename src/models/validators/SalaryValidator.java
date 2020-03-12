package models.validators;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import models.Salary;

public class SalaryValidator {
    public static List<String> validate(Salary s) {
        List<String> errors = new ArrayList<String>();

        String date_error = _validateDate(s.getJob_date());
        if(!date_error.equals("")) {
            errors.add(date_error);
        }

        String name_error = _validateName(s.getShop_name());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String hour_error = _validateHour(s.getHour());
        if(!hour_error.equals("")) {
            errors.add(hour_error);
        }

        String wage_error = _validateWage(s.getHourly_wage());
        if(!wage_error.equals("")) {
            errors.add(wage_error);
        }

        return errors;
    }

    private static String _validateWage(Integer wage) {
        if(wage == null) {
            return "時給を入力してください。";
            }

        return "";
    }

    private static String _validateHour(Integer hour) {
        if(hour == null) {
            return "勤務時間を入力してください。";
            }

        return "";
    }

    private static String _validateDate(Date job_date) {
        if(job_date == null) {
            return "日付を入力してください。";
            }

        return "";
    }

    private static String _validateName(String shop_name) {
        if(shop_name == null || shop_name.equals("")) {
            return "店名を入力してください。";
            }

        return "";
    }
}
