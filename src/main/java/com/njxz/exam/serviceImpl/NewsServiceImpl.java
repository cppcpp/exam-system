package com.njxz.exam.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.njxz.exam.dao.NewsMapper;
import com.njxz.exam.modle.News;
import com.njxz.exam.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService{

	@Autowired
	NewsMapper mapper;
	@Override
	public int add(News news) {
		
		return mapper.insert(news);
	}

	@Override
	public List<News> getAll() {
		return mapper.getAll();
	}

	@Override
	public int modify(News news) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(news);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(new Long(id));
	}

	@Override
	public List<News> latelyNews(int count) {
		
		return mapper.latelyNews(count);
	}

}
