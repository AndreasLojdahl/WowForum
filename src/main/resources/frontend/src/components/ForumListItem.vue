<template>
  <div class="container">
    <div class="row">
      <h2>{{ threadObj.title }}</h2>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    threadObj: Object,
  },
};
</script>
<style scoped></style>
