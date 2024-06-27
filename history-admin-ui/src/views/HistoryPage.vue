<template>
    <div>
        <div class='crumbs'>
            <el-breadcrumb separator='/'>
                <el-breadcrumb-item><i class='el-icon-lx-calendar'></i> 历史管理</el-breadcrumb-item>
                <el-breadcrumb-item>历史板块</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class='container'>
            <div class='handle-box'>
                <el-button
                    type='success'
                    icon='el-icon-delete'
                    class='handle-del mr10'
                    @click='addVisible = true'>添加
                </el-button>
                <el-input v-model='searchInfo'
                          placeholder='请输入搜索内容'
                          class='handle-input mr10'></el-input>
                <el-button type='primary'
                           icon='el-icon-search'
                           @click='handleSearch'>搜索
                </el-button>
            </div>
            <el-table :data='dynasties' border height='480' class='table'>
                <el-table-column prop='dynastyName' label='名称' width='55' align='center'></el-table-column>
                <el-table-column prop='dynastyDescribe' label='描述' align='center'></el-table-column>
                <el-table-column prop='dynastyPeriod' width='160' label='时间段' align='center'></el-table-column>
                <el-table-column label='封面' width='80' align='center'>
                    <template slot-scope='scope'>
                        <el-image class='table-td-thumb'
                                  :src='scope.row.dynastyCover'
                                  :preview-src-list='[scope.row.dynastyCover]'></el-image>
                    </template>
                </el-table-column>
                <el-table-column label='地图' width='80' align='center'>
                    <template slot-scope='scope'>
                        <el-image class='table-td-thumb'
                                  :src='scope.row.dynastyMap'
                                  :preview-src-list='[scope.row.dynastyMap]'></el-image>
                    </template>
                </el-table-column>
                <el-table-column label='状态' width='70' align='center'>
                    <template slot-scope='scope'>
                        <el-tag v-if='scope.row.isAlive === 0' type='success'>活跃</el-tag>
                        <el-tag v-else type='danger'>禁用</el-tag>
                    </template>
                </el-table-column>
                
                <el-table-column prop='createTime' width='154' label='添加时间' align='center'></el-table-column>
                <el-table-column label='操作' width='130' align='center'>
                    <template slot-scope='scope'>
                        <el-button type='text'
                                   icon='el-icon-edit'
                                   @click='clickEdit(scope.row)'>修改
                        </el-button>
                        <el-button type='text'
                                   icon='el-icon-delete'
                                   class='red'
                                   @click='deleteDynasty(scope.row.dynastyId)'>删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        
        <!-- 添加弹出框 -->
        <el-dialog title='添加' :visible.sync='addVisible' width='30%'>
            <el-form ref='form' :model='addForm' label-width='70px'>
                <el-form-item label='名称'>
                    <el-input v-model='addForm.dynastyName' placeholder='请输入名称'></el-input>
                </el-form-item>
                <el-form-item label='描述'>
                    <el-input type='textarea' v-model='addForm.dynastyDescribe' placeholder='请输入描述'></el-input>
                </el-form-item>
                <el-form-item label='时间段'>
                    <el-input v-model='addForm.dynastyPeriod' placeholder='请输入时间段'></el-input>
                </el-form-item>
                <el-form-item label='请依次 添加封面添加地图'>
                    <vue-upload-multiple-image
                        class='upload-vue'
                        @upload-success='uploadImageSuccess'
                        @before-remove='beforeRemove'
                        @edit-image='editImage'
                        @data-change='dataChange'
                        :max-image='2'
                        drag-text='点击添加封面'
                        browse-text='中国历史平台'
                        mark-is-primary-text='地图'
                        popup-text='第一张图片为封面，第二张图片为地图'
                        primary-text='中国历史平台-封面'
                        :data-images='images' />
                </el-form-item>
            </el-form>
            <span slot='footer' class='dialog-footer'>
                <el-button @click='addVisible = false'>取 消</el-button>
                <el-button type='primary' @click='addDynasty'>确 定</el-button>
            </span>
        </el-dialog>
        
        
        
        <!-- 编辑 弹出框 -->
        <el-dialog title='编辑' :visible.sync='editVisible' width='30%'>
            <el-form ref='form' v-if='editDynasty' :model='editDynasty' label-width='70px'>
                <el-form-item label='名称'>
                    <el-input v-model='editDynasty.dynastyName' placeholder='请输入名称'></el-input>
                </el-form-item>
                <el-form-item label='描述'>
                    <el-input type='textarea' v-model='editDynasty.dynastyDescribe' placeholder='请输入描述'></el-input>
                </el-form-item>
                <el-form-item label='时间段'>
                    <el-input v-model='editDynasty.dynastyPeriod' placeholder='请输入时间段'></el-input>
                </el-form-item>
                <el-form-item label='修改封面'>
                    <el-button @click='uploadCoverAndMap("cover")' size='small' type='primary' plain>点击上传封面</el-button>
                </el-form-item>
                <el-form-item label='修改地图'>
                    <el-button @click='uploadCoverAndMap("map")' size='small' type='primary' plain>点击上传地图</el-button>
                </el-form-item>
            </el-form>
            <span slot='footer' class='dialog-footer'>
                <el-button @click='editVisible = false'>取 消</el-button>
                <el-button type='primary' @click='updateDynasty'>确 定</el-button>
            </span>
        </el-dialog>
        
        <!-- 编辑封面、地图 -->
        <el-dialog title='上传' :visible.sync='uploadVisible' width='31%'>
            <el-upload
                ref='coverAndMap'
                action="#"
                accept="'image/*'"
                list-type="picture-card"
                :auto-upload='false'
                :limit="1"
                :show-file-list="true"
                :on-exceed="handleExceed"
                :on-preview="handlePreview"
                :on-change="handleChange"
                :handle-remove="handleHideRemove">
                <i class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt="">
            </el-dialog>
            
            <span slot='footer' class='dialog-footer'>
                <el-button @click='uploadVisible = false'>取 消</el-button>
                <el-button type='primary' @click='confirmUploadImg'>确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import VueUploadMultipleImage from 'vue-upload-multiple-image';

export default {
    name: 'HistoryPage',
    components: { VueUploadMultipleImage },
    data() {
        return {
            searchInfo: '',
            dynasties: [],
            addVisible: false,
            editVisible: false,
            addForm: {},
            images: [],
            addImageUrl: [],
            
            editDynasty: null, // 用户修改朝代的信息
            
            uploadVisible: false,
            imageFile: {},
            dialogImageUrl: '',
            dialogVisible: false,
            disabled: false,
            
            uploadType:'',
            
        };
    },
    mounted() {
        this.getAllDynasties();
        this.addVisible = false;
        this.editVisible = false;
        this.uploadVisible = false;
        this.dialogVisible = false
    },
    methods: {
        getAllDynasties() {
            this.getRequest('/common/getDynasties', 'all').then(resp => {
                if (resp) {
                    this.dynasties = resp.obj;
                }
            });
        },
        addDynasty() {
            const dynasty = {
                dynastyName: this.addForm.dynastyName,
                dynastyDescribe: this.addForm.dynastyDescribe,
                dynastyPeriod: this.addForm.dynastyPeriod,
                dynastyCover: this.addImageUrl[0],
                dynastyMap: this.addImageUrl[1]
            };
            console.log('dynasty', dynasty);
            // 发送请求
            this.postRequest('/admin/daaDynasty', dynasty).then(resp => {
                if (resp) {
                    this.getAllDynasties();
                    this.$message.success('添加成功！');
                    this.addVisible = false;
                }
            });
            
            
        },
        handleSearch() {
            if (this.searchInfo === '') {
                this.getAllDynasties();
            } else {
                this.getRequest('/admin/getDynastyByKeywords', this.searchInfo).then(resp => {
                    if (resp) {
                        this.dynasties = resp.obj;
                    }
                });
            }
        },
        
        clickEdit(dynasty) {
            console.log('编辑前的数据', dynasty);
            this.editVisible = true;
            this.editDynasty = dynasty;
        },
        updateDynasty() {
            console.log('修改之后的数据', this.editDynasty);
            this.$confirm('确定保存修改内容吗？', '提示', { type: 'warning' }).then(() => {
                this.postRequest("/admin/updateDynasty",this.editDynasty).then(resp =>{
                    if (resp){
                        this.editVisible = false;
                        this.$message.success("编辑成功！")
                        this.getAllDynasties()
                    }
                })
            }).catch(() => {
            });
        },
        
        deleteDynasty(dynastyId) {
            this.$confirm('确定删除吗？', '提示', { type: 'warning' }).then(() => {
                this.deleteRequest('/admin/deleteDynastyByDynastyId', dynastyId).then(resp => {
                    if (resp) {
                        this.getAllDynasties();
                        this.$message.success('删除成功！');
                    }
                });
            }).catch(() => {
            });
        },
        
        uploadImageSuccess(formData, index, fileList) {
            if (fileList.length === 2) {
                console.log('fileList=>', fileList);
                let formdata = new FormData();
                formdata.append('imageCover', fileList[0].path);
                formdata.append('imageMap', fileList[1].path);
                //console.log(formdata)
                this.postRequest('/admin/addCoverAndMap', formdata).then(resp => {
                    if (resp) {
                        this.addImageUrl = resp;
                    }
                });
            }
        },
        beforeRemove(index, done, fileList) {
            console.log('index', index, fileList);
            var r = confirm('remove image');
            if (r == true) {
                done();
            } else {
            }
        },
        editImage(formData, index, fileList) {
            console.log('edit data', formData, index, fileList);
        },
        dataChange(data) {
            console.log(data);
        },
    
        uploadCoverAndMap(type){
            this.uploadVisible = true
            // 设置点击类型，封面还是地图
            this.uploadType = type
        },
        confirmUploadImg(){
            let formdata = new FormData();
            const image = this.imageFile.raw
            console.log(this.imageFile.raw)
            if (this.uploadType === "cover"){
                formdata.append('imageCover', image);
                this.postRequest("/admin/editCover",formdata).then(resp =>{
                    if (resp){
                        console.log(resp)
                        this.uploadVisible = false
                        this.editDynasty.dynastyCover = resp
                        // 清空upload框
                        this.$refs.coverAndMap.clearFiles()
                    }
                })
            }
            if(this.uploadType === "map"){
                formdata.append('imageMap', image);
                this.postRequest("/admin/editMap",formdata).then(resp =>{
                    if (resp){
                        console.log(resp)
                        this.uploadVisible = false
                        this.editDynasty.dynastyMap = resp
                        this.$refs.coverAndMap.clearFiles()
                    }
                })
            }
        },
        //============================== 修改上传框 ============================
        handleChange(file,fileList) {
            // file指的就是选择的文件对象
            this.imageFile = file;
        },
        handlePreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        handleHideRemove(file,fileList){},
        handleExceed(files, fileList) {
            this.$message.warning("只能上传一张图片！");
        },
        
        
    }
};
</script>

<style>
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

.image-container[data-v-10e59822] {
    background-color: unset !important;
    width: unset !important;
    /*height: 170px!important;*/
}

/*!* 修改信息 上传框样式*!
.upload-demo .el-upload--text {
    height: unset;
    width: 100%;
}

.el-upload {
    text-align: left;
}*/
</style>
