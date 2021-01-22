<template>
  <div v-if="user ? user.roles.includes('ADMIN') : false" class="container">
    <h1 class="text-center mt-5">Admin sida</h1>
    <form @submit.prevent="searchUsers()" class="mb-5">
      <div class="form-group">
        <label for="username">Sök användare</label>
        <input
          placeholder="användarnamn"
          type="text"
          class="form-control"
          id="username"
          v-model="searchedUsername"
        />
      </div>
      <button type="submit" class="btn btn-dark">
        Sök användare
      </button>
    </form>
    <UserListItem
      @deletedUser="searchUsers()"
      v-for="user in this.users"
      :key="user.user_id"
      :user="user"
      class="mb-4 border-bottom border-dark"
    />
  </div>
</template>
<script>
import UserListItem from "../components/UserListItem.vue";
export default {
  components: { UserListItem },
  name: "AdminPage",
  data() {
    return {
      searchedUsername: "",
    };
  },
  computed: {
    user() {
      return this.$store.state.loggedInUser;
    },
    users() {
      return this.$store.state.users;
    },
  },
  methods: {
    searchUsers() {
      this.$store.dispatch("searchUsers", this.searchedUsername);
      this.searchedUsername = "";
    },
  },
  async created() {
    if (!this.user) {
      await this.$store.dispatch("whoami");
      if (!this.user || !this.user.roles.includes("ADMIN")) {
        this.$router.push({ path: "/" });
      }
    }
  },
  destroyed() {
    this.$store.commit("setUsers", null);
    console.log("DESTROY", this.$store.state.searchedUsername);
  },
};
</script>
<style scoped></style>
