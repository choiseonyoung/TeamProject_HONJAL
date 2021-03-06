package com.honjal.honjal.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.honjal.honjal.dao.ext.ContentDao;
import com.honjal.honjal.dao.ext.ContentGoodDao;
import com.honjal.honjal.model.BestContentVO;
import com.honjal.honjal.model.ContentListDTO;
import com.honjal.honjal.model.ContentVO;
import com.honjal.honjal.model.GoodVO;
import com.honjal.honjal.model.PageDTO;
import com.honjal.honjal.service.ContentService;
import com.honjal.honjal.service.PageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("contentServiceV1")
public class ContentServiceImplV1 implements ContentService {

	protected final ContentDao contentDao;
	protected final ContentGoodDao contentGoodDao;
	protected final PageService pageService;
	
	@Override
	public ContentVO findByIdContent(Integer content_num) {
		ContentVO contentVO = contentDao.findByIdContent(content_num);
		return contentVO;
	}
	
	@Override
	public void insert(ContentVO contentVO) throws Exception {
		contentDao.insert(contentVO);
		return;
	}

	@Override
	public void update(ContentVO contentVO) throws Exception {
		contentDao.update(contentVO);
		return;
	}

	@Override
	public void delete(Integer content_num) throws Exception {
		contentDao.delete(content_num);
		return;
	}
	
	@Override
	public List<ContentListDTO> contentMenuAllPage(String menu, int intPageNum, Model model) {
		
		List<ContentListDTO> contentAll = contentDao.contentMenuAllPage(menu);
		// 메뉴별 전체글담기
		int totalContents = contentAll.size();
		
		PageDTO pageDTO = pageService.makePagination(totalContents, intPageNum);
		
		List<ContentListDTO> pageList = new ArrayList<>();
		// 이 페이지에 보여줄 글리스트
		for(int i = pageDTO.getOffset(); i<pageDTO.getLimit(); i++) {
			pageList.add(contentAll.get(i));
		}
		
		model.addAttribute("PAGE_NAV", pageDTO);
		model.addAttribute("CONTENTS", pageList);
		
		return null;
	}
	
	@Override
	public int checkGood(GoodVO goodVO) {
		return contentGoodDao.checkGood(goodVO);
	}

	@Override
	public void insertGood(GoodVO goodVO) {
		contentGoodDao.insertGood(goodVO);
		contentGoodDao.insertUpGood(goodVO);
		return;
	}

	@Override
	public void deleteGood(GoodVO goodVO) {
		contentGoodDao.deleteGood(goodVO);
		contentGoodDao.deleteUpGood(goodVO);
		return;
	}

//	@Override
//	public int getGood(Integer content_num) {
//		int good = contentGoodDao.getGood(content_num);
//		return good;
//	}

	@Override
	public List<BestContentVO> bestContent() {
		List<BestContentVO> bList = contentDao.bestContent();
		return bList;
	}
	
	public List<ContentListDTO> infoContent() {
		List<ContentListDTO> iList = contentDao.contentMenuAllPage("INF");
		Collections.shuffle(iList);
		return iList;
	}
	
	
	@Override
	public int view_count(ContentVO contentVO) {
		// TODO Auto-generated method stub
		contentDao.view_count(contentVO);
		return 0;
	}
	
	

}
