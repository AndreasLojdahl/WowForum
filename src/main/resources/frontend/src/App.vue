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
/* @font-face{
  font-family: "LifeCraft";
  src: local("LifeCraft"), url('./assets/fonts/LifeCraft_Font.ttf') format("truetype");
} */
.app-bg{
  display: flex;
  flex-direction: column;
  background-color: rgb(228, 228, 228);
  /* background-color: rgb(233, 233, 233); */
  min-height: 100vh;
  /* background: url("./assets/images/backgroundsmoke.png"); */
}



</style>
