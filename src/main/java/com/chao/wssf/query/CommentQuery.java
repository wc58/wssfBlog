package com.chao.wssf.query;

import lombok.Data;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@ToString
public class CommentQuery extends PagingQuery {
    private String title;
    private String username;
    private String content;
    private String contentSize;
    private String startTime;
    private String endTime;

    //对日期进行转换
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Date getStartTime() {
        if (!StringUtils.isEmpty(startTime)) {
            try {
                return simpleDateFormat.parse(startTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Date getEndTime(){
        if (!StringUtils.isEmpty(endTime)) {
            try {
                return simpleDateFormat.parse(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
