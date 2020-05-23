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
          <el-table-column label="操作" width="200">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">状态</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="deleteUser(scope.$index, scope.row)">删除</el-button>
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
                    id: '-1',
                    username: '没有获取到后端数据',
                    nickName: '没有获取到后端数据'
                }]
            }
        },
        methods:{
            initData(){
                this.axios({
                    method:'get',
                    url:'http://localhost:8080/api/v1/admin/userManage/list'
                }).then(response =>{
                    console.log(response);
                    this.tableData = response.data.data;
                }).catch(error => {
                    console.log(error);
                })
            },
            deleteUser:function (row,index) {
                this.axios({
                    method:'post',
                    url:'http://localhost:8080/api/v1/admin/userManage/del',
                    data:Json.stringify({
                        id:this.tableData[index].id
                    }),
                    headers: {'Content-Type': 'application/json'}
                }).then(response => {
                    console.log(response)
                })
            }
        }
    }
</script>

<style scoped>

</style>
