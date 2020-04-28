package com.luozhe.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luozhe.pojo.Type;
import com.luozhe.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeService typeService;

    /**
     * 进入分类页面展示所有
     * @param model
     * @param pageNum
     * @return
     */
    @GetMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, 3);
        List<Type> types = typeService.listType();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    /**
     * 点击新增分类
     * @param model
     * @return
     */
    @GetMapping("/types/input")
    public String input(Model model){
        //保证前端可用   th:object="${type}"
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /**
     * 新增分类
     * @param type
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/types")
    public String post(@Valid Type type,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes){

        //验证name是否才存在
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName!=null){
            bindingResult.rejectValue("name","nameError","不能添加重复分类！");
        }

        if (bindingResult.hasErrors()){
            return "/admin/types-input";
        }

        try {
            typeService.saveType(type);
            redirectAttributes.addFlashAttribute("message","新增成功！");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","新增失败！");
        }
        return "redirect:/admin/types";
    }

    /**
     * 删除分类
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        try {
            typeService.deleteType(id);
            redirectAttributes.addFlashAttribute("message","删除成功！");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","删除失败！");
        }
        return "redirect:/admin/types";
    }

    /**
     * 编辑分类
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/types/{id}/input")
    public String edit(@PathVariable Long id,Model model){
        Type typeById = typeService.getTypeById(id);
        model.addAttribute("type",typeById);
        return "/admin/types-input";

    }


    /**
     * 更新分类
     * @param type
     * @param bindingResult
     * @param id
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/types/{id}")
    public String update(@Valid Type type,
                       BindingResult bindingResult,
                       @PathVariable Long id,
                       RedirectAttributes redirectAttributes){

        //验证name是否才存在
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName!=null){
            bindingResult.rejectValue("name","nameError","不能添加重复分类！");
        }

        if (bindingResult.hasErrors()){
            return "/admin/types-input";
        }

        try {
            typeService.updateType(id,type);
            redirectAttributes.addFlashAttribute("message","更新成功！");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","更新失败！");
        }
        return "redirect:/admin/types";
    }
}
