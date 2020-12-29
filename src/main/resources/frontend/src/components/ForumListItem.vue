<template>
  <div class="container mt-5 border-bottom border-dark" @click="goTo()">
    <div class="row justify-content-between">
      <h2>{{ threadObj.title }}</h2>
      <h6>{{ getDate }}</h6>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    threadObj: Object,
    forum: String
  },
  computed: {
    getDate(){
        return new Date(this.threadObj.timestamp).toLocaleString("sv-SE")
    }
  },
  methods : {
      goTo(){
          this.$store.commit("setThread", this.threadObj)
          this.$router.push({ path: `${this.forum}/${this.threadObj.thread_id}` });
      }
  }
};
</script>
<style scoped>
</style>
