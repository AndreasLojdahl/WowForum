<template>
  <div class="container">
    <h3 class="mt-5 text-center">Skapa Konto</h3>
    <h4 v-if="error" class="text-center text-danger mt-5">{{error}}</h4>
    <form @submit.prevent="createUser" class="mt-5 col-6 mx-auto">
      <div class="form-group">
        <label for="title">Användarnamn</label>
        <input type="text" class="form-control" id="username" required />
      </div>
      <div class="form-group">
        <label for="title">e-mail</label>
        <input type="email" class="form-control" id="email" required />
      </div>
      <div class="form-group">
        <label for="message">Lösenord</label>
        <input type="password" class="form-control" id="password" required />
      </div>
      <button type="submit" class="btn btn-dark">
        Skapa Konto
      </button>
    </form>
  </div>
</template>
<script>
export default {
  name: "CreateAccount",
  data() {
    return {
      error: null,
    };
  },
  methods: {
    async createUser(e) {
      let user = {
        username: e.target.username.value,
        email: e.target.email.value,
        password: e.target.password.value,
      };
      let res = await fetch("/api/v1/users", {
        method: "POST",
        headers: {
          "Content-Type": "Application/json",
        },
        body: JSON.stringify(user),
      })
      if(res.ok){
        this.$router.push({ path: "/login" }); 
      }
      else if(res.status === 400){
        res = await res.json()
        this.error = res.message
      }else{
        this.error = "Något gick fel prova igen!"
      }
    },
  },
};
</script>
<style scoped></style>
