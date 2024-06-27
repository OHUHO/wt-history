<template>
	<div class="login-wrapper">
		<header class="rn-header header-default">
			<div class="write-header-left">
				<h5 class="title w-600 theme-gradient">中国历史平台</h5>
				<h5 class="title w-600">&nbsp; > &nbsp;写文章</h5>
			</div>
			<div class="write-header-right">
				<div class="header-btn">
					<a class="btn-default btn-small round" href="#" @click.prevent="publish">
						<span>发布</span>
					</a>
				</div>
				<div id="my_switcher" class="my_switcher">
					<ul>
						<li>
							<a href="javascript: void(0);"
							   @click.prevent="AppFunctions.toggleClass('body', 'active-light-mode')">
								<img class="light-icon"
								     src="../assets/images/icons/sun-01.svg"
								     alt="Sun images">
								<img class="dark-icon"
								     src="../assets/images/icons/vector.svg"
								     alt="Moon Images">
							</a>
						</li>
					</ul>
				</div>
			</div>
		</header>
		
		<div class="rn-blog-area rn-section-gap article-area">
			<div class="container">
				<div class="row row--30">
					<div class="col-lg-9">
						<div class="article-title rnform-group">
							<input type="text"
							       required
							       maxlength="30"
							       v-model="article.title"
							       placeholder="请输入文章标题，在30个字以内">
						</div>
						<div class="write">
							<Editor :editor="article.editor"/>
						</div>
						<div class="rbt-single-widget widget_tag_cloud mt--40">
							<h3 class="title">词条标签</h3>
							<div class="inner mt--20">
								<ul class="tagcloud">
									<a v-for="(label, index) in labels"
									   :key="index"
									   :class="[{'dynasty-selected-style': selectedLabelId === label.labelId}]"
									   @click="labelChoose(label.labelId)">
										{{ label.labelName }}
									</a>
								</ul>
							</div>
						</div>
					</div>
					
					<div class="col-lg-3 mt_md--40 mt_sm--40">
						<div class="rbt-single-widget widget_tag_cloud mt--40">
							<h3 class="title">文章封面</h3>
							<div class="inner mt--20 article-cover">
								<!--
									https://vuejsexamples.com/a-simple-upload-multiple-image-component-for-vuejs/
								-->
								<vue-upload-multiple-image
									class="upload-vue"
									@upload-success="uploadImageSuccess"
									@before-remove="beforeRemove"
									@edit-image="editImage"
									@data-change="dataChange"
									:max-image="2"
									drag-text="点击上传文章封面"
									browse-text="中国历史平台"
									popup-text="该图片用来作为文章的封面"
									primary-text="中国历史平台"
									:data-images="images"/>
								
								<!--<el-upload
									action="#"
									accept="'image/*'"
									list-type="picture-card"
									:on-upload="false"
									:limit="1"
									:show-file-list="true"
									:on-change="handleChange"
									:handle-remove="handleHideRemove"
									:before-upload="beforeAvatarUpload"
									:class="{hide:hideUpload}"
								>
									<img v-if="articleCover" :src="articleCover" class="avatar" />
									<i v-else class="el-icon-plus avatar-uploader-icon"></i>
								</el-upload>-->
							
							</div>
						</div>
						<div class="rbt-single-widget widget_tag_cloud mt--40">
							<h3 class="title">文章摘要</h3>
							<div class="inner mt--20">
								<textarea class="textarea"
								          v-model="article.articleSummary"
								          placeholder="请输入文章摘要，不超过90个字"></textarea>
							</div>
						</div>
						<div class="rbt-single-widget widget_tag_cloud mt--40">
							<h3 class="title">选择朝代</h3>
							<div class="inner mt--20">
								<ul class="tagcloud">
									<a v-for="(dynasty, index) in dynasties"
									   :key="index"
									   :class="[{'dynasty-selected-style': selectedDynastyId === dynasty.dynastyId}]"
									   @click="dynastyChoose(dynasty.dynastyId)">
										{{ dynasty.dynastyName }}
									</a>
								</ul>
							</div>
						</div>
						<div class="rbt-single-widget widget_tag_cloud mt--40">
							<h3 class="title">历史事件</h3>
							<div class="inner mt--20">
								<div class="rnform-group">
									<input type="text"
									       v-model="article.events"
									       placeholder="请输入历史关键字，以逗号分割">
								</div>
							</div>
						</div>
					
					</div>
				</div>
			</div>
		</div>
		
		
		<!--<div class="tiptap-wrapper container">
			<div class="row article-title rnform-group">
				<input type="text"
				       required
				       maxlength="30"
				       v-model="article.title"
				       placeholder="请输入文章标题，在30个字以内">
			</div>
			<div class="row write">
				<Editor :editor="article.editor"/>
			</div>
		</div>
		
		<div class="container">
			<div class="rn-comment-form pt&#45;&#45;60">
				<div class="inner">
					<div class="row">
						<div class="col-lg-4">
							<div class="article-cover">
							</div>
						</div>
						<div class="col-lg-8">
							<div class="rnform-group">
								<input type="text" placeholder="请输入文章关键词">
							</div>
							<div class="rnform-group">
								<textarea class="textarea"
								          maxlength="90"
								          placeholder="请输入文章摘要，不超过90个字"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="rn-comment-form pt&#45;&#45;60">
				<div class="inner">
					<div class="row">
						<div class="col-lg-1">
							选择朝代
						</div>
						<div class="col-lg-11">
							<div class="category-meta">
								<div class="tagcloud">
									<a href="#">唐朝</a>
									<a href="#">唐朝</a>
									<a href="#">唐朝</a>
									<a href="#">唐朝</a>
									<a href="#">唐朝</a>
									<a href="#">唐朝</a>
									<a href="#">唐朝</a>
									<a href="#">唐朝</a>
									<a href="#">唐朝</a>
									<a href="#">唐朝</a>
									<a href="#">三国两晋南北朝</a>
									<a href="#">五代十国</a>
									<a href="#">文明曙光</a>
									<a href="#">唐朝</a>
									<a href="#">唐朝</a>
									<a href="#">春秋战国</a>
								</div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="rn-comment-form pt&#45;&#45;60">
				<div class="inner">
					<div class="row">
						<div class="col-lg-1">
							历史事件
						</div>
						<div class="col-lg-11">
							<div class="rnform-group">
								<input type="text" placeholder="请输入历史关键字，以逗号分割">
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>-->
	
	
	</div>
</template>

<script>
import Layout from "@/components/common/Layout";
import AppFunctions from "@/helpers/AppFunctions";
import Editor from "@/components/editor/Editor";
import Breadcrumb from "@/components/elements/breadcrumb/Breadcrumb";
import Separator from "@/components/elements/separator/Separator";
import VueUploadMultipleImage from 'vue-upload-multiple-image'
import {Message} from "element-ui";


export default {
	name: "WritePage",
	components: {Separator, Breadcrumb, Editor, Layout, VueUploadMultipleImage},
	
	data() {
		return {
			AppFunctions,
			dynasties: [],
			selectedDynastyId: null,
			
			labels: [],
			selectedLabelId: null,
			
			images: [],
			
			article: {
				title: '',
				summary: '',
				articleCover:'',
				events:'',
				articleSummary:'',
				
				editor: {
					value: '',
					ref: '',//保存mavonEditor实例
					default_open: 'edit',
					toolbars: {
						bold: true, // 粗体
						italic: true, // 斜体
						header: true, // 标题
						underline: true, // 下划线
						strikethrough: true, // 中划线
						mark: true, // 标记
						superscript: true, // 上角标
						subscript: true, // 下角标
						quote: true, // 引用
						ol: true, // 有序列表
						ul: true, // 无序列表
						imagelink: true, // 图片链接
						code: true, // code
						fullscreen: true, // 全屏编辑
						readmodel: true, // 沉浸式阅读
						help: true, // 帮助
						undo: true, // 上一步
						redo: true, // 下一步
						trash: true, // 清空
						navigation: true, // 导航目录
						//subfield: true, // 单双栏模式
						preview: true, // 预览
						boxShadow: true,  //边框阴影
						ishljs: true, //代码高亮
						scrollStyle: false
					}
				}
			}
		}
	},
	methods: {
		uploadImageSuccess(formData, index, fileList) {
			let userId = JSON.parse(window.sessionStorage.getItem('user')).userId;
			let formdata = new FormData();
			formdata.append('userId', userId);
			formdata.append('image', fileList[0].path);
			console.log(formdata)
			
			this.postRequest("/user/article/coverImg", formdata).then(resp => {
				if (resp) {
					this.article.articleCover = resp
				}
			})
		},
		beforeRemove(index, done, fileList) {
			console.log('index', index, fileList)
			var r = confirm("remove image")
			if (r == true) {
				done()
			} else {
			}
		},
		editImage(formData, index, fileList) {
			console.log('edit data', formData, index, fileList)
		},
		dataChange(data) {
			console.log(data)
		},
		getDynasties() {
			this.getRequest("/common/getDynasties", 'all').then(resp => {
				if (resp) {
					this.dynasties = resp.obj
				}
			})
		},
		getLabels() {
			this.getRequest("/user/getLabels", "all").then(resp => {
				if (resp) {
					this.labels = resp.obj
				}
			})
		},
		dynastyChoose(dynastyId) {
			this.selectedDynastyId = dynastyId;
		},
		labelChoose(labelId) {
			this.selectedLabelId = labelId;
		},
		
		
		publish() {
			// 发布文章
			const article = {
				articleTitle:this.article.title,
				articleCover: this.article.articleCover,
				articleDynastyId: this.selectedDynastyId,
				articleLabelId:  this.selectedLabelId,
				articleEvent: this.article.events,
				articleAuthorId:JSON.parse(window.sessionStorage.getItem("user")).userId,
				articleSummary:this.article.articleSummary,
				articleContent: this.article.editor.value,
				articleContentHtml: this.article.editor.ref.d_render,
			}
			console.log(article)
			this.postRequest("/user/saveArticle",article).then(resp => {
				if (resp){
					console.log(resp)
					Message({
						type:'success',
						message:resp.message
					})
					this.$router.replace("/")
				}
			})
		}
	},
	
	mounted() {
		//this.toggleStickyHeader();
		
		// 初始时页面为 light
		this.AppFunctions.toggleClass('body', 'active-light-mode')
		
		this.getDynasties()
		
		this.getLabels()
	},
	
}
</script>

<style>
.hide .el-upload--picture-card {
	display: none;
}
</style>
