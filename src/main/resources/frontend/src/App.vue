<template>
  <div id="app">
    <ForumNav/>
    <router-view />
  </div>
</template>
<script>

import ForumNav from './components/ForumNav.vue';
export default {
  components: { ForumNav },
  async created(){
    this.$store.dispatch("whoami");
  }
 };
</script>
<style>

</style>
