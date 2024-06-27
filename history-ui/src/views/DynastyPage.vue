<template>
	<Layout>
		
		<div class="header-wrapper">
			<CallToAction
				:id="dynasty.dynastyId"
				:name="dynasty.dynastyName"
				:description="dynasty.dynastyDescribe"
			/>
		</div>
		
		<div class="rn-section-block">
			<div class="container">
				<div class="row row--15">
					<div class="col-lg-12">
						<div class="rn-contact-address mt_dec--30">
							<div class="row">
								<div class="col-lg-6 col-md-6 col-12">
									<div class="rn-address">
										<div class="icon">
											<Icon name="mail" :size="30"/>
											<span class="theme-gradient h4">&nbsp;&nbsp;历史事件</span>
										</div>
										<div class="inner">
											<p>
												<a v-for="event in events"
												   
												   href="#">∬{{event.eventName}} &nbsp;&nbsp;</a>
											</p>
										</div>
									</div>
								</div>
								
								<div class="col-lg-6 col-md-6 col-12">
									<div class="rn-address">
										<div class="icon">
											<Icon name="tag" :size="30"/>
											<span class="theme-gradient h4">&nbsp;&nbsp;词条标签</span>
										</div>
										<div class="inner">
											<p>
												<a v-for="label in labels"
												   
												   href="#">#{{label.labelName}} &nbsp;&nbsp;</a>
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
		
		<Breadcrumb title="全部历史记录" :current-dynasty="dynasty.dynastyName"/>
		
		<div class="rn-blog-area rn-section-gap blog-padding">
			<div class="container">
				<div class="row mt_dec--30">
					<div class="col-lg-12">
						<div class="row row--15">
							<!-- list -->
							<!--<div class="col-lg-6 mt&#45;&#45;30"-->
							<div class="col-lg-4 col-md-6 col-12 mt--30"
							     data-aos="slide-up"
							     data-aos-duration="800"
							     :data-aos-delay="100 + index"
							     v-for="(article, index) in articles"
							     :key="index">
								<BlogPost :article="article"/>
							</div>
						</div>
					</div>
					<div class="col-lg-12 text-center">
						<div class="rwt-load-more text-center mt--60 look-more-btn-wrapper">
							<div class="look-more-btn" @click.prevent="loadMore">
								<a class="btn-default" href="#">
									加载更多 <Icon name="loader"/>
								</a>
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
import Icon from "@/components/icon/Icon";
import BlogPost from "@/components/blog/BlogPost";
import Breadcrumb from "@/components/elements/breadcrumb/Breadcrumb";
import CallToAction from "@/components/elements/callToAction/CallToAction";
import {Message} from "element-ui";

export default {
	name: "DynastyPage",
	components: {Layout,Icon, Breadcrumb, BlogPost, CallToAction},
	mounted() {
		this.getDynastyByDynastyId(this.$route.params.dynastyId)
		
		this.getArticlesByDynastyId(this.$route.params.dynastyId ,1)
		
		this.getEvents(this.$route.params.dynastyId)
		
		this.getLabels(this.$route.params.dynastyId)
	},
	data(){
		return{
			dynasty:{},
			pageIndex:1,
			hasNextPage: true,
			articles:[],
			events:[],
			labels:[],
		}
	},
	methods:{
		getDynastyByDynastyId(dynastyId) {
			// 发送请求获取地图信息
			this.getRequest("/common/getDynastyByDynastyId",dynastyId).then(resp => {
				if (resp){
					this.dynasty = resp.obj
					// 设置网页title
					document.title = '中国历史平台 || ' + resp.obj.dynastyName
				}
			})
		},
		
		getArticlesByDynastyId(dynastyId,pageIndex) {
			const params = {
				dynastyId: dynastyId,
				pageIndex: pageIndex
			}
			this.getRequest("/common/getArticlesByDynastyId/page",JSON.stringify(params)).then(resp => {
				if (resp){
					console.log(resp.obj)
					this.articles = this.articles.concat(resp.obj.list)
					if (resp.obj.list.length == 0){
						this.hasNextPage = false
					}
					//this.hasNextPage = resp.obj.hasNextPage
				}
			})
		},
		loadMore(){
			if (this.hasNextPage){
				this.pageIndex ++;
				this.getArticlesByDynastyId(this.dynasty.dynastyId ,this.pageIndex);
			} else {
				Message({
					type:'info',
					message:'已加载全部内容！'
				})
			}
		},
		
		getEvents(dynastyId){
			this.getRequest("/common/getEventsByDynastyId",dynastyId).then(resp =>{
				if (resp){
					this.events = resp.obj
				}
			})
		},
		getLabels(dynastyId){
			this.getRequest("/common/getLabelsByDynasty",dynastyId).then(resp => {
				if (resp){
					this.labels = resp.obj
				}
			})
		}
		
	}
}
</script>

<style scoped>

</style>
