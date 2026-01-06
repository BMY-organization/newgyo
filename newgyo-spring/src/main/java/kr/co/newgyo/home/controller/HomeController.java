package kr.co.newgyo.home.controller;

import kr.co.newgyo.article.service.ArticleDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.plaf.PanelUI;

@Controller
@RequestMapping("/home")
public class HomeController {

    ArticleDetailService articleDetailService;

    public HomeController(ArticleDetailService articleDetailService) {
        this.articleDetailService = articleDetailService;
    }

    @GetMapping
    public String home(Model model){
        // 주요 뉴스 (카테고리별로 안바뀌는 가정하에)
        model.addAttribute("article", articleDetailService.homeArticleData());
        return "home";
    }
}
