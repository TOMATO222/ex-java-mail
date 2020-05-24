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
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="端口号设置" prop="name">
            <el-col :span="2">POP3</el-col>
            <el-col :span="10">
              <el-input v-model="ruleForm.pop3Port"></el-input>
            </el-col>
            <el-col :span="2">SMTP</el-col>
            <el-col :span="10">
              <el-input v-model="ruleForm.smtpPort"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="服务启停" prop="name">
            <el-col :span="2">POP3</el-col>
            <el-col :span="10">
              <el-radio v-model="ruleForm.pop3Status" label="enable">开启</el-radio>
              <el-radio v-model="ruleForm.pop3Status" label="disable">关闭</el-radio>
            </el-col>
            <el-col :span="2">SMTP</el-col>
            <el-col :span="10">
              <el-radio v-model="ruleForm.smtpStatus" label="enable">开启</el-radio>
              <el-radio v-model="ruleForm.smtpStatus" label="disable">关闭</el-radio>
            </el-col>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">保存</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
    import Header from "../components/Header";
    import Navbar from "../components/Navbar";
    import Global from "../components/Global";
    export default {
        name: "System",
        components: {Navbar, Header},
        beforeRouteEnter: (from, to, next) => {
            next(vm => {
                vm.initData();
            });
        },
        data() {
            return {
                ruleForm: {
                    pop3Port: '',
                    pop3Status: '',
                    smtpPort: '',
                    smtpStatus: '',
                    value: ''
                },
                rules: {

                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                        this.axios({
                            method:'post',
                            url: Global.httpUrl + 'system/change',
                            data:JSON.stringify({
                                pop3Port: this.ruleForm.pop3Port,
                                pop3Status: this.ruleForm.pop3Status,
                                smtpPort: this.ruleForm.smtpPort,
                                smtpStatus: this.ruleForm.smtpStatus,
                            }),
                            headers: {'Content-Type': 'application/json'},
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            initData(){
                this.axios({
                    method:'get',
                    url: Global.httpUrl + 'system/settings',
                    headers: {'Content-Type': 'application/json'},
                }).then(response =>{
                    this.ruleForm = response.data.data
                })
            }
        }
    }
</script>

<style scoped>

</style>
