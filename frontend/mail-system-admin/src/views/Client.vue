<template>
  <el-container>
    <el-header>
      <Header/>
    </el-header>
    <el-container>
      <el-aside>
        <Navbar/>
      </el-aside>
      <el-main>
        <el-table
          :data="tableData"
          border
          height="750"
          style="width: 100%">
          <el-table-column
            prop="id"
            label="用户id"
            width="450">
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
          <el-table-column
            prop="state"
            label="状态"
            width="450"
          >
          </el-table-column>
          <el-table-column label="操作" width="200">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="changeState(scope.$index, scope.row)">状态
              </el-button>
              <el-button
                size="mini"
                type="danger"
                @click="deleteUser(scope.$index, scope.row)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
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
                            title: '成功',
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
                            title: '成功',
                            message: response.data.message,
                        });
                    }
                })
                console.log(row.id)
            },
        }
    }
</script>

<style scoped>

</style>
