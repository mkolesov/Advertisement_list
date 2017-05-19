package Training.mvc;

import Training.App_constants;
import Training.Dao.AdvDAO;
import Training.Entities.Advertisement;
import Training.Entities.Photo;
import Training.Utils.XmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Администратор on 25.04.2017.
 */
@Controller
public class MainController implements App_constants {

    private String indexPage = "index";

    @Autowired
    private AdvDAO advDao;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("advs",advDao.list(NOT_IN_BASKET));
        model.addAttribute("inBasketCount", advDao.list(IN_BASKET).size());
        return indexPage;
    }

    @RequestMapping(value = "/add_page" ,method = RequestMethod.POST)
    public String addPage(){
        return "add_page";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam(value = "pattern") String pattern, @RequestParam(value = "location") String location){
        if (location.equals("basket")){
            return new ModelAndView("basket", "advs", advDao.list(IN_BASKET, pattern));
        }
        return new ModelAndView(indexPage, "advs", advDao.list(NOT_IN_BASKET, pattern));
    }

    @RequestMapping("/clean_basket")
    public String cleanBasket(){
        List<Advertisement> list = advDao.list(IN_BASKET);
        for (Advertisement adv : list){
            advDao.delete(adv.getId());
        }
        return "redirect:/basket";
    }

    @RequestMapping("/restore_all")
    public String restoreAll(){
        List<Advertisement> list = advDao.list(IN_BASKET);
        for (Advertisement adv : list){
            advDao.changeBasketStatus(NOT_IN_BASKET, adv.getId());
        }
        return "redirect:/basket";
    }

    @RequestMapping("/image/{file_id}")
    public void getFile(HttpServletResponse response,
                        @PathVariable("file_id") long fileId){
        try{
            byte[] content = advDao.getPhoto(fileId);
            response.setContentType("image/png");
            response.getOutputStream().write(content);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAdv(@RequestParam(value = "name") String name,
                               @RequestParam(value = "shortDesc") String shortDesc,
                               @RequestParam(value = "longDesc", required = false) String longDesc,
                               @RequestParam(value = "phone") String phone,
                               @RequestParam(value = "price") double price,
                               @RequestParam(value = "photo") MultipartFile photo,
                               HttpServletResponse response){
        try {
            Advertisement advertisement = new Advertisement(name, shortDesc, longDesc, phone, price,
                    photo.isEmpty()?null: new Photo(photo.getOriginalFilename(), photo.getBytes()));
            advDao.add(advertisement);
            return "redirect:/";
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/doAction", method = RequestMethod.POST)
    public String moveToBasket(@RequestParam(value = "id") long[] ids, @RequestParam(value = "action") String action, HttpServletResponse response){
        try {
            if (action.equals("export")){
                XmlUtils.exportToXml(ids, advDao, "e:\\export.xml");
            }
            for (long id: ids){
                if (action.equals("remove")){
                    advDao.changeBasketStatus(IN_BASKET, id);
                } else if (action.equals("restore")){
                    advDao.changeBasketStatus(NOT_IN_BASKET, id);
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        if (action.equals("restore")){
            return "redirect:/basket";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/basket")
    public String toBasket(Model model) {
        model.addAttribute("advs",advDao.list(IN_BASKET));
        return "basket";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importAdvs(@RequestParam(value = "import") MultipartFile importFile, HttpServletResponse response){
        try {
            InputStream file = importFile.getInputStream();
            if (file.available()>0) {
                XmlUtils.importFromXml(file, advDao);
            }
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
