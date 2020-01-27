package localhost;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Helper to navigate to swagger directly.
 */
@Controller
public class Root {

    @RequestMapping("/")
    @ResponseBody
    public ModelAndView getRoot() {
        return new ModelAndView("/index.html");
    }


}
