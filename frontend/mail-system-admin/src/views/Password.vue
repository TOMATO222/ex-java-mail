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
            <span>修改密码</span>
          </div>
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="旧密码">
              <el-input v-model="form.oldPassword"></el-input>
            </el-form-item>
            <el-form-item label="新密码">
              <el-input v-model="form.newPassword"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">修改</el-button>
              <el-button>取消</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
    import Header from "../components/Header";
    import Navbar from "../components/Navbar";
    export default {
        name: "Password",
        components: {Navbar, Header},
        data() {
            return {
                form: {
                    oldPassword: '',
                    newPassword: ''
                }
            }
        },
    }
</script>

<style scoped>
  .el-card{
    margin: 0 280px;
    width: 700px;
  }
</style>
