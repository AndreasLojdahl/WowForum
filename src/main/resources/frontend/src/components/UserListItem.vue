<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <div class="col">{{ user.username }}</div>
        <div class="col">{{ user.email }}</div>
      </div>
      <div>{{user.roles}}</div>
    </div>
  </div>
</template>
<script>
export default {
  name: "UserListItem",
  props: {
    user: Object,
  },
};
</script>
<style scoped></style>
