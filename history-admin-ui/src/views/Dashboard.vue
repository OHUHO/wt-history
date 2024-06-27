<template>
    <div>
        <el-row :gutter="20">
            <el-col :span="8">
                <el-card shadow="hover" class="mgb20" style="height:252px;">
                    <div class="user-info">
                        <img src='../assets/img/img.jpeg' class="user-avator" alt />
                        <div class="user-info-cont">
                            <div class="user-info-name">{{name}}</div>
                            <div>系统管理员</div>
                        </div>
                    </div>
                    <div class="user-info-list">
                        注册时间：<span>2023-02-19</span>
                    </div>
                    <div class="user-info-list">
                        我的位置：<span>四川成都</span>
                    </div>
                </el-card>
                <el-card shadow="hover" style="height:172px;">
                    <div slot="header" class="clearfix">
                        <span>已审核 / 全部</span>
                    </div>
                    文章<el-progress :percentage="articlePercentage" color="#42b983"></el-progress>
                    评论<el-progress :percentage="commentPercentage" color="#f1e05a"></el-progress>
                </el-card>
            </el-col>
            <el-col :span="16">
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-1">
                                <i class="el-icon-lx-people grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{userCount}}</div>
                                    <div>用户总数</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-2">
                                <i class="el-icon-c-scale-to-original grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{dynastyCount}}</div>
                                    <div>历史块数</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-reading grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">{{articleCount}}</div>
                                    <div>文章数量</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
                <el-row>
                    <el-card shadow="hover" style="height:323px;">
                        <div class="schart-box">
                            <schart v-if='options' class="schart" canvasId="pie" :options="options"></schart>
                        </div>
                    </el-card>
                </el-row>
            </el-col>
        </el-row>
        
        <el-row :gutter="20">
            <el-row>
                <el-card shadow="hover">
                    <schart ref="line" class="schart" canvasId="line" :options="options1"></schart>
                </el-card>
                <!--<el-card shadow="hover">
                    <schart ref="bar" class="schart" canvasId="bar" :options="options2"></schart>
                </el-card>-->
            </el-row>
        </el-row>
    </div>
</template>

<script>
import Schart from 'vue-schart';
import bus from '../components/common/bus';
export default {
    name: 'dashboard',
    data() {
        return {
            name: JSON.parse(window.sessionStorage.getItem("admin")).username,
            userCount:5498,
            dynastyCount:16,
            articleCount:290,
            articlePercentage: 9.9,
            commentPercentage: 4.4,
            
            data: [
                {
                    name: '2018/09/04',
                    value: 1083
                },
                {
                    name: '2018/09/05',
                    value: 941
                },
                {
                    name: '2018/09/06',
                    value: 1139
                },
                {
                    name: '2018/09/07',
                    value: 816
                },
                {
                    name: '2018/09/08',
                    value: 327
                },
                {
                    name: '2018/09/09',
                    value: 228
                },
                {
                    name: '2018/09/10',
                    value: 1065
                }
            ],
            /*options2: {
                type: 'bar',
                title: { text: '最近一周各品类销售图' },
                xRorate: 25,
                labels: ['周一', '周二', '周三', '周四', '周五'],
                datasets: [
                    { label: '家电', data: [234, 278, 270, 190, 230] },
                    { label: '百货', data: [164, 178, 190, 135, 160] },
                    { label: '食品', data: [144, 198, 150, 235, 120] }
                ]
            },*/
            options1: {
                type: 'line',
                title: { text: '近期系统中的数据趋势图' },
                labels: ['1月', '2月', '3月', '4月', '5月','6月', '7月', '8月', '9月', '10月'],
                datasets: [
                    { label: '用户', data: [234, 278, 270, 190, 230,104, 278, 270, 190, 230] },
                    { label: '文章', data: [164, 178, 150, 135, 160,184, 27, 20, 90, 30] },
                    { label: '评论', data: [74, 118, 200, 235, 90,134, 28, 70, 10, 20] }
                ]
            },
            options: {
                type: 'pie',
                title: { text: '历史板块文章数量' },
                legend: { position: 'left' },
                bgColor: '#fbfbfb',
                labels: ['中','国','历','史','平','台'],
                datasets: [
                    { data: [1,2,3,4,5,6] }
                ]
            },
        };
    },
    components: { Schart },

    mounted() {
        this.getUserCount();
        this.getDynastyInfo();
        this.getArticleCount();
        this.getArticleCountEveryDynasty();
        this.getArticleCountWithNotPass();
        this.getCommentCountWithNotPass();
    },
    
    methods: {
        getUserCount(){
            this.getRequest("/admin/getUserCount","all").then(resp =>{
                if (resp){
                    this.userCount = resp.obj
                }
            })
        },
        getDynastyInfo(){
            this.getRequest("/admin/getDynastyInfo","all").then(resp =>{
                if (resp){
                    this.dynastyCount = resp.obj.length
                    this.options.labels = resp.obj
                }
            })
        },
        getArticleCount(){
            this.getRequest("/admin/getArticleCount","all").then(resp =>{
                if (resp){
                    this.articleCount = resp.obj
                }
            })
        },
        getArticleCountEveryDynasty(){
            this.getRequest("/admin/getArticleCountEveryDynasty","all").then(resp =>{
                if (resp){
                    this.options.datasets[0].data = resp.obj
                }
            })
        },
        getArticleCountWithNotPass(){
            this.getRequest("/admin/getArticleCountWithNotPass","all").then(resp =>{
                if (resp){
                    this.articlePercentage = (((this.articleCount - resp.obj) / this.articleCount)*100).toFixed(1)
                }
            })
        },
        getCommentCountWithNotPass(){
            this.getRequest("/admin/getCommentPassRatio","all").then(resp =>{
                if (resp){
                    this.commentPercentage = (resp.obj).toFixed(1)
                }
            })
        },
        
    }
};
</script>


<style scoped>
.el-row {
    margin-bottom: 20px;
}

.grid-content {
    display: flex;
    align-items: center;
    height: 100px;
}

.grid-cont-right {
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: #999;
}

.grid-num {
    font-size: 30px;
    font-weight: bold;
}

.grid-con-icon {
    font-size: 50px;
    width: 100px;
    height: 100px;
    text-align: center;
    line-height: 100px;
    color: #fff;
}

.grid-con-1 .grid-con-icon {
    background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
    color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
    background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
    color: rgb(45, 140, 240);
}

.grid-con-3 .grid-con-icon {
    background: rgb(242, 94, 67);
}

.grid-con-3 .grid-num {
    color: rgb(242, 94, 67);
}

.user-info {
    display: flex;
    align-items: center;
    padding-bottom: 20px;
    border-bottom: 2px solid #ccc;
    margin-bottom: 20px;
}

.user-avator {
    width: 120px;
    height: 120px;
    border-radius: 50%;
}

.user-info-cont {
    padding-left: 50px;
    flex: 1;
    font-size: 14px;
    color: #999;
}

.user-info-cont div:first-child {
    font-size: 30px;
    color: #222;
    margin-bottom: 6px;
}

.user-info-list {
    font-size: 14px;
    color: #999;
    line-height: 25px;
}

.user-info-list span {
    margin-left: 70px;
}

.mgb20 {
    margin-bottom: 20px;
}

.todo-item {
    font-size: 14px;
}

.todo-item-del {
    text-decoration: line-through;
    color: #999;
}

.schart {
    width: 100%;
    height: 300px;
}
</style>
