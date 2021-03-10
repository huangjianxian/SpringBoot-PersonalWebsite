package com.jontyhua.web.vo;

import java.util.List;

/**
 * @ClassName FlagVO
 * @Author: JontyHua
 * @Date: 2019/5/1
 * @Description: 博客类型 原创 翻译 转载
 */
public class FlagVO {

    // 名称
    private String name;

    public FlagVO() {

    }

    public FlagVO(String name) {
        this.name = name;
    }

    public List<FlagVO> blogFlag(){
        List<FlagVO> flagList = null;
        FlagVO flag1 = new FlagVO("原创");
        FlagVO flag2 = new FlagVO("转载");
        FlagVO flag3 = new FlagVO("翻译");
        flagList.add(flag1);
        flagList.add(flag2);
        flagList.add(flag3);
        return flagList;
    }
}
