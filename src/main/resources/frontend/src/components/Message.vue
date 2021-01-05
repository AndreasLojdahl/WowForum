<template>
  <div class="col mt-5">
    <div v-if="message.messageOwner" class="msg-font mb-2">{{ message.messageOwner.username }}:</div>
    <h5>{{ message.messageContent }}</h5>
    <div class="msg-font">{{ getDate }}</div>
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
<style scoped>
.msg-font{
  font-size: 0.8rem;
  font-style: oblique;
}
</style>
