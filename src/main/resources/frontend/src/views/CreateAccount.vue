<template>
  <div class="container">
    <h3 class="mt-5 text-center">Skapa Konto</h3>
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
  methods: {
    async createUser(e) {
      let user = {
        username: e.target.username.value,
        email: e.target.email.value,
        password: e.target.password.value,
        //roles: "USER",
      };
      await fetch("/api/v1/users", {
        method: "POST",
        headers: {
          "Content-Type": "Application/json",
        },
        body: JSON.stringify(user),
      })
        .then((response) => {
          if (response.ok) return response.json();
          // else if (response.status == 400) {
          //   this.errorMessage = `${this.user.username} already exists`;
          // }
        })
        .then((data) => {
          if (data) {
            console.log("DATA", data);
            this.$router.push({ path: "/login" });
          }
        })
        .catch((error) => {
          console.error(error);
        });
      console.log(user);
    },
  },
};
</script>
<style scoped></style>
