package com.itsun.stock.vo.resp;

import java.util.List;
import java.util.Map;

public class UpdownRespVo {
    private List<Map> upList;
    private List<Map> downList;

    @Override
    public String toString() {
        return "UpdownRespVo{" +
                "upList=" + upList +
                ", downList=" + downList +
                '}';
    }

    public List<Map> getUpList() {
        return upList;
    }

    public void setUpList(List<Map> upList) {
        this.upList = upList;
    }

    public List<Map> getDownList() {
        return downList;
    }

    public void setDownList(List<Map> downList) {
        this.downList = downList;
    }
}
