<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-read"></i> 文章管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <el-tabs v-model="message">
                <el-tab-pane :label="`已审核(${done.length})`" name="first">
                    <el-table :data="done" :show-header="false" style="width: 100%">
                        <el-table-column>
                            <template slot-scope="scope">
                                <span class="message-title"
                                      @click='handleRead(scope.row.articleId)'>
                                    《 {{scope.row.articleTitle}} 》
                                </span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="createTime" width="150"></el-table-column>
                        <el-table-column width="120">
                            <template slot-scope="scope">
                                <el-button type="danger"
                                           @click="handleDel(scope.row.articleId)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <div class="handle-row">
                        <el-button type="danger"
                                   @click='handleBatchDeleteArticlesByAlive'>
                            删除全部文章
                        </el-button>
                    </div>
                </el-tab-pane>
                
                <el-tab-pane :label="`未审核(${notDone.length})`" name="second">
                    <template v-if="message === 'second'">
                        <el-table :data="notDone" :show-header="false" style="width: 100%">
                            <el-table-column>
                                <template slot-scope="scope">
                                    <span class="message-title"
                                          @click='handleRead(scope.row.articleId)'>
                                        《 {{scope.row.articleTitle}} 》
                                    </span>
                                </template>
                            </el-table-column>
                            <el-table-column prop="createTime" width="150"></el-table-column>
                            <el-table-column width="120">
                                <template slot-scope="scope">
                                    <el-button type="primary"
                                               @click="handleAudit(scope.row.articleId)">通过审核</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <div class="handle-row">
                            <el-button type="primary" @click='handleBatchAudit'>全部通过审核</el-button>
                        </div>
                    </template>
                </el-tab-pane>
                
            </el-tabs>
        </div>
    </div>
</template>

<script>
export default {
    name: 'ArticlePage',
    data() {
        return {
            message: 'first',
            showHeader: false,
            notDone: [],
            done: [],
        }
    },
    methods: {
        handleRead(articleId) {
            console.log("文章Id",articleId)
            this.$router.push({path:`/article/id`,query:{'articleId': articleId}})
            
        },
        handleDel(articleId) {
            console.log("删除的文章Id",articleId)
            this.$confirm('确定要删除该文章吗？', '提示', { type: 'warning' }).then(() => {
                this.deleteRequest("/admin/deleteArticleByArticleId",articleId).then(resp =>{
                    if (resp){
                        this.$message.success('删除成功！');
                        this.getAliveArticles();
                        this.getNotAliveArticles();
                    }
                })
            }).catch(() => {});
        },
        handleAudit(articleId){
            console.log("审核的文章Id",articleId)
            this.$confirm('确定此片文章通过审核吗？', '提示', { type: 'warning' }).then(() => {
                this.putRequest(`/admin/auditArticleByArticleId/${articleId}`).then(resp =>{
                    if (resp){
                        this.$message.success('审核通过！');
                        this.getAliveArticles();
                        this.getNotAliveArticles();
                    }
                })
            }).catch(() => {});
        },
    
        handleBatchDeleteArticlesByAlive(){
            this.$confirm('确定要删除该文章吗？', '提示', { type: 'error' }).then(() => {
                this.deleteRequest("/admin/handleBatchDeleteArticlesByAlive").then(resp =>{
                    if (resp){
                        this.getNotAliveArticles()
                        this.getAliveArticles()
                    }
                })
            }).catch(() => {});
        },
        handleBatchAudit(){
            this.$confirm('确定全部通过审核吗？', '提示', { type: 'warning' }).then(() => {
                this.putRequest("/admin/batchAudit/").then(resp =>{
                    if (resp){
                        this.$message.success('全部审核通过！');
                        this.getAliveArticles();
                        this.getNotAliveArticles();
                    }
                })
            }).catch(() => {});
        },
     
        getAliveArticles(){
            this.getRequest("/admin/getAliveArticles","all").then(resp =>{
                if (resp){
                    this.done = resp.obj
                }
            })
        },
        getNotAliveArticles(){
            this.getRequest("/admin/getNotAliveArticles","all").then(resp =>{
                if (resp){
                    this.notDone = resp.obj
                }
            })
        }
    },
    mounted() {
        this.getAliveArticles();
        this.getNotAliveArticles()
    }
    
}

</script>

<style>
.message-title{
    cursor: pointer;
}
.handle-row{
    margin-top: 30px;
}
</style>
