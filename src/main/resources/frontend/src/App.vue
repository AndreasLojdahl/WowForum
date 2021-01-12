<template>
  <div id="app" class="app-bg">
    <ForumNav/>
    <router-view/>
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
.app-bg{
  background-color: rgb(233, 233, 233);
  min-height: 100vh;
}
</style>
