import Vue from 'vue'
import VueRouter from 'vue-router'
import Forum from '../views/Forum.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/:name',
    name: 'forum',
    component: Forum
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
