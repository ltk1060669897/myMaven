package com.example.boot.entity;


import com.example.boot.annotation.Length;
import lombok.Data;

/**
 * user entity
 *
 * @author ltk
 * @date 2019/11/24
 */
@Data
public class TbUser {

    private Integer userId;
    @Length(max = 30, min = 1, message = "名字长度不符")
    private String userName;
    private Integer userAge;
    private String delYn;
    private String strObj;

    @Override
    public String toString() {
        return "TbUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", delYn='" + delYn + '\'' +
                ", strObj='" + strObj + '\'' +
                '}';
    }
}
