<template>
  <div class="container">
      <h3 class="mt-5 text-center">Skapa Konto</h3>
    <form @submit.prevent="createThread" class="mt-5">
      <div class="form-group">
        <label for="title">Användarnamn</label>
        <input type="text" class="form-control" id="title" required />
      </div>
      <div class="form-group">
        <label for="message">Lösenord</label>
        <input type="text" class="form-control" id="message" required />
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
};
</script>
<style scoped></style>