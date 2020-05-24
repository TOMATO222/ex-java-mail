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
              <el-table-column label="操作" width="400">
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
                    type="primary"
                    @click="changeRole(scope.$index, scope.row)">授权/撤销
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
        <el-button type="primary" @click="saveAddForm()">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="dialogEditVisible" @close="closeAddForm">

      <el-form :model="editForm" ref="addForm">
        <el-form-item label="昵称">
          <el-input v-model="editForm.nickName"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddgsVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveAddForm()">确 定</el-button>
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
                    nickName: ''
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
                this.title = '',
                    this.addForm = {
                        username: '',
                        password: '',
                    }
            },
            openEditForm(index,row) {
                this.dialogEditVisible = true;
                this.title = "编辑用户信息";
                this.editForm.nickName = row.nickName
            },
            closeEditForm() {
                this.$refs.addForm.resetFields();//element封装的方法,清空模态框的值
                this.title = '',
                    this.editForm = {
                        nickName: '',
                    }
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
