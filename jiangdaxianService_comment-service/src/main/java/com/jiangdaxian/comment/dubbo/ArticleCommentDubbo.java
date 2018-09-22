package com.jiangdaxian.comment.dubbo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiangdaxian.comment.api.ArticleCommentApi;
import com.jiangdaxian.comment.model.ArticleCommentModel;
import com.jiangdaxian.comment.model.ArticleCommentResponseModel;
import com.jiangdaxian.comment.service.ArticleCommentService;
import com.jiangdaxian.mongodb.pagelimit.PageLimitMongo;
import com.jiangdaxian.mongodb.pagelimit.PageListMongo;

@Service("articleCommentDubbo")
public class ArticleCommentDubbo implements ArticleCommentApi{
	
	private static final Logger LOG = LoggerFactory.getLogger(ArticleCommentDubbo.class);

	@Autowired
	private ArticleCommentService articleCommentService;
	
	public Boolean addArticleComment(ArticleCommentModel articleCommentModel) throws Exception {
		if(articleCommentModel==null) {
			return false;
		}
		
		try {
			articleCommentService.addArticleComment(articleCommentModel);
			return true;
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			return false;
		}
	}

	public PageListMongo<ArticleCommentResponseModel> getArticleCommentList(ArticleCommentModel articleCommentModel,
			PageLimitMongo pageLimitMongo) throws Exception {
		if(articleCommentModel==null || pageLimitMongo==null) {
			throw new Exception();
		}
		
		try {
			PageListMongo<ArticleCommentResponseModel> resultPageList = articleCommentService.getArticleCommentList(articleCommentModel, pageLimitMongo);
			return resultPageList;
		}catch(Exception e) {
			LOG.error(e.getMessage(),e);
			return null;
		}
	}

	public ArticleCommentResponseModel getArticleCommentReference(String id) throws Exception {
		if(StringUtils.isBlank(id)) {
			throw new Exception();
		}
		return articleCommentService.getArticleCommentReference(id);
	}



}
