package com.jiangdaxian.comment.dubbo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiangdaxian.comment.api.CommentApi;
import com.jiangdaxian.comment.model.CommentModel;
import com.jiangdaxian.comment.model.CommentResponseModel;
import com.jiangdaxian.comment.service.CommentService;
import com.jiangdaxian.mongodb.pagelimit.PageLimitMongo;
import com.jiangdaxian.mongodb.pagelimit.PageListMongo;

@Service("commentDubbo")
public class CommentDubbo implements CommentApi{
	
	private static final Logger LOG = LoggerFactory.getLogger(CommentDubbo.class);

	@Autowired
	private CommentService commentService;
	
	public Boolean addComment(CommentModel commentModel) throws Exception {
		if(commentModel==null) {
			return false;
		}
		
		try {
			commentService.addComment(commentModel);
			return true;
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			return false;
		}
	}

	public PageListMongo<CommentResponseModel> getCommentList(CommentModel commentModel,
			PageLimitMongo pageLimitMongo) throws Exception {
		if(commentModel==null || pageLimitMongo==null) {
			throw new Exception();
		}
		
		try {
			PageListMongo<CommentResponseModel> resultPageList = commentService.getCommentList(commentModel, pageLimitMongo);
			return resultPageList;
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			return null;
		}
	}

	public CommentResponseModel getCommentReference(String id) throws Exception {
		if(StringUtils.isBlank(id)) {
			throw new Exception();
		}
		return commentService.getCommentReference(id);
	}



}
