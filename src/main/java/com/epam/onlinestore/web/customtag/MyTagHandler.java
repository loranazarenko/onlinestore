package com.epam.onlinestore.web.customtag;

import java.util.Calendar;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class MyTagHandler  extends TagSupport{

    @Override
    public int doStartTag() throws JspException {
        JspWriter out=pageContext.getOut();//returns the instance of JspWriter
        try{
            out.print(Calendar.getInstance().getTime());//printing date and time using JspWriter
        }catch(Exception e){System.out.println(e.getMessage());}
        return SKIP_BODY;//will not evaluate the body content of the tag
    }
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
