<template>
  <div class="">
      <h1>{{forum.name}}</h1>
      <h1>{{forum.forum_id}}</h1>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Forum',
    props: {
    forumName: String
  },
  components: {
   
  },
  computed: {
    paramName(){
        return this.$route.params.name;
    },
    forum(){
        return this.$store.state.forum
    }
  },
  watch: {
      paramName: function(){
          this.$store.dispatch("fetchForum", this.paramName)
      }
  },
  created(){
    this.$store.dispatch("fetchForum", this.paramName)
  }
}
</script>