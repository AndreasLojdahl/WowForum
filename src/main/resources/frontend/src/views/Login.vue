<template>
  <div class="container">
      <h3 class="mt-5 text-center">Logga in</h3>
    <form @submit.prevent="login" class="mt-5">
      <div class="form-group">
        <label for="title">Användarnamn</label>
        <input type="text" class="form-control" id="title" required />
      </div>
      <div class="form-group">
        <label for="message">Lösenord</label>
        <input type="text" class="form-control" id="message" required />
      </div>
      <button type="submit" class="btn btn-dark">
        Logga in
      </button>
    </form>
    <div class="mt-5 mb-3">Inget konto?</div>
    <button type="submit" class="btn btn-dark" @click="goToCreateAccount()">
        Skapa konto
      </button>
  </div>
</template>
<script>
export default {
  name: "Login",
  methods: {
      goToCreateAccount(){
         this.$router.push({ path: "/create-account" }); 
      }
  }
};
</script>
<style scoped></style>
