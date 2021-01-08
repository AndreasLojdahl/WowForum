<template>
  <div class="container">
    <h3 class="mt-5 text-center">Logga in</h3>
    <form @submit.prevent="login" class="mt-5 col-6 mx-auto">
      <div class="form-group ">
        <label for="title">Användarnamn</label>
        <input type="text" class="form-control" id="username" required />
      </div>
      <div class="form-group">
        <label for="message">Lösenord</label>
        <input type="password" class="form-control" id="password" required />
      </div>
      <button type="submit" class="btn btn-dark">
        Logga in
      </button>
    </form>
    <div class="mt-5 mb-3 col-6 mx-auto d-flex justify-content-center">
      <div class="mr-2">Inget konto?</div>
      <button type="submit" class="btn btn-dark" @click="goToCreateAccount()">
        Skapa konto
      </button>
    </div>
  </div>
</template>
<script>

export default {
  name: "Login",
  methods: {
    goToCreateAccount() {
      this.$router.push({ path: "/create-account" });
    },
    login(e) {
      let loginCredentials = {
        username: e.target.username.value,
        password: e.target.password.value,
      };
      this.attemptLogIn(loginCredentials);
    },

    async attemptLogIn(userToLogin) {
      let credentials =
        "username=" +
        encodeURIComponent(userToLogin.username) +
        "&password=" +
        encodeURIComponent(userToLogin.password);
      let response = await fetch("/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
        // mode: "no-cors",
        body: credentials,
      });
      if (response.url.includes("error")) {
        console.log(response, "LOGIN RESPONSE")
        console.log("ERROR: Login failed.");
      } else {
        console.log("SUCCESS: Login succeeded");
        console.log(response, "LOGIN RESPONSE")
        await this.$store.dispatch("whoami");
        this.$router.push({path: "/"});
      }
    },
  },
};
</script>
<style scoped></style>
