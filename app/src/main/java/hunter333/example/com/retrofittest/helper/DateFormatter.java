package hunter333.example.com.retrofittest.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hunter333 on 9.1.2018 г..
 */

public class DateFormatter {
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private Date date;
    private String dateString;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);

    public DateFormatter(Date date) {
        this.date = date;
        this.dateString=simpleDateFormat.format(date);
    }

    public DateFormatter(String dateString) {
        this.dateString = dateString;
        try {
            this.date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
}
