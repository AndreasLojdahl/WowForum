<template>
     <div class="container">

    <div class="row ">
      <div
        id="nav"
        v-for="forum in forums"
        :key="forum.id"
        :class="forum.name"
        class="col cont"
        @click="navigate(forum)"
      >
        {{ forum.name }}
      </div>
    </div>
    </div>
</template>
<script>
export default {
    name: 'ForumNav',
  computed: {
    forums() {
      return this.$store.state.forums;
    },
  },
  created() {
    this.$store.dispatch("fetchAllForums");
  },
  methods: {
    navigate(forum) {
      console.log(forum);
      if(this.$router.currentRoute.path !== '/' + forum.name){
        this.$router.push({ path: `/${forum.name}` });
      }
      
    },
  },
};
</script>
<style>


.Warrior {
  background-color: #cebc98;
}
.Mage {
  background-color: #75dfff;
}
.Paladin {
  background-color: #fc98ff;
}
.Rogue {
  background-color: #f5c833;
}
.Druid {
  background-color: #f77f0f;
}
.Priest {
  background-color: #e6e2dc;
}
.Warlock {
  background-color: #a38aaa;
}

.cont{
  height: 5vh;
  color: rgb(63, 65, 64);
  font-size: 1.5em;
  margin-top: 2em;
  
}


</style>
