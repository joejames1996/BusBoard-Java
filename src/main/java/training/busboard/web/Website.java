package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.ConsoleInput;
import training.busboard.StopPoint;

import java.lang.reflect.Array;
import java.util.Arrays;

@Controller
@EnableAutoConfiguration
public class Website
{

    @RequestMapping("/")
    ModelAndView home ()
    {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo (@RequestParam("postcode") String postcode)
    {
        StopPoint[] result;
        try
        {
            result = ConsoleInput.readInput(postcode);
        } catch (Exception e) { return new ModelAndView("index", "error", "error"); }

        System.out.println(result);

        if (result == null) return new ModelAndView("index");

        StopPoint[] shortResults = new StopPoint[10];
        for (int index = 0; index < 10 && index < result.length; index++)
        {
            shortResults[index] = result[index];
        }

        return new ModelAndView("info", "busInfo", shortResults);
    }

    public static void main (String[] args) throws Exception
    {
        SpringApplication.run(Website.class, args);
    }

}