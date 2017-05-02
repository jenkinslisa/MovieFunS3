package org.superbiz.moviefun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by lisajenkins on 5/1/17.
 */

@Controller
public class HomeController implements ErrorController {


    private MoviesBean moviesBean;



    public HomeController(@Autowired MoviesBean moviesBean) {
        this.moviesBean = moviesBean;
    }


    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/setup")
    public String setup(Map<String, Object> model) {
        moviesBean.addMovie(new Movie("Wedding Crashers", "David Dobkin", "Comedy", 7, 2005));
        moviesBean.addMovie(new Movie("Starsky & Hutch", "Todd Phillips", "Action", 6, 2004));
        moviesBean.addMovie(new Movie("Shanghai Knights", "David Dobkin", "Action", 6, 2003));
        moviesBean.addMovie(new Movie("I-Spy", "Betty Thomas", "Adventure", 5, 2002));
        moviesBean.addMovie(new Movie("The Royal Tenenbaums", "Wes Anderson", "Comedy", 8, 2001));
        moviesBean.addMovie(new Movie("Zoolander", "Ben Stiller", "Comedy", 6, 2001));
        moviesBean.addMovie(new Movie("Shanghai Noon", "Tom Dey", "Comedy", 7, 2000));

        model.put("movies", moviesBean.getMovies());
        return "setup";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/lisaError")
    public String lisaError() {
        return "lisaError";
    }


    @GetMapping("/index.jsp")
    public String anotherIndex(){
            return "index";
    }

    @GetMapping("/setup.jsp")
    public String setupFix(){
        return "setup";
    }

    @Override
    public String getErrorPath() {
        System.out.println("getErrorPath");
        return "lisaError";
    }


    @ExceptionHandler(Exception.class)
    public ModelAndView globalExceptionHandler(Exception e) {

        System.out.println("insideExceptionHandler");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

}
