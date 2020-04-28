package com.luozhe.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luozhe.pojo.Type;
import com.luozhe.service.TyepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TyepService tyepService;

    @GetMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, 3);
        List<Type> types = tyepService.listType();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }
}
