package dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mapper.CommodityMapper;
import mo.Activity_ViewMo;
import mo.CommodityMo;
import mo.CommodityNamesMo;
import mo.Commodity_ViewMo;
import mo.Images_ViewMo;
import mo.SearchMo;

public class CommodityDaoImpl implements CommodityDao {

	@Autowired
	private CommodityMapper commodityMapper;
	
	public List<Activity_ViewMo> getActivityCommodity() throws Exception {
		
		return commodityMapper.getActivityCommodity();
	}

	
	public List<Commodity_ViewMo> getIndexCommodity(String commodityType) throws Exception {
		
		return commodityMapper.getIndexCommodity(commodityType);
	}


	
	public int getSearchNumber(String search) throws Exception {
		
		return commodityMapper.getSearchNumber(search);
	}


	
	public List<Commodity_ViewMo> getSearchCommodity(SearchMo searchMo) throws Exception {
		if(searchMo.getCurrentPage()<1 || searchMo.getPageSize()<1) {
			return null;
		}
		
		List<Commodity_ViewMo> commodityList=new ArrayList<Commodity_ViewMo>();
		if(searchMo.getSelectsort().equals("zhonghe")) {
			commodityList=commodityMapper.getSearchCommodity(searchMo);
		}
		
		if(searchMo.getSelectsort().equals("xiaoliang")){
			commodityList=commodityMapper.getSearchCommodityBySaleNumber(searchMo);
		}
		
		if(searchMo.getSelectsort().equals("jiage")){
			commodityList=commodityMapper.getSearchCommodityByPrice(searchMo);
		}
		
		if(searchMo.getSelectsort().equals("pinglun")){
			commodityList=commodityMapper.getSearchCommodityByCommentNumber(searchMo);
		}
		
		return commodityList;
	}


	
	public CommodityMo CommodityInfo(int comm_id) throws Exception {
		
		return commodityMapper.getCommodityInfo(comm_id);
	}


	
	public CommodityNamesMo CommodityNames(int comm_id) throws Exception {
	    
		return commodityMapper.getCommodityNames(comm_id);
	}


	
	public List<Images_ViewMo> getImages(int comm_id) throws Exception {
		
		return commodityMapper.getImages(comm_id);
	}

	
	
	
	

}
