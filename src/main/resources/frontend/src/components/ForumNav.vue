<template>
  <div class="">
    <nav class="navbar navbar-expand-lg navbar-dark black">
      <div class="navbar-brand" @click="goTo('/')">Wow Forum</div>
      <div class=" navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
          <div v-if="user? user.roles.includes('ADMIN'): false" class="nav-item nav-link"  @click="goTo('/admin')">
          Admin sida
        </div>
          <div v-if="!user" class="nav-item nav-link" @click="goTo('/login')">
            Logga in
          </div>
          <div v-if="user" class="nav-item nav-link" @click="logOut()">
            Logga ut {{ user.username }}
          </div>
          <!-- <a class="nav-item nav-link disabled" href="#">Disabled</a> -->
        </div>
      </div>
    </nav>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark no-pad">
      <div class=" navbar-collapse d-flex justify-content-center row no-pad-bm">
        <div
          v-for="forum in forums"
          :key="forum.id"
          :class="forum.name"
          class="nav-item nav-link col-xs-3 col-sm-3 col-md-2 col-lg-2 col-xl-1 text-center"
          @click="goToForum(forum.forum_id)"
        >
          {{ forum.name }}
        </div>
      </div>
    </nav>
    <!-- <div class="wow-header bg-dark text-light font-weight-bold row">
      <div class="col" @click="goToHome()">Wow Forum</div>
      
        <div v-if="user" class="col text-center log-in">
          {{ user.username }}
        </div>

        <div v-if="user? user.roles.includes('ADMIN'): false" @click="goToAdmin()">
          Admin sida
        </div>

        <div v-if="!user" class="col-4 text-center log-in" @click="goToLogIn()">
          Logga in
        </div>
        <div v-if="user" class="col-4 text-center log-in" @click="logOut()">
          Logga ut
        </div>
     
    </div>
    <div class="row ">
      <div
        id="nav"
        v-for="forum in forums"
        :key="forum.id"
        :class="forum.name"
        class="col cont"
        @click="goToForum(forum)"
      >
        {{ forum.name }}
      </div>
    </div> -->
  </div>
</template>
<script>
export default {
  name: "ForumNav",
  computed: {
    forums() {
      return this.$store.state.forums;
    },
    user() {
      return this.$store.state.loggedInUser;
    },
  },
  created() {
    this.$store.dispatch("fetchAllForums");
  },
  methods: {
    goTo(route){
      if(this.$route.path != route){
        this.$router.push({path: route})
      }
    },
    goToForum(id) {
     this.goTo(`/forum/${id}`)
    },
    async logOut() {
      fetch("/auth/logout", {
        method: "GET",
      })
        .then((response) => {
          if (response.ok) {
            this.$store.commit("setloggedInUser", null);
            if (this.$route.path === "/") return;
            this.$router.push("/");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
<style>
.Warrior {
  background-color:#C69B6D;
}
.Mage {
  background-color:#3FC7EB;
}
.Paladin {
  background-color:#F48CBA;
}
.Rogue {
  background-color:#FFF468;
}
.Druid {
  background-color: #FF7C0A;
}
.Priest {
  background-color:#FFFFFF;
}
.Warlock {
  background-color:#8788EE;
}
.Hunter {
  background-color:#AAD372;
}
.Shaman {
  background-color:#0070DD;
}
.DemonHunter {
  background-color:#A330C9;
}
.DeathKnight {
  background-color:#C41E3A;
}
.Monk {
  background-color:#00FF98;
}

.nav-link:hover {
  cursor: pointer;
}

.black{
  background-color: black;
}

.navbar-brand{
  font-family: "LifeCraft";
}
.no-pad{
  padding-bottom: 0 !important;
  padding-top: 0 !important;
  /* padding: 0 !important; */
}

.cont:hover {
  cursor: pointer;
  background-color: rgb(137, 139, 163);
  color: white;
}
</style>
