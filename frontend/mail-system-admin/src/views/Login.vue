<template>
  <el-container>
    <div class="home-box" style="border-radius: 4px">
      <el-header>
        <h1>登录</h1>
      </el-header>
      <el-main>
        <el-form :label-position="left" label-width="80px" :model="formLabelAlign">
          <el-form-item label="账号">
            <el-input v-model="formLabelAlign.username"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="formLabelAlign.password" type="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-col :span="12" :offset="4">
              <el-button type="primary" @click="submitForm('formLabelAlign')">登录</el-button>
            </el-col>
          </el-form-item>
        </el-form>
      </el-main>
    </div>

  </el-container>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                labelPosition: 'right',
                formLabelAlign: {
                    username: '',
                    password: '',
                }
            };
        },
        methods: {
            submitForm: function () {
                this.axios({
                    method: 'post',
                    url: 'http://localhost:8080/api/v1/admin/login',
                    data: JSON.stringify({
                        username: this.formLabelAlign.username,
                        password: this.formLabelAlign.password
                    }),
                    headers: {'Content-Type': 'application/json'}
                }).then(response =>{
                    console.log(response);
                    if(response.data.code === 200){
                        this.$router.push({path:'/main'})
                    }
                })

            }
        }
    }
</script>

<style scoped>
  .el-header, .el-footer {
    background: rgba(64, 158, 255, 0.5);
    color: #333;
    text-align: center;
    line-height: 50px;
  }

  .el-main {
    background: rgba(220, 223, 230, 0.8);
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .home-box {
    width: 600px;
    margin: 0 auto;
    padding: 200px 20px 20px 20px;
    display: block;
    color: #2c3e50;
  }

  .el-button {
    width: 100%;
  }

</style>
