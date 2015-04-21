package by.itechart.flowerty.web.controller;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import by.itechart.flowerty.dao.repository.GoodsRepository;
import by.itechart.flowerty.local.settings.Settings;
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
    private Settings settings;

    @Autowired
    private GoodsRepository goodsRepository;

    @ResponseBody
    @RequestMapping(value = "goods/add", method = RequestMethod.POST)
    public void add(@RequestParam("goods") String goodsJson, @RequestPart(value = "picture") MultipartFile goodsPicture) {
	try {
	    LOGGER.info("add new goods. json: {}, picture name: {}", goodsJson, goodsPicture.getOriginalFilename());

	    FlowertUtil.processMultipart(settings.getPicturesPath(), goodsPicture);

	    LOGGER.info("goods picture saved successful!");

	    ObjectMapper mapper = new ObjectMapper();
	    Goods goods = mapper.readValue(goodsJson, Goods.class);

//	    goodsRepository.save(goods);

//	    LOGGER.info("goods saved successful!");
	} catch (IOException e) {
	    LOGGER.error(e.getMessage());
	}
    }
}
