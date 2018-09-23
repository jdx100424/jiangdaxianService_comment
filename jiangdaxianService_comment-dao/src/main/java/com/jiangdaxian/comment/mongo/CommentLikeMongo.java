package com.jiangdaxian.comment.mongo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "commentLikeMongo")
public class CommentLikeMongo implements Serializable {
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	/**
	 * 评论ID
	 */
	@Field(value = "commentId")
	private Long commentId;
	
	
	/**
	 * 状态。0，不启用；1，启用
	 */
	@Field(value = "status")
	private Integer status;
	
	/**
	 * 用户Id
	 */
	@Field(value = "userId")
	private Long userId;
	
	@Field(value = "createTime")
	private Date createTime;
	
	@Field(value = "createBy")
	private Long createBy;
	
	@Field(value = "updateTime")
	private Date updateTime;
	
	@Field(value = "updateBy")
	private Long updateBy;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
