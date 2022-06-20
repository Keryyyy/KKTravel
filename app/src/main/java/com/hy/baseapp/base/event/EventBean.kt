package com.hy.baseapp.base.event

import androidx.annotation.Keep

/**
 * 关注或者取关
 * @param contentId
 * @param isFollow true：关注了 false:取关了
 */
@Keep
data class FollowEventBean (val contentId:Int = 0, val isFollow:Boolean,val accId:String= "")

/**
 * 评论点赞
 * @param commentId
 * @param currentNumber 点赞数量
 */
@Keep
data class LikeCommentEventBean (val commentId:Int, val isLike:Boolean, val currentNumber:Int = -1)

/**
 * 故事点赞
 * @param contentId
 * @param currentNumber 点赞数量
 */
@Keep
data class LikeEventBean (val contentId: Int, val currentNumber:Int = -1)


/**
 * 评论事件
 * @param contentId
 * @param currentNumber 当前评论数量
 */
@Keep
data class CommentEventBean (val contentId:Int, val currentNumber: Int)
