package dao;

import java.util.List;

import mo.Activity_ViewMo;
import mo.CommodityMo;
import mo.CommodityNamesMo;
import mo.Commodity_ViewMo;
import mo.Images_ViewMo;
import mo.SearchMo;

public interface CommodityDao {

	public List<Activity_ViewMo> getActivityCommodity() throws Exception;

	public List<Commodity_ViewMo> getIndexCommodity(String commodityType) throws Exception;

	public int getSearchNumber(String search) throws Exception;

	public List<Commodity_ViewMo> getSearchCommodity(SearchMo searchMo) throws Exception;

	public CommodityMo CommodityInfo(int comm_id) throws Exception;

	public CommodityNamesMo CommodityNames(int comm_id) throws Exception;

	public List<Images_ViewMo> getImages(int comm_id) throws Exception;

	

}
