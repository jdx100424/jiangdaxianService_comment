package com.jiangdaxian.comment.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jiangdaxian.comment.model.ArticleCommentModel;
import com.jiangdaxian.comment.model.ArticleCommentResponseModel;
import com.jiangdaxian.comment.mongo.ArticleCommentMongo;
import com.jiangdaxian.mongodb.pagelimit.PageLimitMongo;
import com.jiangdaxian.mongodb.pagelimit.PageListMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class ArticleCommentService {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	private final static String COLLECTION_NAME = "articleCommentMongo";
	
	//每次获取评论引用最多10条
	private final static int MAX_REFERENCE_COUNT = 10;
	
	public void addArticleComment(ArticleCommentModel articleCommentModel) {
		ArticleCommentMongo ArticleCommentMongo = JSONObject.parseObject(JSONObject.toJSONString(articleCommentModel), ArticleCommentMongo.class);
		mongoTemplate.insert(ArticleCommentMongo,COLLECTION_NAME);
	}
	
	public PageListMongo<ArticleCommentResponseModel> getArticleCommentList(ArticleCommentModel articleCommentModel,
			PageLimitMongo pageLimitMongo) {
		DBObject dbObject = new BasicDBObject();
		
		if(articleCommentModel.getArticleId()!=null) {
			dbObject.put("articleId", articleCommentModel.getArticleId());
		}

		Query query = new BasicQuery(dbObject);
		query.skip((pageLimitMongo.getPage()-1)*pageLimitMongo.getLimit());
	    //设置查询条数
	    query.limit(pageLimitMongo.getLimit());
		List<ArticleCommentMongo> list = mongoTemplate.find(query, ArticleCommentMongo.class,COLLECTION_NAME);
		
		//count
		Long count = null;
		if(pageLimitMongo.isContainsTotalCount()) {
			count = mongoTemplate.count(query, ArticleCommentMongo.class,COLLECTION_NAME);
		}
		PageListMongo<ArticleCommentResponseModel> resultPageList = new PageListMongo<ArticleCommentResponseModel>();
		resultPageList.setPage(pageLimitMongo.getPage());
		resultPageList.setLimit(pageLimitMongo.getLimit());
		resultPageList.setTotalCount(count);
		if(list!=null && list.isEmpty()==false) {
			list.forEach(articleCommentMongo->{
				ArticleCommentResponseModel articleCommentResponseModel = JSONObject.parseObject(JSONObject.toJSONString(articleCommentMongo), ArticleCommentResponseModel.class);
				resultPageList.add(articleCommentResponseModel);
			});
		}
		//每个评论，并行去获取子评论
		resultPageList.stream().forEach(action->{
			setArticleCommentReferenceId(action,1);
		});
		return resultPageList;
	}
	
	/**
	 * 评论引用获取
	 * @param id
	 * @return
	 */
	public ArticleCommentResponseModel getArticleCommentReference(String id) {
		ArticleCommentResponseModel model = getArticleCommentById(id);
		if(model!=null) {
			setArticleCommentReferenceId(model,1);
		}
		return model;
	}
	
	private ArticleCommentResponseModel getArticleCommentById(String referenceId) {
		DBObject dbObject = new BasicDBObject();
		dbObject.put("_id", new ObjectId(referenceId));
		Query query = new BasicQuery(dbObject);
		List<ArticleCommentMongo> list = mongoTemplate.find(query, ArticleCommentMongo.class,COLLECTION_NAME);
		ArticleCommentResponseModel articleCommentResponseModel = null;
		if(list!=null && list.isEmpty()==false) {
			articleCommentResponseModel = JSONObject.parseObject(JSONObject.toJSONString(list.get(0)), ArticleCommentResponseModel.class);
		}
		return articleCommentResponseModel;
	}
	
	/**
	 * 获取引用评论
	 */
	private void setArticleCommentReferenceId(ArticleCommentResponseModel articleCommentResponseModel,Integer count) {
		if(articleCommentResponseModel.getReferenceId()!=null && count<=MAX_REFERENCE_COUNT) {
			ArticleCommentResponseModel model = getArticleCommentById(articleCommentResponseModel.getReferenceId());
			articleCommentResponseModel.setSubArticleCommentResponseModel(model);
			count = count +1;
			setArticleCommentReferenceId(articleCommentResponseModel.getSubArticleCommentResponseModel(),count);
		}
	}
}
