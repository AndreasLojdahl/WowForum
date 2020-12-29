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
 };
</script>
<style>

</style>
