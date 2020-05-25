<template>
  <div>
    <el-container>
      <el-header>
        <Header/>
      </el-header>
      <el-container>
        <el-aside>
          <Navbar/>
        </el-aside>
        <el-main>
          <el-card>
            <div slot="header" class="clearfix">
              <el-button class="add-button" type="primary" style="float: left; padding: 0px 0;font-size: small"
                         @click="openAddForm" round>新增用户
              </el-button>
            </div>
            <el-table
              :data="tableData"
              border
              height="750"
              style="width: 100%">
              <el-table-column
                prop="state"
                label="状态"
                width="150"
                :formatter="statesFormatter"
              >
              </el-table-column>
              <el-table-column
                prop="id"
                label="用户id"
                width="200">
              </el-table-column>
              <el-table-column
                prop="username"
                label="用户账号"
                width="450">
              </el-table-column>
              <el-table-column
                prop="nickName"
                label="用户昵称"
              >
              </el-table-column>
              <el-table-column label="操作" width="300">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="changeState(scope.$index, scope.row)">锁定\解锁
                  </el-button>
                  <el-button
                    size="mini"
                    type="primary"
                    @click="openEditForm(scope.$index, scope.row)">编辑
                  </el-button>
                  <el-button
                    size="mini"
                    type="danger"
                    @click="deleteUser(scope.$index, scope.row)">删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-main>
      </el-container>
    </el-container>
    <el-dialog :title="title" :visible.sync="dialogAddgsVisible" @close="closeAddForm">

      <el-form :model="addForm" ref="addForm">
        <el-form-item label="用户名">
          <el-input v-model="addForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="addForm.password"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddgsVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveAddForm">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="dialogEditVisible" @close="closeAddForm">

      <el-form :model="editForm" ref="addForm">
        <el-form-item label="昵称">
          <el-input v-model="editForm.nickName"></el-input>
        </el-form-item>
        <el-form-item label="角色">
          <el-radio-group v-model="editForm.roleType">
            <el-radio :label="0">普通用户</el-radio>
            <el-radio :label="1">管理员</el-radio>
          </el-radio-group>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddgsVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEditForm()">确 定</el-button>
      </div>
    </el-dialog>
  </div>


</template>

<script>
    import Navbar from "../components/Navbar";
    import Header from "../components/Header";
    import Global from "../components/Global";

    export default {
        name: "Client",
        components: {Header, Navbar},
        beforeRouteEnter: (from, to, next) => {
            next(vm => {
                vm.initData();
            });
        },
        data() {
            return {
                tableData: [{
                    id: '',
                    username: '',
                    nickName: '',
                    state: ''
                }],
                addForm: {
                    username: '',
                    password: ''
                },
                dialogAddgsVisible: false,
                dialogEditVisible: false,
                editForm: {
                    nickName: '',
                    id: '',
                    roleType: ''
                }
            }
        },
        methods: {
            initData() {
                this.axios({
                    method: 'get',
                    url: Global.httpUrl + 'admin/userManage/list'
                }).then(response => {
                    console.log(response);
                    this.tableData = response.data.data;
                }).catch(error => {
                    console.log(error);
                })
            },
            deleteUser(index, row) {
                this.axios({
                    method: 'post',
                    url: Global.httpUrl + 'admin/userManage/del',
                    data: JSON.stringify({
                        userId: row.id
                    }),
                    headers: {'Content-Type': 'application/json'}
                }).then(response => {
                    if (response.data.code === 200) {
                        this.$notify({
                            title: '成功',
                            message: response.data.data,
                            type: 'success'
                        });
                        this.initData();
                    } else {
                        this.$notify.error({
                            title: '失败',
                            message: response.data.message,
                        });
                    }
                })
            },
            changeState(index, row) {
                this.axios({
                    method: 'post',
                    url: Global.httpUrl + 'admin/userManage/changeState',
                    data: JSON.stringify({
                        userId: row.id
                    }),
                    headers: {'Content-Type': 'application/json'}
                }).then(response => {
                    if (response.data.code === 200) {
                        this.$notify({
                            title: '成功',
                            message: response.data.data,
                            type: 'success'
                        });
                        this.initData();
                    } else {
                        this.$notify.error({
                            title: '失败',
                            message: response.data.message,
                        });
                    }
                });
                console.log(row.id)
            },
            statesFormatter(row, column) {
                let state = row.state;
                return state === 0 ? "正常" : "锁定";
            },
            openAddForm() {
                this.dialogAddgsVisible = true;
                this.title = "新增用户"
            },
            closeAddForm() {
                this.$refs.addForm.resetFields();//element封装的方法,清空模态框的值
                this.title = '';
                this.addForm = {
                    username: '',
                    password: '',
                }
            },
            openEditForm(index, row) {
                console.log(Global.token);
                this.dialogEditVisible = true;
                this.title = "编辑用户信息";
                this.editForm.nickName = row.nickName;
                this.editForm.id = row.id;
                this.axios({
                    method: 'post',
                    url: Global.httpUrl + 'admin/info/role',
                    data: JSON.stringify({
                        userId: this.editForm.id,
                    }),
                    headers: {'Content-Type': 'application/json'}
                }).then(response =>{
                    if (response.data.code === 200) {
                        this.editForm.roleType = response.data.data;
                    } else {
                        this.$notify.error({
                            title: '失败',
                            message: response.data.message,
                        });
                        this.editForm.roleType = 0
                    }
                })
            },
            closeEditForm() {
                this.$refs.addForm.resetFields();//element封装的方法,清空模态框的值
                this.title = '';
                this.editForm = {
                    nickName: '',
                }
            },
            saveAddForm() {
                this.axios({
                    method: 'post',
                    url: Global.httpUrl + 'admin/register',
                    data: JSON.stringify({
                        username: this.addForm.username,
                        password: this.addForm.password
                    }),
                    headers: {'Content-Type': 'application/json'}
                }).then(response => {
                    if (response.data.code === 200) {
                        this.$notify({
                            title: '成功',
                            message: response.data.data,
                            type: 'success'
                        });
                        this.dialogAddgsVisible = false;
                        this.closeAddForm();
                        this.initData();
                    } else {
                        this.$notify.error({
                            title: '失败',
                            message: response.data.message,
                        });
                    }
                })
            },
            saveEditForm() {
                this.axios({
                    method: 'post',
                    url: Global.httpUrl + 'admin/userManage/info',
                    data: JSON.stringify({
                        userId: this.editForm.id,
                        nickName: this.editForm.nickName,
                        roleType: this.editForm.roleType
                    }),
                    headers: {'Content-Type': 'application/json'}
                }).then(response => {
                    if (response.data.code === 200) {
                        this.$notify({
                            title: '成功',
                            message: response.data.data,
                            type: 'success'
                        });
                        this.dialogEditVisible = false;
                        this.closeEditForm();
                        this.initData();
                    } else {
                        this.$notify.error({
                            title: '失败',
                            message: response.data.message,
                        });
                    }
                })
            },
        }
    }
</script>

<style scoped>
  .clearfix {
    height: 30px;
  }

  .add-button {
    width: 100px;
    height: 40px;
  }


</style>
