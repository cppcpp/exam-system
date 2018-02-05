package com.njxz.exam.service;

import java.util.List;

import com.njxz.exam.modle.News;

public interface NewsService {
	public int add(News news);
	public List<News> getAll();
	public int modify(News news);
	public int delete(String id);
	//取出最新的count条新闻
	public List<News> latelyNews(int count);
}
