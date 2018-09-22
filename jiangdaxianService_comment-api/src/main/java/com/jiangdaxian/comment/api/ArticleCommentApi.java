package com.jiangdaxian.comment.api;

import com.jiangdaxian.comment.model.ArticleCommentModel;
import com.jiangdaxian.comment.model.ArticleCommentResponseModel;
import com.jiangdaxian.mongodb.pagelimit.PageLimitMongo;
import com.jiangdaxian.mongodb.pagelimit.PageListMongo;

/**
 * 评论DUBBO接口
 * @author jdx100424
 *
 */
public interface ArticleCommentApi {
	
	/**
	 * 增加评论
	 */
	public Boolean addArticleComment(ArticleCommentModel articleCommentModel) throws Exception;
	
	/**
	 * 获取评论信息
	 */
	public PageListMongo<ArticleCommentResponseModel> getArticleCommentList(ArticleCommentModel articleCommentModel,PageLimitMongo pageLimitMongo) throws Exception;
	
	/**
	 * 获取引用，开始位置根据评论ID去循环查询
	 */
	public ArticleCommentResponseModel getArticleCommentReference(String id) throws Exception;
}
