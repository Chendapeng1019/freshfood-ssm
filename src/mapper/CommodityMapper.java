package mapper;

import java.util.List;

import mo.Activity_ViewMo;
import mo.CommodityMo;
import mo.CommodityNamesMo;
import mo.Commodity_ViewMo;
import mo.Images_ViewMo;
import mo.SearchMo;

public interface CommodityMapper {

	public List<Activity_ViewMo> getActivityCommodity() throws Exception; //查找四条活动商品信息
	
	public List<Commodity_ViewMo> getIndexCommodity(String commodityType) throws Exception;//查找主页各种类前7条商品信息
	
	public int getSearchNumber(String search) throws Exception;//搜索商品数目
	
	public List<Commodity_ViewMo> getSearchCommodity(SearchMo searchMo) throws Exception;//搜索商品信息  综合排序
	
	public List<Commodity_ViewMo> getSearchCommodityBySaleNumber(SearchMo searchMo) throws Exception;//搜索商品信息  销量排序
	
	public List<Commodity_ViewMo> getSearchCommodityByPrice(SearchMo searchMo) throws Exception;//搜索商品信息  价格排序
	
	public List<Commodity_ViewMo> getSearchCommodityByCommentNumber(SearchMo searchMo) throws Exception;//搜索商品信息  评价排序

	public CommodityMo getCommodityInfo(int comm_id) throws Exception;//查询商品详情信息
	
	public CommodityNamesMo getCommodityNames(int comm_id) throws Exception;//查询商品种类名称
	
	public List<Images_ViewMo> getImages(int comm_id) throws Exception;//查询商品详情图片

}
