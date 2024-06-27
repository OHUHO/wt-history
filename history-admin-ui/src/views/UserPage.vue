<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-friend"></i> 用户管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button
                    type="primary"
                    icon="el-icon-delete"
                    class="handle-del mr10"
                    @click="delAllSelection"
                >批量删除</el-button>
                <el-input v-model="searchInfo" placeholder="请输入关键词" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
            </div>
            <el-table
                :data="users"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
            >
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="userId" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="username" width='110' label="手机号" align='center'></el-table-column>
                <el-table-column prop="nickname" width='120' label="昵称" align='center'></el-table-column>
                <el-table-column prop="password" label="密码" align='center'></el-table-column>
                <el-table-column label="头像" width='80' align="center">
                    <template slot-scope="scope">
                        <el-image class="table-td-thumb" :src="scope.row.avatar" :preview-src-list="[scope.row.avatar]"></el-image>
                    </template>
                </el-table-column>
                <el-table-column label="状态" width='80' align="center">
                    <template slot-scope="scope">
                        <el-tag v-if='scope.row.isAlive === 0' type='success'>活跃</el-tag>
                        <el-tag v-else type='danger'>禁用</el-tag>
                    </template>
                </el-table-column>

                <el-table-column prop="createTime" width='154' label="注册时间" align='center'></el-table-column>
                <el-table-column label="操作" width="150" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-warning-outline"  @click="handleUpdateAlive(scope.$index, scope.row.userId)">修改状态</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row.userId)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total,sizes,prev, pager, next"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :page-sizes="[20,40,60,80,100]"
                    :current-page="pageParams.pageIndex"
                    :page-size="pageParams.pageSize"
                    :total="total"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <!--<el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="用户名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>-->
        
    </div>
</template>

<script>
export default {
    name: 'UserPage',
    data() {
        return {
            searchInfo:'',
            users: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            pageParams:{
                pageIndex:1,
                pageSize:15,
            },
            total: 0,
            form: {},
            idx: -1,
            id: -1
        };
    },
    mounted() {
        this.getAllUserPage();
    },
    methods: {
        getAllUserPage() {
            const params = {
                pageIndex: this.pageParams.pageIndex,
                pageSize: this.pageParams.pageSize,
                searchInfo:this.searchInfo
            }
            //console.log(JSON.stringify(params))
            this.getRequest("/admin/getAllUser",JSON.stringify(params)).then(resp => {
                console.log(resp)
                if (resp){
                    this.total = resp.obj.total
                    this.users = resp.obj.list;
                }
            })
        },
        // 触发搜索按钮
        handleSearch() {
            this.getAllUserPage()
        },
        // 删除操作
        handleDelete(index, userId) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', { type: 'warning' }).then(() => {
                this.deleteRequest("/admin/deleteUserByUserId",userId).then(resp =>{
                    if (resp){
                        this.$message.success('删除成功');
                        this.getAllUserPage()
                    }
                })
            }).catch(() => {});
        },
        // 更新用户状态
        handleUpdateAlive(index,userId){
            this.$confirm('确定要修改状态吗？', '提示', { type: 'warning' }).then(() => {
                this.putRequest(`/admin/updateUserAliveByUserId/${userId}`).then(resp =>{
                    if (resp){
                        this.$message.success('修改成功');
                        this.getAllUserPage()
                    }
                })
            }).catch(() => {});
        },
        
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        delAllSelection() {
            const  users = this.multipleSelection
            const userIds = []
            users.forEach(function(user){
                userIds.push(user.userId)
            })
            // 二次确认删除
            this.$confirm('确定要删除选中的用户吗？', '提示', { type: 'warning' }).then(() => {
                this.deleteRequest("/admin/batchDeleteUsersByUserIds",userIds).then(resp =>{
                    if (resp){
                        this.$message.success('修改成功');
                        this.getAllUserPage()
                    }
                })
            }).catch(() => {});
        },
        
        handleSizeChange(val){
            console.log("每页页",val,"条")
            this.pageParams.pageSize = val
            this.getAllUserPage();
        },
        // 分页导航
        handleCurrentChange(val) {
            console.log("当前页",val)
            //this.$set(this.query, 'pageIndex', val);
            this.pageParams.pageIndex = val
            this.getAllUserPage();
        }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
</style>
