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
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: '',
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
                    name: [
                        { required: true, message: '请输入活动名称', trigger: 'blur' },
                        { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                    ],
                    region: [
                        { required: true, message: '请选择活动区域', trigger: 'change' }
                    ],
                    date1: [
                        { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
                    ],
                    date2: [
                        { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
                    ],
                    type: [
                        { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
                    ],
                    resource: [
                        { required: true, message: '请选择活动资源', trigger: 'change' }
                    ],
                    desc: [
                        { required: true, message: '请填写活动形式', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
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
