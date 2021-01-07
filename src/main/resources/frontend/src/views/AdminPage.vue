<template>
  <div class="container">
    <h1 class="text-center mt-5">Admin sida</h1>
    <form @submit.prevent="searchUsers()" class="mb-5">
      <div class="form-group">
        <label for="username">Sök användare</label>
        <input
          placeholder="användarnamn"
          type="text"
          class="form-control"
          id="username"
          v-model="username"
        />
      </div>
      <button type="submit" class="btn btn-dark">
        Sök användare
      </button>
    </form>
    <UserListItem @deletedUser="searchUsers()" v-for="user in this.users" :key="user.user_id" :user="user" class="mb-4 border-bottom border-dark"/>

  </div>
</template>
<script>

import UserListItem from '../components/UserListItem.vue';
export default {
  components: { UserListItem },
  name: "AdminPage",
  data() {
    return {
      username: "",
    };
  },
  computed: {
    user() {
      return this.$store.state.loggedInUser;
    },
    users(){
       return this.$store.state.users; 
    }
  },
  methods: {
    searchUsers(){
        this.$store.dispatch("searchUsers", this.username)

        console.log(this.users, "USERS IN ADMIN PAGE")
    }
  },
  async created() {
    if (!this.user) {
      await this.$store.dispatch("whoami");
      if (!this.user || !this.user.roles.includes("ADMIN")) {
        this.$router.push({ path: "/" });
      }
    }
  },
};
</script>
<style scoped></style>
