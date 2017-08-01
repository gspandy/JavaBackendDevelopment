package com.lavor.springmvc;

import java.util.List;

/**
 * Created by lei.zeng on 2017/7/6.
 */
public class StringList {
    private List<String> list;
    public StringList() {
    }
    public StringList(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
