<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-tickets"></i> 文章详情页
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class='container'>
            <div class="plugins-tips">
                <div class="content-title">标题:《{{articleTitle}}》</div>
            </div>
            <mavon-editor ref='md'
                          v-model='editor.value'
                          v-bind='editor'/>
    
            <div class="plugins-tips">
                <div class="content-title">评论数: {{comments.length}}</div>
            </div>
            
            <div>
    
                <el-table :data="comments" :show-header="false" style="width: 100%">
                    <el-table-column>
                        <template slot-scope="scope">
                                    <span class="message-title"
                                          @click='handleRead(scope.row.commentId)'>
                                        {{scope.row.content}}
                                    </span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="createTime" width="150"></el-table-column>
                    <el-table-column width="100">
                        <template slot-scope="scope">
                            <el-button v-if='!scope.row.isAlive' type="primary" @click="handleNo(scope.row.commentId)">已审核</el-button>
                            <el-button v-else type="warning" @click="handleYes(scope.row.commentId)">未审核</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column width="100">
                        <template slot-scope="scope">
                            <el-button type="danger" @click="handleDelete(scope.row.commentId)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                
            </div>
        </div>
    </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

export default {
    name: 'ArticleDetailsPage',
    components:{mavonEditor},
    data(){
        return{
            articleTitle:'',
            editor:{
                value:'',
                toolbarsFlag: false,
                subfield: false,
                defaultOpen: 'preview'
            },
            comments:[],
        }
    },
    mounted() {
        this.$set(this.editor, 'ref', this.$refs.md)
        this.getArticleByArticleId(this.$route.query.articleId)
    },
    
    activated() {
        this.getArticleByArticleId(this.$route.query.articleId)
    },
    methods:{
        getArticleByArticleId(articleId){
            console.log(articleId)
            this.getRequest("/admin/getArticleByArticleId",articleId).then(resp =>{
                if (resp){
                    console.log(resp)
                    this.editor.value = resp.obj.articleContent
                    this.articleTitle = resp.obj.articleTitle
                    
                    // 获取文章评论
                    this.getCommentByArticleId(articleId)
                }
            })
        },
        getCommentByArticleId(articleId){
            this.getRequest("/admin/getCommentByArticleId",articleId).then(res => {
                if (res){
                    this.comments = res.obj
                }
            })
        },
        
        handleNo(commentId){
            this.$confirm('确定该评论审核不通过吗？', '提示', { type: 'warning' }).then(() => {
                this.handleAlive(commentId,1)
                
            }).catch(() => {});
        },
        handleYes(commentId){
            this.$confirm('确定该评论通过审核吗？', '提示', { type: 'warning' }).then(() => {
                this.handleAlive(commentId,0)
        
            }).catch(() => {});
        },
        
        handleDelete(commentId){
            this.$confirm('确定删除该评论吗？', '提示', { type: 'warning' }).then(() => {
                this.deleteRequest("/admin/deleteCommentByCommentId",commentId).then(resp =>{
                    if (resp){
                        this.$message.success('删除成功！');
                        this.getCommentByArticleId(this.$route.query.articleId)
                    }
                })
            }).catch(() => {});
        },
        handleAlive(commentId,num){
            const params = {
                commentId:commentId,
                setAlive: num,
            }
            this.putRequest(`/admin/updateAliveByCommentId/${JSON.stringify(params)}`).then(resp =>{
                if (resp){
                    this.$message.success('操作成功！');
                    this.getCommentByArticleId(this.$route.query.articleId)
                }
            })
        }
        
        
    }
};
</script>

<style scoped>
.content-title{
    font-weight: 400;
    font-size: 22px;
    color: #1f2f3d;
}
</style>
