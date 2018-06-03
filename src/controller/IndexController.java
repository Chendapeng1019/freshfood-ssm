package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mo.Activity_ViewMo;
import mo.CommodityMo;
import mo.CommodityNamesMo;
import mo.Commodity_ViewMo;
import mo.Images_ViewMo;
import mo.SearchMo;
import service.CommodityService;
import utils.PageBean;

@Controller
@RequestMapping("/home")
public class IndexController {
 
	@Autowired
	private CommodityService commodityService;
	
	@RequestMapping("/index")
	public String index(Model model) throws Exception{
		List<Activity_ViewMo> commodityList=new ArrayList<Activity_ViewMo>();
		List<Commodity_ViewMo> fruits=new ArrayList<Commodity_ViewMo>();
		List<Commodity_ViewMo> egg=new ArrayList<Commodity_ViewMo>();
		List<Commodity_ViewMo> seafood=new ArrayList<Commodity_ViewMo>();
		List<Commodity_ViewMo> meat=new ArrayList<Commodity_ViewMo>();
		List<Commodity_ViewMo> vegetables=new ArrayList<Commodity_ViewMo>();
		List<Commodity_ViewMo> milk=new ArrayList<Commodity_ViewMo>();
		List<Commodity_ViewMo> fastfood=new ArrayList<Commodity_ViewMo>();
		List<Commodity_ViewMo> grain=new ArrayList<Commodity_ViewMo>();
		
		commodityList=commodityService.getActivityCommodity();
		fruits = commodityService.getIndexCommodity("优质水果");
		egg = commodityService.getIndexCommodity("禽品蛋类");
		seafood = commodityService.getIndexCommodity("海鲜水产");
		meat = commodityService.getIndexCommodity("精选肉类");
		vegetables = commodityService.getIndexCommodity("新鲜蔬菜");
		milk = commodityService.getIndexCommodity("乳品糕点");
		fastfood = commodityService.getIndexCommodity("方便速食");
		grain = commodityService.getIndexCommodity("粮油杂货");
		
		
		model.addAttribute("commodityList", commodityList);
		model.addAttribute("fruits", fruits);
		model.addAttribute("egg", egg);
		model.addAttribute("seafood", seafood);
		model.addAttribute("meat", meat);
		model.addAttribute("vegetables", vegetables);
		model.addAttribute("milk", milk);
		model.addAttribute("fastfood", fastfood);
		model.addAttribute("grain", grain);
		return "/front/index.jsp";
	}
	
	
	@RequestMapping("/search")
	public String search(Model model,String selectsort,String curPage,String search ) throws Exception{
		PageBean<Commodity_ViewMo> pagebean=new PageBean<Commodity_ViewMo>();
		
		
		if (selectsort == null || selectsort.equals("")) {
			selectsort = "zhonghe";
		}
		System.out.println("分类"+selectsort);
		int intPage = 1;
		if (curPage == null || curPage.equals("")) {
			curPage = "1";
		}

		intPage = Integer.parseInt(curPage);
		int rowCount = 0;
		rowCount = commodityService.getSearchNumber(search);

		int size = 10;

		pagebean.setRowCount(rowCount);
		pagebean.setPageSize(size);
		pagebean.setCurrentPage(intPage);
        
		List<Commodity_ViewMo> commodityList=null;
		SearchMo searchMo=new SearchMo();
		searchMo.setCurrentPage(pagebean.getCurrentPage());
		searchMo.setPageSize(pagebean.getPageSize());
		searchMo.setSearch(search);
		searchMo.setSelectsort(selectsort);
		commodityList=commodityService.getSearchCommodity(searchMo);
		pagebean.setPageList(commodityList);
		
		model.addAttribute("selectsort", selectsort);
		model.addAttribute("search", search);
		model.addAttribute("pagebean", pagebean);
		return "/front/search.jsp";
	}
	
	
	@RequestMapping("/commodityinfo")
	public String commodityInfo(Model model,int comm_id) throws Exception{
		
		CommodityMo commodityinfo=new CommodityMo();
		CommodityNamesMo commoditynames = new CommodityNamesMo();
		List<Images_ViewMo> commodityimages = new ArrayList<Images_ViewMo>();
		
		commodityinfo = commodityService.CommodityInfo(comm_id);
		commoditynames = commodityService.CommodityNames(comm_id);
		commodityimages = commodityService.getImages(comm_id);
		
		model.addAttribute("commodityinfo", commodityinfo);
		model.addAttribute("commoditynames", commoditynames);
		model.addAttribute("commodityimages", commodityimages);
		
		return "/front/introduction.jsp";
	}
	
	
}
