import Vue from 'vue'
import VueRouter from 'vue-router'
import Forum from '../views/Forum.vue'
import Thread from '../views/Thread.vue'
import Login from '../views/Login.vue'
import CreateAccount from '../views/CreateAccount.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: "/login",
    name: "login",
    component: Login,
  },
  {
    path: "/create-account",
    name: "create-account",
    component: CreateAccount,
  },
  {
    path: "/:forum",
    name: "forum",
    component: Forum,
  },
  {
    path: "/:forum/:id",
    name: "thread",
    component: Thread,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
