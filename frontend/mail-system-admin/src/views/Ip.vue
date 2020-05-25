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
            <el-button class="add-button" type="primary" style="float: left; padding: 0px 0;font-size: small" round
                       @click="openAddForm">新增地址
            </el-button>
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
              prop="updateTime"
              label="修改时间"
            >
            </el-table-column>
            <el-table-column label="操作" width="150">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="danger"
                  @click="deleteIp(scope.$index, scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-main>
    </el-container>


    <el-dialog :title="title" :visible.sync="dialogAddVisible" @close="closeAddForm">

      <el-form :model="addForm" ref="addForm">
        <el-form-item label="IP">
          <el-input v-model="addForm.ip"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-radio-group v-model="addForm.type">
            <el-radio :label="0">禁止</el-radio>
            <el-radio :label="1">允许</el-radio>
          </el-radio-group>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveAddForm">确 定</el-button>
      </div>
    </el-dialog>
  </el-container>

</template>

<script>
    import Header from "../components/Header";
    import Navbar from "../components/Navbar";
    import Global from "../components/Global";

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
                    updateTime: ''
                }],
                addForm: [{
                    ip: '',
                    type: '',
                }],
                dialogAddVisible: false,
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
                    if (response.data.code === 200) {
                        this.$message({
                            message: response.data.data,
                            type: 'success'
                        });
                        this.initData();
                    } else {
                        this.$message.error(response.data.message);
                    }
                })
            },
            openAddForm() {
                this.title = '添加规则';
                this.dialogAddVisible = true;
            },
            closeAddForm() {
                this.$refs.addForm.resetFields();//element封装的方法,清空模态框的值
                this.addForm = {
                    ip: '',
                    type: '',
                }
            },
            saveAddForm() {
                this.axios({
                    method: 'post',
                    url: Global.httpUrl + 'admin/ip/add',
                    data: JSON.stringify({
                        ip: this.addForm.ip,
                        type: this.addForm.type
                    }),
                    headers: {'Content-Type': 'application/json'}
                }).then(response => {
                    if (response.data.code === 200) {
                        this.$notify({
                            title: '成功',
                            message: response.data.data,
                            type: 'success'
                        });
                        this.dialogAddVisible = false;
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
            typeFormatter(row, column) {
                let type = row.type;
                return type === 0 ? "禁止访问" : "允许访问";
            }
        }
    }
</script>

<style scoped>
  .clearfix {
    height: 40px;
  }

  .add-button {
    width: 100px;
    height: 40px;
  }
</style>
