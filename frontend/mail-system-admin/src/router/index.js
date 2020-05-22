import Vue from "vue";
import VueRouter from "vue-router";

import Main from '@/views/Main'
import Login from '@/views/Login'
import Client from '@/views/Client'
import System from '@/views/System'
import Server from '@/views/Server'

Vue.use(VueRouter);

export default new VueRouter({
  routes:[
    {
      name:'Login',
      path:'/',
      component: Login
    },
    {
      name:'Client',
      path:'/Client',
      component: Client
    },
    {
      name:'Main',
      path:'/Main',
      component: Main
    },
    {
      name:'Server',
      path:'/Server',
      component: Server
    },
    {
      name:'System',
      path:'/System',
      component: System
    },
  ]
})
