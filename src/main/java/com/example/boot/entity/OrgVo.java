package com.example.boot.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * user entity
 *
 * @author ltk
 * @date 2019/11/24
 */
public class OrgVo {

    private Integer orgId;

    private Integer parentId;

    private String orgName;

    private List<OrgVo> childList;


    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<OrgVo> getChildList() {
        return childList == null ? childList = new ArrayList<>() : childList;
    }

    public void setChildList(List<OrgVo> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "OrgVo{" +
                "orgId=" + orgId +
                ", parentId=" + parentId +
                ", orgName='" + orgName + '\'' +
                ", childList=" + childList +
                '}';
    }
}
