package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dao.CommodityDao;
import mo.Activity_ViewMo;
import mo.CommodityMo;
import mo.CommodityNamesMo;
import mo.Commodity_ViewMo;
import mo.Images_ViewMo;
import mo.SearchMo;

@Controller
public class CommodityService {

	@Autowired
	private CommodityDao commodityDao;

	public List<Activity_ViewMo> getActivityCommodity() throws Exception {

		return commodityDao.getActivityCommodity();
	}

	public List<Commodity_ViewMo> getIndexCommodity(String commodityType) throws Exception {

		return commodityDao.getIndexCommodity(commodityType);
	}

	public int getSearchNumber(String search) throws Exception {

		return commodityDao.getSearchNumber(search);
	}

	public List<Commodity_ViewMo> getSearchCommodity(SearchMo searchMo) throws Exception{
		
		return commodityDao.getSearchCommodity(searchMo);
	}

	public CommodityMo CommodityInfo(int comm_id) throws Exception{
		
		return commodityDao.CommodityInfo(comm_id);
	}

	public CommodityNamesMo CommodityNames(int comm_id) throws Exception{
		
		return commodityDao.CommodityNames(comm_id);
	}

	public List<Images_ViewMo> getImages(int comm_id) throws Exception{
		
		return commodityDao.getImages(comm_id);
	}

	
}
