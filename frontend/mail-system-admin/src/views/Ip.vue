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
        <el-card>
          <div slot="header" class="clearfix">
            <el-button class="add-button" type="primary" style="float: left; padding: 0px 0;font-size: small" round>新增地址</el-button>
            <el-button class="add-button" type="danger" style="float: left; padding: 0px 0;font-size: small" round>删除地址</el-button>
          </div>
          <el-table
            :data="tableData"
            border
            height="750"
            style="width: 100%">
            <el-table-column
              prop="ip"
              label="IP地址"

            >
            </el-table-column>
            <el-table-column
              prop="type"
              label="类型"
              :formatter="typeFormatter">
            </el-table-column>
            <el-table-column
              prop="date"
              label="修改时间"
            >
            </el-table-column>
          </el-table>
        </el-card>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
    import Header from "../components/Header";
    import Navbar from "../components/Navbar";

    export default {
        name: "Ip",
        components: {Navbar, Header},
        beforeRouteEnter: (from, to, next) => {
            next(vm => {
                vm.initData();
            });
        },
        data() {
            return {
                tableData: [{
                    ip: '',
                    type: '',
                    date: ''
                }],
            }
        },
        methods: {
            initData() {
                this.axios({
                    method: 'get',
                    url: Global.httpUrl + 'admin/ip/list',
                    headers: {'Content-Type': 'application/json'}
                }).then(response => {
                    console.log(response);
                    this.tableData = response.data.data;
                }).catch(error => {
                    console.log(error);
                })
            },
            deleteIp(index, row) {
                this.axios({
                    method: 'post',
                    url: Global.httpUrl + 'admin/ip/del',
                    data: JSON.stringify({
                        ip: row.ip
                    }),
                    headers: {'Content-Type': 'application/json'}
                }).then(response => {
                    if(response.data.code === 200){
                        this.$message({
                            message: response.data.data,
                            type: 'success'
                        });
                    }else {
                        this.$message.error(response.data.message);
                    }
                })
            },
        }
    }
</script>

<style scoped>
  .clearfix{
    height: 40px;
  }
  .add-button{
    width: 100px;
    height: 40px;
  }
</style>
