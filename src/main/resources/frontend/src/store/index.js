import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    forums: null,
    forum: null
  },
  mutations: {
    setForums(state, data){
      state.forums = data;
    },
    setForum(state, data){
      state.forum = data;
    }
  },
  actions: {
    async fetchAllForums({ commit }){
      const rawForums = await fetch("/api/v1/forums")
      const forums = await rawForums.json();
      commit("setForums", forums)
    },
    async fetchForum({ commit }, name){
      const rawForum = await fetch(`/api/v1/forums/${name}`)
      const forum = await rawForum.json();
      commit("setForum", forum)
    }
  },
  modules: {
  }
})
