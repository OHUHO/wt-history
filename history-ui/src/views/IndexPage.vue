<template>
    <Layout :footer-style="2">
	    
        <div class="image-wrapper">
	        <img src="../assets/images/index/10007.png" alt="">
        </div>
	    
        <div class="container">
            <div class="row">
	            <div class="scroll-wrapper" id="nav">
		            <Card class="scroll-item"
		                  v-for="(article, index) in articleCardData"
		                  :key="index" :article="article"/>
	            </div>
                <div data-aos="fade-up" data-aos-delay="90">
                    <table class="check-btns">
                        <tr class="table-color">
	                        <td v-for="(item,index) in checks">
		                        <div :style="{'background-color': item.colorLine}"></div>
	                        </td>
                        </tr>
                        <tr><td colspan="16"></td></tr>
                        <tr>
	                        <td  class="check-hover"
	                             v-for="(item,index) in checks" :style="{'color': item.colorName,}"
	                             :class="{'check-active': activeStyle == index}"
	                             @click="clickTime(index,item.dynastyId)">{{item.name}}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
	    
	    <div class="container demo-block">
		    <div class="row" data-aos="fade-up">
			    <ul>
				    <a href="/timeline">
					    <img src=".././assets/images/index/block-1.png" alt="">
					    <li>
						    <span class="title">时间线条</span>
						    <span class="desc">用流程线条，展示历史发展进程</span>
						    <span class="btn">点击进入</span>
					    </li>
				    </a>
				    <a href="/maps">
					    <img src=".././assets/images/index/block-2.png" alt="">
					    <li>
						    <span class="title">时空地图</span>
						    <span class="desc">跨越时空界限，探索极致大历史</span>
						    <span class="btn">点击进入</span>
					    </li>
				    </a>
			    </ul>
		    </div>
	    </div>

        <Separator/>
        
        <div class="rn-demo-area rn-section-gap hot-history" id="demo">
            <div class="container">
	            <!-- 大标题 -->
                <div class="row">
                    <div class="col-lg-12">
                        <SectionTitle
                            title="热门历史知识"
                            description="中国古代历史是一个丰富多彩的历程，跨越了数千年的时间。从夏、商、周三代的封建王朝起源，到秦始皇统一六国、建立起中国的第一个中央集权国家，再到汉、唐、宋等多个盛世时期的兴盛与繁荣，中国古代历史平台展现了其在政治、经济、文化等多个领域的深厚积淀和不断发展。"
                            data-aos="fade-up"
                        />
                    </div>
                </div>
                <div class="row row--15">
                    <div class="col-lg-4 col-md-6 col-12 mt--40"
                         v-for="(article, index) in hotArticles"
                         :key="index"
                         data-aos="fade-up"
                         :data-aos-delay="100 + index">
                        <div class="single-demo">
	                        <router-link :to="`/article/${article.articleId}`">
                                <div class="thumbnail">
	                                <img v-if="article.articleCover" class="image" :src="article.articleCover" alt=""/>
	                                <img v-else src="../assets/images/index/slid.jpeg" alt="">
                                </div>
		                        <h4 class="title">{{ article.articleTitle }}</h4>
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>
	        <div class="row look-more">
		        <div class="look-more-btn" @click.prevent="loadMore()">
			        <Button title="查看更多"
			                size="small"
			                :outlined="true"
			                icon="arrow-right"/>
		        </div>
	        </div>
        </div>
	
	    <div class="button-group" data-aos="fade-up" data-aos-delay="70"></div>
	    
    </Layout>
</template>

<script>
    import Layout from '../components/common/Layout'
    import Icon from '../components/icon/Icon'
    import Separator from '../components/elements/separator/Separator'
    import SectionTitle from '../components/elements/sectionTitle/SectionTitle'
	import Card from "@/components/elements/card/Card";
	import Button from "@/components/elements/button/Button";
    import {Message} from "element-ui";

    export default {
        name: 'IndexPage',
        components: {Icon, Layout, Separator, SectionTitle, Card, Button},
        data() {
            return {
	            activeStyle: -1,
	            articleCardData:[],
                hotArticles: [],
	            pageIndex: 1,
	            hasNextPage: null,
	            checks:[
		            {dynastyId: 1,colorLine:'rgba(237, 95, 0, 0.5)',colorName:'rgb(237, 95, 0)',name:'文明曙光'},
		            {dynastyId: 2,colorLine:'rgba(237, 95, 0, 0.5)',colorName:'rgb(237, 95, 0)',name:'夏朝'},
		            {dynastyId: 3,colorLine:'rgba(114, 78, 0, 0.5)',colorName:'rgb(114, 78, 0)',name:'商朝'},
		            {dynastyId: 4,colorLine:'rgba(114, 107, 0, 0.5)',colorName:'rgb(114, 107, 0)',name:'西周'},
		            {dynastyId: 5,colorLine:'rgba(85, 114, 0, 0.5)',colorName:'rgb(85, 114, 0)',name:'春秋战国'},
		            {dynastyId: 6,colorLine:'rgba(17, 114, 0, 0.5)',colorName:'rgb(17, 114, 0)',name:'秦朝'},
		            {dynastyId: 7,colorLine:'rgba(0, 114, 71, 0.5)',colorName:'rgb(0, 114, 71)',name:'汉朝'},
		            {dynastyId: 8,colorLine:'rgba(0, 102, 111, 0.5)',colorName:'rgb(0, 102, 111)',name:'三国两晋南北朝'},
		            {dynastyId: 9,colorLine:'rgba(0, 82, 111, 0.5)',colorName:'rgb(0, 82, 111)',name:'隋朝'},
		            {dynastyId: 10,colorLine:'rgba(30, 68, 127, 0.5)',colorName:'rgb(30, 68, 127)',name:'唐朝'},
		            {dynastyId: 11,colorLine:'rgba(237, 95, 0, 0.5)',colorName:'rgb(237, 95, 0)',name:'五代十国'},
		            {dynastyId: 12,colorLine:'rgba(87, 74, 165, 0.5)',colorName:'rgb(87, 74, 165)',name:'宋朝'},
		            {dynastyId: 13,colorLine:'rgba(96, 40, 131, 0.5)',colorName:'rgb(96, 40, 131)',name:'辽夏金'},
		            {dynastyId: 14,colorLine:'rgba(150, 69, 164, 0.5)',colorName:'rgb(150, 69, 164)',name:'元朝'},
		            {dynastyId: 15,colorLine:'rgba(127, 32, 65, 0.5)',colorName:'rgb(127, 32, 65)',name:'明朝'},
		            {dynastyId: 16,colorLine:'rgba(127, 32, 65, 0.5)',colorName:'rgb(127, 32, 65)',name:'清朝'},
	
	            ],
	            
            }
        },
	    methods: {
		
		    clickTime(index,dynastyId) {
			    //console.log(index)
			    this.activeStyle = index
			    this.getArticlesByDynastyId(dynastyId)
			    
		    },
		    // 通过朝代id获取该朝代的文章
		    getArticlesByDynastyId(dynastyId){
			    this.getRequest("/common/getArticlesByDynastyId",dynastyId).then(resp => {
					if (resp){
						this.articleCardData = resp.obj;
					}
			    })
			    console.log(this.articleCardData)
		    },
		    // 获取热门历史知识
			getHotArticles(pageIndex){
				this.getRequest("/common/getHotArticles",pageIndex).then(resp =>{
					if (resp){
						console.log(resp.obj)
						this.hotArticles = this.hotArticles.concat(resp.obj.list)
						this.hasNextPage = resp.obj.hasNextPage
					}
				});
			},
		    
		    loadMore() {
				if (this.hasNextPage){
					this.pageIndex ++;
					this.getHotArticles(this.pageIndex);
				} else {
					Message({
						type:'info',
						message:'已加载全部内容！'
					})
				}
			   
		    },
		    
		    scrollInit() {
			    // 获取要绑定事件的元素
			    this.scrollDiv = document.getElementById("nav");
			    if (navigator.userAgent.indexOf('Firefox') >= 0) {
				    // 火狐写法 添加滚轮滚动监听事件
				    this.scrollDiv.addEventListener('DOMMouseScroll', this.scrollHandler, false)
			    } else {
				    // 非火狐 添加滚轮滚动监听事件
				    this.scrollDiv.addEventListener('mousewheel', this.scrollHandler, false)
			    }
		    },
		    scrollHandler(event){
			    if(event.preventDefault)
			    {
				    event.preventDefault();
			    }else{
				    event.returnValue = false;
			    }
			    // 获取滚动方向
			    const detail = event.wheelDelta || event.detail;
			    // 定义滚动方向，其实也可以在赋值的时候写
			    const moveForwardStep = 1;
			    const moveBackStep = -1;
			    // 定义滚动距离
			    let step = 0;
			    // 判断滚动方向,这里的100可以改，代表滚动幅度，也就是说滚动幅度是自定义的
			    if (navigator.userAgent.indexOf('Firefox') >= 0) {
				    // 火狐浏览器和谷歌的值正好相反
				    if (detail > 0) {
					    step = moveForwardStep * 100;
				    } else {
					    step = moveBackStep * 100;
				    }
			    }else{
				    if (detail < 0) {
					    step = moveForwardStep * 100;
				    } else {
					    step = moveBackStep * 100;
				    }
			    }
			    // 对需要滚动的元素进行滚动操作
			    this.scrollDiv.scrollLeft += step;
		    },
	    },
	    beforeDestroy() {
		    if (navigator.userAgent.indexOf('Firefox') >= 0) {
			    this.scrollDiv.removeEventListener('DOMMouseScroll', this.scrollHandler, false)
		    } else {
			    this.scrollDiv.removeEventListener('mousewheel', this.scrollHandler, false)
		    }
	    },
	
	    mounted() {
		    this.scrollInit();
			
		    this.getArticlesByDynastyId(1)
		    this.getHotArticles(1)
	    },

    }
</script>
