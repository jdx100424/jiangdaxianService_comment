package com.jiangdaxian.comment.service.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jiangdaxian.comment.BaseTestCase;
import com.jiangdaxian.comment.constant.CommentType;
import com.jiangdaxian.comment.model.CommentModel;
import com.jiangdaxian.comment.model.CommentResponseModel;
import com.jiangdaxian.comment.service.CommentService;
import com.jiangdaxian.mongodb.pagelimit.PageLimitMongo;
import com.jiangdaxian.mongodb.pagelimit.PageListMongo;

public class CommentServiceTest extends BaseTestCase {

	@Autowired
	private CommentService commentService;
	
	@Test
	public void testAddComment() {
		for(int i = 3;i<30;i++) {
			Date date = new Date();
			CommentModel commentModel = new CommentModel();
			commentModel.setType(CommentType.ARTICLE_COMMENT_TYPE);
			commentModel.setUserId(System.currentTimeMillis());
			commentModel.setItemId(1L);
			commentModel.setContent("猫神第" +i+"次评论");
			commentModel.setCreateTime(date);
			commentModel.setHeadImage("http://www.xx.com");
			commentModel.setNickName("猫神" + i);
			commentModel.setReferenceId(null);
			commentModel.setUpdateTime(date);
			commentService.addComment(commentModel);
		}
	}
	
	@Test
	public void testAddCommentReference() {
		Date date = new Date();
		CommentModel commentModel = new CommentModel();
		commentModel.setType(CommentType.ARTICLE_COMMENT_TYPE);
		commentModel.setItemId(1L);
		commentModel.setContent("猫神------sub3------第3次评论");
		commentModel.setCreateTime(date);
		commentModel.setUserId(System.currentTimeMillis());
		commentModel.setHeadImage("http://www.xx.com");
		commentModel.setNickName("猫神");
		commentModel.setUpdateTime(date);
		commentModel.setReferenceId("5ba78946f152e60c58e91988");
		commentService.addComment(commentModel);
	}
	
	@Test
	public void testGetCommentList() {
		System.out.println("testGetArticleCommentList start");
		CommentModel commentModel = new CommentModel();
		commentModel.setType(CommentType.ARTICLE_COMMENT_TYPE);
		commentModel.setItemId(1L);
		PageLimitMongo pageLimitMongo = new PageLimitMongo(1,1,true);
		PageListMongo<CommentResponseModel> list =commentService.getCommentList(commentModel, pageLimitMongo);
		System.out.println("Limit:"+list.getLimit()+",Page:" + list.getPage());
		System.out.println(JSONObject.toJSONString(list));
	}

	@Test
	public void testGetCommentReference() {
		CommentResponseModel model = commentService.getCommentReference("5ba63d78f152e62de9687c7c");
		System.out.println(JSONObject.toJSONString(model));
	}
}
