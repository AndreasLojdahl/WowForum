<template>
  <div class="">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="navbar-brand" @click="goToHome()">Wow Forum</div>
      <div class=" navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
          <div v-if="user? user.roles.includes('ADMIN'): false" class="nav-item nav-link"  @click="goToAdmin()">
          Admin sida
        </div>
          <div v-if="!user" class="nav-item nav-link" @click="goToLogIn()">
            Logga in
          </div>
          <div v-if="user" class="nav-item nav-link" @click="logOut()">
            Logga ut {{ user.username }}
          </div>
          <!-- <a class="nav-item nav-link disabled" href="#">Disabled</a> -->
        </div>
      </div>
    </nav>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class=" navbar-collapse d-flex justify-content-center row">
        <div
          v-for="forum in forums"
          :key="forum.id"
          :class="forum.name"
          class="nav-item nav-link col text-center forums-font"
          @click="goToForum(forum)"
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
    goToForum(forum) {
      console.log(forum);
      if (this.$router.currentRoute.path !== "/forum/" + forum.forum_id) {
        this.$router.push({ path: `/forum/${forum.forum_id}` });
      }
    },
    goToLogIn() {
      this.$router.push({ path: "/login" });
    },
    goToHome() {
      this.$router.push({ path: "/" });
    },
    goToAdmin() {
      this.$router.push({ path: "/admin" });
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

.nav-link:hover {
  cursor: pointer;
}

.cont:hover {
  cursor: pointer;
  background-color: rgb(47, 48, 58);
  color: white;
}
</style>
