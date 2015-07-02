package com.github.drcoelho.paginationmenu;

import org.junit.Assert;
import org.junit.Test;

import com.github.drcoelho.paginationmenu.PaginationHelper;

/**
 * 
 * The pagination data will be the table below to be simple to understand the
 * tests and the algorithm.
 * 
 * Group 0
 * ---------
 * 0 5 10 15 --- pages items
 * 1 6 11 16
 * 2 7 12 17
 * 3 8 13 18
 * 4 9 14 19
 * 
 * 0 1 02 03  -- pages from 0 to 3
 * 
 * Group 1
 * ---------
 * 20 25 30 35 --- itens
 * 21 26 31 36
 * 22 27 32 37
 * 23 28 33 38
 * 24 29 34 39
 * 
 * 04 05 06 07 -- pages from 4 to 7
 * 
 * Group 2
 * ---------
 * 40 45 50 --- itens
 * 41 46 51 
 * 42 47 52 
 * 43 48 53 
 * 44 49  
 * 
 * 08 09 10 -- pages from 8 to 10
 * 
 * Example:
 * 	Based on this table, if I select the page 11, so the algotithm should return informations about group 1, 
 *  because page 11 is part of group 1, as is shown in table.
 * 
 */
public class PaginationHelperTest {

    /**
     * Configuration from all tests, as said in class comments.
     */
    private static final int PAGE_SIZE = 5;
    private static final int TOTAL_ITEMS = 53;
    private static final int PAGE_GROUP_SIZE = 4;

    /**
     * 
     */
    @Test
    public void should_return_the_first_page_based_on_actual_group() {
        Assert.assertEquals(8, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 10).getFirstPage());
        Assert.assertEquals(8, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 9).getFirstPage());
        Assert.assertEquals(8, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 8).getFirstPage());

        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 7).getFirstPage());
        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 6).getFirstPage());
        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 5).getFirstPage());
        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 4).getFirstPage());

        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 3).getFirstPage());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 2).getFirstPage());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 1).getFirstPage());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 0).getFirstPage());
    }

    @Test
    public void should_return_zero_for_first_page_when_the_selected_page_is_less_or_equals_to_zero() {
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, -10).getFirstPage());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, -1).getFirstPage());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 0).getFirstPage());
    }

    @Test
    public void should_return_last_page_based_on_actual_group() throws Exception {
        Assert.assertEquals(10, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 10).getLastPage());
        Assert.assertEquals(10, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 9).getLastPage());
        Assert.assertEquals(10, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 8).getLastPage());

        Assert.assertEquals(7, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 7).getLastPage());
        Assert.assertEquals(7, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 6).getLastPage());
        Assert.assertEquals(7, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 5).getLastPage());
        Assert.assertEquals(7, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 4).getLastPage());

        Assert.assertEquals(3, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 3).getLastPage());
        Assert.assertEquals(3, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 2).getLastPage());
        Assert.assertEquals(3, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 1).getLastPage());
        Assert.assertEquals(3, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 0).getLastPage());
    }

    @Test
    public void should_return_zero_for_last_page_when_selected_page_is_less_than_zero() {
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, -10).getLastPage());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, -1).getLastPage());
    }

    @Test
    public void should_return_the_previous_page_based_on_selected_page() {
        Assert.assertEquals(9, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 10).getPreviousPage());
        Assert.assertEquals(8, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 9).getPreviousPage());
        Assert.assertEquals(8, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 8).getPreviousPage());

        Assert.assertEquals(6, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 7).getPreviousPage());
        Assert.assertEquals(5, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 6).getPreviousPage());
        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 5).getPreviousPage());
        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 4).getPreviousPage());

        Assert.assertEquals(2, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 3).getPreviousPage());
        Assert.assertEquals(1, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 2).getPreviousPage());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 1).getPreviousPage());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 0).getPreviousPage());
    }

    @Test
    public void should_return_the_next_page_based_on_selected_page() {
        Assert.assertEquals(10, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 10).getNextPage());
        Assert.assertEquals(10, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 9).getNextPage());
        Assert.assertEquals(9, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 8).getNextPage());

        Assert.assertEquals(7, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 7).getNextPage());
        Assert.assertEquals(7, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 6).getNextPage());
        Assert.assertEquals(6, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 5).getNextPage());
        Assert.assertEquals(5, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 4).getNextPage());

        Assert.assertEquals(3, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 3).getNextPage());
        Assert.assertEquals(3, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 2).getNextPage());
        Assert.assertEquals(2, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 1).getNextPage());
        Assert.assertEquals(1, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 0).getNextPage());
    }

    @Test
    public void should_return_zero_from_previous_group_when_selected_page_is_less_than_zero() {
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, -10).getFirstPageFromPreviousGroup());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, -1).getFirstPageFromPreviousGroup());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 0).getFirstPageFromPreviousGroup());
    }

    @Test
    public void should_return_first_page_from_previous_group() {
        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 10).getFirstPageFromPreviousGroup());
        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 9).getFirstPageFromPreviousGroup());
        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 8).getFirstPageFromPreviousGroup());

        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 7).getFirstPageFromPreviousGroup());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 6).getFirstPageFromPreviousGroup());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 5).getFirstPageFromPreviousGroup());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 4).getFirstPageFromPreviousGroup());

        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 3).getFirstPageFromPreviousGroup());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 2).getFirstPageFromPreviousGroup());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 1).getFirstPageFromPreviousGroup());
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 0).getFirstPageFromPreviousGroup());
    }

    @Test
    public void should_return_first_page_from_next_group() {
        Assert.assertEquals(10, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 10).getFirstPageFromNextGroup());
        Assert.assertEquals(10, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 9).getFirstPageFromNextGroup());
        Assert.assertEquals(10, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 8).getFirstPageFromNextGroup());

        Assert.assertEquals(8, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 7).getFirstPageFromNextGroup());
        Assert.assertEquals(8, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 6).getFirstPageFromNextGroup());
        Assert.assertEquals(8, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 5).getFirstPageFromNextGroup());
        Assert.assertEquals(8, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 4).getFirstPageFromNextGroup());

        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 3).getFirstPageFromNextGroup());
        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 2).getFirstPageFromNextGroup());
        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 1).getFirstPageFromNextGroup());
        Assert.assertEquals(4, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 0).getFirstPageFromNextGroup());
    }

    @Test
    public void should_return_zero_from_next_group_when_selected_page_is_less_than_zero() {
        Assert.assertEquals(0, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, -10).getFirstPageFromNextGroup());
    }

    @Test
    public void should_return_selected_page() {
        Assert.assertEquals(10, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 10).getSelectedPage());
        Assert.assertEquals(-10, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, -10).getSelectedPage());
    }

    @Test
    public void should_return_max_page_count() {
        Assert.assertEquals(11, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, 10).getMaxPageCount());
        Assert.assertEquals(11, new PaginationHelper(PAGE_SIZE, TOTAL_ITEMS, PAGE_GROUP_SIZE, -10).getMaxPageCount());
    }

}
