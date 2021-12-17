package data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
   @GetMapping("/")
   public String home() {
      
      return "/layout/guestMain";
   }
   
   @GetMapping("/host/main")
   public String hostmain() {

      return "/layout/hostMain";
   }
}