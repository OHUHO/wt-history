<template>
	<Layout>
		<div class="rn-section-gap timeline-wrapper pt--70">
			<div class="container">
				<div class="row">
					<div class="article-header">
						<img v-if="article.articleCover" :src="article.articleCover" alt="">
						<img v-else src="../assets/images/index/10006.jpeg" alt="">
						<div class="header-other-info">
							<h3 class="h3">{{article.articleTitle}}</h3>
							<div class="other-info-bottom">
								<div class="author-info">
									<img :src="article.authorAvatar" alt="">
									<h5>{{article.authorName}}</h5>
								</div>
								<h6>{{article.createTime}}</h6>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="blog-details-content pt--20 rn-section-gapBottom article-details">
						<p v-html="editor.value"></p>
						<!--<div class="container">
							<div class="row">
								<div class="col-lg-8 offset-lg-2">
									<div class="content">
										<p v-html="editor.value"/>
									</div>
								</div>
							</div>
						</div>-->
						<div class="article-goods"
						     @click="articleClickGood(article.articleId)">
							<Icon :style="{color: article.hasClickGood?'#FB5343':''}"
							      name="thumbs-up"
							      size="24"/>
							<span>{{article.goodNum}}</span>
						</div>
					</div>
				</div>
				
				
				<div class="row article-comment-wrapper">
					<div class="rbt-single-widget ">
						<h3 class="title">全部评论</h3>
						<div class="inner mt--20">
							<div class="rnform-group">
								<!--<textarea class="textarea"
								          maxlength="800"
								          placeholder="请输入你的评论内容，最多 800 个字"></textarea>
								<div class="blog-btn">
									<a class="btn-default" href="#">
										<span>评论</span>
									</a>
								</div>-->
								<div class="blog-search">
									<input type="text"
									       v-model="inputComment"
									       placeholder="请输入你的评论内容，最多 200 个字"/>
									<button class="search-button" @click="publishComment">
										<Icon name="send" size="20"/>
									</button>
								</div>
								
								<!-- 评论内容 -->
								<div class="comment-details">
									<div class="comment-item"
									     v-for="(comment,index) in comments"
									     :key="index">
										<div class="comment-left">
											<img :src="comment.avatar" alt="">
										</div>
										<div class="comment-right">
											<div class="comment-right-top">
												<div class="comment-right-top-left">
													<h6>{{comment.nickname}}</h6>
													<span>{{comment.createTime}}</span>
												</div>
												<div class="comment-right-top-right"
												     @click="commentClickGood(index,comment.commentId)">
													<Icon name="thumbs-up"
													      size="20"
													      :style="{color: comment.hasClickGood?'#FB5343':''}" />
													<span>{{comment.goodNum}}</span>
												</div>
											</div>
											<p class="comment-content">
												{{comment.content}}
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</Layout>
</template>

<script>
import Layout from "@/components/common/Layout";
import Editor from "@/components/editor/Editor";
import Separator from "@/components/elements/separator/Separator";
import Icon from "@/components/icon/Icon";
import {Message} from "element-ui";
export default {
	name: "ArticlePage",
	components: {Separator, Editor, Layout, Icon},
	data(){
		return{
			article:{},
			editor:{
				value:'',
				toolbarsFlag: false,
				subfield: false,
				defaultOpen: 'preview'
			},
			comments:[],
			inputComment:'',
			
		}
	},
	mounted() {
		this.getArticleByArticleId(this.$route.params.articleId)
		this.getCommentsByArticleId(this.$route.params.articleId)
	},
	methods:{
		
		getArticleByArticleId(articleId){
			// 获取userId
			const userId = JSON.parse(window.sessionStorage.getItem("user")).userId
			const params = {
				userId: userId,
				articleId: articleId,
			}
			this.getRequest("/common/getArticleByUserIdAndArticleId",JSON.stringify(params)).then(resp =>{
				if (resp){
					this.article = resp.obj
					this.editor.value = resp.obj.articleContentHtml
				}
			})
			
		},
		
		getCommentsByArticleId(articleId){
			// 获取userId
			const userId = JSON.parse(window.sessionStorage.getItem("user")).userId
			const params = {
				userId: userId,
				articleId: articleId,
			}
			this.getRequest("/common/getCommentsByUserIdAndArticleId",JSON.stringify(params)).then(resp => {
				if (resp){
					this.comments = resp.obj
				}
			})
		},
		
		// 文章点赞
		articleClickGood(articleId){
			if (this.article.hasClickGood){
				this.article.goodNum --
				// 发送请求，更改数据库中的值
				this.articleClickGoodOperation(-1)
			}else {
				this.article.goodNum ++
				// 发送请求，更改数据库中的值
				this.articleClickGoodOperation(1)
			}
			this.article.hasClickGood = !this.article.hasClickGood
			
		},
		articleClickGoodOperation(operation){
			const userId = JSON.parse(window.sessionStorage.getItem("user")).userId
			const data ={
				userId: userId,
				articleId: this.article.articleId,
				operation: operation,
			}
			this.putRequest("/user/articleClickGoodOperation",data).then(resp => {
				if (resp){
				
				}
			})
		},
		
		// 评论点赞
		commentClickGood(index,commentId){
			if (this.comments[index].hasClickGood){
				this.comments[index].goodNum --
				// 发送请求，更改数据库中的值
				this.commentClickGoodOperation(commentId,-1)
			}else {
				this.comments[index].goodNum ++
				// 发送请求，更改数据库中的值
				this.commentClickGoodOperation(commentId,+1)
			}
			this.comments[index].hasClickGood = !this.comments[index].hasClickGood
		},
		commentClickGoodOperation(commentId,operation){
			const userId = JSON.parse(window.sessionStorage.getItem("user")).userId
			const data ={
				userId: userId,
				commentId: commentId,
				articleId: this.article.articleId,
				operation: operation,
			}
			this.putRequest("/user/commentClickGoodOperation",data).then(resp => {
				if (resp){
				
				}
			})
		},
		
		publishComment(){
			if (this.inputComment == ''){
				Message({
					type:'error',
					message:'评论不能为空！',
				})
			}else {
				// 获取userId
				const userId = JSON.parse(window.sessionStorage.getItem("user")).userId
				const data = {
					userId: userId,
					articleId: this.article.articleId,
					content: this.inputComment
				}
				this.putRequest("/user/comment",data).then(resp => {
					if (resp){
						this.inputComment = ''
						Message({
							type:'info',
							message:'评论成功！',
						})
						this.getCommentsByArticleId(this.article.articleId)
					}
				})
			}
			
		}
		
		
	}
}
</script>

<style scoped>

</style>
