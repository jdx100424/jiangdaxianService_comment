package com.jiangdaxian.comment.service.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jiangdaxian.comment.BaseTestCase;
import com.jiangdaxian.comment.model.ArticleCommentModel;
import com.jiangdaxian.comment.model.ArticleCommentResponseModel;
import com.jiangdaxian.comment.service.ArticleCommentService;
import com.jiangdaxian.mongodb.pagelimit.PageLimitMongo;
import com.jiangdaxian.mongodb.pagelimit.PageListMongo;

public class ArticleCommentServiceTest extends BaseTestCase {

	@Autowired
	private ArticleCommentService articleCommentService;
	
	@Test
	public void testAddArticleComment() {
		for(int i = 3;i<30;i++) {
			Date date = new Date();
			ArticleCommentModel articleCommentModel = new ArticleCommentModel();
			articleCommentModel.setArticleId(1L);
			articleCommentModel.setContent("猫神第" +i+"次评论");
			articleCommentModel.setCreateTime(date);
			articleCommentModel.setHeadImage("http://www.xx.com");
			articleCommentModel.setNickName("猫神" + i);
			articleCommentModel.setReferenceId(null);
			articleCommentModel.setUpdateTime(date);
			articleCommentService.addArticleComment(articleCommentModel);
		}
	}
	
	@Test
	public void testAddArticleCommentReference() {
		Date date = new Date();
		ArticleCommentModel articleCommentModel = new ArticleCommentModel();
		articleCommentModel.setArticleId(1L);
		articleCommentModel.setContent("猫神------sub2------第2次评论");
		articleCommentModel.setCreateTime(date);
		articleCommentModel.setHeadImage("http://www.xx.com");
		articleCommentModel.setNickName("猫神");
		articleCommentModel.setReferenceId(null);
		articleCommentModel.setUpdateTime(date);
		articleCommentModel.setReferenceId("5ba63d78f152e62de9687c7c");
		articleCommentService.addArticleComment(articleCommentModel);
	}
	
	@Test
	public void testGetArticleCommentList() {
		System.out.println("testGetArticleCommentList start");
		ArticleCommentModel articleCommentModel = new ArticleCommentModel();
		articleCommentModel.setArticleId(1L);
		PageLimitMongo pageLimitMongo = new PageLimitMongo(31,1,true);
		PageListMongo<ArticleCommentResponseModel> list = articleCommentService.getArticleCommentList(articleCommentModel, pageLimitMongo);
		System.out.println("Limit:"+list.getLimit()+",Page:" + list.getPage());
		System.out.println(JSONObject.toJSONString(list));
	}

	@Test
	public void testGetArticleCommentReference() {
		ArticleCommentResponseModel model = articleCommentService.getArticleCommentReference("5ba63d78f152e62de9687c7c");
		System.out.println(JSONObject.toJSONString(model));
	}
}
