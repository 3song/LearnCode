package com;

import org.apache.commons.text.StringEscapeUtils;

public class Test {
    public static void main(String[] args) {
        String s = StringEscapeUtils.escapeHtml4("<script>");
        System.out.println(s);
    }
}
