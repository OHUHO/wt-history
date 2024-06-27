<template>
    <div class="rn-default-tab">
        <ul class="nav nav-tabs tab-button" role="tablist">
            <li class="nav-item tabs__tab"
                role="presentation"
                v-for="(tab, index) in tabs"
                :key="index">
                <button class="nav-link text-capitalize"
                        :class="{'active': index === 0}"
                        data-bs-toggle="tab"
                        :data-bs-target="`#${tab.tabName}`"
                        type="button"
                        role="tab"
                        :aria-controls="tab.tabName"
                        :aria-selected="index === activeTab ? 'true' : 'false'"
                        @click="activeTab = index">
                    {{ tab.tabName }}
                </button>
            </li>
        </ul>
	
	    <div class="rn-tab-content tab-content">
		    <!-- Tab1 -->
		    <div class="tab-pane fade"
		         :class="{'show active': activeTab === 0}">
			    <div class="inner">
				    <div class="row align-items-center row--30">
					   <div class="center-userinfo">
						  <div class="userinfo-username-avatar">
							  <img :src="userinfo.avatar" alt="">
							  <div class="userinfo">
								  <h4>{{userinfo.nickname}}</h4>
								  <span>注册时间：{{userinfo.createTime}}</span>
							  </div>
						  </div>
						   <Separator/>
						   <div class="userinfo-all">
							   <div class="userinfo-username">
								   <h5>用户昵称 :</h5>
								   <div class="rnform-group">
									   <input type="text"
									          v-model="tempUserinfo.nickname">
								   </div>
							   </div>
							   <div class="userinfo-avatar">
								   <h5>用户头像 :</h5>
								   <div class="upload">
									   <vue-upload-multiple-image
										   class="upload-vue"
										   @upload-success="uploadImageSuccess"
										   @before-remove="beforeRemove"
										   @edit-image="editImage"
										   @data-change="dataChange"
										   :max-image="1"
										   drag-text="点击修改用户头像"
										   browse-text="中国历史平台"
										   popup-text="该图片用来作为用户的头像"
										   primary-text="中国历史平台"
										   :data-images="images"/>
								   </div>
							   </div>
						   </div>
						   <div class="update-userinfo-btn" @click.prevent="updateUserInfo()">
							   <Button title="提交"
							           size="small"
							           :outlined="true"
							           icon="edit"/>
						   </div>
					   </div>
				    </div>
			    </div>
		    </div>
		    <!-- Tab2 -->
		    <div class="tab-pane fade"
		         :class="{'show active': activeTab === 1}">
			    <div class="inner">
				    <div class="row align-items-center row--30 publish-block">
					    <div class="col-lg-4 col-md-6 col-12 mt--30"
					         data-aos="slide-up"
					         data-aos-duration="800"
					         :data-aos-delay="100 + index"
					         v-for="(article, index) in myArticles"
					         :key="index">
						    <BlogPost :article="article"/>
					    </div>
				    </div>
			    </div>
		    </div>
		    <!-- Tab3 -->
		    <div class="tab-pane fade"
		         :class="{'show active': activeTab === 2}">
			    <div class="inner">
				    <div class="row align-items-center row--30">
					    <div class="center-info">
						   <div class="info-comment" v-for="(comment,index) in myComments" :key="index">
							   <h5>我评论了文章
								   <a :href="`/article/${comment.articleId}`">《{{comment.articleTitle}}》</a>
							   </h5>
							   <span>{{comment.createTime}}</span>
						   </div>
					    </div>
				    </div>
			    </div>
		    </div>
		    
		    <!--<div class="tab-pane fade"
		         :class="{'show active': activeTab === 3}">
			    <div class="inner">
				    <div class="row align-items-center row&#45;&#45;30">
					    <div class="center-info">
						    <div class="info-comment" v-for="(comment,index) in myComments" :key="index">
							    <h5>我点赞了文章
								    <a :href="`/article/${comment.articleId}`">《{{comment.articleTitle}}》</a>
							    </h5>
							    <span>{{comment.createTime}}</span>
						    </div>
					    </div>
				    </div>
			    </div>
		    </div>-->
		    
	    </div>
	    
    </div>
</template>

<script>
    import Separator from "@/components/elements/separator/Separator";
    import VueUploadMultipleImage from 'vue-upload-multiple-image'
    import BlogPost from "@/components/blog/BlogPost";
	import Icon from "@/components/icon/Icon";
	import Button from "@/components/elements/button/Button";
    import {Message} from "element-ui";
    export default {
        name: 'Tab',
	    components: {Separator,VueUploadMultipleImage,BlogPost,Icon,Button},
	    data() {
            return {
                activeTab: 0,
	            tabs: [
		            {id:1,tabName:'个人中心'},
		            {id:2,tabName:'我的发布'},
		            {id:3,tabName:'我的评论'},
		            //{id:4,tabName:'我的点赞'},
	            ],
	            userinfo:JSON.parse(window.sessionStorage.getItem("user")),
	            tempUserinfo:JSON.parse(window.sessionStorage.getItem("user")),
	            images:[],
	            myArticles: [],
	            myComments:[],
	            
            }
        },
	    mounted() {
			const userId = JSON.parse(window.sessionStorage.getItem("user")).userId
			this.getMyArticles(userId)
		    this.getMyComments(userId)
	    },
	    methods:{
		    uploadImageSuccess(formData, index, fileList) {
			    let userId = JSON.parse(window.sessionStorage.getItem('user')).userId;
			    let formdata = new FormData();
			    formdata.append('userId', userId);
			    formdata.append('image', fileList[0].path);
			    console.log(formdata)
			
			    this.postRequest("/user/updateAvatar", formdata).then(resp => {
				    if (resp) {
					    this.tempUserinfo.avatar = resp
				    }
			    })
		    },
		    beforeRemove (index, done, fileList) {
			    console.log('index', index, fileList)
			    var r = confirm("remove image")
			    if (r == true) {
				    done()
			    } else {
			    }
		    },
		    editImage (formData, index, fileList) {
			    console.log('edit data', formData, index, fileList)
		    },
		    dataChange (data) {
			    console.log(data)
		    },
		    
		    getMyArticles(userId){
				this.getRequest("/user/getArticlesByUserId",userId).then(resp => {
					if (resp){
						this.myArticles = resp.obj
					}
				})
		    },
		    getMyComments(userId){
				this.getRequest("/uer/getCommentByUserId",userId).then(resp => {
					if (resp){
						this.myComments = resp.obj
					}
				})
		    },
		    updateUserInfo(){
				if (this.userinfo.nickname == this.tempUserinfo.nickname && this.userinfo.avatar == this.tempUserinfo.avatar){
					Message({
						type:'info',
						message:'未修改信息，提交无效！',
					})
				}else {
					const user = {
						userId:JSON.parse(window.sessionStorage.getItem('user')).userId,
						nickname:this.tempUserinfo.nickname,
						avatar: this.tempUserinfo.avatar,
					}
					this.putRequest("/user/updateUserInfo",user).then(resp => {
						if (resp){
							Message({
								type:'success',
								message:resp.message,
							})
							this.userinfo = this.tempUserinfo
							
							// 更新浏览器 sessionStorage
							window.sessionStorage.setItem('user',JSON.stringify(this.userinfo));
						}
					})
					
					
				}
				
		    }
			
	    }
    }
</script>
