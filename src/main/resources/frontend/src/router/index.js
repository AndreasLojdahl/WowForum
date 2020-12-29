import Vue from 'vue'
import VueRouter from 'vue-router'
import Forum from '../views/Forum.vue'
import Thread from '../views/Thread.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/:forum',
    name: 'forum',
    component: Forum
  },
  {
    path: '/:forum/:id',
    name: 'thread',
    component: Thread
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
