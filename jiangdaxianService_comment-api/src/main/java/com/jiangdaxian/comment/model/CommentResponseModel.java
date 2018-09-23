package com.jiangdaxian.comment.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CommentResponseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//评论回复集合
	private List<CommentReplyModel> commentReplyModelList;
	
	public List<CommentReplyModel> getCommentReplyModelList() {
		return commentReplyModelList;
	}

	public void setCommentReplyModelList(List<CommentReplyModel> commentReplyModelList) {
		this.commentReplyModelList = commentReplyModelList;
	}

	// 引用的其他评论
	private CommentResponseModel subCommentResponseModel;

	public CommentResponseModel getSubCommentResponseModel() {
		return subCommentResponseModel;
	}

	public void setSubCommentResponseModel(CommentResponseModel subCommentResponseModel) {
		this.subCommentResponseModel = subCommentResponseModel;
	}

	private String id;

	/**
	 * 
	 */
	private Long ItemId;

	
	private Integer type;
	
	
	
	public Long getItemId() {
		return ItemId;
	}

	public void setItemId(Long itemId) {
		ItemId = itemId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 评论内容
	 */
	private String content;

	/**
	 * 用户头像URL
	 */
	private String headImage;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 用户昵称
	 */
	private String nickName;

	/**
	 * 引用评论的ID
	 */
	private String referenceId;

	private Date createTime;

	private Long createBy;

	private Date updateTime;

	private Long updateBy;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
