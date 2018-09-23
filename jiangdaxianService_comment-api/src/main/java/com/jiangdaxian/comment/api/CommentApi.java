package com.jiangdaxian.comment.api;

import com.jiangdaxian.comment.model.CommentModel;
import com.jiangdaxian.comment.model.CommentResponseModel;
import com.jiangdaxian.mongodb.pagelimit.PageLimitMongo;
import com.jiangdaxian.mongodb.pagelimit.PageListMongo;

/**
 * 评论DUBBO接口
 * @author jdx100424
 *
 */
public interface CommentApi {
	
	/**
	 * 增加评论
	 */
	public Boolean addComment(CommentModel articleCommentModel) throws Exception;
	
	/**
	 * 获取评论信息
	 */
	public PageListMongo<CommentResponseModel> getCommentList(CommentModel commentModel,PageLimitMongo pageLimitMongo) throws Exception;
	
	/**
	 * 获取引用，开始位置根据评论ID去循环查询
	 */
	public CommentResponseModel getCommentReference(String id) throws Exception;
}
