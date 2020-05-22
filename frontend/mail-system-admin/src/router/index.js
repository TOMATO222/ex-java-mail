import Vue from "vue";
import VueRouter from "vue-router";

import Main from '@/views/Main'
import Login from '@/views/Login'

Vue.use(VueRouter);

export default new VueRouter({
  routes:[
    {
      name:'Login',
      path:'/Login',
      component: Login
    },
    {
      name:'Main',
      path:'/Main',
      component: Main
    },
  ]
})
