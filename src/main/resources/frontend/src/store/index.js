import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    forums: null,
    forum: null,
    thread: null,
    loggedInUser: null
  },
  mutations: {
    setForums(state, data) {
      state.forums = data;
    },
    setForum(state, data) {
      state.forum = data;
    },
    setThread(state, data) {
      state.thread = data;
    },
    setloggedInUser(state, data) {
      state.loggedInUser = data;
    },
  },
  actions: {
    async fetchAllForums({ commit }) {
      const rawForums = await fetch("/api/v1/forums");
      const forums = await rawForums.json();
      commit("setForums", forums);
    },
    async fetchForum({ commit }, forum_id) {
      const rawForum = await fetch(`/api/v1/forums/${forum_id}`);
      const forum = await rawForum.json();
      commit("setForum", forum);
    },
    async fetchThread({ commit }, obj) {
      const rawThread = await fetch(`/api/v1/forums/${obj.forumId}/threads/${obj.threadId}`);
      const thread = await rawThread.json();
      console.log(thread, "FETCHEDTHREAD")
      commit("setThread", thread);
    },
    async whoami({ commit }) {
      let user = await fetch("/auth/whoami");
      try {
        user = await user.json();
        commit("setloggedInUser", user);
        console.log(this.state.loggedInUser, " USSSEEEERR");
      } catch {
        console.log("Not authenticated");
      }
    },
  },
  modules: {},
});
