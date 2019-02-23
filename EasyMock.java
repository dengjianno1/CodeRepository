package cn.com.ioisii.pservice.miniprogram.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cn.com.ioisii.common.utils.validate.CommonResult;
import cn.com.ioisii.ibatis.impl.template.MyBatisSessionTemplate;
import cn.com.ioisii.pservice.miniprogram.common.BlackPo;
import cn.com.ioisii.pservice.miniprogram.common.CardInfoPo;
import cn.com.ioisii.pservice.miniprogram.common.ProjectPo;
import cn.com.ioisii.pservice.miniprogram.common.RecommendApplyPo;
import cn.com.ioisii.pservice.miniprogram.service.IAdvertisementService;
import cn.com.ioisii.pservice.miniprogram.service.ICardInfoService;
import cn.com.ioisii.pservice.miniprogram.service.IRecommendService;
import cn.com.ioisii.redis.utils.redis.RedisCacheService;

public class TestHomeServiceImpl {
	
	MyBatisSessionTemplate mockSession;
	IRecommendService mockRecommendService;
	
	
    @Before
    public void setUp(){
    	mockSession = EasyMock.createMock(MyBatisSessionTemplate.class);
    	mockRecommendService = EasyMock.createMock(IRecommendService.class);
    }
	
	@Test
	public void testGetIndexProjectNotExist(){
		EasyMock.expect(mockSession.selectOne(EasyMock.anyString(),EasyMock.anyString())).andReturn(null);
		EasyMock.replay(mockSession);
		
		HomeServiceImpl homeServiceImpl = new HomeServiceImpl();
		homeServiceImpl.setSession(mockSession);
		
		CommonResult result = homeServiceImpl.getIndex("", "", "", "", "12345678");
		Assert.assertEquals("000001", result.getCode());
	}
	
	@Test
	public void testGetIndexRecommCheck(){
		CommonResult recomResult = new CommonResult();
		recomResult.returnError("error");
		
		EasyMock.expect(mockSession.selectOne(EasyMock.anyString(),EasyMock.anyString())).andReturn(new ProjectPo());
		EasyMock.expect(mockRecommendService.getRecommend(EasyMock.anyString())).andReturn(recomResult);
		EasyMock.replay(mockRecommendService,mockSession);
		
		HomeServiceImpl homeServiceImpl = new HomeServiceImpl();
		homeServiceImpl.setSession(mockSession);
		homeServiceImpl.setRecommendService(mockRecommendService);
		
		CommonResult mockResult = homeServiceImpl.getIndex("1195314f368345dbaf08e1ff974f18b4", "", "", "", "12345678");
		Assert.assertEquals("000001", mockResult.getCode());
		EasyMock.verify();
	}
	
	@Test
	public void testGetIndexPersonCheck(){
		
		List<Object> blacks = new ArrayList<Object>();
		blacks.add(new BlackPo());
		CommonResult recomResult = new CommonResult();
		RecommendApplyPo recommendPo = new RecommendApplyPo();
		recommendPo.setPersonCode("mock");
		recomResult.returnSuccess();
		recomResult.setData(recommendPo);
		
		EasyMock.expect(mockSession.selectList(EasyMock.anyString(),EasyMock.anyString())).andReturn(blacks);
		EasyMock.expect(mockSession.selectOne(EasyMock.anyString(),EasyMock.anyString())).andReturn(new ProjectPo());
		EasyMock.expect(mockRecommendService.getRecommend(EasyMock.anyString())).andReturn(recomResult);
		EasyMock.replay(mockRecommendService,mockSession);
		
		HomeServiceImpl homeServiceImpl = new HomeServiceImpl();
		homeServiceImpl.setSession(mockSession);
		homeServiceImpl.setRecommendService(mockRecommendService);
		
		CommonResult mockResult = homeServiceImpl.getIndex("1195314f368345dbaf08e1ff974f18b4", "", "", "", "12345678");
		Assert.assertEquals("000001", mockResult.getCode());
		EasyMock.verify();
	}
	
	@Test
	public void testGetIndexSetCardsInfoByCardsAndFacesCheck(){
		ICardInfoService mockCardService = EasyMock.createMock(ICardInfoService.class);
		
		CommonResult cardResult = new CommonResult();
		cardResult.returnError("");
		CommonResult recomResult = new CommonResult();
		RecommendApplyPo recommendPo = new RecommendApplyPo();
		recommendPo.setType("1");
		recomResult.returnSuccess();
		recomResult.setData(recommendPo);
		
		EasyMock.expect(mockRecommendService.getRecommend(EasyMock.anyString())).andReturn(recomResult);
		EasyMock.expect(mockCardService.getCardInfo(EasyMock.anyString(), EasyMock.anyString())).andReturn(cardResult);
		EasyMock.replay(mockRecommendService,mockCardService);
		
		HomeServiceImpl homeServiceImpl = new HomeServiceImpl();
		homeServiceImpl.setCardInfoService(mockCardService);
		homeServiceImpl.setRecommendService(mockRecommendService);
		
		CommonResult mockResult = homeServiceImpl.getIndex("", "1179", "A", "", "");
		Assert.assertEquals("000001",mockResult.getCode());
	}
	
	@Test
	public void testGetIndexSetCardsInfoByChannelCheck(){
		ICardInfoService mockCardService = EasyMock.createMock(ICardInfoService.class);
		
		CommonResult cardResult = new CommonResult();
		cardResult.returnError("");
		CommonResult recomResult = new CommonResult();
		RecommendApplyPo recommendPo = new RecommendApplyPo();
		recommendPo.setType("1");
		recommendPo.setCardChooseDirection("3");
		recomResult.returnSuccess();
		recomResult.setData(recommendPo);
		
		EasyMock.expect(mockRecommendService.getRecommend(EasyMock.anyString())).andReturn(recomResult);
		EasyMock.expect(mockCardService.getCardInfoByChannel("F")).andReturn(cardResult);
		EasyMock.replay(mockRecommendService,mockCardService);
		
		HomeServiceImpl homeServiceImpl = new HomeServiceImpl();
		homeServiceImpl.setCardInfoService(mockCardService);
		homeServiceImpl.setRecommendService(mockRecommendService);
		
		CommonResult mockResult = homeServiceImpl.getIndex("", "", "", "", "");
		Assert.assertEquals("000001",mockResult.getCode());
	}
	
	@Test
	public void testGetIndexSetCardsInfoCheck(){
		ICardInfoService mockCardService = EasyMock.createMock(ICardInfoService.class);
		
		CommonResult cardResult = new CommonResult();
		cardResult.returnError("");
		CommonResult recomResult = new CommonResult();
		RecommendApplyPo recommendPo = new RecommendApplyPo();
		recommendPo.setType("1");
		recommendPo.setCardChooseDirection("0");
		recomResult.returnSuccess();
		recomResult.setData(recommendPo);
		
		EasyMock.expect(mockRecommendService.getRecommend(EasyMock.anyString())).andReturn(recomResult);
		EasyMock.expect(mockCardService.getCardInfo(EasyMock.isA(RecommendApplyPo.class))).andReturn(cardResult);
		EasyMock.replay(mockRecommendService,mockCardService);
		
		HomeServiceImpl homeServiceImpl = new HomeServiceImpl();
		homeServiceImpl.setCardInfoService(mockCardService);
		homeServiceImpl.setRecommendService(mockRecommendService);
		
		CommonResult mockResult = homeServiceImpl.getIndex("", "", "", "", "");
		Assert.assertEquals("000001",mockResult.getCode());
	}
	
	@Test
	public void testGetIndexADCheck(){
		ICardInfoService mockCardService = EasyMock.createMock(ICardInfoService.class);
		IAdvertisementService mockAdService = EasyMock.createMock(IAdvertisementService.class);
		
		CommonResult recomResult = new CommonResult();
		RecommendApplyPo recommendPo = new RecommendApplyPo();
		recommendPo.setType("1");
		recommendPo.setCardChooseDirection("0");
		recomResult.returnSuccess();
		recomResult.setData(recommendPo);
		
		CommonResult cardResult = new CommonResult();
		cardResult.returnSuccess(new ArrayList<CardInfoPo>());
		
		CommonResult adResult = new CommonResult();
		adResult.returnError("");
		
		EasyMock.expect(mockRecommendService.getRecommend(EasyMock.anyString())).andReturn(recomResult);
		EasyMock.expect(mockCardService.getCardInfo(EasyMock.isA(RecommendApplyPo.class))).andReturn(cardResult);
		EasyMock.expect(mockAdService.getAdvertisement()).andReturn(adResult);
		EasyMock.replay(mockRecommendService,mockCardService,mockAdService);
		
		HomeServiceImpl homeServiceImpl = new HomeServiceImpl();
		homeServiceImpl.setCardInfoService(mockCardService);
		homeServiceImpl.setRecommendService(mockRecommendService);
		homeServiceImpl.setAdServiceImpl(mockAdService);
		
		CommonResult mockResult = new CommonResult();
		mockResult = homeServiceImpl.getIndex("", "", "", "", "");
		Assert.assertEquals("000001",mockResult.getCode());
	}
	
	@Test
	public void testGetIndexDictionaryCheck(){
		ICardInfoService mockCardService = EasyMock.createMock(ICardInfoService.class);
		IAdvertisementService mockAdService = EasyMock.createMock(IAdvertisementService.class);
		DictionaryServiceImpl mockDictionaryService = EasyMock.createMock(DictionaryServiceImpl.class);
		RedisCacheService mockRedisService = EasyMock.createMock(RedisCacheService.class);
		
		CommonResult recomResult = new CommonResult();
		RecommendApplyPo recommendPo = new RecommendApplyPo();
		recommendPo.setType("1");
		recommendPo.setCardChooseDirection("0");
		recomResult.returnSuccess();
		recomResult.setData(recommendPo);
		
		CommonResult cardResult = new CommonResult();
		cardResult.returnSuccess(new ArrayList<CardInfoPo>());
		
		CommonResult adResult = new CommonResult();
		adResult.returnSuccess();
		
		CommonResult dictionaryResult = new CommonResult();
		dictionaryResult.returnError("");
		
		EasyMock.expect(mockRecommendService.getRecommend(EasyMock.anyString())).andReturn(recomResult);
		EasyMock.expect(mockCardService.getCardInfo(EasyMock.isA(RecommendApplyPo.class))).andReturn(cardResult);
		EasyMock.expect(mockAdService.getAdvertisement()).andReturn(adResult);
		EasyMock.expect(mockDictionaryService.getType()).andReturn(dictionaryResult);
		EasyMock.replay(mockRecommendService,mockCardService,mockAdService,mockDictionaryService);
		
		HomeServiceImpl homeServiceImpl = new HomeServiceImpl();
		homeServiceImpl.setCardInfoService(mockCardService);
		homeServiceImpl.setRecommendService(mockRecommendService);
		homeServiceImpl.setAdServiceImpl(mockAdService);
		homeServiceImpl.setDictionaryService(mockDictionaryService);
		homeServiceImpl.setRedis(mockRedisService);
		
		CommonResult mockResult = new CommonResult();
		mockResult = homeServiceImpl.getIndex("", "", "", "", "");
		Assert.assertEquals("000001",mockResult.getCode());
	}
	
	@Test
	public void testGetIndexSectorCheck(){
		ICardInfoService mockCardService = EasyMock.createMock(ICardInfoService.class);
		IAdvertisementService mockAdService = EasyMock.createMock(IAdvertisementService.class);
		DictionaryServiceImpl mockDictionaryService = EasyMock.createMock(DictionaryServiceImpl.class);
		RedisCacheService mockRedisService = EasyMock.createMock(RedisCacheService.class);
		
		CommonResult recomResult = new CommonResult();
		RecommendApplyPo recommendPo = new RecommendApplyPo();
		recommendPo.setType("1");
		recommendPo.setCardChooseDirection("0");
		recomResult.returnSuccess();
		recomResult.setData(recommendPo);
		
		CommonResult cardResult = new CommonResult();
		cardResult.returnSuccess(new ArrayList<CardInfoPo>());
		
		CommonResult adResult = new CommonResult();
		adResult.returnSuccess();
		
		CommonResult dictionaryResult = new CommonResult();
		dictionaryResult.returnSuccess();
		
		EasyMock.expect(mockRecommendService.getRecommend(EasyMock.anyString())).andReturn(recomResult);
		EasyMock.expect(mockCardService.getCardInfo(EasyMock.isA(RecommendApplyPo.class))).andReturn(cardResult);
		EasyMock.expect(mockAdService.getAdvertisement()).andReturn(adResult);
		EasyMock.expect(mockDictionaryService.getType()).andReturn(dictionaryResult);
		EasyMock.replay(mockRecommendService,mockCardService,mockAdService,mockDictionaryService);
		
		HomeServiceImpl homeServiceImpl = new HomeServiceImpl();
		homeServiceImpl.setCardInfoService(mockCardService);
		homeServiceImpl.setRecommendService(mockRecommendService);
		homeServiceImpl.setAdServiceImpl(mockAdService);
		homeServiceImpl.setDictionaryService(mockDictionaryService);
		homeServiceImpl.setRedis(mockRedisService);
		
		CommonResult mockResult = new CommonResult();
		mockResult = homeServiceImpl.getIndex("", "", "", "", "");
		Assert.assertEquals("000000",mockResult.getCode());
	}
}
