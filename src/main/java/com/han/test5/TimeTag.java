package com.han.test5;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String format = "yyyy-MM-dd hh:mm:ss";
    private String foreColor = "black";
    private String backColor = "white";

    /**
     * 起始标签默认处理
     * @return
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        JspWriter writer = pageContext.getOut();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "<span style = 'color:%s;background-color:%s'>%s</span>",
                foreColor,
                backColor,
                sdf.format(new Date())
                ));
        try {
            writer.print(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setForeColor(String foreColor) {
        this.foreColor = foreColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }
}
