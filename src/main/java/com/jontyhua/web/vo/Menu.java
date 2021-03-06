package com.jontyhua.web.vo;

import java.io.Serializable;

/**
 * @ClassName Menu
 * @Author: JONTY HUA
 * @Date: 2019/3/30 20:43
 * @Description: 后台管理菜单项
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String url;

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
