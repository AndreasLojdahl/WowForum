import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    forums: null,
    forum: null,
    thread: null,
    loggedInUser: null,
    users: null,
    searchedUsername: null
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
    addNewThread(state, data) {
      state.forum.threads.push(data);
    },
    addNewMessage(state, data) {
      state.thread.messages.push(data);
    },
    setloggedInUser(state, data) {
      state.loggedInUser = data;
    },
    setUsers(state, data) {
      state.users = data;
    },
    setSearchedUsername(state, data) {
      state.searchedUsername = data;
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
    async fetchThread({ commit }, thread_id) {
      const rawThread = await fetch(
        `/api/v1/forums/threads/${thread_id}`
      );
      const thread = await rawThread.json();
      commit("setThread", thread);
    },
    async whoami({ commit }) {
      let user = await fetch("/auth/whoami");
      try {
        user = await user.json();
        commit("setloggedInUser", user);
      } catch {
        console.log("Not authenticated");
      }
    },
    async searchUsers({ commit }, searchedUsername) {
      commit("setSearchedUsername", searchedUsername)
      let users = await fetch(`/api/v1/users?username=${searchedUsername}`, {
        method: "GET",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(users),
      });
      users = await users.json();
      commit("setUsers", users);
    },
  },
  modules: {},
});
