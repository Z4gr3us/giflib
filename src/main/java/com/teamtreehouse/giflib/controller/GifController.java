package com.teamtreehouse.giflib.controller;

import com.teamtreehouse.giflib.data.GifRepository;
import com.teamtreehouse.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GifController {
    @Autowired
    private GifRepository gifRepository;

    @RequestMapping("/")
    public String listGifs(ModelMap modelMap){
        List<Gif> allGifs = gifRepository.getAllGifs();
        modelMap.put("gifs", allGifs);
        return "home";
    }

    // Mapping to Categories Page
    @RequestMapping("/gif/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap) {
        Gif gif = gifRepository.findByName(name);
        modelMap.put("gif",gif);
        return "gif-details";
    }

    // Mapping to Favorites Page
    @RequestMapping("/favorites")
    public String listFavorites(ModelMap modelMap) {
        List<Gif> gifs = gifRepository.findByFavoriteMark();
        modelMap.put("gifs", gifs);
        return "favorites";
    }

    // Mapping to Search using @RequestParam
    @RequestMapping(value="/", params="q")
    public String listSearch(@RequestParam("q") String q, ModelMap modelMap) {
        List<Gif> gifs= gifRepository.findBySearch(q);
        modelMap.put("gifs", gifs);
        return "search";
    }
}
