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
              <el-input v-model="ruleForm.port.pop3"></el-input>
            </el-col>
            <el-col :span="2">SMTP</el-col>
            <el-col :span="10">
              <el-input v-model="ruleForm.port.smtp"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="服务启停" prop="name">
            <el-col :span="2">POP3</el-col>
            <el-col :span="10">
              <el-select v-model="ruleForm.server.pop3" placeholder="请选择">
                <el-option
                  v-for="item in ruleForm.options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="2">SMTP</el-col>
            <el-col :span="10">
              <el-select v-model="ruleForm.server.smtp" placeholder="请选择">
                <el-option
                  v-for="item in ruleForm.options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
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
    export default {
        name: "System",
        components: {Navbar, Header},
        data() {
            return {
                ruleForm: {
                    port:{
                      pop3:'110',
                      smtp:'25',
                    },
                    server:{
                      pop3:'',
                      smtp:'',
                    },
                    options:[{
                        value: 'enable',
                        label: '启动'
                    }, {
                        value: 'disable',
                        label: '关闭'
                    }],
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
                            url:'https:localhost:8080/api/v1/system/change',
                            data:JSON.stringify({
                                pop3Port: this.ruleForm.port.pop3,
                                pop3Status: this.ruleForm.server.pop3,
                                smtpPort: this.ruleForm.port.pop3,
                                smtpStatus: this.ruleForm.server.smtp,
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
            }
        }
    }
</script>

<style scoped>

</style>
