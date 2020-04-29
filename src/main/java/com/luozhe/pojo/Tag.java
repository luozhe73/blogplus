package com.luozhe.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tag {

    private Long id;

    @NotBlank(message = "不能为空")
    private String name;

    private List<Blog> blogs = new ArrayList<>();
}
