package by.itechart.flowerty.web.controller;

import java.io.IOException;

import javassist.tools.web.BadHttpRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import by.itechart.flowerty.persistence.repository.UserRepository;
import by.itechart.flowerty.local.settings.Settings;
import by.itechart.flowerty.persistence.repository.model.Company;
import by.itechart.flowerty.persistence.repository.model.Goods;
import by.itechart.flowerty.web.controller.util.FlowertUtil;
import by.itechart.flowerty.web.service.GoodsService;

/**
 * @author Eugene Putsykovich(slesh) Apr 21, 2015
 * 
 *         handler actions of goods
 */
@Controller
public class GoodsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private Settings settings;

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping(value = "goods/add", method = RequestMethod.POST)
    public void add(@RequestParam("goods") String goodsJson, @RequestPart(value = "picture") MultipartFile goodsPicture)
	    throws IOException, BadHttpRequest {
	
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	if (!(authentication instanceof AnonymousAuthenticationToken)) {
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    String login = userDetails.getUsername();

	    LOGGER.info("add new goods. json: {}, picture name: {}, login: {}", goodsJson,
		    goodsPicture.getOriginalFilename(), login);
	    
	    String name = FlowertUtil.processMultipart(settings.getPicturesPath(), goodsPicture);
	    
	    ObjectMapper mapper = new ObjectMapper();
	    Goods goods = mapper.readValue(goodsJson, Goods.class);
	    Company company = userRepository.findUserByLogin(login).getContact().getCompany();
	    goods.setCompany(company);
	    goods.setImage(name);
	    
	    goodsService.save(goods);
	}else{
	    LOGGER.info("anonymous user can't add goods");
	    
	    throw new BadHttpRequest();
	}
    }

    @ResponseBody
    @RequestMapping(value = "goods/list/{page}", method = RequestMethod.GET)
    public Page<Goods> page(@PathVariable("page") Integer page){
        LOGGER.info("get goods page with number {}", page);

        page = (page == null || page < 1) ? 0 : --page;
        return goodsService.getPage(page, 10);
    }
}
