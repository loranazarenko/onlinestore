package com.epam.onlinestore.web.customtag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Calendar;

public class MyTagHandler extends TagSupport {

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        try {
            out.print(Calendar.getInstance().getTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

}
