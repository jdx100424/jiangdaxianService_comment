package com.jiangdaxian.comment.service;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jiangdaxian.comment.model.CommentReplyModel;
import com.jiangdaxian.comment.mongo.CommentReplyMongo;
import com.jiangdaxian.mongodb.pagelimit.PageLimitMongo;
import com.jiangdaxian.mongodb.pagelimit.PageListMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class CommentReplyService {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	//评论回复名
	private final static String COMMENT_REPLY_MONGO = "commentReplyMongo";
	
	/**
	 * 增加评论回复
	 * @param commentReplyMongo
	 */
	public void addCommentReply(CommentReplyModel commentReplyModel) {
		CommentReplyMongo commentReplyMongo = JSONObject.parseObject(JSONObject.toJSONString(commentReplyModel), CommentReplyMongo.class);
		mongoTemplate.insert(commentReplyMongo,COMMENT_REPLY_MONGO);
	}
	
	public PageListMongo<CommentReplyModel> getCommentReplyList(CommentReplyModel commentReplyModel,
			PageLimitMongo pageLimitMongo) {
		DBObject dbObject = new BasicDBObject();
		
		if(StringUtils.isNotBlank(commentReplyModel.getCommentId())) {
			dbObject.put("commentId", commentReplyModel.getCommentId());
		}
		Query query = new BasicQuery(dbObject);
		query.skip((pageLimitMongo.getPage()-1)*pageLimitMongo.getLimit());
	    //设置查询条数
	    query.limit(pageLimitMongo.getLimit());
	    
	    query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"createTime")));
   
		List<CommentReplyMongo> list = mongoTemplate.find(query, CommentReplyMongo.class,COMMENT_REPLY_MONGO);

		PageListMongo<CommentReplyModel> resultPageList = new PageListMongo<CommentReplyModel>();
		resultPageList.setPage(pageLimitMongo.getPage());
		resultPageList.setLimit(pageLimitMongo.getLimit());
		if(list!=null && list.isEmpty()==false) {
			list.forEach(commentReplyMongo->{
				CommentReplyModel model = JSONObject.parseObject(JSONObject.toJSONString(commentReplyMongo), CommentReplyModel.class);
				resultPageList.add(model);
			});
		}
		return resultPageList;
	}
}
