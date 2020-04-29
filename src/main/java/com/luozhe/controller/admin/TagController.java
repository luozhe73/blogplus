package com.luozhe.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luozhe.pojo.Tag;
import com.luozhe.pojo.Type;
import com.luozhe.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    TagService tagService;

    /**
     * 展示所有标签
     *
     * @param model
     * @param pageNum
     * @return
     */
    @RequestMapping("/tags")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 3);
        List<Tag> tagList = tagService.listTag();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(tagList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    /**
     * 新增标签跳转
     *
     * @param model
     * @return
     */
    @RequestMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "/admin/tags-input";
    }

    /**
     * 新增标签
     *
     * @param tag
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/tags")
    public String post(@Valid Tag tag,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName != null) {
            bindingResult.rejectValue("name", "nameError", "不能添加重复标签！");
        }
        if (bindingResult.hasErrors()) {
            return "/admin/tags-input";
        }
        try {
            tagService.saveTag(tag);
            redirectAttributes.addFlashAttribute("message", "新增成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "新增失败！");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 删除标签
     *
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            tagService.deleteTag(id);
            redirectAttributes.addFlashAttribute("message", "删除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "删除失败！");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 编辑分类
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/tags/{id}/input")
    public String edit(@PathVariable Long id, Model model) {
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag", tag);
        return "/admin/tags-input";

    }


    /**
     * 更新分类
     *
     * @param tag
     * @param bindingResult
     * @param id
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String update(@Valid Tag tag,
                         BindingResult bindingResult,
                         @PathVariable Long id,
                         RedirectAttributes redirectAttributes) {

        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName != null) {
            bindingResult.rejectValue("name", "nameError", "不能添加重复标签！");
        }
        if (bindingResult.hasErrors()) {
            return "/admin/tags-input";
        }
        try {
            tagService.updateTag(id,tag);
            redirectAttributes.addFlashAttribute("message", "更新成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "更新失败！");
        }
        return "redirect:/admin/tags";
    }
}
