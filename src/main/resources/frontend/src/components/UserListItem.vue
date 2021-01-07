<template>
  <div class="container">
    <div class="row">
      <div class="col-3">
        <div class="col font-bold font-size-username">{{ user.username }}</div>
        <div class="col font-oblique">{{ user.email }}</div>
      </div>
      <div class="col-6">
      <div class="row text-center">
        <label class="col font-bold">User
        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" :class="{isAuthorized: user.roles.includes('USER') || user.roles.includes('ADMIN')}" class="bi bi-check2-square col color-red icon" viewBox="0 0 16 16">
            <path d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5H3z"/>
            <path d="M8.354 10.354l7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"/>
        </svg>
        </label>

        <label class="col font-bold">Moderator
        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" :class="{isAuthorized: user.roles.includes('MODERATOR') || user.roles.includes('ADMIN')}" class="bi bi-check2-square col color-red icon" viewBox="0 0 16 16">
            <path d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5H3z"/>
            <path d="M8.354 10.354l7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"/>
        </svg>
        </label>

        <label class="col font-bold">Admin
        <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" :class="{isAuthorized: user.roles.includes('ADMIN')}" class="bi bi-check2-square col color-red icon" viewBox="0 0 16 16">
            <path d="M3 14.5A1.5 1.5 0 0 1 1.5 13V3A1.5 1.5 0 0 1 3 1.5h8a.5.5 0 0 1 0 1H3a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h10a.5.5 0 0 0 .5-.5V8a.5.5 0 0 1 1 0v5a1.5 1.5 0 0 1-1.5 1.5H3z"/>
            <path d="M8.354 10.354l7-7a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0z"/>
        </svg>
        </label>
      </div>
      </div>
      <div class="col-3 ">

      <div class="row d-flex justify-content-end ">

      <select  v-if="user.roles.includes('MODERATOR') " multiple class="custom-select col" id="selectedForum">
        <option selected>Moderator för...</option>
        <option value="forum" v-for="forum in this.forums" :key="forum.forum_id">{{forum.name}} forum</option>
      </select>

      <div v-if="!user.roles.includes('ADMIN')" class="col d-flex justify-content-end mx-auto">
        <svg @click="deleteUser()" xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-trash icon-trash" viewBox="0 0 16 16">
            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
        </svg>
      </div>
      </div>

      </div>

    </div>
  </div>
</template>
<script>
export default {
  name: "UserListItem",
  props: {
    user: Object,
  },
  computed: {
    forums(){
      return this.$store.state.forums
    }
  },
  methods: {
    async deleteUser(){
         const response = await fetch(
        `/api/v1/users/${this.user.user_id}`,
        {
          method: "DELETE",
        }
      );
      if (response.status === 204) {
         this.$emit('deletedUser')
      } 
    }
  }
};
</script>
<style scoped>
.font-bold{
    font-weight: bold;
}
.font-size-username{
    font-size: 1.3rem;
}
.font-oblique{
    font-style: oblique;
}
.color-red{
    color: red;
}
.icon:hover{
    cursor: pointer;
    color: green;
}
.icon-trash:hover{
    color: red;
    cursor: pointer;
}
.icon.isAuthorized:hover{
    color: red;
    cursor: pointer;
}
.isAuthorized{
color:green
}
</style>
