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
          stripe
          height="750"
          style="width: 100%">
          <el-table-column
            prop="id"
            label="邮件id"
            width="150">
          </el-table-column>
          <el-table-column
            prop="from"
            label="发信人"
            width="150">
          </el-table-column>
          <el-table-column
            prop="to"
            label="接收人"
            width="150">
          </el-table-column>
          <el-table-column
            prop="date"
            label="日期"
            width="150">
          </el-table-column>
          <el-table-column
            prop="subject"
            label="邮件标题"
            width="300"
          >
          </el-table-column>
          <el-table-column
            prop="content"
            label="邮件内容">
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
    import Header from "../components/Header";
    import Navbar from "../components/Navbar";
    export default {
        name: "Server",
        components: {Navbar, Header},
        beforeRouteEnter: (from, to, next) => {
            next(vm => {
                vm.initTable();
            });
        },
        data() {
            return {
                tableData:[{
                    id:"",
                    from:"",
                    to:"",
                    subject:"",
                    content:"",
                    date:"",
                }]
            }
        },
        methods:{
            initTable(){
                this.axios({
                    method:'get',
                    url:'http://192.168.43.1:8080/api/v1/admin/mailManage/list'
                }).then(response =>{
                    console.log(response);
                    this.tableData = response.data.data;
                    console.log(this.port.ports);
                }).catch(error => {
                    console.log(error);
                })
            }
        }
    }
</script>

<style scoped>
  .el-header{
    background: cornflowerblue;
  }

</style>
