package com.jiangdaxian.comment.service.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.jiangdaxian.comment.BaseTestCase;
import com.jiangdaxian.comment.model.CommentReplyModel;
import com.jiangdaxian.comment.service.CommentReplyService;
import com.jiangdaxian.mongodb.pagelimit.PageLimitMongo;
import com.jiangdaxian.mongodb.pagelimit.PageListMongo;

public class CommentReplyServiceTest extends BaseTestCase {

	@Autowired
	private CommentReplyService commentReplyService;

	@Test
	public void testAddCommentReply() {
		Date date = new Date();
		Long fromUserId = System.currentTimeMillis();
		Long toUserId = System.currentTimeMillis();
		CommentReplyModel commentReplyModel = new CommentReplyModel();
		commentReplyModel.setCommentId("5ba78966f152e60c886a9b78");
		commentReplyModel.setContent("猫神向ID为"+ toUserId +"的用户回复");
		commentReplyModel.setCreateTime(date);
		commentReplyModel.setUserId(fromUserId);
		commentReplyModel.setToUserId(toUserId);
		commentReplyModel.setToNickName("猫神To");
		commentReplyModel.setNickName("猫神from");
		commentReplyModel.setUpdateTime(date);
		commentReplyService.addCommentReply(commentReplyModel);
	}
	
	@Test
	public void testGetCommentReplyList() {
		PageLimitMongo pageLimitMongo = new PageLimitMongo(1,10,false);
		CommentReplyModel commentReplyModel = new CommentReplyModel();
		commentReplyModel.setCommentId("5ba78966f152e60c886a9b78");
		PageListMongo<CommentReplyModel>  resultPageList = commentReplyService.getCommentReplyList(commentReplyModel, pageLimitMongo);
		System.out.println(JSONObject.toJSONString(resultPageList));
	}
}
