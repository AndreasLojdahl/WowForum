<template>
  <div class="container">
    <form v-if="user" @submit.prevent="createThread" class="mb-4">
      <div class="form-group">
        <label for="username">Användarnamn</label>
        <input
          type="text"
          class="form-control"
          id="username"
          required
          v-model="username"
        />
      </div>
      <button type="submit" class="btn btn-dark">
        Sök användare
      </button>
    </form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      username: null
    };
  },
};
</script>
<style scoped></style>
