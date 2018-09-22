package com.jiangdaxian.comment.mongo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "articleCommentMongo")
public class ArticleCommentMongo implements Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 文章ID
	 */
	@Field(value = "articleId")
	private Long articleId;
	
	/**
	 * 评论内容
	 */
	@Field(value = "content")
	private String content;
	
	/**
	 * 用户头像URL
	 */
	@Field(value = "headImage")
	private String headImage;
	
	/**
	 * 用户ID
	 */
	@Field(value = "userId")
	private Long userId;
	
	/**
	 * 用户昵称
	 */
	@Field(value = "nickName")
	private String nickName;
	
	/**
	 * 引用评论的ID
	 */
	@Field(value = "referenceId")
	private String referenceId;

	@Field(value = "createTime")
	private Date createTime;
	
	@Field(value = "createBy")
	private Long createBy;
	
	@Field(value = "updateTime")
	private Date updateTime;
	
	@Field(value = "updateBy")
	private Long updateBy;

	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	
	
}
