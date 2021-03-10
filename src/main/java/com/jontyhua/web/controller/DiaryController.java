package com.jontyhua.web.controller;

import com.jontyhua.web.domain.Diary;
import com.jontyhua.web.service.DiaryService;
import com.jontyhua.web.util.ConstraintViolationExceptionHandler;
import com.jontyhua.web.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @ClassName DiaryController
 * @Author: JontyHua
 * @Date: 2019/4/30
 * @Description: 日记 控制器
 */
@Controller
@RequestMapping("/admin/diaries")
public class DiaryController {

    /** 刷新页面 URL */
    private static final String RefreshUrl = "/admin/diaries";

    @Autowired
    private DiaryService diaryService;

    /**
     * 后台分页展示日记数据
     * @param async
     * @param pageIndex
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView listDiary(@RequestParam(value="async",required=false) boolean async,
                            @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                            @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                            Model model){
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable = new PageRequest(pageIndex, pageSize,sort);
        Page<Diary> page = diaryService.listDiary(pageable);
        List<Diary> diaryList = page.getContent();
        model.addAttribute("page", page);
        model.addAttribute("diaryList",diaryList);
        return new ModelAndView(async==true?"admin/diary/listDiaries :: #mainContainerRepleace":"admin/diary/listDiaries", "diaryModel", model);
    }

    /**
     * 返回添加博客页面
     * @return
     */
    @GetMapping("/add")
    public String addBlog(Model model){
        return "admin/diary/addDiary";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteDiary(@PathVariable("id") Long id){
        try{
            diaryService.deleteDiary(id);
        }catch (Exception e){
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "成功删除日记", RefreshUrl));
    }

    /**
     * 获取日记修改界面和回显数据
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/edit/{id}")
    public ModelAndView editDiaryPage(@PathVariable("id") Long id, Model model) {
        Diary diary = diaryService.findDiaryById(id);
        model.addAttribute("diary", diary);
        return new ModelAndView("admin/diary/editDiary", "diaryModel", model);
    }

    // 新增公告
    @PostMapping
    public ResponseEntity<Response> saveDiary(@RequestBody Diary diary){
        try{
            diaryService.saveDiary(diary);
        }catch (ConstraintViolationException e){
            return ResponseEntity.ok().body(new Response(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "新增日记成功", RefreshUrl));
    }

    /**
     * 更新日记
     * @param diary
     * @return
     */
    @ResponseBody
    @PostMapping("/update")
    public ResponseEntity<Response> updateDiary(@RequestBody Diary diary) {
        try {
            Diary originDiary = diaryService.findDiaryById(diary.getId());
            originDiary.setTitle(diary.getTitle());
            originDiary.setContent(diary.getContent());
            originDiary.setState(diary.getState());
            diaryService.saveDiary(originDiary);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new Response(true, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) { //如果分类重复，在这里捕获异常
            return ResponseEntity.ok().body(new Response(true, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "更新成功", RefreshUrl));
    }

    // 下线日记
    @PostMapping("/offline/{id}")
    public ResponseEntity<Response> offlineDiary(@PathVariable("id") Long id){
        try{
            diaryService.offlineDiary(id);
        }catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "成功下线日记", RefreshUrl));
    }

    // 上线日记
    @PostMapping("/online/{id}")
    public ResponseEntity<Response> onlineDiary(@PathVariable("id") Long id){
        try{
            diaryService.onlineDiary(id);
        }catch (Exception e) {
            return ResponseEntity.ok().body(new Response(false, e.getMessage()));
        }
        return ResponseEntity.ok().body(new Response(true, "成功上线日记", RefreshUrl));
    }
}
