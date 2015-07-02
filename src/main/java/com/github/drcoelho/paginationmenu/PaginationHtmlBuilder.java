package com.github.drcoelho.paginationmenu;

import java.text.MessageFormat;

public class PaginationHtmlBuilder {

    private static final String PREVIOUS_PAGE_LI = "\n<li class=\"{0}\"><a href=\"{1}\" aria-label=\"Previous\"> <span aria-hidden=\"true\">&laquo;</span></a></li>";
    private static final String NEXT_PAGE_LI = "\n<li class=\"{0}\"><a href=\"{1}\" aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span></a></li>";
    private static final String FIRST_PAGE_FROM_OTHER_GROUP_LI = "\n<li class=\"{0}\"><a href=\"{1}\">...</a></li>";
    private static final String PAGE_LI = "\n<li class=\"{0}\"><a href=\"{1}\">{2}</a></li>";
    private static final String PAGE_PARAM = "?{0}=";

    private PaginationHelper helper;
    private String requestPath;
    private String pageParameterName;

    public PaginationHtmlBuilder(PaginationHelper helper, String requestPath, String pageParameterName) {
        this.helper = helper;
        this.requestPath = requestPath;
        this.pageParameterName = pageParameterName;
    }

    public String buildPaginationMenu() {

        StringBuilder html = new StringBuilder();

        html.append("\n<nav>");
        html.append("\n<ul class=\"pagination center\">");

        html.append(getPreviousPageLi());
        html.append(getFirstPageFromPreviousGroupLi());
        html.append(getPagesLi());
        html.append(getFirstPageFromNextGroupLi());
        html.append(getNextPageLi());

        html.append("\n</ul></nav>");

        html.append("\n");
        
        return html.toString();
    }

    private String getPreviousPageLi() {
        String clazz = "disabled";
        String href = "#";
        if (helper.getSelectedPage() != helper.getFirstPage()) {
            clazz = "";
            href = this.requestPath + getPageParam() + helper.getPreviousPage();
        }
        return MessageFormat.format(PREVIOUS_PAGE_LI, clazz, href);
    }

    private String getNextPageLi() {
        String clazz = "disabled";
        String href = "#";
        if (helper.getSelectedPage() != helper.getLastPage()) {
            clazz = "";
            href = this.requestPath + getPageParam() + helper.getNextPage();
        }
        return MessageFormat.format(NEXT_PAGE_LI, clazz, href);
    }

    private String getFirstPageFromPreviousGroupLi() {
        String clazz = "disabled";
        String href = "#";
        if (helper.getFirstPageFromPreviousGroup() < helper.getFirstPage()) {
            clazz = "";
            href = this.requestPath + getPageParam() + helper.getFirstPageFromPreviousGroup();
        }
        return MessageFormat.format(FIRST_PAGE_FROM_OTHER_GROUP_LI, clazz, href);
    }

    private String getFirstPageFromNextGroupLi() {
        String clazz = "disabled";
        String href = "#";
        if (helper.getFirstPageFromNextGroup() > helper.getLastPage()) {
            clazz = "";
            href = this.requestPath + getPageParam() + helper.getFirstPageFromNextGroup();
        }
        return MessageFormat.format(FIRST_PAGE_FROM_OTHER_GROUP_LI, clazz, href);
    }

    private String getPagesLi() {
        StringBuilder pagesLi = new StringBuilder();
        for (int item : helper.getPages()) {
            String href = this.requestPath + getPageParam() + item;
            String clazz = (item == helper.getSelectedPage()) ? "active" : "";
            pagesLi.append(MessageFormat.format(PAGE_LI, clazz, href, item));
        }
        return pagesLi.toString();
    }
    
    public String getPageParam() {
        return MessageFormat.format(PAGE_PARAM, pageParameterName);
    }

}
