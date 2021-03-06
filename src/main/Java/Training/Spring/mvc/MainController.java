package Training.Spring.mvc;

import Training.Constants.App_constants;
import Training.Dao.AdvDAO;
import Training.Dao.UserDao;
import Training.Entities.Advertisement.Advertisement;
import Training.Entities.Advertisement.Photo;
import Training.Utils.UserInfo;
import Training.Utils.XML.AdvertisementFileTransformer;
import Training.dto.testDTO;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static Training.Utils.StringUtils.ValidateAddData;
import static Training.Utils.XML.AdvertisementFileTransformer.sendXml;

//import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Администратор on 25.04.2017.
 */
@Controller
public class MainController implements App_constants {

    private final String indexPage = "index";

    @Autowired
    private AdvDAO advDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public String root(){
        return "redirect:/index";
    }

    @RequestMapping(indexPage)
    public String index(Model model) {
        model.addAttribute("roles", UserInfo.getCurrentUserRoles());
        model.addAttribute("userName", UserInfo.getCurrentUserName());
        model.addAttribute("advs",advDao.list(NOT_IN_BASKET));
        model.addAttribute("inBasketCount", advDao.list(IN_BASKET).size());
        return indexPage;
    }

    @RequestMapping(value = "/auth/add_page" ,method = RequestMethod.POST)
    public String addPage(){
        if (!userDao.isAdvAdditionAllowed()){
            return "error403";
        }
        return "add_page";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam(value = "pattern") String pattern, @RequestParam(value = "location") String location, Model model){
        model.addAttribute("inSearch", "yes");
        if (location.equals("basket")){
            model.addAttribute("advs", advDao.list(IN_BASKET, pattern));
            return "basket";
        }
        model.addAttribute("advs", advDao.list(NOT_IN_BASKET, pattern));
        model.addAllAttributes(UserInfo.getCurrentUserRoles());
        return indexPage;
    }

    @RequestMapping("/administration/clean_basket")
    public String cleanBasket(){
        List<Advertisement> list = advDao.list(IN_BASKET);
        for (Advertisement adv : list){
            advDao.delete(adv.getId());
        }
        return "redirect:/administration/basket";
    }

    @RequestMapping("/administration/restore_all")
    public String restoreAll(){
        List<Advertisement> list = advDao.list(IN_BASKET);
        for (Advertisement adv : list){
            advDao.changeBasketStatus(NOT_IN_BASKET, adv.getId());
        }
        return "redirect:/administration/basket";
    }

    @RequestMapping(value = {"/image/{file_id}", "/image"})
    public void getFile(HttpServletResponse response,
                        @PathVariable("file_id") long fileId){
        if (fileId!=0){
            try{
                byte[] content = advDao.getPhoto(fileId);
                response.setContentType("image/png");
                response.getOutputStream().write(content);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/auth/add", method = RequestMethod.POST)
    public String addAdv(@RequestParam(value = "name") String name,
                               @RequestParam(value = "shortDesc") String shortDesc,
                               @RequestParam(value = "longDesc", required = false) String longDesc,
                               @RequestParam(value = "phone") String phone,
                               @RequestParam(value = "price") String price,
                               @RequestParam(value = "photo") MultipartFile photo,
                               HttpServletResponse response, Model model){

        if (!userDao.isAdvAdditionAllowed()){
            model.addAttribute("error", "ERROR");
            return "add_page";
        }
        String validationError = ValidateAddData(new String[]{name, shortDesc, longDesc, phone}, photo.getOriginalFilename(), price);
        if(validationError!=null){
            model.addAttribute("error", validationError);
            return "add_page";
        }
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Advertisement advertisement = new Advertisement(name, shortDesc, longDesc, phone, Double.valueOf(price),
                    photo.isEmpty()?null: new Photo(photo.getOriginalFilename(), photo.getBytes()), false,
                    new Date(System.currentTimeMillis()), authentication.getName());
            advDao.add(advertisement);
            return "redirect:/"+indexPage;
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            return "redirect:/"+indexPage;
        }
    }

    @RequestMapping(value = "/administration/doAction", method = RequestMethod.POST)
    public String moveToBasket(@RequestParam(value = "id", required = false) long[] ids, @RequestParam(value = "action") String action, HttpServletResponse response){
        if(ids != null) {
            try {
                for (long id : ids) {
                    if (action.equals("remove")) {
                        advDao.changeBasketStatus(IN_BASKET, id);
                    } else if (action.equals("restore")) {
                        advDao.changeBasketStatus(NOT_IN_BASKET, id);
                    }
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                e.printStackTrace();
            }
            if (action.equals("restore")) {
                return "redirect:/administration/basket";
            }
        }
        return "redirect:/"+indexPage;
    }

    @RequestMapping(value = "/administration/basket")
    public String toBasket(Model model) {
        model.addAttribute("advs",advDao.list(IN_BASKET));
        return "basket";
    }

    @RequestMapping(value = "/auth/import", method = RequestMethod.POST)
    public String importAdvs(@RequestParam(value = "import") MultipartFile importFile, HttpServletResponse response){
        try {
            InputStream file = importFile.getInputStream();
            if (file.available()>0) {
                AdvertisementFileTransformer.importFromXml(file, advDao);
            }
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return "redirect:/"+indexPage;
    }

    @RequestMapping(value = "/auth/export", method = RequestMethod.POST)
    public String export(@RequestParam(value = "id", required = false) long[] ids, HttpServletResponse response){
        if(ids != null) {
            try {
                sendXml(ids, advDao, response);
                /*
                РЕАЛИЗОВАТЬ ОТПРАВКУ ОШИБКИ
                О ОТСУТСТВИИ ФАЙЛА
                */
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                e.printStackTrace();
            }
        }
        return "redirect:/"+indexPage;
    }

    @RequestMapping(value = "/login")

    public String login(){
        return "login_page";
    }

    @RequestMapping(value = "/access_denied")
    public String error403(){
        return "error403";
    }



    @RequestMapping(value = "/REST")
    public void testREST(HttpServletResponse response){
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        ObjectMapper mapper = new ObjectMapper();
        testDTO test = new testDTO("test1", "test2", 33);
        try {
            String res= mapper.writeValueAsString(test);
            response.getOutputStream().write(res.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
