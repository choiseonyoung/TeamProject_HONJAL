package com.honjal.honjal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.honjal.honjal.dao.ext.ContentDao;
import com.honjal.honjal.model.ContentDTO;
import com.honjal.honjal.model.ContentListDTO;
import com.honjal.honjal.model.ContentVO;
import com.honjal.honjal.service.ContentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service("contentServiceV1")
public class ContentServiceImplV1 implements ContentService {

	protected final ContentDao contentDao;
	
	@Override
	public ContentDTO findByIdContent(String content_num) {
		ContentDTO contentDTO = contentDao.findByIdContent(content_num);
		return contentDTO;
	}
	
	@Override
	public int insert(ContentVO contentVO) throws Exception {
		contentDao.insert(contentVO);
		return 0;
	}

	@Override
	public int update(ContentVO contentVO) throws Exception {
		contentDao.update(contentVO);
		return 0;
	}

	@Override
	public int delete(String content_num) throws Exception {
		contentDao.delete(content_num);
		return 0;
	}
	
	@Override
	public List<ContentListDTO> allContent() {
		List<ContentListDTO> list = contentDao.allContent();
		return list;
	}

	@Override
	public List<ContentListDTO> menuContent(String board_code) {
		List<ContentListDTO> contentList = contentDao.menuContent(board_code);
		return contentList;
	}

	@Override
	public List<ContentListDTO> searchTitleContent(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContentListDTO> searchTextContent(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContentListDTO> searchNameContent(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContentListDTO> MyContent(Integer member_num) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
