package by.itechart.flowerty.web.controller;

import java.io.IOException;
import java.util.List;

import by.itechart.flowerty.web.service.GoodsService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import by.itechart.flowerty.dao.repository.GoodsRepository;
import by.itechart.flowerty.dao.repository.UserRepository;
import by.itechart.flowerty.local.settings.Settings;
import by.itechart.flowerty.model.Company;
import by.itechart.flowerty.model.Goods;
import by.itechart.flowerty.web.controller.util.FlowertUtil;

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
    private GoodsRepository goodsRepository;

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping(value = "goods/add", method = RequestMethod.POST)
    public void add(@RequestParam("goods") String goodsJson, @RequestPart(value = "picture") MultipartFile goodsPicture)
	    throws IOException {

	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	if (!(authentication instanceof AnonymousAuthenticationToken)) {
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    String login = userDetails.getUsername();

	    LOGGER.info("add new goods. json: {}, picture name: {}, login: {}", goodsJson,
		    goodsPicture.getOriginalFilename(), login);

	    // TODO: need field in db
	    FlowertUtil.processMultipart(settings.getPicturesPath(), goodsPicture);

	    ObjectMapper mapper = new ObjectMapper();
	    Goods goods = mapper.readValue(goodsJson, Goods.class);
	    Company company = userRepository.findUserByLogin(login).getContact().getCompany();
	    goods.setCompany(company);

	    goodsRepository.save(goods);
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
