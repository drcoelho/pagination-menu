package com.github.drcoelho.paginationmenu;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

public class PaginationCustomTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_MAX_ITEMS_PER_PAGE = 100;
    private static final int DEFAULT_MAX_PAGES_IN_MENU = 23;
    private static final String DEFAULT_PAGE_PARAMETER_NAME = "page";

    private int maxItemsPerPage;
    private int maxPagesInMenu;
    private int itemsCount;
    private String pageParameterName;

    public void setMaxItemsPerPage(int maxItemsPerPage) {
        this.maxItemsPerPage = maxItemsPerPage;
    }

    public void setMaxPagesInMenu(int maxPagesInMenu) {
        this.maxPagesInMenu = maxPagesInMenu;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public void setPageParameterName(String pageParameterName) {
        this.pageParameterName = pageParameterName;
    }

    @Override
    public int doStartTag() throws JspException {

        int selectedPage = 0;

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        String parameterName = StringUtils.defaultIfEmpty(pageParameterName, DEFAULT_PAGE_PARAMETER_NAME);
        String param = pageContext.getRequest().getParameter(parameterName);
        if (param != null) {
            selectedPage = Integer.parseInt(param);
        }

        String requestPath = request.getContextPath() + request.getServletPath();

        PaginationHelper helper = new PaginationHelper(defaultIfZero(maxItemsPerPage, DEFAULT_MAX_ITEMS_PER_PAGE), itemsCount,
                defaultIfZero(maxPagesInMenu, DEFAULT_MAX_PAGES_IN_MENU), selectedPage);

        PaginationHtmlBuilder builder = new PaginationHtmlBuilder(helper, requestPath, parameterName);

        JspWriter out = pageContext.getOut();
        try {
            out.println(builder.buildPaginationMenu());
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    private int defaultIfZero(int preferedValue, int defaultValue) {
        return (preferedValue > 0) ? preferedValue : defaultValue;
    }

}
