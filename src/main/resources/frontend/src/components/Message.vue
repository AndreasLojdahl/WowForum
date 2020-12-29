<template>
  <div>
    <span>{{ message.messageOwner }}</span>
    <h5>{{ message.messageContent }}</h5>
    <span>{{ getDate }}</span>
  </div>
</template>
<script>
export default {
  props: {
    message: Object,
  },
  computed: {
    getDate() {
      return new Date(this.message.timestamp).toLocaleString("sv-SE");
    },
  },
};
</script>
<style scoped></style>
