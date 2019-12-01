package com.only4play.prome.model;

import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @Author: Gim
 * @Date: 2019/11/29 14:53
 * @Description:
 */
@Data
public class Groups {
    private String name;
    private List<Map<String,Object>> rules;
}