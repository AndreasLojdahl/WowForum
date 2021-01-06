import Vue from 'vue'
import VueRouter from 'vue-router'
import Forum from '../views/Forum.vue'
import Thread from '../views/Thread.vue'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import AdminPage from '../views/AdminPage'
import CreateAccount from '../views/CreateAccount.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "login",
    component: Login,
  },
  {
    path: "/admin",
    name: "admin",
    component: AdminPage,
  },
  {
    path: "/create-account",
    name: "create-account",
    component: CreateAccount,
  },
  {
    path: "/forum/:forum_id",
    name: "forum",
    component: Forum,
  },
  {
    path: "/forum/:forum_id/thread/:thread_id",
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
