package com.jiangdaxian.comment.mongo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "commentReplyMongo")
public class CommentReplyMongo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * 该类型数据相关ID
	 */
	@Field(value = "commentId")
	private String commentId;

	/**
	 * 评论内容
	 */
	@Field(value = "content")
	private String content;

	/**
	 * 被回复用户ID
	 */
	@Field(value = "toUserId")
	private Long toUserId;

	@Field(value = "toNickName")
	private String toNickName;
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

	@Field(value = "createTime")
	private Date createTime;
	
	@Field(value = "createBy")
	private Long createBy;
	
	@Field(value = "updateTime")
	private Date updateTime;
	
	@Field(value = "updateBy")
	private Long updateBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
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

	public String getToNickName() {
		return toNickName;
	}

	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}

}
