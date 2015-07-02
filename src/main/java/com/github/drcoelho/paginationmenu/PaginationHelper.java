package com.github.drcoelho.paginationmenu;

import java.util.ArrayList;
import java.util.List;

public class PaginationHelper {

    private int pageSize;
    private int pageGroupSize;
    private int totalItems;
    private int selectedPage;

    public PaginationHelper(int pageSize, int totalItems, int pageGroupSize, int selectedPage) {
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.pageGroupSize = pageGroupSize;
        this.selectedPage = selectedPage;
    }

    public List<Integer> getPages() {
        List<Integer> pages = new ArrayList<Integer>();
        for (int page = getFirstPage(); page <= getLastPage(); page++) {
            pages.add(page);
        }
        return pages;
    }

    public int getPreviousPage() {
        int previousPage = getSelectedPage() - 1;
        return (previousPage >= getFirstPage()) ? previousPage : getFirstPage();
    }

    public int getNextPage() {
        int next = getSelectedPage() + 1;
        return (next <= getLastPage()) ? next : getLastPage();
    }

    public int getLastPage() {
        if (getSelectedPage() < 0) {
            return 0;
        }
        int lastPage = getFirstPage() + getPageGroupSize() - 1;
        if (lastPage >= getMaxPageCount()) {
            return getMaxPageCount() - 1;
        } else {
            return lastPage;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getFirstPageFromPreviousGroup() {
        int tmp = calculatePageGroupFromPage(getFirstPage() - 1) * getPageGroupSize();
        if (tmp <= 0) {
            return 0;
        } else {
            return tmp;
        }
    }

    public int getFirstPage() {
        int tmp = calculatePageGroupFromPage(selectedPage) * getPageGroupSize();
        if (tmp <= 0) {
            return 0;
        } else {
            return tmp;
        }
    }

    public int getFirstPageFromNextGroup() {
        int actualGroup = calculatePageGroupFromPage(getSelectedPage());
        int nextGroup = calculatePageGroupFromPage(getLastPage() + 1);

        if (actualGroup == nextGroup) {
            return getLastPage();

        } else {

            int firstPageFromNextGroup = nextGroup * getPageGroupSize();
            if (firstPageFromNextGroup >= getMaxPageCount()) {
                return getMaxPageCount() - 1;
            } else {
                return firstPageFromNextGroup;
            }
        }
    }

    private int calculatePageGroupFromPage(int page) {
        return page / getPageGroupSize();
    }

    public int getMaxPageCount() {
        int tmp = totalItems / pageSize;
        int rest = totalItems % pageSize;
        if (rest > 0) {
            return tmp + 1;
        } else {
            return tmp;
        }
    }

    public int getSelectedPage() {
        return selectedPage;
    }

    public int getPageGroupSize() {
        return pageGroupSize;
    }

}
