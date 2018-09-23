package com.jiangdaxian.comment.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jiangdaxian.comment.model.CommentModel;
import com.jiangdaxian.comment.model.CommentReplyModel;
import com.jiangdaxian.comment.model.CommentResponseModel;
import com.jiangdaxian.comment.mongo.CommentMongo;
import com.jiangdaxian.mongodb.pagelimit.PageLimitMongo;
import com.jiangdaxian.mongodb.pagelimit.PageListMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class CommentService {
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private CommentReplyService commentReplyService;
	
	//评论表名
	private final static String COMMENT_MONGO = "commentMongo";
	
	//每次获取评论引用最多10条
	private final static int MAX_REFERENCE_COUNT = 10;
	
	public void addComment(CommentModel commentModel) {
		CommentMongo commentMongo = JSONObject.parseObject(JSONObject.toJSONString(commentModel), CommentMongo.class);
		mongoTemplate.insert(commentMongo,COMMENT_MONGO);
	}
	
	public PageListMongo<CommentResponseModel> getCommentList(CommentModel commentModel,
			PageLimitMongo pageLimitMongo) {
		DBObject dbObject = new BasicDBObject();
		
		if(commentModel.getItemId()!=null) {
			dbObject.put("itemId", commentModel.getItemId());
		}
		if(commentModel.getType()!=null) {
			dbObject.put("type", commentModel.getType());
		}

		Query query = new BasicQuery(dbObject);
		query.skip((pageLimitMongo.getPage()-1)*pageLimitMongo.getLimit());
	    //设置查询条数
	    query.limit(pageLimitMongo.getLimit());
	    
	    query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"createTime")));
   
		List<CommentMongo> list = mongoTemplate.find(query, CommentMongo.class,COMMENT_MONGO);
		
		//count
		Long count = null;
		if(pageLimitMongo.isContainsTotalCount()) {
			count = mongoTemplate.count(query, CommentMongo.class,COMMENT_MONGO);
		}
		PageListMongo<CommentResponseModel> resultPageList = new PageListMongo<CommentResponseModel>();
		resultPageList.setPage(pageLimitMongo.getPage());
		resultPageList.setLimit(pageLimitMongo.getLimit());
		resultPageList.setTotalCount(count);
		if(list!=null && list.isEmpty()==false) {
			list.forEach(commentMongo->{
				CommentResponseModel commentResponseModel = JSONObject.parseObject(JSONObject.toJSONString(commentMongo), CommentResponseModel.class);
				resultPageList.add(commentResponseModel);
			});
		}
		//每个评论，并行去获取子评论，去获取回复
		PageLimitMongo replyPageLimitMongo = new PageLimitMongo(1,10,false);
		resultPageList.stream().forEach(action->{
			setCommentReferenceId(action,1);
			
			CommentReplyModel commentReplyModel = new CommentReplyModel();
			commentReplyModel.setCommentId(action.getId());
			PageListMongo<CommentReplyModel> pageListReplyList = commentReplyService.getCommentReplyList(commentReplyModel, replyPageLimitMongo);
			if(pageListReplyList!=null && pageListReplyList.isEmpty()==false) {
				action.setCommentReplyModelList(pageListReplyList);
			}else {
				action.setCommentReplyModelList(new ArrayList<CommentReplyModel>());
			}
		});
		
		return resultPageList;
	}
	
	/**
	 * 评论引用获取
	 * @param id
	 * @return
	 */
	public CommentResponseModel getCommentReference(String id) {
		CommentResponseModel model = getCommentById(id);
		if(model!=null) {
			setCommentReferenceId(model,1);
		}
		return model;
	}
	
	private CommentResponseModel getCommentById(String referenceId) {
		DBObject dbObject = new BasicDBObject();
		dbObject.put("_id", new ObjectId(referenceId));
		Query query = new BasicQuery(dbObject);
		List<CommentMongo> list = mongoTemplate.find(query, CommentMongo.class,COMMENT_MONGO);
		CommentResponseModel commentResponseModel = null;
		if(list!=null && list.isEmpty()==false) {
			commentResponseModel = JSONObject.parseObject(JSONObject.toJSONString(list.get(0)), CommentResponseModel.class);
		}
		return commentResponseModel;
	}
	
	/**
	 * 获取引用评论
	 */
	private void setCommentReferenceId(CommentResponseModel commentResponseModel,Integer count) {
		if(commentResponseModel.getReferenceId()!=null && count<=MAX_REFERENCE_COUNT) {
			CommentResponseModel model = getCommentById(commentResponseModel.getReferenceId());
			commentResponseModel.setSubCommentResponseModel(model);
			count = count +1;
			setCommentReferenceId(commentResponseModel.getSubCommentResponseModel(),count);
		}
	}
}
